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
import java.util.Set;

public class FramesAndWindowsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public FramesAndWindowsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ParameterProvider.get("wait.time"))));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "iframe.demo-frame")
    private WebElement iframe;

    @FindBy(xpath = "//a[contains(text(),'New Browser Tab')]")
    private WebElement newBrowserTabLink;

    @Step("Открыть страницу")
    public FramesAndWindowsPage openPage() {
        driver.get(ParameterProvider.get("tabs.url"));
        return this;
    }

    @Step("Переключиться в iframe")
    public FramesAndWindowsPage switchToFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        return this;
    }

    @Step("Открыть новую вкладку и переключиться на неё")
    public void openNewTab() {
        Set<String> oldWindows = driver.getWindowHandles();
        wait.until(ExpectedConditions.elementToBeClickable(newBrowserTabLink)).click();
        wait.until(driver -> driver.getWindowHandles().size() > oldWindows.size());
        Set<String> newWindows = driver.getWindowHandles();
        newWindows.removeAll(oldWindows);
        String newTab = newWindows.iterator().next();
        driver.switchTo().window(newTab);
    }

    @Step("Открыть вторую вкладку")
    public FramesAndWindowsPage openSecondTab() {
        openNewTab();
        return this;
    }

    @Step("Открыть третью вкладку")
    public FramesAndWindowsPage openThirdTab() {
        openNewTab();
        return this;
    }

    @Step("Проверить количество вкладок")
    public boolean isTabOpened(int expectedTabsCount) {
        return driver.getWindowHandles().size() == expectedTabsCount;
    }
}