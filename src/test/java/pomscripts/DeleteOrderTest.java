package pomscripts;

import data.DataFile;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.AllOrdersPage;
import pages.LoginPage;
import utilities.DriverUtilities;

import static org.junit.Assert.assertEquals;

public class DeleteOrderTest {
    private WebDriver driver = Runner.driver;
//    private AllOrdersPage allOrdersPage = Runner.allOrdersPage; //NEW WAY
    @Test //1 method is 1 test case
    public void deleteOrderTest(){
        AllOrdersPage allOrdersPage = new AllOrdersPage(driver); //OLD WAY
//        Make sure I have 8 orders in the beginning
        assertEquals(8, allOrdersPage.allCheckboxes().size()); //even these expected values should be stored in data file
//        Delete 1 order row
            // 1. Select a checkbox - lets delete steve john's
        allOrdersPage.steveJohnCheckbox().click();
            // 2. Delete the row by clicking delete order
                //locate deletebutton and click
        allOrdersPage.getDeleteButton().click();
        //There should only be 7 orders now
        assertEquals(7, allOrdersPage.allCheckboxes().size());


    }

}
