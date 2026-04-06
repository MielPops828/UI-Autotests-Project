package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FramesAndWindowsPage;
import utils.ParameterProvider;

@Epic("Автоматизация страницы Way2Automation")
@Feature("Страница Frames and Windows")
@Story("Проверка открытия новых вкладок")
public class TabsTest extends BaseTest{
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testTabs(){
        FramesAndWindowsPage framesAndWindowsPage = new FramesAndWindowsPage(getDriver())
                .openPage()
                .switchToFrame()
                .openSecondTab()
                .openThirdTab();
        Assert.assertTrue(framesAndWindowsPage.isTabOpened(Integer.parseInt((ParameterProvider.get("tabs.count")))));
    }
}
