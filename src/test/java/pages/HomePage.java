package pages;

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

    @FindBy(xpath = "//div[contains(@class, 'swiper-button-prev-c50f9f0')]")
    public WebElement swiperButtonLeft;

    @FindBy(xpath = "//div[contains(@class, 'swiper-button-next-c50f9f0')]")
    public WebElement swiperButtonRight;

    @FindBy(xpath = "//div[contains(@class, 'pp-info-box swiper-slide')]")
    public List<WebElement> swiperSlides;

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

    public void openPage(){
        driver.get(ParameterProvider.get("base.url"));
    }

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

    public boolean footerVisibility(){
        return footerElement.isDisplayed();
    }

    public boolean hasAboutContact(String address){
        return aboutSection.getText().trim().contains(address);
    }

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

    public boolean isTransitionUrl(String url){
        return url.equals(driver.getCurrentUrl());
    }

    public boolean hasTransition(String url){
        allCoursesMenu.click();
        lifetimeMembershipLink.click();
        wait.until(ExpectedConditions.visibilityOf(lifetimeMembershipTitle));
        if (isTransitionUrl(url)){
            return lifetimeMembershipTitle.isDisplayed();
        }
        return false;
    }

    public boolean isSliderWorking(){
        try{
            WebElement firstCard = swiperSlides.getFirst();
            String firstSlideTitle = firstCard.findElement(By.cssSelector("h4")).getText();
            swiperButtonRight.click();
            Thread.sleep(1000);
            WebElement firstCardAfter = swiperSlides.getFirst();
            String firstSlideTitleAfter = firstCardAfter.findElement(By.cssSelector("h4")).getText();
            return !firstSlideTitle.equals(firstSlideTitleAfter);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
