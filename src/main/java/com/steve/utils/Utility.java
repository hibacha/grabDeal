package com.steve.utils;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Utility {
	public static File captureBitMap(WebElement webElement){
		WebDriver driver = BrowserDriver.getCurrentDriver();
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		return file;
	}
}
