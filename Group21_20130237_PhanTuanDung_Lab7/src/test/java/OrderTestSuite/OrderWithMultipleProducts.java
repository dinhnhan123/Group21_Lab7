package OrderTestSuite;

import java.util.regex.Pattern;
import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class OrderWithMultipleProducts {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  private void pause(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
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
  public void testOrderWithMultipleProducts() throws Exception {
    driver.get("https://mwc.com.vn/");
    pause(2000);

    driver.findElement(By.xpath("//img[contains(@src,'z7183542718841')]")).click();
    pause(2000);

    driver.findElement(By.xpath("//a[@id='btnAddToCart']/i")).click();
    pause(2000);

    driver.findElement(By.id("mm-blocker")).click();
    pause(1500);

    driver.findElement(By.xpath("//img[@alt='MWC']")).click();
    pause(2000);

    WebElement product = driver.findElement(By.xpath("//img[contains(@alt, 'G250')]"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product);
    Thread.sleep(800);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", product);

    pause(2000);

    driver.findElement(By.id("btnAddToCart")).click();
    pause(2000);

    driver.findElement(By.id("mm-blocker")).click();
    pause(1500);

    driver.findElement(By.xpath("//div[@id='nav']/nav/ul/li[3]/a")).click();
    pause(2000);

    driver.findElement(By.xpath("//img[contains(@alt, 'MWC 5831')]")).click();
    pause(2000);

    driver.findElement(By.id("btnAddToCart")).click();
    pause(2000);

    driver.findElement(By.xpath("//div[@id='cart-list-item']/div[3]/a/span")).click();
    pause(2000);

    driver.findElement(By.id("FullName")).clear();
    driver.findElement(By.id("FullName")).sendKeys("dung phan");
    pause(800);

    driver.findElement(By.id("Phone")).clear();
    driver.findElement(By.id("Phone")).sendKeys("0329676413");
    pause(800);

    driver.findElement(By.id("Address")).clear();
    driver.findElement(By.id("Address")).sendKeys("thu duc");
    pause(800);

    new Select(driver.findElement(By.id("provinceOptions"))).selectByVisibleText("TP Hồ Chí Minh");
    pause(1000);

    new Select(driver.findElement(By.id("districtSelect"))).selectByVisibleText("Quận Thủ Đức");
    pause(1000);

    new Select(driver.findElement(By.id("wardSelect"))).selectByVisibleText("Phường Linh Trung");
    pause(1000);

    driver.findElement(By.xpath("//button[@id='btnDatHang']/span")).click();
    pause(3000);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
}
