package com.example.ProductDetailTestSuite;

import com.example.core.BaseTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test suite Product Detail
 *
 *  - [PD-1] Kiểm tra trang chi tiết sản phẩm hiển thị đầy đủ thông tin
 *  - [PD-2] Kiểm tra hiển thị nhiều hình ảnh (thumbnail -> ảnh lớn)
 *  - [PD-6] Thêm sản phẩm vào giỏ hàng thành công
 *
 * Lưu ý:
 *  - Nếu một thành phần UI không tồn tại trên trang => mặc định test PASS (return sớm).
 */
public class ProductDetailTests extends BaseTest {

    // URL sản phẩm cụ thể để test – có thể đổi sang sản phẩm khác nếu cần
    private static final String PRODUCT_DETAIL_URL =
            "https://mwc.com.vn/products/giay-cao-got-mwc-nucg--g280?c=nau";

    /**
     * Helper tạo WebDriverWait 10s
     * (không dùng tên wait() để tránh conflict với Object.wait()).
     */
    private WebDriverWait shortWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Helper check element có tồn tại trong DOM hay không.
     */
    private boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    /**
     * [PD-1] Kiểm tra trang chi tiết sản phẩm hiển thị đầy đủ thông tin:
     *  - Tên sản phẩm
     *  - Giá
     *  - Màu
     *  - Kích thước
     *  - Tab "Chi tiết sản phẩm"
     */
    @Test
    public void PD1_productDetail_shows_basic_info() {
        driver.get(PRODUCT_DETAIL_URL);

        // Đợi tên sản phẩm xuất hiện
        shortWait().until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.product-name"))
        );

        // Title sản phẩm
        WebElement title = driver.findElement(By.cssSelector("h1.product-name"));
        assertTrue("Title sản phẩm không hiển thị", title.isDisplayed());

        // Giá – lấy span có class .product-grid-price-new-text
        if (!isElementPresent(By.cssSelector(".product-grid-price-new-text"))) {
            // Không có block giá -> mặc định pass phần kiểm tra giá
        } else {
            WebElement price = driver.findElement(By.cssSelector(".product-grid-price-new-text"));
            assertTrue("Giá sản phẩm không hiển thị", price.isDisplayed());
        }

        // Section "Màu"
        if (!isElementPresent(By.xpath("//*[normalize-space(text())='Màu']"))) {
            // Trang không có lựa chọn màu -> coi như pass phần này
        } else {
            WebElement colorSection = driver.findElement(
                    By.xpath("//*[normalize-space(text())='Màu']")
            );
            assertTrue("Section Màu không hiển thị", colorSection.isDisplayed());
        }

        // Section "Kích thước" hoặc "Size"
        if (!isElementPresent(By.xpath("//*[normalize-space(text())='Kích thước' or contains(normalize-space(text()),'Size')]"))) {
            // Không có lựa chọn size -> coi như pass
        } else {
            WebElement sizeSection = driver.findElement(
                    By.xpath("//*[normalize-space(text())='Kích thước' or contains(normalize-space(text()),'Size')]")
            );
            assertTrue("Section Kích thước/Size không hiển thị", sizeSection.isDisplayed());
        }

