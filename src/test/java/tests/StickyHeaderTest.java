package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class StickyHeaderTest extends BaseTest{
    @Test
    public void testStickyHeader(){
        HomePage home = new HomePage(getDriver()).openPage().scrollDown(2000);
        Assert.assertTrue(home.isHeaderVisible());
    }
}
