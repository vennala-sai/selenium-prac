package stepdefinitions;

import data.DataFile;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AllOrdersPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stepdefinitions.Hooks.allOrdersPage;
import static stepdefinitions.Hooks.loginPage;

public class F1_LoginTest_StepDef {
    @Given("User launches the application")
    public void user_launches_the_application() {
        //Navigation
        Hooks.driver.navigate().to(DataFile.loginURL);
    }
    @Given("All login fields should be displayed")
    public void all_login_fields_should_be_displayed() {
        assertTrue(loginPage.isUsernameFieldDisplayed());
        assertTrue(loginPage.isPasswordFieldDisplayed());
    }
    @When("User enters valid username and password")
    public void user_enters_valid_username_and_password() {
        loginPage.enterUsername(DataFile.userName);
        loginPage.enterPassword(DataFile.password);
    }
    @When("Click the login button")
    public void click_the_login_button() {
        loginPage.clickLoginButton();
    }
    @Then("User should be able to see the title {string}")
    public void user_should_be_able_to_see_the_title(String title) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(title, allOrdersPage.getPageTitle().getText());
//        assertTrue(allOrdersPage.isPageTitleDisplayed());
    }
    @When("User enter invalid {string} and {string}")
    public void user_enter_invalid_and(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @Then("User should be able to see the alert {string}")
    public void user_should_be_able_to_see_the_alert(String alertMessage) {
        assertEquals(alertMessage, loginPage.getInvalidCredsError());
    }
}
