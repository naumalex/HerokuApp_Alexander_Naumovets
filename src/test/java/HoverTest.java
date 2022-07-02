import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HoverTest extends BaseTest {

    private String getDisplayedUserName(int index) {
        List<WebElement> userTextElements = driver.findElements(
                By.xpath("//div[@class = 'figcaption']/h5[contains(text(), 'name:')]"));
        return userTextElements.get(index).getText();
    }

    @BeforeMethod
    public void navigate() { driver.get("http://the-internet.herokuapp.com/hovers");}

    @Test
    public void hoverTest() {
        Actions actions = new Actions(driver);
        List<WebElement> profiles = driver.findElements(By.className("figure"));
        for (int i = 0; i < profiles.size(); i++) {
            WebElement profile = profiles.get(i);
            actions.moveToElement(profile).build().perform();
            Assert.assertEquals(getDisplayedUserName(i), "name: user" + (i + 1),
                   "Incorrect User Name is shown");
            actions.moveToElement(driver.findElement(By.linkText("View profile")))
                   .click().build().perform();
            WebElement userPage = driver.findElement(By.tagName("h1"));
            Assert.assertFalse(userPage.getText().contains("Not Found"),
                    "URL is broken. Page not found");
            navigate(); // Return to the original page after oprning a user profile page
            profiles = driver.findElements(By.className("figure"));// to refresh DOM
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

}
