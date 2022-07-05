import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CheckboxesTest extends BaseTest {
    @BeforeMethod
    public void navigate() { driver.get("https://the-internet.herokuapp.com/checkboxes"); }

    @Test
    public void checkboxesTest() {
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type = 'checkbox']"));
        WebElement checkbox = checkboxes.get(0);
        Assert.assertFalse(checkbox.isSelected(),
                "Initially first checkbox should be unchecked");
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected(),
                "After click the first checkbox should be checked");

        checkbox = checkboxes.get(1);
        Assert.assertTrue(checkbox.isSelected(),
                "Initially second checkbox should be checked");
        checkbox.click();
        Assert.assertFalse(checkbox.isSelected(),
                "After click second checkbox should be unchecked");
    }
}
