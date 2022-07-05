import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddRemoveElementsTest extends BaseTest {

    @BeforeMethod
    public void navigate() { driver.get("https://the-internet.herokuapp.com/add_remove_elements/"); }

    public void addElement() {
        WebElement addElementButton = driver.findElement(
                By.xpath("//button[text()='Add Element']"));
        addElementButton.click();
    }

    public void deleteElement() {
        WebElement deleteButton = driver.findElement(
                By.xpath("//button[text()='Delete']"));
        deleteButton.click();
    }
    public int getElementsCount() {
        List<WebElement> deleteButtons = driver.findElements(
                By.xpath("//button[text()='Delete']"));
        return deleteButtons.size();
    }

    @Test
    public void addRemoveElementsTest() {
        final int EXPECTED_NUMBER_OF_ELEMENTS = 1;
        addElement();
        addElement();
        deleteElement();
        Assert.assertEquals(getElementsCount(), EXPECTED_NUMBER_OF_ELEMENTS,
                "Number of added elements is different from the expected value");
    }
}

