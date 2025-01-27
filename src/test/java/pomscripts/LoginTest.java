package pomscripts;

import data.DataFile;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import utilities.DriverUtilities;

public class LoginTest {
    private WebDriver driver = Runner.driver;
    @Test //1 method is 1 test case
    public void loginTest(){
        LoginPage loginPage = new LoginPage(driver);
        //Navigation
        driver.navigate().to(DataFile.loginURL);

        //send username and password (instead of locating them here, locate the web elements in the login page)
        loginPage.enterUsername(DataFile.userName);
//      OLD WAY
//        WebElement usernameField = loginPage.usernameField();
//        usernameField.sendKeys("Tester");

        loginPage.enterPassword(DataFile.password);
        loginPage.pressEnterKey();
//        WebElement passwordField = loginPage.passwordField();
//        passwordField.sendKeys("test" + Keys.ENTER);

        //we can assertEquals here, that title of the new logged in page "WebOrders" matches with WebOrders - to show login is successful

    }

}
