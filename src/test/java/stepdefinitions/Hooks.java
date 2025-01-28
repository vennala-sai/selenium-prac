package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import pages.AllOrdersPage;
import pages.LoginPage;
import utilities.DriverUtilities;

public class Hooks {
    //Setting up the driver
    static DriverUtilities driverUtilities;
    static WebDriver driver;
    //Page initialization - instead of creating the same object in multiple test scripts or multiple test methods - u can create them here, go to the required test class and do smthn like private LoginPage page = Runner.loginPage;
    static LoginPage loginPage;
    static AllOrdersPage allOrdersPage;
    @Before
    //BeforeAll is needed here from cucumber library
    public void init(){ // we only need static if we use BeforeAll or Afterall annotations- we are changing it to before and after becuz we want these to be initialized for every feature run, to promote independence
        driverUtilities = utilities.DriverUtilities.getInstance();
        driver = driverUtilities.getWebDriver();

        driver.manage().window().maximize();

        allOrdersPage = new AllOrdersPage(driver);
        loginPage = new LoginPage(driver);


    }
    @After
    public void tearDown(){
        DriverUtilities.resetDriver();
//        driver.quit(); // to close all windows
//        driver.close(); // to only close 1 window
    }
}
