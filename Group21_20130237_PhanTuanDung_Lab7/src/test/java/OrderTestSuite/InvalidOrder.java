package OrderTestSuite;

import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class InvalidOrder {
  private WebDriver driver;
  private void pause(long ms) {
    try { Thread.sleep(ms); } catch (Exception ignored) {}
  }

  @Before
  public void setUp() throws Exception {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
  }

  @Test
  public void testInvalidOrder() throws Exception {

    driver.get("https://mwc.com.vn/");

    WebElement img = driver.findElement(By.xpath(
            "//img[contains(@src,'z7183679343829_8749a5e82df15ca51a87858f6723fc6c.jpg')]"
    ));

// scroll vào giữa màn hình (tránh header che)
    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center'});", img
    );

    Thread.sleep(800);

// dùng JS click để bỏ qua overlay
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", img);



    driver.findElement(By.id("btnAddToCart")).click();
    pause(1200);

    driver.findElement(By.xpath("//div[@id='cart-list-item']/div[3]/a/span")).click();
    pause(1200);

    driver.findElement(By.id("FullName")).clear();
    driver.findElement(By.id("FullName")).sendKeys("dad");
    pause(1000);

    driver.findElement(By.id("Phone")).clear();
    driver.findElement(By.id("Phone")).sendKeys("dada");
    pause(1000);

    driver.findElement(By.id("Address")).clear();
    driver.findElement(By.id("Address")).sendKeys("dada");
    pause(1000);

    new Select(driver.findElement(By.id("provinceOptions"))).selectByVisibleText("Trà Vinh");
    pause(1200);

    new Select(driver.findElement(By.id("districtSelect"))).selectByVisibleText("Huyện Cầu Ngang");
    pause(1200);

    new Select(driver.findElement(By.id("wardSelect"))).selectByVisibleText("Xã Trường Thọ");
    pause(1200);

    driver.findElement(By.id("btnDatHang")).click();
    pause(1500);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
}
