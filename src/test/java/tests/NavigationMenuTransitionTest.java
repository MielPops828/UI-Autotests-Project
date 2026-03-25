package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.ParameterProvider;

public class NavigationMenuTransitionTest extends BaseTest{
    @Test
    public void testNavigationMenuTransition(){
        HomePage home = new HomePage(getDriver()).openPage().navigateTransition();
        Assert.assertTrue(home.hasTransition(ParameterProvider.get("transition.url")));
    }
}
