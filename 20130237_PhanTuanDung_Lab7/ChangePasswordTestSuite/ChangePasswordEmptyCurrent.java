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

public class ChangePasswordEmptyCurrent {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://www.google.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testChangePasswordEmptyCurrent() throws Exception {
		selenium.open("https://mwc.com.vn/profile");
		selenium.click("xpath=//a[@id='password']/span");
		selenium.open("https://mwc.com.vn/password");
		selenium.click("id=PasswordNew");
		selenium.type("id=PasswordNew", "opaki921");
		selenium.type("id=PasswordNewConfirm", "opaki921");
		selenium.click("xpath=//div[@id='main']/section[2]/div/div/div[2]/div/form/div/div[2]/div[4]/div[2]/button");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
