package test.cps3230.user;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.example.UserOperator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import test.cps3230.user.enums.UserStateEnums;

import java.util.Random;

public class UserModelTest implements FsmModel {
    //Create an instance of the system under test
    UserOperator sut = new UserOperator();
    //Start state
    UserStateEnums stateEnums = UserStateEnums.LOGGEDOUT;
    //Driver instance
    static WebDriver driver;

    //Variables
    boolean validLogin = false;
    boolean alertScreen = false;
    boolean loginScreen = true;

    /*The model which drives the system not vice versa.
      Here we'll define a number of transitions and the modelJUnit
      picks one of them randomly. It might also pick the reset method
      and start reprocessing.  */
    /* It does this because we might have a system with branching in the
    model and once we take one branch, it will be impossible for us to go
    into the other branch */

    @Override
    public UserStateEnums getState() {
        return stateEnums;
    }

    @Override
    public void reset(boolean b) {
        if(b){
            sut = new UserOperator();
        }
        validLogin = false;
        alertScreen = false;
        loginScreen = false;
        stateEnums = UserStateEnums.LOGGEDOUT;
    }

    //Teardown driver method
    @AfterEach
    public void teardown(){
        driver.quit();
    }

    /*Transitions are called randomly so, we need to create a form of structure called
    a GUARD - checking the states and returns a boolean value */
    //Test 1: Test that when user is viewing alerts, they are loggedIn
    public boolean logInGuard(){
        return getState().equals(UserStateEnums.LOGGEDOUT);
    }
    //The transition for this guard if returns true
    public @Action void logIn(){
        sut.validLoggingIn();

        stateEnums = UserStateEnums.LOGGEDIN;
        validLogin = true;
        alertScreen = true;

        //Assert
        Assert.assertEquals(validLogin && alertScreen, sut.isValidLogin());
        Assert.assertEquals(validLogin, sut.inAlertScreen());
    }

    //Test 2: Invalid Login
    public boolean invalidLoginGuard(){
        return getState().equals(UserStateEnums.LOGGEDOUT);
    }

    //The transition for this guard if returns false
    public @Action void invalidLogin(){
        sut.invalidLoggingIn();

        stateEnums = UserStateEnums.LOGGEDOUT;
        validLogin = false;
        alertScreen = false;
        loginScreen = true;

        Assert.assertEquals(validLogin && alertScreen, sut.inLoginScreen());
        Assert.assertFalse(sut.isValidLogin());
    }

    //Test runner
    @Test
    public void Runner() {
        /*Creates a test generator that can generate random walks. A greedy random walk
        gives preference to transitions that have never been taken before. Once all
        transitions out of a state have been taken, it behaves the same as a random walk. */
        final GreedyTester tester = new GreedyTester(new UserModelTest());
        //Allows for a random path each time the model is run.
        tester.setRandom(new Random());
        //Builds a model of our FSM to ensure that the coverage metrics are correct.
        tester.buildGraph();
        /*This listener forces the test class to stop running as soon as a failure is
         encountered in the model. */
        tester.addListener(new StopOnFailureListener());
        /*This gives you printed statements of the transitions being performed along
          with the source and destination states. */
        tester.addListener("verbose");
        /*Records the transition pair coverage i.e. the number of paired transitions
         traversed during the execution of the test. */
        tester.addCoverageMetric(new TransitionPairCoverage());
        /*Records the state coverage i.e. the number of states which have been
         visited during the execution of the test. */
        tester.addCoverageMetric(new StateCoverage());
        /*Records the number of @Action methods which have been executed during
         the execution of the test. */
        tester.addCoverageMetric(new ActionCoverage());
        //Generates 500 transitions
        tester.generate(10);
        //Prints the coverage metrics specified above.
        tester.printCoverage();
    }
}
