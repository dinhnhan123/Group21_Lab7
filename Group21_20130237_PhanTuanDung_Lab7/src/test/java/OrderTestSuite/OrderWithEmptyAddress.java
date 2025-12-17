package OrderTestSuite;

import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class OrderWithEmptyAddress {
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
  public void testOrderWithEmptyAddress() throws Exception {

    driver.get("https://mwc.com.vn/");
    pause(1200);

    WebElement img = driver.findElement(
            By.xpath("//div[@id='home']//a[.//p][1]")
    );


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
    driver.findElement(By.id("FullName")).sendKeys("dung phan");
    pause(1200);

    driver.findElement(By.id("Phone")).clear();
    driver.findElement(By.id("Phone")).sendKeys("0329676413");
    pause(1200);

    driver.findElement(By.id("Address")).clear();
    driver.findElement(By.id("Address")).sendKeys("dâdada");
    pause(1200);

    new Select(driver.findElement(By.id("provinceOptions"))).selectByVisibleText("Bạc Liêu");
    pause(1200);

    driver.findElement(By.id("districtSelect")).click();
    pause(1200);

    // Chọn lại "-- Chọn tỉnh --"
    new Select(driver.findElement(By.id("provinceOptions"))).selectByVisibleText("-- Chọn tỉnh --");
    pause(1200);

    driver.findElement(By.id("btnDatHang")).click();
    pause(1500);

    // Bấm nút OK popup lỗi
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bạn chưa nhập thông tin nhận hàng!'])[1]/following::button[1]")).click();
    pause(1500);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
}
