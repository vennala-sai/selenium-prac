package scripts;

import org.junit.*;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.DriverUtilities;

import java.io.File;
import java.io.IOException;


import static org.junit.Assert.assertEquals;

public class BasicWebDriverDemo {
    static DriverUtilities driverUtilities;
    static WebDriver driver;

    @BeforeClass //BeforeClass because it will be created once before all tests are ran - since class we need it as static
    public static void init(){
        driverUtilities = DriverUtilities.getInstance();
        driver = driverUtilities.getWebDriver();
    }

    @Test
    public void testNavigationCommands() throws InterruptedException {
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
//        Thread.sleep(1000);
        String window1 = driver.getWindowHandle();
        System.out.println(window1);

        //switch to a new window
//        driver.switchTo().newWindow(WindowType.WINDOW); //--
//        driver.get("http://www.fdmgroup.com"); //--

        //every window has a name or handle, to get that name
//        String windowName = driver.getWindowHandle();
//        System.out.println(windowName);
        //how to switch between windows - interview ques
//        driver.switchTo().window(windowName);
        //driver.switchTo().window(window1); //--

        //goes to this url from "fdmgroup.com" - home page
//        driver.get("https://www.fdmgroup.com/about-us/diversity-equity-inclusion/");

        //to go back or navigate to home page
//        driver.navigate().back();
//        Thread.sleep(2000);
        //more navigate commands
//        driver.navigate().forward();
//        Thread.sleep(2000);
        driver.navigate().refresh();

    }

    @Test
    public void testBrowserCommands() throws InterruptedException {
        driver.manage().window().maximize(); //maximize windows
        String title  = driver.getTitle();
        System.out.println(title);
        Thread.sleep(2000);
        assertEquals("Web Orders Login", title);

        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        assertEquals("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx", currentUrl);

    }

    @Test //getting browser information like browser name - used for Test Reporting
    public void testCapabilityCommands() throws InterruptedException{
        String browserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
        System.out.println(browserName);

        String browserVersion = ((RemoteWebDriver) driver).getCapabilities().getBrowserVersion();
        System.out.println(browserVersion);

    }
    @Test //taking screenshots
    public void testTakingScreenshot() throws InterruptedException, IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); //OutputType.bytes if u wanna put it in database
        FileHandler.copy(srcFile, new File("src/test/resources/images/screenshot1.png"));
    }
    @AfterClass
    public static void tearDown(){
        driver.quit(); // to close all windows
//        driver.close(); // to only close 1 window
    }
}
