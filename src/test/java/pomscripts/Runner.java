package pomscripts;

import data.DataFile;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.AllOrdersPage;
import pages.LoginPage;
import utilities.DriverUtilities;

@RunWith(Suite.class)
@Suite.SuiteClasses({
       LoginTest.class,
       DeleteOrderTest.class,
        LogoutTest.class
})

public class Runner {
    //Setting up the driver
    static DriverUtilities driverUtilities;
    static WebDriver driver;
    //Page initialization - instead of creating the same object in multiple test scripts or multiple test methods - u can create them here, go to the required test class and do smthn like private LoginPage page = Runner.loginPage;
    static LoginPage loginPage;
    static AllOrdersPage allOrdersPage;
    @BeforeClass
    //BeforeClass because it will be created once before all tests are ran - since class we need it as static
    public static void init(){
        driverUtilities = utilities.DriverUtilities.getInstance();
        driver = driverUtilities.getWebDriver();

        driver.manage().window().maximize();

        allOrdersPage = new AllOrdersPage(driver);
        loginPage = new LoginPage(driver);


    }
    @AfterClass
    public static void tearDown(){
        driver.quit(); // to close all windows
//        driver.close(); // to only close 1 window
    }
}
