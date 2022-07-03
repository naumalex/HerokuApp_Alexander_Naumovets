import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class SortableDataTablesTest extends BaseTest {

    @BeforeMethod
    public void navigate() {
        driver.get("http://the-internet.herokuapp.com/tables");
    }

    public String getValueFromCell(int row, int column) {
        WebElement cell = driver.findElement(
                By.xpath(String.format("//table//tr[%d]//td[%d]", row, column)));
        return cell.getText();
    }

    public void sortTable(int column /*String columnName*/) {
       WebElement columnHeader = driver.findElement(
                By.xpath(String.format("//table[1]//th[%d]", column)));
        Actions actions = new Actions(driver);
        actions.moveToElement(columnHeader).click().build().perform();
    }

    @Test
    public void sortableTablesTest() {
        sortTable(2);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Assert.assertEquals(getValueFromCell(2, 1), "Doe",
                "Incorrect value in the cell (2, 1)");
        Assert.assertEquals(getValueFromCell(4, 3), "tconway@earthlink.net",
                "Incorrect value in the cell (4, 3)");
        Assert.assertEquals(getValueFromCell(3, 1), "Smith",
                "Incorrect value in the cell (3, 1)");
    }
}