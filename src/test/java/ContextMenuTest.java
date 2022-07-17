import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContextMenuTest extends BaseTest {

    private final By rectangularAreaLocator = By.cssSelector("#hot-spot");

    public void rightClickRectangular() {
        Actions actions = new Actions(driver);
        WebElement rectangularAreaControl = driver.findElement(rectangularAreaLocator);
        actions.contextClick(rectangularAreaControl).build().perform();
    }

    @BeforeMethod
    public void navigate() {
        driver.get("https://the-internet.herokuapp.com/context_menu");
    }

    @Test
    public void testContextMenu() {
        rightClickRectangular();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "You selected a context menu");
        alert.accept();
        driver.switchTo().defaultContent();
    }
}
