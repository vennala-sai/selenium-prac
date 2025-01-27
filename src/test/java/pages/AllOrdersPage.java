package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AllOrdersPage {
    WebDriver driver;
    public AllOrdersPage(WebDriver driver) {
        this.driver = driver;
    }
    public List<WebElement> allCheckboxes(){
        return driver.findElements(By.xpath("//input[@type='checkbox']"));
    }
    public WebElement steveJohnCheckbox(){
        return driver.findElement(By.id("ctl00_MainContent_orderGrid_ctl04_OrderSelector"));
    }
    public WebElement getDeleteButton(){
        return driver.findElement(By.id("ctl00_MainContent_btnDelete"));
    }
    public WebElement getLogout(){
        return driver.findElement(By.xpath("//*[@id='ctl00_logout']"));
    }

}
