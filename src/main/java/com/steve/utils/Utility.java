package com.steve.utils;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Utility {
  public static File captureBitMap(WebElement webElement) {
    WebDriver driver = BrowserDriver.getCurrentDriver();
    File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    return file;
  }

  public static String getLocalIp() {
    String ip = "localhost";
    InetAddress address;
    try {
      address = Inet4Address.getLocalHost();
      ip = address.getHostAddress();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return ip;
  }
}
