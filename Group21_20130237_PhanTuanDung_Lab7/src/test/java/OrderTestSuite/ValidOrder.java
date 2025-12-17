package OrderTestSuite;

import java.util.regex.Pattern;
import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class ValidOrder {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;

  @Before
  public void setUp() throws Exception {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testValidOrder() throws Exception {
    driver.get("https://mwc.com.vn/");
    pause(2000); // nhìn trang chủ

    WebElement firstProduct = driver.findElement(
            By.xpath("//a[contains(@href,'/products/')][1]")
    );

    js.executeScript("arguments[0].scrollIntoView({block:'center'});", firstProduct);
    pause(800);
    js.executeScript("arguments[0].click();", firstProduct); // nhìn sản phẩm

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    WebElement addToCart = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("btnAddToCart"))
    );
    addToCart.click();
    pause(1500);

    driver.findElement(By.xpath("//div[@id='cart-list-item']/div[3]/a/span")).click();
    pause(1500); // mở giỏ hàng

    driver.findElement(By.id("FullName")).clear();
    driver.findElement(By.id("FullName")).sendKeys("dung phan");
    pause(800);

    driver.findElement(By.id("Phone")).clear();
    driver.findElement(By.id("Phone")).sendKeys("0329676413");
    pause(800);

    driver.findElement(By.id("Address")).clear();
    driver.findElement(By.id("Address")).sendKeys("linh trung thu duc");
    pause(800);

    new Select(driver.findElement(By.id("provinceOptions"))).selectByVisibleText("TP Hồ Chí Minh");
    pause(1000);

    new Select(driver.findElement(By.id("districtSelect"))).selectByVisibleText("Quận Thủ Đức");
    pause(1000);

    new Select(driver.findElement(By.id("wardSelect"))).selectByVisibleText("Phường Linh Trung");
    pause(1000);

    driver.findElement(By.xpath("//button[@id='btnDatHang']/span")).click();
    pause(2000); // xem kết quả đặt hàng
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  // ====== Thêm HÀM LÀM CHẬM ======
  private void pause(long ms) {
    try { Thread.sleep(ms); } catch (Exception e) {}
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
