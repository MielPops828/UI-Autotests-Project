package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ParameterProvider;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ParameterProvider.get("wait.time"))));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[name='username']")
    private WebElement loginField;

    @FindBy(css = "input[name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[starts-with(@id, 'formly_')]")
    private WebElement usernameField;

    @FindBy(css = "button.btn.btn-danger")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(text(), 'Username or password is incorrect')]")
    private WebElement errorMessage;

    @Step("Открыть страницу")
    public LoginPage openPage(){
        driver.get(ParameterProvider.get("login.url"));
        return this;
    }

    @Step("Проверить отображение поля логина и пароля")
    public boolean isFieldsVisible(){
        return loginField.isDisplayed() && passwordField.isDisplayed() && usernameField.isDisplayed();
    }

    @Step("Очистить поля ввода")
    public LoginPage clearFields(){
        wait.until(ExpectedConditions.visibilityOf(loginField));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        loginField.clear();
        passwordField.clear();
        usernameField.clear();
        return this;
    }

    @Step("Проверить, что кнопка имеет состояние disabled при незаполненных полях")
    public boolean isButtonDisable(){
        String disabledAttr = loginButton.getAttribute("disabled");
        return !loginButton.isEnabled();
    }

    @Step("Заполнить поля данными: login: {login}, password: {password}, username: {username}")
    public LoginPage inputData(String login, String password, String username){
        wait.until(ExpectedConditions.visibilityOf(loginField));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        usernameField.sendKeys(username);
        return this;
    }

    @Step("Нажать на кнопку 'Login' при указанных валидных данных")
    public DashboardPage loginValid(){
        loginButton.click();
        return new DashboardPage(driver);
    }

    @Step("Нажать на кнопку 'Login' при указанных невалидных данных")
    public LoginPage loginInvalid(){
        loginButton.click();
        return this;
    }

    @Step("Проверить отображение сообщения об ошибке авторизации")
    public boolean isFailAuth(String message){
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText().equals(message);
    }

    @Step("Проверить возврат к странице авторизации")
    public boolean isLoginPageOpened(){
        return driver.getCurrentUrl().contains("login") && isFieldsVisible();
    }
}
