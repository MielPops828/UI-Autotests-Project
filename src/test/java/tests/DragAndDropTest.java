package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DragAndDropPage;
import utils.ParameterProvider;

@Epic("Автоматизация страницы Way2Automation")
@Feature("Страница Droppable")
@Story("Проверка работы Drag n Drop на странице")
public class DragAndDropTest extends BaseTest{
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testDragAndDrop(){
        DragAndDropPage dragAndDropPage = new DragAndDropPage(getDriver())
                .openPage()
                .switchToFrame()
                .dragAndDrop();
        Assert.assertTrue(dragAndDropPage.isDropped(ParameterProvider.get("droppable.message")));
    }
}
