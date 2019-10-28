package com.expedia.flightbooking;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG_Screenshots {
	private static final Logger log = LogManager.getLogger(TestNG_Screenshots.class.getName());
	private WebDriver driver;
	private String baseUrl;
//	ExtentReports report;
//	ExtentTest test;

	@BeforeMethod
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Projects\\java\\selenium\\chromedriver_win32\\chromedriver.exe");
		baseUrl = "https://login.yahoo.com";

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@Test
	public void test1_invalidCredentials() throws Exception {
		WebElement emailField = driver.findElement(By.id("login-username"));
		emailField.sendKeys("h660057075");
		log.info("QQQQQQQQQ");
		WebElement goButton = driver.findElement(By.id("login-signin"));
		goButton.sendKeys(Keys.ENTER);

		Thread.sleep(3000);

		WebElement passWordField = driver.findElement(By.id("login-passwd"));
		passWordField.sendKeys("201314qq");

		WebElement goButton2 = driver.findElement(By.id("login-signin"));
		goButton2.sendKeys(Keys.ENTER);

//		Thread.sleep(3000);

		WebElement welComeText = null;

		try {
			welComeText = driver.findElement(By.xpath("//*[@id=\"uh-profile\"]/b"));
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(welComeText != null);
	};

	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
//		if (testResult.getStatus() == ITestResult.FAILURE) {
//			Screenshots.takeScreenshot(driver, testResult.getName());
//		}

		driver.quit();
	}
}