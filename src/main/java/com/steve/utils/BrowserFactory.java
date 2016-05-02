package com.steve.utils;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


public class BrowserFactory {

  private static final String BROWSER_PROP_KEY = "browser";

  /**
   * creates the browser driver specified in the system property "browser" if no property is set
   * then a firefox browser driver is created. The allow properties are firefox, safari and chrome
   * e.g to run with safari, pass in the option -Dbrowser=safari at runtime
   * 
   * @return WebDriver
   */
  public static WebDriver getBrowser() {
    Browsers browser;
    WebDriver driver;

    if (System.getProperty(BROWSER_PROP_KEY) == null) {
      browser = Browsers.CHROME;
    } else {
      browser = Browsers.browserForName(System.getProperty(BROWSER_PROP_KEY));
    }
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

  private static WebDriver createSafariDriver() {
    return new SafariDriver();
  }

  private static WebDriver createChromeDriver() {
    DesiredCapabilities capability = DesiredCapabilities.chrome();
    capability.setPlatform(Platform.ANY);
    capability.setBrowserName("chrome");
    RemoteWebDriver wd = null;
    try {
      String dockerHostHub = "hub:32888";
      wd = new RemoteWebDriver(new URL("http://"+dockerHostHub+"/wd/hub"), capability);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    };
    return wd;
  }

  private static WebDriver createFirefoxDriver(FirefoxProfile firefoxProfile) {
    return new FirefoxDriver(firefoxProfile);
  }

  private static FirefoxProfile getFirefoxProfile() {
    FirefoxProfile firefoxProfile = new FirefoxProfile();
    try {
      firefoxProfile.addExtension(FileUtils.getFile("firebug/firebug-1.9.2.xpi"));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    // See http://getfirebug.com/wiki/index.php/Firebug_Preferences
    firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.9.2"); // Avoid startup
                                                                                // screen
    firefoxProfile.setPreference("extensions.firebug.script.enableSites", true);
    firefoxProfile.setPreference("extensions.firebug.console.enableSites", true);
    firefoxProfile.setPreference("extensions.firebug.allPagesActivation", true);
    firefoxProfile.setPreference("extensions.firebug.delayLoad", false);
    return firefoxProfile;
  }

  private static void addAllBrowserSetup(WebDriver driver) {
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    driver.manage().window().setPosition(new Point(0, 0));
    java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
    driver.manage().window().setSize(dim);
  }

}
