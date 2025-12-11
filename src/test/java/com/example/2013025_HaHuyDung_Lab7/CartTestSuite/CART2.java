import java.time.Duration;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class CART2 {
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
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(55));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testCART2() throws Exception {
    driver.get("https://mwc.com.vn/");
    WebElement element = driver.findElement(
            By.xpath("//div[@id='home']/section[3]/div/div/div/div/div[2]/div/div/div/a/div/p")
    );

    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    Thread.sleep(500);

    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    driver.findElement(By.id("btnAddToCart")).click();
    driver.findElement(By.xpath("//div[@id='cart-list-item']/div[3]/a/span")).click();
    driver.findElement(By.xpath("//span[@id='site-cart-handle']/a/img")).click();
    driver.findElement(By.xpath("//div[@id='cart-list-item']/div[3]/a/span")).click();
    driver.findElement(By.xpath("//div[@id='cart-list']/div[2]/div/div/div/div[5]/span")).click();
    Thread.sleep(1000);
    driver.findElement(By.cssSelector("button[onclick*='increase'][onclick*=', 1)']")).click();
    Thread.sleep(500);
    driver.findElement(By.cssSelector("button[onclick*='increase'][onclick*='-1)']")).click();
    Thread.sleep(500);
    driver.findElement(By.cssSelector("button[onclick*='increase'][onclick*='-1)']")).click();

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
