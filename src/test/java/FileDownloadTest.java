import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FileDownloadTest extends BaseTest{
    final By fileLink = By.cssSelector("#content a");

    private boolean waitAndCheckIsFileDownloaded(String fileName) {
        File file = new File(System.getProperty("user.dir"), fileName);
        int time = 0;
        int timeDelta = 500;//ms
        long timeout = TimeUnit.SECONDS.toMillis(30);
        boolean isFileExists;
        do {
            isFileExists = file.exists();
            try {
                Thread.sleep(timeDelta);
            } catch (InterruptedException exc) {

            }
            time += timeDelta;
        }
        while (!isFileExists && (time < timeout));
        return isFileExists;
    }
    public void deleteFileIfExists(String fileName) {
        File file = new File(System.getProperty("user.dir"), fileName);
        if (file.exists()) {
            file.delete();
        }
    }
    private String clickRandomFileLink() {
        List<WebElement> links = driver.findElements(fileLink);
        Random r = new Random();
        WebElement linkToClick = links.get(r.nextInt(links.size() - 1));
        linkToClick.click();
        return linkToClick.getText();
    }

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void navigate() {
        driver.get("http://the-internet.herokuapp.com/download");
    }

    @Test
    public void testDownloadFile() {
        String fileName = clickRandomFileLink();
        boolean isFileDownloaded = waitAndCheckIsFileDownloaded(fileName);
        deleteFileIfExists(fileName);
        Assert.assertTrue(isFileDownloaded, "File was not successfully downloaded");
    }
}
