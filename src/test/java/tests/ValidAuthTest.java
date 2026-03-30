package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.LoginDataProvider;
import utils.ParameterProvider;

@Epic("Автоматизация страницы Way2Automation")
@Feature("Страница авторизации")
@Story("Проверка авторизации")
public class ValidAuthTest extends BaseTest{
    @Test(dataProvider = "validLoginData", dataProviderClass = LoginDataProvider.class, description = "Проверка авторизации с валидными данными")
    @Severity(SeverityLevel.NORMAL)
    public void testValidAuth(String username, String password, String usernameDescription){
        DashboardPage loginPage = new LoginPage(getDriver())
                .openPage()
                .clearFields()
                .inputData(username, password, usernameDescription)
                .loginValid();
        Assert.assertTrue(loginPage.isSuccessAuth(ParameterProvider.get("success.message")));
    }
}