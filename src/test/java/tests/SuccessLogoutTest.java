package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.LoginDataProvider;

@Epic("Автоматизация страницы Way2Automation")
@Feature("Страница авторизации")
@Story("Проверка успешного разлогирования")
public class SuccessLogoutTest extends BaseTest{
    @Test(dataProvider = "validLoginData", dataProviderClass = LoginDataProvider.class, description = "Проверка разлогирования после авторизации с валидными данными")
    @Severity(SeverityLevel.NORMAL)
    public void testSuccessLogout(String login, String password, String username){
        LoginPage loginPage = new LoginPage(getDriver()).openPage().clearFields().inputData(login, password, username).loginValid().clickLogout();
        Assert.assertTrue(loginPage.isLoginPageOpened());
    }
}
