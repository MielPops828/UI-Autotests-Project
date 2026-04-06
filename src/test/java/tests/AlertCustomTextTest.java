package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertPage;
import utils.ParameterProvider;

@Epic("Автоматизация страницы Way2Automation")
@Feature("Страница Alert")
@Story("Проверка вывода текста из алерта")
public class AlertCustomTextTest extends BaseTest{
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testAlertCustomText(){
        String text = ParameterProvider.get("alert.text");
        AlertPage alertPage = new AlertPage(getDriver())
                .openPage()
                .clickInputAlertCharter()
                .switchToFrame()
                .clickDemonstrateInputAlertButton()
                .inputTextInAlert(text)
                .acceptAlert()
                .switchBackToFrame();
        Assert.assertTrue(alertPage.hasTextApply(text));
    }
}