        // Tab "Chi tiết sản phẩm"
        if (!isElementPresent(By.xpath("//button[contains(normalize-space(text()),'Chi tiết sản phẩm')]"))) {
            // Trang không dùng tab này -> coi như pass
        } else {
            WebElement detailTab = driver.findElement(
                    By.xpath("//button[contains(normalize-space(text()),'Chi tiết sản phẩm')]")
            );
            assertTrue("Tab Chi tiết sản phẩm không hiển thị", detailTab.isDisplayed());
        }
    }

    /**
     * [PD-2] Kiểm tra hiển thị nhiều hình ảnh sản phẩm:
     *  - Click thumbnail thứ 2
     *  - Ảnh lớn thay đổi (src khác).
     *
     * Nếu trang không có đủ thumbnail / gallery -> test tự pass (return).
     */
    @Test
    public void PD2_change_main_image_when_click_thumbnail() {
        driver.get(PRODUCT_DETAIL_URL);

        // Đợi vùng gallery load
        shortWait().until(
                ExpectedConditions.presenceOfElementLocated(By.id("product-detail-thumb"))
        );

        // Lấy tất cả ảnh trong khung gallery
        List<WebElement> galleryImages =
                driver.findElements(By.cssSelector("#product-detail-thumb img"));

        // Nếu không có hoặc < 2 ảnh => theo rule "không có thì coi như pass"
        if (galleryImages.size() < 2) {
            System.out.println("[PD-2] Không đủ thumbnail trong gallery -> bỏ qua, coi như PASS.");
            return;
        }

        WebElement mainImage = galleryImages.get(0);
        String firstSrc = mainImage.getAttribute("src");

        // Thumbnail thứ 2
        WebElement secondThumb = galleryImages.get(1);
        secondThumb.click();

        // Chờ src ảnh lớn thay đổi
        shortWait().until(driver ->
                !mainImage.getAttribute("src").equalsIgnoreCase(firstSrc)
        );

        String changedSrc = mainImage.getAttribute("src");
        assertTrue("Ảnh lớn không đổi khi click thumbnail",
                !firstSrc.equalsIgnoreCase(changedSrc));
    }

    /**
     * [PD-6] Thêm sản phẩm vào giỏ hàng thành công:
     *  - Chọn màu + size hợp lệ (nếu có)
     *  - Nhấn "Thêm vào giỏ hàng"
     *  - Mở giỏ hàng, kiểm tra không còn dòng "Giỏ hàng trống!"
     *
     * Nếu trang không có color/size section thì bỏ qua bước chọn (vẫn test add to cart).
     */
    @Test
    public void PD6_add_to_cart_success() {
        driver.get(PRODUCT_DETAIL_URL);

        // Chọn màu đầu tiên (nếu có)
        if (isElementPresent(By.cssSelector("#colorOptions .product-option-item"))) {
            WebElement firstColor = driver.findElement(
                    By.cssSelector("#colorOptions .product-option-item")
            );
            firstColor.click();
        }

        // Chọn size đầu tiên (nếu có)
        if (isElementPresent(By.cssSelector("#sizeOptions .product-option-item"))) {
            WebElement firstSize = driver.findElement(
                    By.cssSelector("#sizeOptions .product-option-item")
            );
            firstSize.click();
        }

        // Nút "Thêm vào giỏ hàng" (desktop)
        By addToCartLocator = By.id("btnAddToCart");

        // Nếu không có nút desktop, thử dùng locator theo text (mobile / bản khác)
        if (!isElementPresent(addToCartLocator)) {
            addToCartLocator = By.xpath(
                    "//*[normalize-space(text())='Thêm vào giỏ hàng' or normalize-space(text())='Thêm vào giỏ']"
            );
        }

        if (!isElementPresent(addToCartLocator)) {
            // Trang không có nút "Thêm vào giỏ hàng" -> theo rule coi như PASS
            System.out.println("[PD-6] Không tìm thấy nút 'Thêm vào giỏ hàng' -> bỏ qua, coi như PASS.");
            return;
        }

        WebElement addToCartBtn = driver.findElement(addToCartLocator);
        addToCartBtn.click();

        // Mở giỏ hàng ở header (icon có id site-cart-handle)
        if (isElementPresent(By.id("site-cart-handle"))) {
            WebElement cartIcon = driver.findElement(By.id("site-cart-handle"));
            cartIcon.click();
        }

        // Chờ sidebar-cart hiển thị
        if (isElementPresent(By.id("sidebar-cart"))) {
            shortWait().until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("sidebar-cart"))
            );
        }

        // Chờ tới khi text "Giỏ hàng trống!" biến mất (hoặc không còn trong DOM)
        boolean cartNotEmpty = shortWait().until(driver ->
                driver.findElements(By.xpath("//*[contains(text(),'Giỏ hàng trống')]")).isEmpty()
        );

        assertTrue("Giỏ hàng vẫn trống sau khi thêm sản phẩm", cartNotEmpty);

        // Đồng thời verify thêm là trong DOM có ít nhất 1 item sản phẩm nếu cấu trúc hỗ trợ
        // (optional – nếu không có cấu trúc item cụ thể thì đoạn này vẫn pass).
        if (isElementPresent(By.id("cart-list-item"))) {
            WebElement cartList = driver.findElement(By.id("cart-list-item"));
            String cartHtml = cartList.getText();
            assertFalse("Nội dung giỏ hàng vẫn hiển thị 'Giỏ hàng trống!'",
                    cartHtml != null && cartHtml.contains("Giỏ hàng trống"));
        }
    }
}
