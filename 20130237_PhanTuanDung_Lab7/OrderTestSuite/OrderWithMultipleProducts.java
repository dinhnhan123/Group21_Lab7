package com.example;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

public class OrderWithMultipleProducts {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://www.google.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testOrderWithMultipleProducts() throws Exception {
		selenium.open("https://mwc.com.vn/");
		selenium.click("xpath=//img[contains(@src,'https://img.mwc.com.vn/giay-thoi-trang?w=640&h=640&FileInput=/Resources/Product/2025/11/03/z7183542718841_536fa746b959671027ff41bdab22498e.jpg')]");
		selenium.click("xpath=//a[@id='btnAddToCart']/i");
		selenium.click("id=mm-blocker");
		selenium.click("xpath=//img[@alt='MWC']");
		selenium.click("xpath=//img[@alt='Dép Cao Gót MWC G250 - Guốc Gót Tròn 5P, Mũi Vuông, Quai Bản Viền Bèo Ren Mềm Mại Phối Nơ Nhỏ Xinh Ngọt Ngào, Nữ Tính.']");
		selenium.click("id=btnAddToCart");
		selenium.click("id=mm-blocker");
		selenium.click("xpath=//div[@id='nav']/nav/ul/li[3]/a");
		selenium.click("xpath=//img[@alt='Giày Thể Thao Nam MWC 5863 - Giày Sneaker Nam, Đế Cao Su Đúc Nguyên Khối Êm Ái, Đi Học, Đi Chơi, Dạo Phố Cool Ngầu.']");
		selenium.click("id=btnAddToCart");
		selenium.click("xpath=//div[@id='cart-list-item']/div[3]/a/span");
		selenium.click("id=FullName");
		selenium.click("id=Phone");
		selenium.click("id=FullName");
		selenium.type("id=FullName", "dung phan");
		selenium.click("id=Phone");
		selenium.type("id=Phone", "0329676413");
		selenium.click("id=Address");
		selenium.type("id=Address", "thu duc");
		selenium.click("id=provinceOptions");
		selenium.select("id=provinceOptions", "label=TP Hồ Chí Minh");
		selenium.click("id=districtSelect");
		selenium.select("id=districtSelect", "label=Quận Thủ Đức");
		selenium.click("id=wardSelect");
		selenium.select("id=wardSelect", "label=Phường Linh Trung");
		selenium.click("xpath=//button[@id='btnDatHang']/span");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
