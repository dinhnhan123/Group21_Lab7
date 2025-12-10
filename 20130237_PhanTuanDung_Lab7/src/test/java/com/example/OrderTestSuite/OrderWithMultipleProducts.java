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

public class OrderWithMultipleProducts {
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
  public void testOrderWithMultipleProducts() throws Exception {
    driver.get("https://mwc.com.vn/");
    driver.findElement(By.xpath("//img[contains(@src,'https://img.mwc.com.vn/giay-thoi-trang?w=640&h=640&FileInput=/Resources/Product/2025/11/03/z7183542718841_536fa746b959671027ff41bdab22498e.jpg')]")).click();
    driver.findElement(By.xpath("//a[@id='btnAddToCart']/i")).click();
    driver.findElement(By.id("mm-blocker")).click();
    driver.findElement(By.xpath("//img[@alt='MWC']")).click();
    driver.findElement(By.xpath("//img[@alt='Dép Cao Gót MWC G250 - Guốc Gót Tròn 5P, Mũi Vuông, Quai Bản Viền Bèo Ren Mềm Mại Phối Nơ Nhỏ Xinh Ngọt Ngào, Nữ Tính.']")).click();
    driver.findElement(By.id("btnAddToCart")).click();
    driver.findElement(By.id("mm-blocker")).click();
    driver.findElement(By.xpath("//div[@id='nav']/nav/ul/li[3]/a")).click();
    driver.findElement(By.xpath("//img[@alt='Giày Thể Thao Nam MWC 5863 - Giày Sneaker Nam, Đế Cao Su Đúc Nguyên Khối Êm Ái, Đi Học, Đi Chơi, Dạo Phố Cool Ngầu.']")).click();
    driver.findElement(By.id("btnAddToCart")).click();
    driver.findElement(By.xpath("//div[@id='cart-list-item']/div[3]/a/span")).click();
    driver.findElement(By.id("FullName")).click();
    driver.findElement(By.id("Phone")).click();
    driver.findElement(By.id("FullName")).click();
    driver.findElement(By.id("FullName")).clear();
    driver.findElement(By.id("FullName")).sendKeys("dung phan");
    driver.findElement(By.id("Phone")).click();
    driver.findElement(By.id("Phone")).clear();
    driver.findElement(By.id("Phone")).sendKeys("0329676413");
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
