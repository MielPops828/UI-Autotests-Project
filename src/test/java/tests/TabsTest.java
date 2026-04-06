package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FramesAndWindowsPage;
import utils.ParameterProvider;

public class TabsTest extends BaseTest {
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testTabs() {
        FramesAndWindowsPage framesAndWindowsPage = new FramesAndWindowsPage(getDriver())
                .openPage()
                .switchToFrame()
                .openSecondTab()
                .openThirdTab();
        Assert.assertTrue(framesAndWindowsPage.isTabOpened(Integer.parseInt((ParameterProvider.get("tabs.count")))));
    }
}
