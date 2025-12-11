package com.example.OrderTestSuite;

import java.util.regex.Pattern;
import java.time.Duration;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class OrderWithEmptyName {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;

  // Hàm pause
  private void pause(long ms) {
    try { Thread.sleep(ms); } catch (Exception ignored) {}
  }

  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testOrderWithEmptyName() throws Exception {

    driver.get("https://mwc.com.vn/products/giay-the-thao-nu-mwc-nutt--a374?&c=kem");
    pause(1200);

    driver.findElement(By.id("btnAddToCart")).click();
    pause(1200);

    driver.findElement(By.xpath("//div[@id='cart-list-item']/div[3]/a/span")).click();
    pause(1200);

    driver.get("https://mwc.com.vn/cart");
    pause(1200);

    driver.findElement(By.id("Phone")).click();
    pause(300);

    driver.findElement(By.id("Phone")).clear();
    pause(300);

    driver.findElement(By.id("Phone")).sendKeys("0329676413");
    pause(1200);

    driver.findElement(By.id("Address")).click();
    pause(300);

    driver.findElement(By.id("Address")).clear();
    pause(300);

    driver.findElement(By.id("Address")).sendKeys("thủ đức");
    pause(1200);

    driver.findElement(By.id("provinceOptions")).click();
    pause(600);

    new Select(driver.findElement(By.id("provinceOptions"))).selectByVisibleText("TP Hồ Chí Minh");
    pause(1200);

    driver.findElement(By.id("districtSelect")).click();
    pause(600);

    new Select(driver.findElement(By.id("districtSelect"))).selectByVisibleText("Quận Thủ Đức");
    pause(1200);

    driver.findElement(By.id("info-input")).click();
    pause(600);

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
