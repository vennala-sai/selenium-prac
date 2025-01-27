package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//singleton class to get the browser driver that we want
public class DriverUtilities {

    private static DriverUtilities driverUtilities;
    private WebDriver driver;

    private DriverUtilities(){
        super(); //optional
    }
    //singleton
    public static DriverUtilities getInstance(){
        if (driverUtilities == null) {
            driverUtilities = new DriverUtilities();
        }
        return driverUtilities;
    }

    public WebDriver getWebDriver() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    private void createDriver() {
        String driverName = getDriverName();
        switch (driverName){
            case "Chrome":
                this.driver = new ChromeDriver();
                break;
            case "Firefox":
                this.driver = new FirefoxDriver();
            default:
                break;
        }
    }

    private String getDriverName() {
        //returns the name of the browser you want to test (firefox, chrome, etc...)
        String driverName = "";
        Properties config = new Properties();
        try {
            config.load(new FileInputStream("C:/Users/Sai Vennala/Downloads/SeleniumCucumberJava/SeleniumCucumberJava/src/test/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driverName = config.getProperty("browser");
        return driverName;
    }
}
