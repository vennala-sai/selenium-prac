package scripts;

import org.openqa.selenium.WebDriver;
import utilities.DriverUtilities;

public class Test {
    public static void main(String[] args) {
        DriverUtilities driverUtilities = DriverUtilities.getInstance();
        WebDriver driver = driverUtilities.getWebDriver();
    }
}
