package com.example.OrderTestSuite;

import java.time.Duration;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class OrderWithLargeQuantityProduct {
  private WebDriver driver;
  private void pause(long ms) {
    try { Thread.sleep(ms); } catch (Exception ignored) {}
  }

  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
  }

  @Test
  public void testOrderWithLargeQuantityProduct() throws Exception {

    driver.get("https://mwc.com.vn/");
    pause(1200);

    driver.findElement(By.xpath("//img[contains(@src,'z7183679343829_8749a5e82df15ca51a87858f6723fc6c.jpg')]")).click();
    pause(1200);

    driver.findElement(By.id("btnAddToCart")).click();
    pause(1200);

    driver.findElement(By.xpath("//div[@id='cart-list-item']/div[3]/a/span")).click();
    pause(1200);

    driver.findElement(By.id("5788-58229")).clear();
    pause(500);
    driver.findElement(By.id("5788-58229")).sendKeys("999");
    pause(1200);

    driver.findElement(By.id("FullName")).clear();
    driver.findElement(By.id("FullName")).sendKeys("dung phan");
    pause(1200);

    driver.findElement(By.id("Phone")).clear();
    driver.findElement(By.id("Phone")).sendKeys("0329676413");
    pause(1200);

    driver.findElement(By.id("Address")).clear();
    driver.findElement(By.id("Address")).sendKeys("thu duc");
    pause(1200);

    new Select(driver.findElement(By.id("provinceOptions"))).selectByVisibleText("TP Hồ Chí Minh");
    pause(1200);

    new Select(driver.findElement(By.id("districtSelect"))).selectByVisibleText("Quận Thủ Đức");
    pause(1200);

    new Select(driver.findElement(By.id("wardSelect"))).selectByVisibleText("Phường Linh Trung");
    pause(1200);

    driver.findElement(By.id("btnDatHang")).click();
    pause(2000);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
}
