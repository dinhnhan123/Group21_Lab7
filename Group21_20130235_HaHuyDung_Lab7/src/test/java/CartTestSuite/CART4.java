package CartTestSuite;

import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CART4 {
    private WebDriver driver;
    private String baseUrl;
    JavascriptExecutor js;

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "https://mwc.com.vn/";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testCART4() throws Exception {
        driver.get(baseUrl);

        WebElement product1 = driver.findElement(By.xpath(
                "//div[@id='home']/section[3]/div/div/div/div/div[2]/div/div/div/a/div/p"
        ));

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", product1);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", product1);
        Thread.sleep(500);

        driver.findElement(By.id("btnAddToCart")).click();
        Thread.sleep(800);

        // Quay lại trang home
        driver.navigate().back();
        Thread.sleep(700);

        // --- Sản phẩm 2 (lấy sản phẩm kế tiếp) ---
        WebElement product2 = driver.findElement(By.xpath(
                "(//div[@id='home']/section[3]/div/div/div/div/div[2]/div/div/div/a/div/p)[2]"
        ));

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", product2);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", product2);
        Thread.sleep(500);

        driver.findElement(By.id("btnAddToCart")).click();
        Thread.sleep(800);


        driver.findElement(By.xpath("//a[contains(@href,'cart')]")).click();
        Thread.sleep(800);
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
