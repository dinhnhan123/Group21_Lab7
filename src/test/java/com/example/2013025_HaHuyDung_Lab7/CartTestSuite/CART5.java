import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class CART5 {
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
    public void testCART5() throws Exception {
        driver.get(baseUrl);

        // Lấy 1 sản phẩm
        WebElement product = driver.findElement(By.xpath(
                "//div[@id='home']/section[3]/div/div/div/div/div[2]/div/div/div/a/div/p"
        ));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", product);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", product);
        Thread.sleep(500);

        driver.findElement(By.id("btnAddToCart")).click();
        Thread.sleep(800);

        // Vào giỏ hàng
        driver.findElement(By.xpath("//a[contains(@href,'cart')]")).click();
        Thread.sleep(800);

        int beforeRefresh = driver.findElements(By.cssSelector(".cart_item")).size();

        // Refresh trang
        driver.navigate().refresh();
        Thread.sleep(1000);

        int afterRefresh = driver.findElements(By.cssSelector(".cart_item")).size();
        Assert.assertEquals("Giỏ hàng bị reset sau khi refresh!", beforeRefresh, afterRefresh);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
