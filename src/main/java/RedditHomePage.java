import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class RedditHomePage {

    public static AppiumDriver driver;
    @BeforeSuite
    public static void config() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    private static final By skipButton = By.xpath("//*[@text='Skip']");
    private static final By searchButton = new MobileBy.ByAccessibilityId("Search");
    private static final By searchField = new By.ById("com.reddit.frontpage:id/search");
    private static final By bankingResult = By.xpath("//*[@text='r/Banking']");
//    private static final By searchForResult = By.xpath("//*[starts-with(@text, 'Search for')]");
    private static final By hotPosts = By.xpath("//*[@text='HOT POSTS']");
    private static final By sort = By.xpath("//*[@resource-id='com.reddit.frontpage:id/sort_description']");
    private static final By hotOption = By.xpath("//*[@text='Hot']");

    static WebElement findElement(By element) {
        return driver.findElement(element);
    }

    public void openRedditApp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "sdk_gphone_x86");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "11");

        capabilities.setCapability("appPackage", "com.reddit.frontpage");
        capabilities.setCapability("appActivity", "com.reddit.launch.main.MainActivity");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, capabilities);
    }

    public void clickSkipLogin() {
        if (findElement(skipButton).isDisplayed()) {
            findElement(skipButton).click();
        }
    }

    public void searchForBanking() {
        findElement(searchButton).click();
        findElement(searchField).sendKeys("Banking");

        WebDriverWait wait = new WebDriverWait(RedditHomePage.driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(bankingResult));

        findElement(bankingResult).click();
    }

    // By Default Posts are sorted by hot
//    public void sortByHot() {
//        if (!findElement(hotPosts).isDisplayed()) {
//            findElement(sort).click();
//            findElement(hotOption);
//        }
//    }

}

