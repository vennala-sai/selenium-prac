package scripts;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.DriverUtilities;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InteractingWithElementsDemo {
    static DriverUtilities driverUtilities;
    static WebDriver driver;

    @BeforeClass
    //BeforeClass because it will be created once before all tests are ran - since class we need it as static
    public static void init(){
        driverUtilities = utilities.DriverUtilities.getInstance();
        driver = driverUtilities.getWebDriver();

        driver.manage().window().maximize();
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

        WebElement usernameField = driver.findElement(By.id("ctl00_MainContent_username"));
        usernameField.sendKeys("Tester");

        WebElement passwordField = driver.findElement(By.name("ctl00$MainContent$password"));
        passwordField.sendKeys("test");
        passwordField.sendKeys(Keys.ENTER);

    }
    @Test
    @Ignore // ignores the test
    public void interactingWithTextAndButtons() throws InterruptedException {
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

        WebElement usernameField = driver.findElement(By.id("ctl00_MainContent_username"));
        usernameField.sendKeys("Tester");

        WebElement passwordField = driver.findElement(By.name("ctl00$MainContent$password"));
        passwordField.sendKeys("test");
        Thread.sleep(1000);

        //Clear the value
        passwordField.clear();
        Thread.sleep(1000);

        //Send keys again + Enter (instead of locating Login and doing Login.click())
//        passwordField.sendKeys("test" + Keys.ENTER);
        //or
        passwordField.sendKeys("test");
        passwordField.sendKeys(Keys.ENTER);
        //or
        //doesn't work for smartbear website
//        passwordField.submit(); //submits the form - usually form tags have submit option cuz they are sent to the backend
        Thread.sleep(2000);
    }
    @Test
    @Ignore
    public void verifyingElementsTest() throws InterruptedException {
        //navigate to order page
        WebElement orderPage = driver.findElement(By.xpath("//li//a[text()='Order']"));
        orderPage.click();
        Thread.sleep(2000);

        //isDisplayed (you can get the result and put it in assertions) Demo
        WebElement pageTitle = driver.findElement(By.tagName("h1"));
        System.out.println("Page Title is displayed? " + pageTitle.isDisplayed() + " and it is: " + pageTitle.getText());

        WebElement qtyErrorMessage = driver.findElement(By.id("ctl00_MainContent_fmwOrder_RequiredFieldValidator1"));
        System.out.println("qtyErrorMessage is displayed? " + qtyErrorMessage.isDisplayed() + " and it is: " + qtyErrorMessage.getText());

        //isEnabled (related to button)

        //isSelected Demo
        WebElement visaRadioButton = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0"));
        WebElement masterCardRadioButton = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1"));
        WebElement amexRadioButton = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_2"));

        visaRadioButton.click();
        System.out.println("Visa is selected: " + visaRadioButton.isSelected());
        System.out.println("MasterCard is selected: " + masterCardRadioButton.isSelected());
        System.out.println("Amex is selected: " + amexRadioButton.isSelected());


    }
    @Test
    @Ignore
    public void dropDownMenuTest() throws InterruptedException {
        WebElement orderPage = driver.findElement(By.xpath("//li//a[text()='Order']"));
        orderPage.click();

        //for dropdowns you have to locate select not the option tags
        WebElement productDropDownMenu = driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")); //1. locates dropdown menu (select html)
        Select productDropDownSelect = new Select(productDropDownMenu);//2. makes the located menu a select java object

        //Three selects: Select by Text, Index, Value
        //Select By Text
        productDropDownSelect.selectByVisibleText("FamilyAlbum"); //selects from dropdown menu using select object methods
        Thread.sleep(2000);
        String product = productDropDownSelect.getFirstSelectedOption().getText();
        System.out.println("Current Product: " + product);
        assertEquals("FamilyAlbum", product);

        //Select By Index
        productDropDownSelect.selectByIndex(2); //selects by index - gets 3rd element in the list
        Thread.sleep(2000);
        String screenSaver = productDropDownSelect.getFirstSelectedOption().getText();
        System.out.println("Current Product: " + screenSaver);
        assertEquals("ScreenSaver", screenSaver);

        //Select By Value
        productDropDownSelect.selectByValue("MyMoney"); //selects by value - gets 3rd element in the list
        Thread.sleep(2000);
        String myMoney = productDropDownSelect.getFirstSelectedOption().getText();
        System.out.println("Current Product: " + myMoney);
        assertEquals("MyMoney", myMoney);

        //Get options
        List<WebElement> options = productDropDownSelect.getOptions();
        System.out.println("How many options in the drop down menu? " + options.size());
        assertEquals(3, options.size());

        //Multiple options selectable or not
        if (productDropDownSelect.isMultiple()){
            System.out.println("Dropdown allows multiple selections");
        }
        else {
            System.out.println("Dropdown don't allow multiple selections");
        }

    }

    @Test
    public void retreivingElementsInformationTest() throws InterruptedException {
        //go to order page
        WebElement orderPage = driver.findElement(By.xpath("//li//a[text()='Order']"));
        orderPage.click();

        //Locate dropdown
        WebElement productDropDownMenu = driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")); //1. locates dropdown menu (select html)
        Select productDropDownSelect = new Select(productDropDownMenu);//2. makes the located menu a select java object

        //Get options
        List<WebElement> options = productDropDownSelect.getOptions();

        for (WebElement element: options){
            System.out.println("Tag: " + element.getTagName()); //option
            System.out.println("Text: " + element.getText()); //text
            System.out.println("Attribute Value: " + element.getAttribute("value")); //deprecated - gets what value attribute equals to
            Thread.sleep(500); //sometimes the script runs too fast and website won't load in this case - better to slow it down
        }

    }
    @AfterClass
    public static void tearDown(){
        driver.quit(); // to close all windows
//        driver.close(); // to only close 1 window
    }
}
