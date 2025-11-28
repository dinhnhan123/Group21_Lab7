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

public class OrderWithEmptyName {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://www.google.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testOrderWithEmptyName() throws Exception {
		selenium.open("https://mwc.com.vn/products/giay-the-thao-nu-mwc-nutt--a374?&c=kem");
		selenium.click("id=btnAddToCart");
		selenium.click("xpath=//div[@id='cart-list-item']/div[3]/a/span");
		selenium.open("https://mwc.com.vn/cart");
		selenium.click("id=Phone");
		selenium.type("id=Phone", "0329676413");
		selenium.click("id=Address");
		selenium.type("id=Address", "thủ đức");
		selenium.click("id=provinceOptions");
		selenium.select("id=provinceOptions", "label=TP Hồ Chí Minh");
		selenium.click("id=districtSelect");
		selenium.select("id=districtSelect", "label=Quận Thủ Đức");
		selenium.click("id=info-input");
		selenium.click("id=wardSelect");
		selenium.select("id=wardSelect", "label=Phường Linh Trung");
		selenium.click("xpath=//button[@id='btnDatHang']/span");
		selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Bạn chưa nhập thông tin nhận hàng!'])[1]/following::button[1]");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
