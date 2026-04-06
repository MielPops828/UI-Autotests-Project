package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasicAuthPage;
import utils.ParameterProvider;

@Epic("Автоматизация страницы HttpWatch")
@Feature("Страница HTTP Gallery")
@Story("Проверка авторизации на странице")
public class BasicAuthTest extends BaseTest{
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testBasicAuth(){
        BasicAuthPage basicAuthPage = new BasicAuthPage(getDriver())
                .openPage()
                .setupAuthentication(ParameterProvider.get("basicauth.login"), ParameterProvider.get("basicauth.password"))
                .clickDisplayImageButton();
        Assert.assertTrue(basicAuthPage.isImageDisplayed());
    }
}
