package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;

public class ScrollTest extends BaseTest{
    @Test
    public void testScroll(){
        HomePage home = new HomePage(getDriver());
        SoftAssert softAssert = new SoftAssert();
        home.openPage();
        softAssert.assertTrue(home.hasHeaderVisible());
    }
}
