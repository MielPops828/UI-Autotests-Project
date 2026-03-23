package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

@Epic("Автоматизация страницы Way2Automation")
@Feature("Главная страница")
@Story("Отображение меню при скроллинге страницы")
public class StickyHeaderTest extends BaseTest{
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testStickyHeader(){
        HomePage home = new HomePage(getDriver()).openPage().scrollDown(2000);
        Assert.assertTrue(home.isHeaderVisible());
    }
}
