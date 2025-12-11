import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.io.File;
import static org.junit.Assert.fail;

public class Info1 {
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
    //noinspection Since15
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testInfo1() throws Exception {
    driver.get("https://mwc.com.vn/");

    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Đế Lót'])[2]/following::*[name()='svg'][2]")).click();
    driver.findElement(By.id("UserName")).click();
    driver.findElement(By.id("UserName")).clear();
    driver.findElement(By.id("UserName")).sendKeys("myhanh26");
    driver.findElement(By.id("Password")).click();
    driver.findElement(By.id("Password")).clear();
    driver.findElement(By.id("Password")).sendKeys("123@1234");
    driver.findElement(By.xpath("//input[@value='Đăng nhập']")).click();
    driver.findElement(By.xpath("//span[@id='account-handle']/a/img")).click();
    WebElement loginButton = driver.findElement(
            By.xpath("//form[@id='form_login']/div[2]/div/div[11]/button")
    );

// Đóng popup nếu có
    try {
      driver.findElement(By.cssSelector(".modal-dialog .btn-close")).click();
      Thread.sleep(300);
    } catch (Exception ignored) {}

// Scroll vào giữa màn hình
    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", loginButton);
    Thread.sleep(500);


    js.executeScript("arguments[0].click();", loginButton);

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
