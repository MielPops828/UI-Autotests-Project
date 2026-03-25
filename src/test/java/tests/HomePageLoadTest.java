package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import utils.ParameterProvider;

public class HomePageLoadTest extends BaseTest{
    @Test
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
        softAssert.assertTrue(home.hasAboutContact(ParameterProvider.get("contact.second-email")));
        softAssert.assertAll();
    }
}
