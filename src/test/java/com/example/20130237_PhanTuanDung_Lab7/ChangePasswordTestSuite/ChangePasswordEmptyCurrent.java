import java.util.regex.Pattern;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;

public class ChangePasswordEmptyCurrent {
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
  public void testChangePasswordEmptyCurrent() throws Exception {
    driver.get("https://mwc.com.vn/");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Đế Lót'])[2]/following::*[name()='svg'][2]")).click();
    driver.findElement(By.id("UserName")).clear();
    driver.findElement(By.id("UserName")).sendKeys("opaki1000");
    driver.findElement(By.id("Password")).clear();
    driver.findElement(By.id("Password")).sendKeys("opaki1000");
    driver.findElement(By.id("form_login")).submit();
    driver.get("https://mwc.com.vn/profile");
    driver.findElement(By.xpath("//a[@id='password']/span")).click();
    driver.get("https://mwc.com.vn/password");
    driver.findElement(By.id("PasswordNew")).click();
    driver.findElement(By.id("PasswordNew")).clear();
    driver.findElement(By.id("PasswordNew")).sendKeys("opaki921");
    driver.findElement(By.id("PasswordNewConfirm")).clear();
    driver.findElement(By.id("PasswordNewConfirm")).sendKeys("opaki921");
    driver.findElement(By.xpath("//div[@id='main']/section[2]/div/div/div[2]/div/form/div/div[2]/div[4]/div[2]/button")).click();
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
