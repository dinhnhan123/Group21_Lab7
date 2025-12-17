package OrderTestSuite;

import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class OrderWithLargeQuantityProduct {
  private WebDriver driver;
  JavascriptExecutor js;
  private void pause(long ms) {
    try { Thread.sleep(ms); } catch (Exception ignored) {}
  }

  @Before
  public void setUp() throws Exception {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
     js = (JavascriptExecutor) driver;
  }

  @Test
  public void testOrderWithLargeQuantityProduct() throws Exception {

    driver.get("https://mwc.com.vn/");
    pause(1200);

    WebElement product = driver.findElement(By.xpath(
            "//a[contains(@href,'/products')]"
    ));

    ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView({block:'center'});", product);
    Thread.sleep(500);

    ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", product);
    pause(1200);

    driver.findElement(By.id("btnAddToCart")).click();
    pause(1200);

    driver.findElement(By.xpath("//div[@id='cart-list-item']//a/span")).click();
    pause(1200);

    JavascriptExecutor js = (JavascriptExecutor) driver;

    By qtyInput = By.cssSelector("input.cart-item--quantity-actions--input");

    WebElement quantity = driver.findElement(qtyInput);

    js.executeScript(
            "arguments[0].value='999'; arguments[0].dispatchEvent(new Event('change'));",
            quantity
    );

    pause(1500);

    driver.findElement(By.id("FullName")).sendKeys("Dung Phan");
    driver.findElement(By.id("Phone")).sendKeys("0329676413");
    driver.findElement(By.id("Address")).sendKeys("Thu Duc");

    new Select(driver.findElement(By.id("provinceOptions")))
            .selectByVisibleText("TP Hồ Chí Minh");
    pause(800);

    new Select(driver.findElement(By.id("districtSelect")))
            .selectByVisibleText("Quận Thủ Đức");
    pause(800);

    new Select(driver.findElement(By.id("wardSelect")))
            .selectByVisibleText("Phường Linh Trung");
    pause(800);

    driver.findElement(By.id("btnDatHang")).click();
    pause(2000);
  }



  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
}
