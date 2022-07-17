import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DynamicControlsTest extends BaseTest {
    private final By checkboxLocator = By.cssSelector(
            "#checkbox>input[type = 'checkbox'][label = 'blah']");
    private final By buttonRemoveLocator = By.xpath(
            "//form[@id = 'checkbox-example']/button[text() = 'Remove']");
    private final By messageCheckboxLocator = By.xpath(
            "//form[@id = 'checkbox-example']/p[text() = \"It\'s gone!\"]");
    private final By inputLocator = By.cssSelector("#input-example>input");

    private final By buttonEnableLocator = By.xpath(
            "//form[@id = 'input-example']/button[text() = 'Enable']");

    private final By inputMessageLocator = By.xpath(
            "//form[@id = 'input-example']/p[text() = \"It\'s enabled!\"]");

    private WebElement getCheckbox() {
        return driver.findElement(checkboxLocator);
    }

    private void clickRemoveButton() {
        driver.findElement(buttonRemoveLocator).click();
    }

    WebElement getInput() {
        return driver.findElement(inputLocator);
    }

    private void clickEnableButton() {
        driver.findElement(buttonEnableLocator).click();
    }

    private boolean isControlExists(By locator) {
        List<WebElement> controls = driver.findElements(locator);
        return controls.size() > 0;
    }

    @BeforeClass
    public void initialize() {
        wait = new WebDriverWait(driver, 50);
    }

    @BeforeMethod
    public void navigate() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
    }

    @Test
    public void testDynamicControls() {
        WebElement checkbox = getCheckbox();
        clickRemoveButton();
        waitUntilMessage(messageCheckboxLocator);
        Assert.assertFalse(isControlExists(checkboxLocator), "Checkbox is still exist");

        WebElement input = getInput();
        Assert.assertFalse(input.isEnabled());
        clickEnableButton();
        waitUntilMessage(inputMessageLocator);
        Assert.assertTrue(input.isEnabled(), "Input is not made enabled");
    }
}
