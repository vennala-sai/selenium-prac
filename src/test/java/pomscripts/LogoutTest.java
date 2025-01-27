package pomscripts;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.AllOrdersPage;
import pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class LogoutTest {
    private WebDriver driver = Runner.driver;
    //to simply further
    private AllOrdersPage allOrdersPage = Runner.allOrdersPage;
    private LoginPage loginPage = Runner.loginPage;

    @Test
    public void logout(){

        allOrdersPage.getLogout().click();
        //u can assert if username field.isdisplayed() - becuz once u logout, u r back in the home/login page in this case
        assertTrue(loginPage.isUsernameFieldDisplayed());
    }
}
