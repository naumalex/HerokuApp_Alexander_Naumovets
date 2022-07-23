import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FramesTest extends BaseTest {
    private final By iframeLink = By.xpath(
            "//div[@id ='content']//a[text() = 'iFrame']");
    private final By editorTextLocator = By.tagName("p");
    public void clickFrameLink() {
        driver.findElement(iframeLink).click();
    }

    public String getFrameText() {
        return driver.findElement(editorTextLocator).getText();
    }

    @BeforeMethod
    public void navigate() {
        driver.get("https://the-internet.herokuapp.com/frames");
    }

    @Test
    public void testFrames() {
        clickFrameLink();
        driver.switchTo().frame("mce_0_ifr");
        String actualFrameText = getFrameText();
        driver.switchTo().defaultContent();
        Assert.assertEquals(actualFrameText, "Your content goes here.",
                "Frame text is different from the expected value");
    }
}

