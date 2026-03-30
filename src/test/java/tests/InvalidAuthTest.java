package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.LoginDataProvider;
import utils.ParameterProvider;

@Epic("Автоматизация страницы Way2Automation")
@Feature("Страница авторизации")
@Story("Проверка авторизации")
public class InvalidAuthTest extends BaseTest{
    @Test(dataProvider = "invalidLoginData", dataProviderClass = LoginDataProvider.class, description = "Проверка авторизации с невалидными данными")
    @Severity(SeverityLevel.NORMAL)
    public void testInvalidAuth(String login, String password, String username){
        LoginPage loginPage = new LoginPage(getDriver())
                .openPage()
                .clearFields()
                .inputData(login, password, username)
                .loginInvalid();
        Assert.assertTrue(loginPage.isFailAuth(ParameterProvider.get("fail.message")));
    }
}
