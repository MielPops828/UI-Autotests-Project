package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;

@Epic("Автоматизация страницы Way2Automation")
@Feature("Главная страница")
@Story("Отображение меню при скроллинге страницы")
public class ScrollTest extends BaseTest{
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testScroll(){
        HomePage home = new HomePage(getDriver());
        SoftAssert softAssert = new SoftAssert();
        home.openPage();
        softAssert.assertTrue(home.hasHeaderVisible());
    }
}
