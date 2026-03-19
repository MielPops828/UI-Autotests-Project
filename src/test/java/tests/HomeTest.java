package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;

public class HomeTest extends BaseTest{
    @Test
    public void testHomePageLoad() {
        HomePage home = new HomePage(getDriver());
        SoftAssert softAssert = new SoftAssert();
        home.openPage();
        softAssert.assertTrue(home.visibilitySections());
        softAssert.assertTrue(home.areAllLinksHaveHref());
        softAssert.assertTrue(home.footerVisibility());
        softAssert.assertTrue(home.hasAboutContact("CDR Complex, 3rd Floor, Naya Bans Market, Sector 15, Noida, Near sec-16 Metro Station"));
        softAssert.assertTrue(home.hasAboutContact("+91 97111-11-558"));
        softAssert.assertTrue(home.hasAboutContact("+91 97111-91-558"));
        softAssert.assertTrue(home.hasAboutContact("trainer@way2automation.com"));
        softAssert.assertTrue(home.hasAboutContact("seleniumcoaching@gmail.com"));
        softAssert.assertTrue(home.isSliderWorking(), "Кнопки навигации работают некорректно: слайды не меняются");
        softAssert.assertAll();
    }
}
