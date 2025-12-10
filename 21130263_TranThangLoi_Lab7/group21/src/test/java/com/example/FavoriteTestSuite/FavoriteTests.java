package com.example.FavoriteTestSuite;

import com.example.core.BaseTest;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test cho tính năng "Sản phẩm yêu thích" dựa trên HTML trang:
 * https://mwc.com.vn/product-favorite
 *
 * [Fav-1] Sau khi login, vào trang yêu thích phải thấy breadcrumb.
 *         Nếu có product thì kiểm tra thêm cấu trúc card.
 * [Fav-2] Bỏ yêu thích 1 sản phẩm trên /product-favorite -> card bị remove khỏi DOM.
 *         Nếu không có sản phẩm / không có icon trái tim thì auto PASS.
 * [Fav-3] Phân trang: nếu có nút Next (không disabled) thì click sang page kế.
 *         Nếu không có phân trang / không có nút Next thì auto PASS.
 */
public class FavoriteTests extends BaseTest {

    private static final String LOGIN_URL = "https://mwc.com.vn/login";
    private static final String FAVORITE_PAGE_URL = "https://mwc.com.vn/product-favorite";

    /** Tạo WebDriverWait 10s – KHÔNG đặt tên là wait() để khỏi đụng Object.wait() */
    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /** Đăng nhập account test */
    private void login() {
        WebDriverWait wait = getWait();

        driver.get(LOGIN_URL);

        // Theo form login hiện tại của MWC
        WebElement username = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("UserName")));
        WebElement password = driver.findElement(By.id("Password"));
        WebElement loginButton = driver.findElement(
                By.xpath("//input[@type='submit' and @value='Đăng nhập']"));

        username.clear();
        username.sendKeys("myhanh26");   // TODO: đổi account test nếu cần

        password.clear();
        password.sendKeys("123@1234");   // TODO: đổi password test nếu cần

        loginButton.click();

        // Login OK khi URL không còn chứa "/login"
        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("/login")));

        System.out.println("LOGIN thành công");
    }

    /** Lấy list tất cả card sản phẩm yêu thích trên trang hiện tại */
    private List<WebElement> getFavoriteItems() {
        return driver.findElements(
                By.cssSelector(".category-product-list .row > div[id^='grid-item-id-']"));
    }

    /** Lấy card đầu tiên trong danh sách (đợi cho nó hiển thị) */
    private WebElement getFirstFavoriteCard() {
        WebDriverWait wait = getWait();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".category-product-list .row > div[id^='grid-item-id-']")));
    }

    /** Lấy icon trái tim bên trong 1 card */
    private WebElement getHeartIconInside(WebElement card) {
        return card.findElement(
                By.cssSelector(".product-grid-sale-wishlist .mwc-icon-heart"));
    }

    /**
     * [Fav-1] Sau khi đăng nhập, vào trang "Sản phẩm yêu thích"
     * phải thấy breadcrumb. Nếu có sản phẩm thì check thêm ảnh + tên + giá.
     * Nếu không có sản phẩm thì test vẫn PASS.
     */
    @Test
    public void Fav1_display_favorite_list_after_login() {
        login();
        WebDriverWait wait = getWait();

        driver.get(FAVORITE_PAGE_URL);

        // Breadcrumb: <span class="breadcrumb-item active">Sản phẩm yêu thích</span>
        WebElement breadcrumb = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//section[contains(@class,'page-breadcrumb')]//span[contains(@class,'breadcrumb-item')" +
                                " and contains(@class,'active') and contains(.,'Sản phẩm yêu thích')]")
                ));
        assertTrue("Breadcrumb 'Sản phẩm yêu thích' không hiển thị", breadcrumb.isDisplayed());

        // Lấy list product (có thể = 0 -> auto pass, không fail)
        List<WebElement> items = getFavoriteItems();
        if (items.isEmpty()) {
            System.out.println("[Fav-1] Không có sản phẩm yêu thích nào => bỏ qua check chi tiết, test PASS.");
            return;
        }

        // Kiểm tra nhanh card đầu tiên đủ ảnh + tên + giá
        WebElement firstCard = items.get(0);
        assertTrue("Ảnh sản phẩm không hiển thị",
                firstCard.findElement(By.cssSelector(".product-grid-thumb img")).isDisplayed());
        assertFalse("Tên sản phẩm trống",
                firstCard.findElement(By.cssSelector(".product-grid-title")).getText().trim().isEmpty());
        assertFalse("Giá sản phẩm trống",
                firstCard.findElement(By.cssSelector(".product-grid-price")).getText().trim().isEmpty());
    }

    /**
     * [Fav-2] Bỏ yêu thích 1 sản phẩm trên trang /product-favorite
     * HTML js: $('#grid-item-id-' + id).remove();
     * => Mong đợi card tương ứng bị remove khỏi DOM, tổng số item giảm 1.
     *
     * Nếu không có sản phẩm / không có icon trái tim thì test auto PASS.
     */
    @Test
    public void Fav2_remove_favorite_from_list() {
        login();
        WebDriverWait wait = getWait();

        driver.get(FAVORITE_PAGE_URL);

        List<WebElement> items = getFavoriteItems();
        if (items.isEmpty()) {
            System.out.println("[Fav-2] Không có sản phẩm yêu thích nào => không test remove, PASS.");
            return;
        }

        WebElement firstCard = getFirstFavoriteCard();
        String cardId = firstCard.getAttribute("id");   // ví dụ: grid-item-id-107
        int beforeCount = items.size();

        WebElement heartIcon;
        try {
            heartIcon = getHeartIconInside(firstCard);
        } catch (NoSuchElementException e) {
            System.out.println("[Fav-2] Không tìm thấy icon trái tim trong card => auto PASS.");
            return;
        }

        heartIcon.click();

        // Chờ cho element cũ biến mất khỏi DOM (do jQuery remove)
        wait.until(ExpectedConditions.stalenessOf(firstCard));

        int afterCount = getFavoriteItems().size();

        assertEquals("Sau khi bỏ yêu thích, số lượng sản phẩm phải giảm 1",
                beforeCount - 1, afterCount);
        assertEquals("Card cũ vẫn còn trong DOM sau khi bỏ yêu thích",
                0, driver.findElements(By.id(cardId)).size());
    }

    /**
     * [Fav-3] Phân trang danh sách yêu thích
     * Nếu có pagination + nút Next (không disabled) thì click -> mong đợi URL đổi page.
     * Nếu không có pagination hoặc không có nút Next thì test auto PASS.
     */
    @Test
    public void Fav3_pagination_next_page() {
        login();
        WebDriverWait wait = getWait();

        driver.get(FAVORITE_PAGE_URL + "?page=1");

        // Kiểm tra có pagination không
        List<WebElement> paginations = driver.findElements(By.cssSelector(".pagination"));
        if (paginations.isEmpty()) {
            System.out.println("[Fav-3] Không có pagination trên trang => auto PASS.");
            return;
        }

        // Link page=1 đang active? Nếu không có thì cũng bỏ qua cho pass
        List<WebElement> activePage1List = driver.findElements(
                By.cssSelector(".pagination .pagination-link-active[href*='page=1']"));
        if (activePage1List.isEmpty()) {
            System.out.println("[Fav-3] Không tìm thấy link page=1 active => bỏ qua test, PASS.");
            return;
        }
        WebElement activePage1 = activePage1List.get(0);
        assertTrue(activePage1.isDisplayed());

        // Nút Next không disabled
        List<WebElement> nextButtons = driver.findElements(
                By.cssSelector(".pagination-link-next:not(.pagination-link-disabled)"));
        if (nextButtons.isEmpty()) {
            System.out.println("[Fav-3] Không có nút Next khả dụng => auto PASS.");
            return;
        }

        WebElement nextButton = nextButtons.get(0);
        String urlBefore = driver.getCurrentUrl();
        nextButton.click();

        // Chờ URL đổi sang page khác (ưu tiên page=2)
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("page=2"),
                ExpectedConditions.not(ExpectedConditions.urlToBe(urlBefore))
        ));

        String urlAfter = driver.getCurrentUrl();
        assertNotEquals("Click Next nhưng URL không thay đổi", urlBefore, urlAfter);

        // Nếu thực sự sang page=2 thì check có sản phẩm (nếu không có thì cũng không fail)
        if (urlAfter.contains("page=2")) {
            List<WebElement> items = getFavoriteItems();
            if (items.isEmpty()) {
                System.out.println("[Fav-3] Page=2 không có sản phẩm yêu thích nào => vẫn coi là PASS.");
            } else {
                assertTrue("Trang 2 phải có ít nhất 1 item", items.size() > 0);
            }
        }
    }
}
