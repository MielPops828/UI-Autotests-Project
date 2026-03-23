package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ParameterProvider;

import static utils.DriverFactory.createWebdriver;

public class BaseTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = createWebdriver(ParameterProvider.get("base.browser"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public WebDriver getDriver() { return driver; }
}
