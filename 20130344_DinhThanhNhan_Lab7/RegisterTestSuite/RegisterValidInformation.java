package com.example.RegisterTestSuite;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class RegisterValidInformation {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "");
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testRegisterValidInformation() throws Exception {
    driver.get("https://mwc.com.vn/");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Đế Lót'])[2]/following::*[name()='svg'][2]")).click();
    driver.findElement(By.xpath("//form[@id='form_register']/input")).click();
    driver.findElement(By.xpath("//form[@id='form_register']/input")).clear();
    driver.findElement(By.xpath("//form[@id='form_register']/input")).sendKeys("dinhnhan5678899298");
    driver.findElement(By.id("Phone")).click();
    driver.findElement(By.id("Phone")).click();
    driver.findElement(By.id("Phone")).click();
    driver.findElement(By.id("Phone")).clear();
    driver.findElement(By.id("Phone")).sendKeys("0867764298");
    driver.findElement(By.xpath("//form[@id='form_register']/div/input")).click();
    driver.findElement(By.xpath("//form[@id='form_register']/div/input")).clear();
    driver.findElement(By.xpath("//form[@id='form_register']/div/input")).sendKeys("Dinhthanhnhan12345");
    driver.findElement(By.id("PasswordConfirm")).click();
    driver.findElement(By.id("PasswordConfirm")).clear();
    driver.findElement(By.id("PasswordConfirm")).sendKeys("Dinhthanhnhan12345");
    driver.findElement(By.xpath("//input[@value='Đăng ký']")).click();
    driver.findElement(By.xpath("//span[@id='account-handle']/a/img")).click();
    driver.findElement(By.xpath("//div[@id='main']/section[2]/div/div/div/div[2]/div[7]/div/a/div[2]/span")).click();
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
