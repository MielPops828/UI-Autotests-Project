package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(text(), \"You're logged in!!\")]")
    private WebElement successMessage;

    @FindBy(css = "a[href='#/login']")
    private WebElement logoutButton;

    @Step("Проверить, что на странице выведено сообщение: {message}")
    public boolean isSuccessAuth(String message){
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.getText().equals(message);
    }

    @Step("Нажать кнопку Logout")
    public LoginPage clickLogout(){
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        return new LoginPage(driver);
    }
}
