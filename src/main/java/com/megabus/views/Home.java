package com.megabus.views;

import static com.steve.utils.ThreadUntils.sleep;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.megabus.containers.HomeContainer;
import com.steve.utils.BrowserDriver;

public class Home {

  private final HomeContainer CONTAINER = PageFactory.initElements(
      BrowserDriver.getCurrentDriver(), HomeContainer.class);

  public void selectDepartureState(String state) {
    WebElement ele = CONTAINER.getDepartState();
    BrowserDriver.waitForElement(ele);
    Select select = new Select(ele);
    select.selectByVisibleText(state);
    sleep(TimeUnit.SECONDS, 2);
  }

  public void selectDepartureCity(String city) {
    WebElement ele = CONTAINER.getDepartCity();
    BrowserDriver.waitForElementEnable(ele, 10);
    Select select = new Select(ele);
    select.selectByVisibleText(city);
  }

  public void selectArrivalCity(String city) {
    WebElement ele = CONTAINER.getArrivalCity();
    BrowserDriver.waitForElement(ele);
    Select select = new Select(ele);
    select.selectByVisibleText(city);
  }

  public void selectDepartureDate(LocalDate date) {
    WebElement ele = CONTAINER.getDepartureDate();
    BrowserDriver.waitForElementEnable(ele, 10);
    // TODO move to util
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    ele.sendKeys(date.format(formatter));
    WebElement datePicker = CONTAINER.getDepartureDatePicker();
    CONTAINER.getDateIcon(date.getDayOfMonth()).click();
    if(datePicker.isDisplayed()) datePicker.click();
  }

  public void selectReturnDate(LocalDate date) {
    WebElement ele = CONTAINER.getReturnDate();
    BrowserDriver.waitForElementEnable(ele, 10);
    // TODO move to util
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    ele.sendKeys(date.format(formatter));
    WebElement datePicker = CONTAINER.getReturnDatePicker();
    datePicker.click();
  }

  public void clickSearchButton() {
    sleep(TimeUnit.SECONDS, 2);
    WebElement searchBtn = CONTAINER.getSearchButton();
    searchBtn.click();
  }
}
