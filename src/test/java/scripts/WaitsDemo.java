package scripts;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverUtilities;

import java.time.Duration;

public class WaitsDemo {
    static DriverUtilities driverUtilities;
    static WebDriver driver;

    @BeforeClass
    //BeforeClass because it will be created once before all tests are ran - since class we need it as static
    public static void init(){
        driverUtilities = utilities.DriverUtilities.getInstance();
        driver = driverUtilities.getWebDriver();

        driver.manage().window().maximize();
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");


    }
    @Test
    @Ignore
    public void implicitWaitTest() throws InterruptedException{
        //Navigation
        driver.navigate().to("https://www.selenium.dev/selenium/web/dynamic.html");

        //Implicit wait usually goes here
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //waits upto 10 seconds, if the redbox or element appears before it will move on unlike thread.sleep which waits the entire time

        //Click
        driver.findElement(By.id("adder")).click();
//        Thread.sleep(5000); //hardcode not recommended cuz the element/redbox might appear earlier

        System.out.println(driver.findElement(By.id("box0")).getAttribute("class"));

    }
    @Test
    public void explicitWaitTest() throws InterruptedException{
        //Navigation
        driver.navigate().to("https://www.selenium.dev/selenium/web/dynamic.html");


        //Click
        driver.findElement(By.id("adder")).click();
//        Thread.sleep(5000); //hardcode not recommended cuz the element/redbox might appear earlier
        //Explicit Wait - waits only for that element instead of making the whole program globally wait like implicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("box0")));
        System.out.println(driver.findElement(By.id("box0")).getAttribute("class"));

    }
    @Test
    public void fluentWaitTest() throws InterruptedException{
        //Navigation
        driver.navigate().to("https://www.selenium.dev/selenium/web/dynamic.html");


        //Click
        driver.findElement(By.id("adder")).click();
//        Thread.sleep(5000); //hardcode not recommended cuz the element/redbox might appear earlier
        //Fluent Wait - like explicit wait, but you can ignore exceptions and add polling time
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchSessionException.class); // u can ignore exception - its optional
        WebElement redBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("box0")));
        System.out.println(redBox.getAttribute("class"));

    }
    @AfterClass
    public static void tearDown(){
        driver.quit(); // to close all windows
//        driver.close(); // to only close 1 window
    }
}
