package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ParameterProvider;
import java.time.Duration;

public class BasicAuthPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public BasicAuthPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ParameterProvider.get("wait.time"))));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[value='Display Image']")
    private WebElement displayImageButton;

    @FindBy(id = "downloadImg")
    private WebElement image;

    @Step("Открыть страницу")
    public BasicAuthPage openPage(){
        driver.get(ParameterProvider.get("basicauth.url"));
        return this;
    }

    @Step("Зарегистрировать аутентификацию")
    public BasicAuthPage setupAuthentication(String username, String password) {
        ((HasAuthentication) driver).register(
                uri -> uri.getHost().contains("httpwatch.com"),
                UsernameAndPassword.of(username, password)
        );
        return this;
    }

    @Step("Нажать на кнопку Display Image")
    public BasicAuthPage clickDisplayImageButton() {
        wait.until(ExpectedConditions.elementToBeClickable(displayImageButton)).click();
        return this;
    }

    @Step("Проверить отображение элемента Image с указанными данными")
    public boolean isImageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
    }
}
