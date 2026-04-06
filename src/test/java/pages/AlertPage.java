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

public class AlertPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public AlertPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ParameterProvider.get("wait.time"))));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[href='#example-1-tab-2']")
    private WebElement inputAlertCharter;

    @FindBy(xpath = "//iframe[@src='alert/input-alert.html']")
    private WebElement iframe;

    @FindBy(css = "button[onclick='myFunction()']")
    private WebElement demonstrateInputAlertButton;

    @FindBy(id = "demo")
    private WebElement textHello;

    @Step("Открыть страницу")
    public AlertPage openPage() {
        driver.get(ParameterProvider.get("alert.url"));
        return this;
    }

    @Step("Нажать на вкладку Input Alert")
    public AlertPage clickInputAlertCharter() {
        wait.until(ExpectedConditions.elementToBeClickable(inputAlertCharter)).click();
        return this;
    }

    @Step("Переключиться в iframe")
    public AlertPage switchToFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        return this;
    }

    @Step("Нажать на кнопку открытия Input Alert")
    public AlertPage clickDemonstrateInputAlertButton() {
        wait.until(ExpectedConditions.elementToBeClickable(demonstrateInputAlertButton)).click();
        return this;
    }

    @Step("Нажать на кнопку открытия Input Alert")
    public AlertPage inputTextInAlert(String text){
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys(text);
        return this;
    }

    @Step("Ввести текст в alert")
    public AlertPage acceptAlert(){
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        return this;
    }

    @Step("Вернуться в iframe после alert")
    public AlertPage switchBackToFrame() {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        return this;
    }

    @Step("Проверить текст на странице")
    public boolean hasTextApply(String text){
        return textHello.getText().contains(text);
    }
}
