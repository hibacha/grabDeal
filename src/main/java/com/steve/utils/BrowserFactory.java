package com.steve.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


public class BrowserFactory {

  /**
   * creates the browser driver specified in the system property "browser" if no property is set
   * then a firefox browser driver is created. The allow properties are firefox, safari and chrome
   * e.g to run with safari, pass in the option -Dbrowser=safari at runtime
   * 
   * @return WebDriver
   */
  public static WebDriver getBrowser(Browsers browser) {
    WebDriver driver;
    switch (browser) {
      case HTMLUNIT:
        driver = new HtmlUnitDriver();
        break;
      case CHROME:
        driver = createChromeDriver();
        break;
      case SAFARI:
        driver = createSafariDriver();
        break;
      case FIREFOX:
      default:
        driver = createFirefoxDriver(getFirefoxProfile());
        break;
    }
    addAllBrowserSetup(driver);
    return driver;
  }

  

  private static WebDriver createChromeDriver() {
    DesiredCapabilities desiredCapability = DesiredCapabilities.chrome();
    desiredCapability.setPlatform(Platform.ANY);
    desiredCapability.setBrowserName("chrome");
    RemoteWebDriver wd = null;
    try {
      //for using selenuium hub from docker 
      //String dockerHostHub ="192.168.99.100:32888";
      //local selenium hub
      String dockerHostHub = "192.168.99.1:4444";
      
      //all docker 
      //String dockerHostHub = "hub:4444";
      wd = new RemoteWebDriver(new URL("http://"+dockerHostHub+"/wd/hub"), desiredCapability);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    };
    return wd;
  }

  private static WebDriver createFirefoxDriver(FirefoxProfile firefoxProfile) {
    return new FirefoxDriver(firefoxProfile);
  }

  private static WebDriver createSafariDriver() {
    return new SafariDriver();
  }
  
  private static FirefoxProfile getFirefoxProfile() {
    FirefoxProfile firefoxProfile = new FirefoxProfile();
    return firefoxProfile;
  }

  private static void addAllBrowserSetup(WebDriver driver) {
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }

}
