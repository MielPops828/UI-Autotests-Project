package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.ParameterProvider;

@Epic("Автоматизация страницы Way2Automation")
@Feature("Главная страница")
@Story("Переход по меню навигации на другие страницы")
public class NavigationMenuTransitionTest extends BaseTest{
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testNavigationMenuTransition(){
        HomePage home = new HomePage(getDriver()).openPage().navigateTransition();
        Assert.assertTrue(home.hasTransition(ParameterProvider.get("transition.url")));
    }
}
