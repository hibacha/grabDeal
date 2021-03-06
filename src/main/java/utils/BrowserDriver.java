package utils;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserDriver {
  private static final Logger LOGGER = Logger.getLogger(BrowserDriver.class.getName());
  private static ThreadLocal<WebDriver> mDriver = new ThreadLocal<WebDriver>();

  public synchronized static WebDriver launchBrowserDriver(Browsers type) {
    if (mDriver.get() == null) {
      try {
        mDriver.set(BrowserFactory.getBrowser(type));
      } catch (UnreachableBrowserException e) {
        mDriver.set( BrowserFactory.getBrowser(type));
      } catch (WebDriverException e) {
        mDriver.set(BrowserFactory.getBrowser(type));
      } finally {
        Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
      }
    }
    return mDriver.get();
  }

  public static WebDriver getCurrentDriver(){
    return mDriver.get();
  }
  
  public static void close() {
    try {
      if (mDriver.get() != null) {
        getCurrentDriver().quit();
        mDriver.set(null);
        LOGGER.info("closing the browser");
      }
    } catch (UnreachableBrowserException e) {
      LOGGER.info("cannot close browser: unreachable browser");
    }
  }

  private static class BrowserCleanup implements Runnable {
    public void run() {
      close();
    }
  }

  public static void loadPage(String url) {
    getCurrentDriver();
    LOGGER.info("Directing browser to:" + url);
    LOGGER.info("try to loadPage [" + url + "]");
    getCurrentDriver().get(url);

  }

  public static void reopenAndLoadPage(String url) {
    mDriver = null;
    getCurrentDriver();
    loadPage(url);
  }

  public static WebElement waitForElement(WebElement elementToWaitFor) {
    return waitForElement(elementToWaitFor, null);
  }

  public static WebElement waitForElement(WebElement elementToWaitFor, Integer waitTimeInSeconds) {
    if (waitTimeInSeconds == null) {
      waitTimeInSeconds = 10;
    }

    WebDriverWait wait = new WebDriverWait(getCurrentDriver(), waitTimeInSeconds);
    return wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
  }

  public static WebElement getParent(WebElement element) {
    return element.findElement(By.xpath(".."));
  }

  public static List<WebElement> getDropDownOptions(WebElement webElement) {
    Select select = new Select(webElement);
    return select.getOptions();
  }

  public static WebElement getDropDownOption(WebElement webElement, String value) {
    WebElement option = null;
    List<WebElement> options = getDropDownOptions(webElement);
    for (WebElement element : options) {
      if (element.getAttribute("value").equalsIgnoreCase(value)) {
        option = element;
        break;
      }
    }
    return option;
  }

  public static WebElement waitForElementEnable(WebElement elementForEnable, Integer waitSeconds) {
    WebDriverWait wait = new WebDriverWait(getCurrentDriver(), waitSeconds);
    return wait.until(ExpectedConditions.elementToBeClickable(elementForEnable));
  }
}
