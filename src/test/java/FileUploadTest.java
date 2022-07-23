import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class FileUploadTest extends BaseTest {
    private final String ABSOLUTE_PATH_TO_FILE_TO_UPLOAD = System.getProperty("user.dir")
            + "/src/main/resources/planet.jpg";
    private final By chooseFileInputLocator = By.cssSelector("#file-upload");
    private final By uploadFileButtonLocator = By.cssSelector("#file-submit");

    private final By fileUploadedMessageLocator = By.xpath(
            "//div[@id = 'content']//h3[text() = 'File Uploaded!']");

    private final By uploadedFilesLocator = By.cssSelector("#uploaded-files");

    public void setFileToUpload(String fileAbsolutePath) {
        WebElement chooseFileInput = driver.findElement(chooseFileInputLocator);
        chooseFileInput.sendKeys(fileAbsolutePath);
    }

    public void clickUploadButton() {
        driver.findElement(uploadFileButtonLocator).click();
    }

    private String getFilename(String absolutePath) {
        return Paths.get(absolutePath).getFileName().toString();
    }

    private String getDisplayedUploadedFileName() {
        return driver.findElement(uploadedFilesLocator).getText();
    }
    @BeforeMethod
    public void navigate() {
        driver.get("https://the-internet.herokuapp.com/upload");
    }

    @Test
    public void testUploadFile() {
        setFileToUpload(ABSOLUTE_PATH_TO_FILE_TO_UPLOAD);
        clickUploadButton();
        waitUntilMessage(fileUploadedMessageLocator);
        Assert.assertEquals(getDisplayedUploadedFileName(),
                getFilename(ABSOLUTE_PATH_TO_FILE_TO_UPLOAD),
                "Displayed uploaded file name does not match the file name " +
                        "that was actually uploaded");
    }
}
