import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InputsTest extends BaseTest {

    private WebElement input;

    @BeforeMethod
    public void navigate() {
        driver.get("http://the-internet.herokuapp.com/inputs");
        input = driver.findElement(By.cssSelector("[type = 'number']"));
    }

    public void enterValueInInput(int valueToEnter) {
        input.clear();
        String keysToEnter = (valueToEnter > 0) ? Keys.ARROW_UP.toString()
                : Keys.ARROW_DOWN.toString();
        keysToEnter = keysToEnter.repeat(Math.abs(valueToEnter));
        input.sendKeys(keysToEnter);
    }

    @Test
    public void inputsTest() {
        WebElement input = driver.findElement(By.cssSelector("[type = 'number']"));
        enterValueInInput(5);
        Assert.assertEquals(input.getAttribute("value"), "5",
                "Different value was entered");
        enterValueInInput(9);
        Assert.assertEquals(input.getAttribute("value"), "9",
                "Different value was entered");
        enterValueInInput(-10);
        Assert.assertEquals(input.getAttribute("value"), "-10",
                "Different value was entered");
    }
}

