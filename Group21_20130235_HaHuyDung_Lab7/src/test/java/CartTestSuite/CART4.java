package CartTestSuite;

import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CART4 {
    private WebDriver driver;
    private WebDriverWait wait;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }

    @Test
    public void testCART4() {
        driver.get("https://mwc.com.vn/");

        // ===== SẢN PHẨM 1 =====
        By product1 = By.xpath(
                "(//div[@id='home']/section[3]//a/div/p)[1]"
        );

        WebElement p1 = wait.until(ExpectedConditions.elementToBeClickable(product1));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", p1);
        js.executeScript("arguments[0].click();", p1);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAddToCart"))).click();

        // Quay lại home (DOM reload)
        driver.navigate().back();

        // ===== SẢN PHẨM 2 =====
        By product2 = By.xpath(
                "(//div[@id='home']/section[3]//a/div/p)[2]"
        );

        WebElement p2 = wait.until(ExpectedConditions.elementToBeClickable(product2));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", p2);
        p2.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAddToCart"))).click();

        // ===== CLICK CART (FIX STALE) =====
        By cartBtn = By.xpath("//a[contains(@href,'cart')]");
        wait.until(ExpectedConditions.elementToBeClickable(cartBtn)).click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
