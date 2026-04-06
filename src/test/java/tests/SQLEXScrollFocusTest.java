package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SQLEXPage;

@Epic("Автоматизация страницы SQL-EX")
@Feature("Страница авторизации")
@Story("Проверка фокуса на поле ввода и наличия скролла")
public class SQLEXScrollFocusTest extends BaseTest{
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testScrollFocus(){
        SQLEXPage sqlexPage = new SQLEXPage(getDriver()).openPage().removeFocus();
        Assert.assertTrue(sqlexPage.hasNoFocus());
        Assert.assertTrue(sqlexPage.hasVerticalScroll());
    }
}
