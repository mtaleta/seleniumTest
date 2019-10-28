package com.expedia.extentReports;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {
	public static ExtentReports getInstance() {
		ExtentReports extent;
		String Path = "D:\\Projects\\java\\report-demo.html";
		extent = new ExtentReports(Path, false);
		//系統訊息可加可不加
		extent.addSystemInfo("Selenium Version", "3.141.59").addSystemInfo("Platform", "Windows");
	
		return extent;
	}
}
