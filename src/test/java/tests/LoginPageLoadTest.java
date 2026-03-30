package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

@Epic("Автоматизация страницы Way2Automation")
@Feature("Страница авторизации")
@Story("Проверка работы страницы авторизации")
public class LoginPageLoadTest extends BaseTest{
    @Test
    @Severity(SeverityLevel.BLOCKER)
    public void testLogin(){
        LoginPage loginPage = new LoginPage(getDriver()).openPage().clearFields();
        Assert.assertTrue(loginPage.isFieldsVisible());
        Assert.assertTrue(loginPage.isButtonDisable());
    }
}
