package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ParameterProvider;

import static utils.DriverFactory.createWebdriver;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp(){
        WebDriver webdriver = createWebdriver(ParameterProvider.get("base.browser"));
        driver.set(webdriver);
    }

    @AfterMethod
    public void tearDown(){
        driver.get().quit();
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}
