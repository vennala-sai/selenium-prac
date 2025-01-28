package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;


    @FindBy(id="ctl00_MainContent_username")
    private WebElement usernameField;

    @FindBy(name="ctl00$MainContent$password")
    private WebElement passwordField;

    @FindBy(className = "button")
    private WebElement loginButton;

    @FindBy(className = "error")
    private WebElement invalidValidation;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this); // will automatically initialize and assign these elements to the correct attributes, you don't have to call them multiple times
    }

    public void enterUsername(String username){
        usernameField.sendKeys(username);
    }
    public void enterPassword(String password){
        passwordField.sendKeys(password);
    }
    public void pressEnterKey(){
        passwordField.sendKeys(Keys.ENTER);
    }
    public void clickLoginButton(){
        loginButton.click();
    }
    public boolean isUsernameFieldDisplayed() {
        return usernameField.isDisplayed();
    }

    public boolean isPasswordFieldDisplayed() {
        return passwordField.isDisplayed();
    }
    public String getInvalidCredsError(){
        return invalidValidation.getText();
    }


    //old way
//    public WebElement usernameField(){
//        return this.driver.findElement(By.id("ctl00_MainContent_username"));
//    }
//    public WebElement passwordField(){
//        return this.driver.findElement(By.name("ctl00$MainContent$password"));
//    }
}
