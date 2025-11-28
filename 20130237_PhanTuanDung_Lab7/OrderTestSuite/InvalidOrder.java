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

public class InvalidOrder {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://www.google.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testInvalidOrder() throws Exception {
		selenium.open("https://mwc.com.vn/");
		selenium.click("xpath=//img[contains(@src,'https://img.mwc.com.vn/giay-thoi-trang?w=640&h=640&FileInput=/Resources/Product/2025/11/03/z7183679343829_8749a5e82df15ca51a87858f6723fc6c.jpg')]");
		selenium.click("id=btnAddToCart");
		selenium.click("xpath=//div[@id='cart-list-item']/div[3]/a/span");
		selenium.click("id=FullName");
		selenium.type("id=FullName", "dad");
		selenium.click("id=Phone");
		selenium.type("id=Phone", "dada");
		selenium.click("id=Address");
		selenium.type("id=Address", "dada");
		selenium.click("id=provinceOptions");
		selenium.select("id=provinceOptions", "label=Trà Vinh");
		selenium.click("id=districtSelect");
		selenium.select("id=districtSelect", "label=Huyện Cầu Ngang");
		selenium.click("id=wardSelect");
		selenium.select("id=wardSelect", "label=Xã Trường Thọ");
		selenium.click("xpath=//button[@id='btnDatHang']/span");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
