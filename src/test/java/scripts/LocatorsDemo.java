package scripts;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utilities.DriverUtilities;

//there are 8 locators in Selenium
public class LocatorsDemo {
    static DriverUtilities driverUtilities;
    static WebDriver driver;

    @BeforeClass
    //BeforeClass because it will be created once before all tests are ran - since class we need it as static
    public static void init(){
        driverUtilities = DriverUtilities.getInstance();
        driver = driverUtilities.getWebDriver();
    }

    @Test
    public void testNavigationCommands() throws InterruptedException {
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        Thread.sleep(1000);
    }

    @AfterClass
    public static void tearDown(){
        driver.quit(); // to close all windows
//        driver.close(); // to only close 1 window
    }
}
