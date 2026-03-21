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
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"ast-desktop-header\"]/div[1]")
    public WebElement headerElement;

    @FindBy(xpath = "//*[@id=\"ast-desktop-header\"]/div[2]")
    public WebElement navBlock;

    @FindBy(xpath = "//a[contains(text(), 'Register Now')]")
    public WebElement regButton;

    @FindBy(css = "section[data-id='5b4952c1']")
    public WebElement listCourses;

    @FindBy(css = "[data-elementor-type='footer']")
    public WebElement footerElement;

    @FindBy(xpath = "//*[@id=\"ast-desktop-header\"]/div[1]")
    public WebElement contactSection;

    @FindBy(css = "div[data-id='695441a0']")
    public WebElement aboutSection;

    @FindBy(css = "div.ast-main-header-wrap div.ast-primary-header-bar")
    public WebElement headerScroll;

    @FindBy(xpath = "//span[contains(text(), 'All Courses')]")
    public WebElement allCoursesMenu;

    @FindBy(xpath = "//span[contains(text(), 'Lifetime Membership')]")
    public WebElement lifetimeMembershipLink;

    @FindBy(xpath = "//h1[contains(text(), 'LIFETIME MEMBERSHIP CLUB')]")
    public WebElement lifetimeMembershipTitle;

    @Step("Открыть сайт")
    public void openPage(){
        driver.get(ParameterProvider.get("base.url"));
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
        return contactSection.findElements(By.tagName("a"));
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

    @Step("Проверить отображение меню при скроллинге страницы вниз")
    public boolean hasHeaderVisible() {
        try{
            js.executeScript("window.scrollTo({top: 2000, behavior: 'smooth'})");
            Thread.sleep(1000);
            if (headerScroll.isDisplayed()){
                js.executeScript("window.scrollTo({down: 2000, behavior: 'smooth'})");
                Thread.sleep(1000);
                return true;
            }
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Проверить, что произошел переход на {url}")
    public boolean isTransitionUrl(String url){
        return url.equals(driver.getCurrentUrl());
    }

    @Step("Перейти по меню навигации на другую страницу: {url}")
    public boolean hasTransition(String url){
        allCoursesMenu.click();
        lifetimeMembershipLink.click();
        wait.until(ExpectedConditions.visibilityOf(lifetimeMembershipTitle));
        if (isTransitionUrl(url)){
            return lifetimeMembershipTitle.isDisplayed();
        }
        return false;
    }
}
