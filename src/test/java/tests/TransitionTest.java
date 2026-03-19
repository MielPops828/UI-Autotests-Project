package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;

public class TransitionTest extends BaseTest{
    @Test
    public void testTransition(){
        HomePage home = new HomePage(getDriver());
        home.openPage();
        Assert.assertTrue(home.hasTransition("https://www.way2automation.com/lifetime-membership-club/"));
    }
}
