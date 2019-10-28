package com.expedia.extentReports;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.expedia.utilities.Screenshots;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class LoginTest {
	private WebDriver driver;
	private String baseUrl;
	ExtentReports report;
	ExtentTest test;

	HomePage hp;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Projects\\java\\selenium\\chromedriver_win32\\chromedriver.exe");
		baseUrl = "https://login.yahoo.com";

		report = ExtentFactory.getInstance();
		test = report.startTest("Verify if login successfully");
		driver = new ChromeDriver();
		hp = new HomePage(driver, test);
		test.log(LogStatus.INFO, "Browser started!");

		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Browser Maximize!");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		test.log(LogStatus.INFO, "Web Application Opened.");
	}

	@Test
	public void validLoginTest() throws Exception {
		hp.usernameStep("h660057075");
		hp.passwordStep("qq");
		
		Thread.sleep(3000);
		boolean result = hp.isWelcomeTextPresent();
		
		Assert.assertTrue(result);
		test.log(LogStatus.PASS, "Verified Login Successfully");
	};

	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			String path = Screenshots.takeScreenshot(driver, testResult.getName());
			String imagePath = test.addScreenCapture(path);
			test.log(LogStatus.FAIL, "Verified Login failed", imagePath);
		}

		driver.quit();
		report.endTest(test);
		report.flush();
	}
}
