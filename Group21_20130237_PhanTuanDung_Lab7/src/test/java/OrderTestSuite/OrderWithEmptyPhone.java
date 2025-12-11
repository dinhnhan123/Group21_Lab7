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
    driver.get("https://mwc.com.vn/");
    pause(1200);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement img = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//img[contains(@src,'z7183679343829_8749a5e82df15ca51a87858f6723fc6c.jpg')]")
    ));

// Scroll vào giữa màn hình
    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center'});", img
    );

    Thread.sleep(800); // chờ animation load hình

// Click bằng JS (bỏ qua việc bị chắn)
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", img);

    Thread.sleep(1200);


    driver.findElement(By.id("btnAddToCart")).click();
    pause(1200);

    driver.findElement(By.xpath("//div[@id='cart-list-item']/div[3]/a/span")).click();


    WebElement fullName = driver.findElement(By.id("FullName"));
    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");
    Thread.sleep(500);
    fullName.click();


    driver.findElement(By.id("FullName")).clear();
    pause(300);
    driver.findElement(By.id("FullName")).sendKeys("dung phan");
    pause(1200);

    // Không nhập phone (theo test)
    driver.findElement(By.id("Address")).click();
    pause(300);
    driver.findElement(By.id("Address")).click();
    pause(300);
    driver.findElement(By.id("Address")).clear();
    pause(300);
    driver.findElement(By.id("Address")).sendKeys("thu duc");
    pause(1200);

    driver.findElement(By.id("provinceOptions")).click();
    pause(600);
    new Select(driver.findElement(By.id("provinceOptions"))).selectByVisibleText("TP Hồ Chí Minh");
    pause(1200);

    driver.findElement(By.id("districtSelect")).click();
    pause(600);
    new Select(driver.findElement(By.id("districtSelect"))).selectByVisibleText("Quận Thủ Đức");
    pause(1200);

    driver.findElement(By.id("wardSelect")).click();
    pause(600);
    new Select(driver.findElement(By.id("wardSelect"))).selectByVisibleText("Phường Linh Trung");
    pause(1200);

    driver.findElement(By.xpath("//button[@id='btnDatHang']/span")).click();
    pause(1500);

    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bạn chưa nhập thông tin nhận hàng!'])[1]/following::button[1]")).click();
    pause(1000);
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
