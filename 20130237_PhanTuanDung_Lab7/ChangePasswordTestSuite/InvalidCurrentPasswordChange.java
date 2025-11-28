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

public class InvalidCurrentPasswordChange {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://www.google.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testInvalidCurrentPasswordChange() throws Exception {
		selenium.open("https://mwc.com.vn/");
		selenium.click("xpath=//span[@id='account-handle']/a/img");
		selenium.click("xpath=//a[@id='password']/span");
		selenium.click("id=PasswordOld");
		selenium.type("id=PasswordOld", "opaki921");
		selenium.click("id=PasswordNew");
		selenium.type("id=PasswordNew", "opaki1000");
		selenium.type("id=PasswordNewConfirm", "opaki1000");
		selenium.click("xpath=//div[@id='main']/section[2]/div/div/div[2]/div/form/div/div[2]/div[4]/div[2]/button");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
