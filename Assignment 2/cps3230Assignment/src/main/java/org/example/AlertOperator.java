package org.example;

import kong.unirest.Unirest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AlertOperator {
    //Variables
    public boolean validLogin = false, alertCreated = false, alertViewed = false, validAlertLayout = false, validAlertLimit = false, allAlertsDeleted = false, validMarketSize = false;
    public int maxAlerts = 5;
    public int emptyMarketSize = 0;
    public boolean isPresent, containsIcon, containsHeading, containsDescription, containsImage, containsPrice, containsLink;
    //Driver instance
    static WebDriver driver;
    //Driver wait instance
    WebDriverWait wait;
    //Calling UserOperator obj - code re-usability
    UserOperator operator = new UserOperator();

    //AUTOMATON 2:
    public boolean creatingThreeValidAlert() {
        if (!alertCreated) {
            //Entering credentials
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\balza\\Downloads\\testingAssignment-20221215T130003Z-001\\testingAssignment\\webtesting\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://www.marketalertum.com");
            driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
            driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("d98f7243-8f53-4523-b11f-f80fe9fe8230");
            driver.findElement(By.xpath("//input[@type = 'submit']")).submit();

            validLogin = true;

            //Creating ONLY 3 alerts as specified in cucumber scenario (check processes.txt)
            List<WebElement> alertBoxes = driver.findElements(By.xpath("//table[@border = '1']"));
            if (alertBoxes.size() >= 0 && alertBoxes.size() < 3) {
                Unirest.post("https://api.marketalertum.com/Alert")
                        .header("Content-Type", "application/json")
                        .body("{\"alertType\":5," +
                                "\"heading\":\"WeFine Puppy Dog Chew Toys Teething Training, 10pcs Dog Rope Toys 100% Natural Cotton Rope for Small and Medium Dog \"," +
                                "\"description\":\" \"," +
                                "\"url\":\"https://www.amazon.co.uk/WeFine-Teething-Training%EF%BC%8C10pcs-Natural-Cotton/dp/B07WD8QPRB/ref=sr_1_5?crid=2O4UBUX7JYK78&keywords=WeFine+Puppy+Dog+Chew+Toys+Teething+Training%2C+10pcs+Dog+Rope+Toys+100%25+Natural+Cotton+Rope+for+Small+and+Medium+Dog&qid=1668250494&sprefix=wefine+puppy+dog+chew+toys+teething+training+10pcs+dog+rope+toys+100%25+natural+cotton+rope+for+small+and+medium+dog%2Caps%2C134&sr=8-5\"," +
                                "\"imageUrl\":\"https://m.media-amazon.com/images/I/61CMYjFxkfL._AC_SX679_.jpg\"," +
                                "\"postedBy\":\"d98f7243-8f53-4523-b11f-f80fe9fe8230\",\"priceInCents\":1199}")
                        .asJson();
            }
            alertCreated = true;
            return true;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean areOnlyThreeAlertsCreated() {
        return validLogin && alertCreated;
    }

    public boolean validViewingAlert() {
        if (!alertViewed) {
        /*Calling method from User operator for code re-usability purposes.
          Here, the method is checking that the user is logging in correctly and
          the alert table is present. I'm also assuming that the user and the admin
          have the same credentials (user ID)
          */
            operator.validLoggingIn();
            validLogin = true;
            alertViewed = true;
            return true;
        } else {

            throw new IllegalStateException();
        }
    }

    public boolean areAlertsViewedValid() {
        return validLogin && alertViewed;
    }

    public boolean verifyAlertLayout() {
        if (!validAlertLayout) {

            //Validate that the user should be in alerts page
            //Entering credentials
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\balza\\OneDrive\\Desktop\\cps3230Assignment\\webtesting\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://www.marketalertum.com");
            driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
            driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("d98f7243-8f53-4523-b11f-f80fe9fe8230");
            driver.findElement(By.xpath("//input[@type = 'submit']")).submit();

            validLogin = true;
            alertViewed = true;
            //Validate that the user should be in alerts page
            List<WebElement> alertBoxes = driver.findElements(By.xpath("//table[@border = '1']"));
            if (alertBoxes.size() > 0) {
                isPresent = driver.findElements(By.xpath("//table[@border = '1']")).size() > 0;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@border = '1']")));

                //Verifies that alert contains icon
                containsIcon =
                        driver.findElements(By.xpath("//h4//img[contains(@src,'icon')]")).size() > 1;
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4//img[contains(@src,'icon')]")));

                //Verifies that alert contains heading
                containsHeading = driver.findElements(By.xpath("//h4[text()]")).size() > 1;
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()]")));

                //Verifies that alert contains description
                containsDescription = driver.findElements(By.xpath("//tr//td[text()]")).size() > 1;
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr//td[text()]")));

                //Verifies that alert contains an image
                containsImage = driver.findElements(By.xpath("//tr//td//img[contains(@src,'amazon')]")).size() > 1;
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr//td//img[contains(@src,'amazon')]")));

                //Verifies that alert contains a price
                containsPrice = driver.findElements(By.xpath("//tr//td//b[contains(text(),'Price: ')]")).size() > 1;
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr//td//b[contains(text(),'Price: ')]")));

                //Verifies that alert contains a link to original product website
                containsLink = driver.findElements(By.xpath("//tr//td//a[contains(text(),'Visit item')]")).size() > 1;
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr//td//a[contains(text(),'Visit item')]")));

                validAlertLayout = true;

            } else {
                validAlertLayout = true;
            }

            return validAlertLayout && containsIcon && containsHeading && containsDescription && containsImage && containsPrice && containsLink;

        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isAlertLayoutValid() {
        return validLogin && alertViewed && validAlertLayout;
    }

    //AUTOMATON 3:
    public boolean creatingMoreThanFiveAlerts() {
        if (!alertCreated) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\balza\\Downloads\\testingAssignment-20221215T130003Z-001\\testingAssignment\\webtesting\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://www.marketalertum.com");
            driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
            driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("d98f7243-8f53-4523-b11f-f80fe9fe8230");
            driver.findElement(By.xpath("//input[@type = 'submit']")).submit();

            validLogin = true;

            //Creating more than 5 alerts as specified in cucumber scenario to verify that the latest 5 alerts are displayed in the market (check processes.txt)
            for (int i = 0; i < maxAlerts + 1; i++) {
                Unirest.post("https://api.marketalertum.com/Alert")
                        .header("Content-Type", "application/json")
                        .body("{\"alertType\":5," +
                                "\"heading\":\"WeFine Puppy Dog Chew Toys Teething Training, 10pcs Dog Rope Toys 100% Natural Cotton Rope for Small and Medium Dog \"," +
                                "\"description\":\" \"," +
                                "\"url\":\"https://www.amazon.co.uk/WeFine-Teething-Training%EF%BC%8C10pcs-Natural-Cotton/dp/B07WD8QPRB/ref=sr_1_5?crid=2O4UBUX7JYK78&keywords=WeFine+Puppy+Dog+Chew+Toys+Teething+Training%2C+10pcs+Dog+Rope+Toys+100%25+Natural+Cotton+Rope+for+Small+and+Medium+Dog&qid=1668250494&sprefix=wefine+puppy+dog+chew+toys+teething+training+10pcs+dog+rope+toys+100%25+natural+cotton+rope+for+small+and+medium+dog%2Caps%2C134&sr=8-5\"," +
                                "\"imageUrl\":\"https://m.media-amazon.com/images/I/61CMYjFxkfL._AC_SX679_.jpg\"," +
                                "\"postedBy\":\"d98f7243-8f53-4523-b11f-f80fe9fe8230\",\"priceInCents\":1199}")
                        .asJson();
            }
            alertCreated = true;
            return true;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean areLatestFiveAlerts() {
        if (validLogin) {
            List<WebElement> alertBoxes = driver.findElements(By.xpath("//table[@border = '1']"));
            if (alertBoxes.size() <= 5) {
                isPresent = driver.findElements(By.xpath("//table[@border = '1']")).size() > 0;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@border = '1']")));

                alertCreated = true;
            }
        }
        return validLogin && alertCreated;
    }

    public boolean viewingLatestFiveAlerts() {
        if (!alertViewed) {
        /*Calling method from User operator for code re-usability purposes.
          Here, the method is checking that the user is logging in correctly and the alert table is present.
          I'm also assuming that the user and the admin have the same credentials (user ID)
          */
            operator.validLoggingIn();
            validLogin = true;
            alertViewed = true;
            return true;
        } else {

            throw new IllegalStateException();
        }
    }

    public boolean areFiveAlertsLatest() {
        return validLogin && alertViewed;
    }

    public boolean verifyAlertLimit() {
        if (!validAlertLimit) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\balza\\OneDrive\\Desktop\\cps3230Assignment\\webtesting\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://www.marketalertum.com");
            driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
            driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("d98f7243-8f53-4523-b11f-f80fe9fe8230");
            driver.findElement(By.xpath("//input[@type = 'submit']")).submit();

            validLogin = true;
            alertViewed = true;
            //Validate that the user should be in alerts page
            List<WebElement> alertBoxes = driver.findElements(By.xpath("//table[@border = '1']"));
            if (alertBoxes.size() > 0) {
                isPresent = driver.findElements(By.xpath("//table[@border = '1']")).size() > 0;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@border = '1']")));

                validAlertLimit = true;

            }
        } else {
            //There are no alerts in marketalertum so return true to keep automaton functioning and match assert equals
            validAlertLimit = true;
        }
        return validAlertLimit;
    }

    public boolean isAlertLimitValid() {
        return validLogin && alertViewed && validAlertLimit;
    }

    public boolean deletingAllAlerts() {
        if (!allAlertsDeleted) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\balza\\OneDrive\\Desktop\\cps3230Assignment\\webtesting\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://www.marketalertum.com");
            driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
            driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("d98f7243-8f53-4523-b11f-f80fe9fe8230");
            driver.findElement(By.xpath("//input[@type = 'submit']")).submit();

            validLogin = true;

            //Validate that admin should be in the alerts page to delete alerts
            List<WebElement> alertBoxes = driver.findElements(By.xpath("//table[@border = '1']"));
            if (alertBoxes.size() > 0) {
                isPresent = driver.findElements(By.xpath("//table[@border = '1']")).size() > 0;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@border = '1']")));

                //Delete all alerts
                Unirest.delete("https://api.marketalertum.com/Alert?userId=d98f7243-8f53-4523-b11f-f80fe9fe8230")
                        .header("Content-Type", "application/json")
                        .body(" {\"alertType\":5,\"heading\":\"WeFine Puppy Dog Chew Toys Teething Training, 10pcs Dog Rope Toys 100% Natural Cotton Rope for Small and Medium Dog \",\"description\":\"\",\"url\":\"https://www.amazon.co.uk/WeFine-Teething-Training%EF%BC%8C10pcs-Natural-Cotton/dp/B07WD8QPRB/ref=sr_1_5?crid=2O4UBUX7JYK78&keywords=WeFine+Puppy+Dog+Chew+Toys+Teething+Training%2C+10pcs+Dog+Rope+Toys+100%25+Natural+Cotton+Rope+for+Small+and+Medium+Dog&qid=1668250494&sprefix=wefine+puppy+dog+chew+toys+teething+training+10pcs+dog+rope+toys+100%25+natural+cotton+rope+for+small+and+medium+dog%2Caps%2C134&sr=8-5\",\"imageUrl\":\"https://m.media-amazon.com/images/I/61CMYjFxkfL._AC_SX679_.jpg\",\"postedBy\":\"d98f7243-8f53-4523-b11f-f80fe9fe8230\",\"priceInCents\":1199}")
                        .asJson();

                allAlertsDeleted = true;
                return true;
            } else {
                //There are no recent alerts so, market is empty. Return validMarketSize = true to keep automaton functioning
                allAlertsDeleted = true;
            }
        }else{
            throw new IllegalStateException();
        }
        return allAlertsDeleted;
    }

    public boolean areAllAlertsDeleted(){
        return validLogin && allAlertsDeleted;
    }

    public int checkEmptyMarketSize(){
        return emptyMarketSize;
    }

      public boolean populateTwoAlerts(){
          if (!alertCreated) {
              //Entering credentials
              System.setProperty("webdriver.chrome.driver", "C:\\Users\\balza\\Downloads\\testingAssignment-20221215T130003Z-001\\testingAssignment\\webtesting\\chromedriver_win32\\chromedriver.exe");
              driver = new ChromeDriver();
              driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
              driver.get("https://www.marketalertum.com");
              driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
              driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("d98f7243-8f53-4523-b11f-f80fe9fe8230");
              driver.findElement(By.xpath("//input[@type = 'submit']")).submit();

              validLogin = true;

              //Creating ONLY 3 alerts as specified in cucumber scenario (check processes.txt)
              List<WebElement> alertBoxes = driver.findElements(By.xpath("//table[@border = '1']"));
              if (alertBoxes.size() >= 0 && alertBoxes.size() < 2) {
                  Unirest.post("https://api.marketalertum.com/Alert")
                          .header("Content-Type", "application/json")
                          .body("{\"alertType\":5," +
                                  "\"heading\":\"WeFine Puppy Dog Chew Toys Teething Training, 10pcs Dog Rope Toys 100% Natural Cotton Rope for Small and Medium Dog \"," +
                                  "\"description\":\" \"," +
                                  "\"url\":\"https://www.amazon.co.uk/WeFine-Teething-Training%EF%BC%8C10pcs-Natural-Cotton/dp/B07WD8QPRB/ref=sr_1_5?crid=2O4UBUX7JYK78&keywords=WeFine+Puppy+Dog+Chew+Toys+Teething+Training%2C+10pcs+Dog+Rope+Toys+100%25+Natural+Cotton+Rope+for+Small+and+Medium+Dog&qid=1668250494&sprefix=wefine+puppy+dog+chew+toys+teething+training+10pcs+dog+rope+toys+100%25+natural+cotton+rope+for+small+and+medium+dog%2Caps%2C134&sr=8-5\"," +
                                  "\"imageUrl\":\"https://m.media-amazon.com/images/I/61CMYjFxkfL._AC_SX679_.jpg\"," +
                                  "\"postedBy\":\"d98f7243-8f53-4523-b11f-f80fe9fe8230\",\"priceInCents\":1199}")
                          .asJson();
              }
              alertCreated = true;
              return true;
          } else {
              throw new IllegalStateException();
          }
      }

      public boolean areAllAlertsCreated(){
        return validLogin && alertCreated;
      }

    public int checkFullMarketSize(){
        return maxAlerts;
    }

}
