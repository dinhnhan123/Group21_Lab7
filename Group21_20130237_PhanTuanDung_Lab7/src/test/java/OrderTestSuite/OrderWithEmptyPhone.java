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

public class OrderWithEmptyPhone {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  private void pause(long ms) {
    try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
  }

  @Before
  public void setUp() throws Exception {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testOrderWithEmptyPhone() throws Exception {

    driver.get("https://mwc.com.vn/products/giay-the-thao-nu-mwc-nutt--a374?&c=kem");
    pause(1500);

    // Add to cart
    WebElement addToCart = driver.findElement(By.id("btnAddToCart"));
    js.executeScript("arguments[0].scrollIntoView({block:'center'});", addToCart);
    pause(500);
    js.executeScript("arguments[0].click();", addToCart);
    pause(1500);

    // Vào trang cart
    driver.get("https://mwc.com.vn/cart");
    pause(1500);

    // Nhập FullName
    WebElement fullName = driver.findElement(By.id("FullName"));
    js.executeScript("arguments[0].scrollIntoView({block:'center'});", fullName);
    pause(500);
    fullName.clear();
    fullName.sendKeys("dung phan");
    pause(800);

    // KHÔNG nhập Phone (theo test case)

    // Nhập Address
    WebElement address = driver.findElement(By.id("Address"));
    address.clear();
    address.sendKeys("thu duc");
    pause(800);

    // Province
    new Select(driver.findElement(By.id("provinceOptions")))
            .selectByVisibleText("TP Hồ Chí Minh");
    pause(800);

    // District
    new Select(driver.findElement(By.id("districtSelect")))
            .selectByVisibleText("Quận Thủ Đức");
    pause(800);

    // Ward
    new Select(driver.findElement(By.id("wardSelect")))
            .selectByVisibleText("Phường Linh Trung");
    pause(800);

    // Đặt hàng
    WebElement orderBtn = driver.findElement(By.id("btnDatHang"));
    js.executeScript("arguments[0].scrollIntoView({block:'center'});", orderBtn);
    pause(500);
    js.executeScript("arguments[0].click();", orderBtn);
    pause(1500);

    // Verify message
    Assert.assertTrue(
            driver.getPageSource().contains("Bạn chưa nhập thông tin nhận hàng!")
    );
  }


  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
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
