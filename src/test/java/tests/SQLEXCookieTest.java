package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SQLEXPage;

@Epic("Автоматизация страницы SQL-EX")
@Feature("Страница авторизации")
@Story("Проверка записи и считывания куков из файла")
public class SQLEXCookieTest extends BaseTest{
    @Test(invocationCount = 2)
    @Severity(SeverityLevel.NORMAL)
    public void testSQLEXCookie() {
        SQLEXPage sqlexPage = new SQLEXPage(getDriver()).openPage().authWithCookie();
        Assert.assertTrue(sqlexPage.isAuthorized());
    }
}
