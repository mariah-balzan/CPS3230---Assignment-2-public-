package test.cps3230.alerts;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.example.AlertOperator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import test.cps3230.alerts.enums.AlertStateEnums;

import java.util.Random;

public class AlertsLayoutModelTest implements FsmModel {
    //Create an instance of the system under test
    AlertOperator sut = new AlertOperator();
    AlertStateEnums stateEnums = AlertStateEnums.LOGIN;
    //Driver instance
    static WebDriver driver;

    //Variables
    boolean validLogin = false;
    boolean alertCreated = false;
    boolean alertViewed = false;
    boolean validAlertLayout = false;

    /*The model which drives the system not vice versa.
      Here we'll define a number of transitions and the modelJUnit picks one of them randomly. It might also pick the
      reset method and start reprocessing.  */
    /* It does this because we might have a system with branching in the model and once we take one branch,
    it will be impossible for us to go into the other branch */

    @Override
    public AlertStateEnums getState() {
        return stateEnums;
    }

    @Override
    public void reset(boolean b) {
        if(b){
            sut = new AlertOperator();
        }
        validLogin = false;
        alertCreated = false;
        alertViewed = false;
        validAlertLayout = false;
        stateEnums = AlertStateEnums.LOGIN;
    }

    //AUTOMATON 2:
    /*Transitions are called randomly so, we need to create a form of structure called a GUARD
    - checking the states and returns a boolean value
      Test 1: Checks that an admin logs in correctly in order to create 3 alerts in marketalertum,
      assuming that the admin and the user have the same credentials */
    public boolean adminCreatingAlertsGuard(){
        return getState().equals(AlertStateEnums.LOGIN);
    }

    public @Action void adminCreatingAlerts(){
        sut.creatingThreeValidAlert();

        stateEnums = AlertStateEnums.CREATEALERT;
        validLogin = true;
        alertCreated = true;

        //Assert
        Assert.assertEquals(validLogin && alertCreated, sut.areOnlyThreeAlertsCreated());
    }

    /*Test 2: Checks that a user logs in correctly (assuming that they have the same userID as the admin),
    is in the alert screen and is able to view alerts. */
    public boolean userViewingAlertsGuard(){
        return getState().equals(AlertStateEnums.LOGIN);
    }

    public @Action void userViewingAlerts() {
        sut.validViewingAlert();

        stateEnums = AlertStateEnums.VIEWALERT;
        validLogin = true;
        alertViewed = true;

        //Assert
        Assert.assertEquals(validLogin && alertViewed, sut.areAlertsViewedValid());
    }

    //Test 3: Verifies that alerts created contain a valid layout and include required attributes
    public boolean alertLayoutVerificationGuard(){
        return getState().equals(AlertStateEnums.VIEWALERT);
    }

    public @Action void alertLayoutVerification(){
        sut.verifyAlertLayout();

        stateEnums = AlertStateEnums.VALIDATEALERTLAYOUT;
        validLogin = true;
        alertViewed = true;
        validAlertLayout = true;

        //Assert
        Assert.assertEquals(validLogin && alertViewed && validAlertLayout, sut.isAlertLayoutValid());
    }

    //Test runner
    @Test
    public void Runner() {
        //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
        final GreedyTester tester = new GreedyTester(new AlertsLayoutModelTest());
        //Allows for a random path each time the model is run.
        tester.setRandom(new Random());
        //Builds a model of our FSM to ensure that the coverage metrics are correct.
        tester.buildGraph();
        //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener(new StopOnFailureListener());
        //This gives you printed statements of the transitions being performed along with the source and destination states.
        tester.addListener("verbose");
        //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new TransitionPairCoverage());
        //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new StateCoverage());
        //Records the number of @Action methods which have been executed during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage());
        //Generates 500 transitions
        tester.generate(10);
        //Prints the coverage metrics specified above.
        tester.printCoverage();
    }
    //Teardown driver method
    @AfterEach
    public void teardown(){
        driver.quit();
    }

}
