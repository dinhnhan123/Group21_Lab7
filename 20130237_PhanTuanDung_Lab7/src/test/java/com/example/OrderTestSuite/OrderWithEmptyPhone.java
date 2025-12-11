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

public class OrderWithEmptyPhone {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testOrderWithEmptyPhone() throws Exception {
    driver.get("https://mwc.com.vn/");
    driver.findElement(By.xpath("//img[contains(@src,'https://img.mwc.com.vn/giay-thoi-trang?w=640&h=640&FileInput=/Resources/Product/2025/11/03/z7183679343829_8749a5e82df15ca51a87858f6723fc6c.jpg')]")).click();
    driver.findElement(By.id("btnAddToCart")).click();
    driver.findElement(By.xpath("//a[@onclick=\"removecart('58799')\"]")).click();
    driver.findElement(By.xpath("//div[@id='cart-list-item']/div[3]/a/span")).click();
    driver.findElement(By.id("FullName")).click();
    driver.findElement(By.id("FullName")).clear();
    driver.findElement(By.id("FullName")).sendKeys("dung phan");
    driver.findElement(By.id("Address")).click();
    driver.findElement(By.id("Address")).click();
    driver.findElement(By.id("Address")).clear();
    driver.findElement(By.id("Address")).sendKeys("thu duc");
    driver.findElement(By.id("provinceOptions")).click();
    new Select(driver.findElement(By.id("provinceOptions"))).selectByVisibleText("TP Hồ Chí Minh");
    driver.findElement(By.id("districtSelect")).click();
    new Select(driver.findElement(By.id("districtSelect"))).selectByVisibleText("Quận Thủ Đức");
    driver.findElement(By.id("wardSelect")).click();
    new Select(driver.findElement(By.id("wardSelect"))).selectByVisibleText("Phường Linh Trung");
    driver.findElement(By.xpath("//button[@id='btnDatHang']/span")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bạn chưa nhập thông tin nhận hàng!'])[1]/following::button[1]")).click();
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
