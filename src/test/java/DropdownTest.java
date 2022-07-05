import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DropdownTest extends BaseTest{
    @BeforeMethod
    public void navigate() { driver.get("http://the-internet.herokuapp.com/dropdown"); }

    @Test
    public void selectDropdownTest() {
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        List<String> expectedOptions = new ArrayList<>();
        expectedOptions.add("Option 1");
        expectedOptions.add("Option 2");
        Select select = new Select(dropdownElement);
        List<String> actualOptions = select.getOptions().stream().
                map(s -> s.getText()).toList();
        boolean isAllExpectedOptionsExist = actualOptions.containsAll(expectedOptions);
        Assert.assertTrue(isAllExpectedOptionsExist,
                "Not all the options exists in the dropdown");
        select.selectByVisibleText("Option 1");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),
                "Option 1", "First item is not selected");
        select.selectByVisibleText("Option 2");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),
                "Option 2", "Second item is not selected");
    }

}
