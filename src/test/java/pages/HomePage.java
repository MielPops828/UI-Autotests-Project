package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ParameterProvider;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    JavascriptExecutor js;

    public HomePage(WebDriver driver){
        this.driver = driver;
        js = (JavascriptExecutor) this.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ParameterProvider.get("wait.time"))));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"ast-desktop-header\"]/div[1]")
    private WebElement headerElement;

    @FindBy(xpath = "//*[@id=\"ast-desktop-header\"]/div[2]")
    private WebElement navBlock;

    @FindBy(xpath = "//a[contains(text(), 'Register Now')]")
    private WebElement regButton;

    @FindBy(css = "section[data-id='5b4952c1']")
    private WebElement listCourses;

    @FindBy(css = "[data-elementor-type='footer']")
    private WebElement footerElement;

    @FindBy(css = "div[data-id='695441a0']")
    private WebElement aboutSection;

    @FindBy(css = "div.ast-main-header-wrap div.ast-primary-header-bar")
    private WebElement headerScroll;

    @FindBy(xpath = "//span[contains(text(), 'All Courses')]")
    private WebElement allCoursesMenu;

    @FindBy(xpath = "//span[contains(text(), 'Lifetime Membership')]")
    private WebElement lifetimeMembershipLink;

    @FindBy(xpath = "//h1[contains(text(), 'LIFETIME MEMBERSHIP CLUB')]")
    private WebElement lifetimeMembershipTitle;

    @Step("Открыть страницу")
    public HomePage openPage(){
        driver.get(ParameterProvider.get("base.url"));
        return this;
    }

    @Step("Проверить отображение элементов на странице")
    public boolean visibilitySections(){
        wait.until(ExpectedConditions.visibilityOf(headerElement));
        wait.until(ExpectedConditions.visibilityOf(navBlock));
        wait.until(ExpectedConditions.visibilityOf(headerScroll));
        wait.until(ExpectedConditions.visibilityOf(regButton));
        wait.until(ExpectedConditions.visibilityOf(listCourses));
        wait.until(ExpectedConditions.visibilityOf(footerElement));
        return headerElement.isDisplayed() && navBlock.isDisplayed() && regButton.isDisplayed() && listCourses.isDisplayed() && footerElement.isDisplayed();
    }

    public List<WebElement> getAllLinks() {
        return headerElement.findElements(By.tagName("a"));
    }

    @Step("Проверить хэдер с контактной информацией")
    public boolean areAllLinksHaveHref() {
        List<WebElement> links = getAllLinks();
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href == null || href.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Step("Проверить отображение футера")
    public boolean footerVisibility(){
        return footerElement.isDisplayed();
    }

    @Step("Проверить, что футер содержит {address}")
    public boolean hasAboutContact(String address){
        return aboutSection.getText().trim().contains(address);
    }

    @Step("Проскроллить страницу вниз")
    public HomePage scrollDown(int value) {
        js.executeScript("window.scrollTo({top: " + value + ", behavior: 'smooth'})");
        wait.until(ExpectedConditions.visibilityOf(headerScroll));
        return this;
    }

    @Step("Проверить отображение меню при скроллинге страницы вниз")
    public boolean isHeaderVisible(){
        return headerScroll.isDisplayed();
    }

    @Step("Перейти по меню навигации на другую страницу: {url}")
    public HomePage navigateTransition(){
        allCoursesMenu.click();
        lifetimeMembershipLink.click();
        wait.until(ExpectedConditions.visibilityOf(lifetimeMembershipTitle));
        return this;
    }
    @Step("Проверить, что произошел переход на {url}")
    public boolean hasTransition(String url){
        return url.equals(driver.getCurrentUrl()) && lifetimeMembershipTitle.isDisplayed();
    }
}
