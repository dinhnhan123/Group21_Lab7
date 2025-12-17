package ProductFiltering;

import java.time.Duration;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class Filter3 {
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
  public void testFilter3() throws Exception {
    driver.get("https://mwc.com.vn/");
    Thread.sleep(2000);

    Actions actions = new Actions(driver);

    // Hover vào menu cha (li[3])
    WebElement menuParent = driver.findElement(
            By.xpath("//div[@id='nav']/nav/ul/li[3]/a")
    );
    actions.moveToElement(menuParent).perform();
    Thread.sleep(800);

    // Click submenu
    WebElement subMenu = driver.findElement(
            By.xpath("//div[@id='nav']/nav/ul/li[3]/ul/li/a")
    );
    subMenu.click();
    Thread.sleep(1500);

    // Click sản phẩm đầu tiên
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement filterBtn = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@id='main']//a/span/span")
            )
    );

// scroll element vào giữa màn hình
    ((JavascriptExecutor)driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", filterBtn
    );

    Thread.sleep(500);
    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", filterBtn
    );

    Thread.sleep(1500);

    // Click filter
    driver.findElement(
            By.xpath("//div[@id='filter-sidebar1']//label")
    ).click();
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
