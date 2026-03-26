package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import utils.ParameterProvider;

@Epic("Автоматизация страницы Way2Automation")
@Feature("Главная страница")
@Story("Проверка создания скриншота")
public class ScreenshotHomePageLoadTest extends BaseTest{
    @Test
    @Severity(SeverityLevel.BLOCKER)
    public void testHomePageLoad() {
        SoftAssert softAssert = new SoftAssert();
        HomePage home = new HomePage(getDriver()).openPage();
        softAssert.assertTrue(home.visibilitySections());
        softAssert.assertTrue(home.areAllLinksHaveHref());
        softAssert.assertTrue(home.footerVisibility());
        softAssert.assertTrue(home.hasAboutContact(ParameterProvider.get("contact.address")));
        softAssert.assertTrue(home.hasAboutContact(ParameterProvider.get("contact.first-phone")));
        softAssert.assertTrue(home.hasAboutContact(ParameterProvider.get("contact.second-phone")));
        softAssert.assertTrue(home.hasAboutContact(ParameterProvider.get("contact.first-email")));
        softAssert.assertTrue(home.hasAboutContact(ParameterProvider.get("success.message")));
        softAssert.assertAll();
    }
}
