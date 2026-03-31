package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CookieUtils;
import utils.ParameterProvider;

import java.io.File;
import java.time.Duration;

public class SQLEXPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String COOKIE_FILE = ParameterProvider.get("cookie.file.path");

    public SQLEXPage (WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ParameterProvider.get("wait.time"))));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[name='login']")
    private WebElement loginField;

    @FindBy(css = "input[name='psw']")
    private WebElement passwordField;

    @FindBy(css = "input[name='subm1']")
    private WebElement entryButton;

    @FindBy(css = "a[href='/personal.php']")
    private WebElement username;

    @Step("Открыть страницу")
    public SQLEXPage openPage(){
        driver.get(ParameterProvider.get("sqlex.url"));
        return this;
    }

    @Step("Авторизация через куки или логин/пароль")
    public SQLEXPage authWithCookie() {
        File file = new File(COOKIE_FILE);
        if (file.exists()) {
            loginWithCookies();
        } else {
            loginWithCredentials();
            saveCookies();
        }
        return this;
    }

    @Step("Авторизация с помощью куков")
    private void loginWithCookies() {
        driver.manage().deleteAllCookies();
        CookieUtils.loadCookies(driver, COOKIE_FILE);
        driver.navigate().refresh();
    }

    @Step("Авторизация через логин и пароль")
    private void loginWithCredentials() {
        wait.until(ExpectedConditions.visibilityOf(loginField));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        wait.until(ExpectedConditions.visibilityOf(entryButton));

        loginField.sendKeys(ParameterProvider.get("sqlex.login"));
        passwordField.sendKeys(ParameterProvider.get("sqlex.password"));
        entryButton.click();
    }

    @Step("Сохранение куков в файл")
    private void saveCookies() {
        CookieUtils.saveCookies(driver, COOKIE_FILE);
    }

    @Step("Проверить успешность авторизации")
    public boolean isAuthorized(){
        try {
            wait.until(ExpectedConditions.visibilityOf(username));
            return username.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
