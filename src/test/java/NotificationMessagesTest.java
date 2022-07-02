import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class NotificationMessagesTest extends BaseTest {

    @BeforeMethod
    public void navigate() {
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
    }

    @Test
    public void notificationsTest() {
        WebElement clickHereLink = driver.findElement(By.linkText("Click here"));
        List<String> expectedMessages = new ArrayList<>();
        expectedMessages.add("Action unsuccessful, please try again");
        expectedMessages.add("Action successful");
        clickHereLink.click();
        WebElement notificationElement = new WebDriverWait(driver,30)
                .until(driver -> driver.findElement(By.id("flash")));
        String actualMessage = notificationElement.getText()
                .replaceAll("[^a-zA-Z/d ]", "");
        Assert.assertTrue(expectedMessages.contains(actualMessage),
                "Incorrect notification text");
    }

}
