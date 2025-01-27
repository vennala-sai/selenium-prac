package scripts;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.DriverUtilities;

import java.util.List;

public class LocatingElementsDemo {
    static DriverUtilities driverUtilities;
    static WebDriver driver;

    @BeforeClass
    //BeforeClass because it will be created once before all tests are ran - since class we need it as static
    public static void init(){
        driverUtilities = utilities.DriverUtilities.getInstance();
        driver = driverUtilities.getWebDriver();

        driver.manage().window().maximize();
    }
    @Test
    public void locatingElementTest() throws InterruptedException {
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        Thread.sleep(1000);
        //1. Locating by ID - best case scenario as id is always unique
        WebElement usernameField = driver.findElement(By.id("ctl00_MainContent_username"));
        usernameField.sendKeys("Tester");
//        Thread.sleep(2000);

        //2. Locating by Name - multiple elements can have same name but rare
        WebElement passwordField = driver.findElement(By.name("ctl00$MainContent$password"));
        passwordField.sendKeys("test");
//        Thread.sleep(2000);

        //3. Locating by Class
        //Class is used for multiple elements, not unique - use it when there are a group of elements you want to cpature with findelements
        //Using class here to retrieve Login Button because it's the only element with class = "button" for demo purposes
        WebElement submitButton = driver.findElement(By.className("button"));
        submitButton.click();
//        Thread.sleep(2000);

        //4. Locate by Tag - like Class, multiple tags of same type can exist - check first, be careful of when you use it. If you want multiple elements with the same tag, this is a good idea
        WebElement pageTitle = driver.findElement(By.tagName("h1"));
        System.out.println("Page Title: " + pageTitle.getText()); // prints the text
        //Alternative with relative xPath: //h1[text() = 'Web Orders']

        //5. Locate by CSS Selector (use DOM heirarchy ul > li if u want to locate all li tags. if there are multiple ul's then use an id for the ul if it exists - something like "ul#ctl00_menu > li" (# represents ID, . represents class))
        List<WebElement> sideMenu = driver.findElements(By.cssSelector("ul#ctl00_menu > li"));
        System.out.println("Number of side Menu: " + sideMenu.size());

        //6. Locating by Link Text - body between <a> tags but not the link inside href of a tag
        WebElement viewAllProductsLink = driver.findElement(By.linkText("View all products"));
        viewAllProductsLink.click();
        Thread.sleep(1000);

        //7. Locating by Partial Link Text - partial text from <a> tags. even if the partial text is in other tags, it will only consider the text from <a> tags - make sure only 1 <a> tag has the partial text
        //Many ppl prefer this as u just need enough keywords from the text to retrieve the element instead of whole text
        WebElement viewAllOrdersLink = driver.findElement(By.partialLinkText("all orders"));
        viewAllOrdersLink.click();
        Thread.sleep(2000);

        //8. Locating by XPath (most asked in interviews and one of the more important ones) - dynamic - similar to cssSelector
        //You can use xPath to locate elements even if they don't have an id/name/className or no unique value
        //2 Types:
        //8A. Absolute xPath (starts with /, starts from html tag or root element of the page) - not recommended as it is too long and sometimes if a dev updates a site, the hierarchy might change which will break the script - fragile. Solution is using Relative xPath
        //commenting code to test relative paths
//        WebElement logoutButton = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td[2]/div[1]/span/a"));
//        logoutButton.click();
//        Thread.sleep(2000);


        //8B. Relative xPath (recommended when there is no unique identifier)
//        MOST RECOMMENDED - requires less refactoring even if dev changes the code - and the slowest so use others if possible
        // (starts with //, you can start from anywhere doesn't have to be from html - for ex: //form, finds the first form element) - You can skip basically
        //You can use it with any attribute name inside the tags
        //* is a wildcard, so u can do something like //*[text() = 'Logout'] or anything
        //If the sentence is too long, you can use subtext like //*[contains(text(), 'out')]
        // -- options --
        //Path: //form//tbody//span//a
        WebElement logoutButton1 = driver.findElement(By.xpath("//form//tbody//span//a"));
        logoutButton1.click();
        Thread.sleep(2000);

        //Path: //span//a
        WebElement logoutButton2 = driver.findElement(By.xpath("//span//a"));
        logoutButton2.click();
        Thread.sleep(2000);

//
       //Path: //a[@id='ctl00_logout'] - single quotes cuz you have to use it as a string
        WebElement logoutButton3 = driver.findElement(By.xpath("//a[@id='ctl00_logout']"));
        logoutButton3.click();
        Thread.sleep(2000);

        //Path: //a[text()='Logout']
        WebElement logoutButton4 = driver.findElement(By.xpath("//a[text()='Logout']"));
        logoutButton4.click();
        Thread.sleep(2000);

        //Path: //*[contains(text(), 'out')]
        WebElement logoutButton5 = driver.findElement(By.xpath("//a[text()='Logout']"));
        logoutButton4.click();
        Thread.sleep(2000);



    }
    @AfterClass
    public static void tearDown(){
        driver.quit(); // to close all windows
//        driver.close(); // to only close 1 window
    }
}
