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

public class OrderWithEmptyPhone {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://www.google.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testOrderWithEmptyPhone() throws Exception {
		selenium.open("https://mwc.com.vn/");
		selenium.click("xpath=//img[contains(@src,'https://img.mwc.com.vn/giay-thoi-trang?w=640&h=640&FileInput=/Resources/Product/2025/11/03/z7183679343829_8749a5e82df15ca51a87858f6723fc6c.jpg')]");
		selenium.click("id=btnAddToCart");
		selenium.click("xpath=//a[@onclick=\"removecart('58799')\"]");
		selenium.click("xpath=//div[@id='cart-list-item']/div[3]/a/span");
		selenium.click("id=FullName");
		selenium.type("id=FullName", "dung phan");
		selenium.click("id=Address");
		selenium.click("id=Address");
		selenium.type("id=Address", "thu duc");
		selenium.click("id=provinceOptions");
		selenium.select("id=provinceOptions", "label=TP Hồ Chí Minh");
		selenium.click("id=districtSelect");
		selenium.select("id=districtSelect", "label=Quận Thủ Đức");
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
