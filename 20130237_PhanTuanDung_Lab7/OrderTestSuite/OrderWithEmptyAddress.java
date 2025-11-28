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

public class OrderWithEmptyAddress {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://www.google.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testOrderWithEmptyAddress() throws Exception {
		selenium.open("https://mwc.com.vn/");
		selenium.click("xpath=//img[contains(@src,'https://img.mwc.com.vn/giay-thoi-trang?w=640&h=640&FileInput=/Resources/Product/2025/11/03/z7183679343829_8749a5e82df15ca51a87858f6723fc6c.jpg')]");
		selenium.click("id=btnAddToCart");
		selenium.click("link=Xem giỏ hàng");
		selenium.click("id=FullName");
		selenium.type("id=FullName", "dung phan");
		selenium.click("id=Phone");
		selenium.type("id=Phone", "0329676413");
		selenium.click("id=Address");
		selenium.type("id=Address", "dâdada");
		selenium.click("id=provinceOptions");
		selenium.select("id=provinceOptions", "label=Bạc Liêu");
		selenium.click("id=districtSelect");
		selenium.click("id=provinceOptions");
		selenium.select("id=provinceOptions", "label=-- Chọn tỉnh --");
		selenium.click("xpath=//button[@id='btnDatHang']/span");
		selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Bạn chưa nhập thông tin nhận hàng!'])[1]/following::button[1]");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
