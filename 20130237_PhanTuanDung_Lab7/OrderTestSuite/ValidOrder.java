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

public class ValidOrder {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://www.google.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testValidOrder() throws Exception {
		selenium.open("https://mwc.com.vn/");
		selenium.click("xpath=//img[contains(@src,'https://img.mwc.com.vn/giay-thoi-trang?w=640&h=640&FileInput=/Resources/Product/2025/11/03/z7183542718841_536fa746b959671027ff41bdab22498e.jpg')]");
		selenium.click("id=btnAddToCart");
		selenium.click("link=Xem giỏ hàng");
		selenium.click("id=FullName");
		selenium.type("id=FullName", "dung phan");
		selenium.type("id=Phone", "0329676413");
		selenium.type("id=Address", "linh trung thu duc");
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
