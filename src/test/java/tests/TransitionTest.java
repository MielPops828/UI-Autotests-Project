package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

@Epic("Автоматизация страницы Way2Automation")
@Feature("Главная страница")
@Story("Переход по меню навигации на другие страницы")
public class TransitionTest extends BaseTest{
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testTransition(){
        HomePage home = new HomePage(getDriver());
        home.openPage();
        Assert.assertTrue(home.hasTransition("https://www.way2automation.com/lifetime-membership-club/"));
    }
}
