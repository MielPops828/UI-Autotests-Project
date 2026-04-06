package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ParameterProvider;

import java.time.Duration;

public class DragAndDropPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(Long.parseLong(ParameterProvider.get("wait.time"))));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "iframe.demo-frame")
    private WebElement iframe;

    @FindBy(id = "draggable")
    private WebElement dragElement;

    @FindBy(id = "droppable")
    private WebElement dropBox;

    @FindBy(css = "#droppable p")
    private WebElement dropText;

    @Step("Открыть страницу")
    public DragAndDropPage openPage() {
        driver.get(ParameterProvider.get("draganddrop.url"));
        return this;
    }

    @Step("Переключиться в iframe")
    public DragAndDropPage switchToFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        return this;
    }

    @Step("Выполнить Drag and Drop")
    public DragAndDropPage dragAndDrop() {
        wait.until(ExpectedConditions.visibilityOf(dragElement));
        wait.until(ExpectedConditions.visibilityOf(dropBox));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(dragElement, dropBox).perform();
        return this;
    }

    @Step("Проверить текст drop зоны")
    public boolean isDropped(String expectedText) {
        wait.until(ExpectedConditions.textToBePresentInElement(dropText, expectedText));
        return dropText.getText().equals(expectedText);
    }
}