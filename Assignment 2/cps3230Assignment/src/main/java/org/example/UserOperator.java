package org.example;

import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserOperator {
    //Variables
    private boolean validLogin = false, alertScreen = false, loginScreen = false;
    //Driver instance
    static WebDriver driver;
    public boolean validLoggingIn() {
        if(!validLogin) {
            //Entering credentials
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\balza\\OneDrive\\Desktop\\cps3230Assignment\\webtesting\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://www.marketalertum.com");
            driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
            driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("d98f7243-8f53-4523-b11f-f80fe9fe8230");
            driver.findElement(By.xpath("//input[@type = 'submit']")).submit();
            validLogin = true;

            //Validate that the user should be in alerts page
            List<WebElement> alertBoxes = driver.findElements(By.xpath("//table[@border = '1']"));
            boolean isPresent;
            if (alertBoxes.size() <= 0 ) {
                isPresent = driver.findElements(By.xpath("//*[@class = 'pb-3']/h1")).size() > 0;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'pb-3']/h1")));
            }else{
                isPresent = driver.findElements(By.xpath("//table[@border = '1']")).size() > 0;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@border = '1']")));
            }
            alertScreen = true;
            loginScreen = false;
            return isPresent;

        }else{
            throw new IllegalStateException();
        }
    }

    public boolean isValidLogin() {
        return validLogin && alertScreen;
    }

    public boolean inAlertScreen() {
        if (validLogin) {
            validLogin = true;

            List<WebElement> alertBoxes = driver.findElements(By.xpath("//table[@border = '1']"));
            boolean isPresent;
            if (alertBoxes.size() <= 0 ) {
                isPresent = driver.findElements(By.xpath("//*[@class = 'pb-3']/h1")).size() > 0;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'pb-3']/h1")));
            }else{
                isPresent = driver.findElements(By.xpath("//table[@border = '1']")).size() > 0;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@border = '1']")));
            }

            alertScreen = true;
            loginScreen = false;
            return isPresent;
        } else {
            throw new IllegalStateException();
        }
    }

        public boolean invalidLoggingIn() {
            if (!validLogin) {
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\balza\\OneDrive\\Desktop\\cps3230Assignment\\webtesting\\chromedriver_win32\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.get("https://www.marketalertum.com");
                //Entering incorrect credentials
                driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
                driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("1234");
                driver.findElement(By.xpath("//input[@type = 'submit']")).submit();
                validLogin = false;

                //Validates that the user is back in log in screen therefore, the alerts table is not visible
                boolean isNotPresent = driver.findElements(By.xpath("//*[@class = 'pb-3']/h1")).size() > 0;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@action='/Alerts/LoginForm']")));

                alertScreen = false;
                loginScreen = true;
                return isNotPresent;

            } else {
                throw new IllegalStateException();
            }
        }

        public boolean inLoginScreen() {
            if (!validLogin) {
                validLogin = false;
                //Returning false because the alert table is not visible
                boolean isNotPresent = driver.findElements(By.xpath("//*[@class = 'pb-3']/h1")).size() > 0;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@action='/Alerts/LoginForm']")));
                alertScreen = false;
                loginScreen = true;
                return isNotPresent;
            } else {
                throw new IllegalStateException();
            }
        }

        public boolean isInvalidLogin(){
            return validLogin && loginScreen;
            }
    }

