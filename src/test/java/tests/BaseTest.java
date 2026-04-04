package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.ParameterProvider;

import static utils.DriverFactory.createWebdriver;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser){
        WebDriver webdriver = createWebdriver(browser);
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
