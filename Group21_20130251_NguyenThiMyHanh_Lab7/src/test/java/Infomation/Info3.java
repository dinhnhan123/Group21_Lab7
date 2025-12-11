package Infomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Info3 {
  private WebDriver driver;
  JavascriptExecutor js;

  @Before
  public void setUp() throws Exception {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testInfo3() throws Exception {
    driver.get("https://mwc.com.vn/");

    // Mở login form
    driver.findElement(By.xpath("(.//*[normalize-space(text())='Đế Lót'])[2]/following::*[name()='svg'][2]")).click();

    // Nhập username
    driver.findElement(By.id("UserName")).clear();
    driver.findElement(By.id("UserName")).sendKeys("myhanh26");

    // Nhập password
    driver.findElement(By.id("Password")).clear();
    driver.findElement(By.id("Password")).sendKeys("123@1234");

    // Click Đăng nhập
    driver.findElement(By.xpath("//input[@value='Đăng nhập']")).click();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

    // ⛔ Chờ toast message biến mất
    try {
      wait.until(ExpectedConditions.invisibilityOfElementLocated(
              By.className("jq-toast-single")
      ));
    } catch (Exception ignored) {}

    // 🔥 Click icon account SAFELY
    WebElement accountIcon = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[@id='account-handle']/a/img")
    ));
    accountIcon.click();

    // ⛔ Chờ modal close nếu còn
    try {
      wait.until(ExpectedConditions.invisibilityOfElementLocated(
              By.cssSelector(".modal-dialog")
      ));
    } catch (Exception ignored) {}

    // Mở form sửa thông tin
    driver.findElement(By.xpath("//form[@id='form_login']/div[2]/div/div[4]/div")).click();

    // Xóa số điện thoại
    WebElement phone = driver.findElement(By.id("Phone"));
    phone.clear();
    phone.sendKeys("");

    // Bấm Lưu
    WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(10));

    By loginBtn = By.xpath("//form[@id='form_login']/div[2]/div/div[11]/button");

// Đóng popup nếu có
    try {
      WebElement closePopup = wait.until(
              ExpectedConditions.elementToBeClickable(By.cssSelector(".modal-dialog .btn-close"))
      );
      closePopup.click();
      Thread.sleep(300);
    } catch (Exception ignored) {}

// Scroll vào đúng nút
    WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(loginBtn));
    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
    Thread.sleep(300);

// Chờ cho nút thực sự click được
    wait.until(ExpectedConditions.elementToBeClickable(btn));

// Bấm bằng JS để bỏ qua overlay
    js.executeScript("arguments[0].click();", btn);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
}
