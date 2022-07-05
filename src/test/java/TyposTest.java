import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TyposTest extends BaseTest{

    @BeforeMethod
    public void navigate() { driver.get("http://the-internet.herokuapp.com/typos"); }

    @Test
    public void typosTest() {
        final int SECOND_PARAGRAPH_INDEX = 1;
        WebElement paragraphToCheck = driver.findElements(By.tagName("p"))
                .get(SECOND_PARAGRAPH_INDEX);
        Assert.assertEquals(paragraphToCheck.getText(),
                "Sometimes you'll see a typo, other times you won't.",
                "Typo in the text");
    }
}
