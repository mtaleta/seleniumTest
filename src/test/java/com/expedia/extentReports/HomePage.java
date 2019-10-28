package com.expedia.extentReports;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage {
	ExtentTest test;
	WebDriver driver = null;

	public HomePage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public void enterUserNameTextBox(String username) {
		WebElement emailField = driver.findElement(By.id("login-username"));
		emailField.sendKeys(username);
		test.log(LogStatus.INFO, "Enter User Name");
	}

	public void enterPasswordTextBox(String password) {
		WebElement passWordField = driver.findElement(By.id("login-passwd"));
		passWordField.sendKeys(password);
		test.log(LogStatus.INFO, "Enter User Password");
	}

	public void clickOnLoginLink() {
		WebElement goButton = driver.findElement(By.id("login-signin"));
		goButton.sendKeys(Keys.ENTER);
		test.log(LogStatus.INFO, "Clicked on login button");
	}

	public boolean isWelcomeTextPresent() {
		WebElement welComeText = null;

		try {
			welComeText = driver.findElement(By.xpath("//*[@id=\"uhWrapper\"]/table/tbody/tr/td[1]/h1/a/img"));
			if (welComeText != null) {
				return true;				
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;
	}
	public void usernameStep(String username) {
		enterUserNameTextBox(username);
		clickOnLoginLink();
	}
	public void passwordStep(String password) {
		enterPasswordTextBox(password);
		clickOnLoginLink();
	}
}
