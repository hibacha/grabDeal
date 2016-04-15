package com.megabus.containers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.steve.utils.BrowserDriver;

public class HomeContainer {

  @FindBy(how = How.ID, using = "JourneyPlanner_ddlLeavingFromState")
  @CacheLookup
  private WebElement departState;

  @FindBy(how = How.ID, using = "JourneyPlanner_ddlOrigin")
  @CacheLookup
  private WebElement departCity;

  @FindBy(how = How.ID, using = "JourneyPlanner_ddlDest")
  @CacheLookup
  private WebElement arrivalCity;

  @FindBy(how = How.ID, using = "JourneyPlanner_txtOutboundDate")
  @CacheLookup
  private WebElement departureDate;

  @FindBy(how = How.ID, using = "JourneyPlanner_txtReturnDate")
  @CacheLookup
  private WebElement returnDate;

  @FindBy(how = How.XPATH, using = "//div[contains(concat(' ',@class,' '),' departdate ')]/img")
  @CacheLookup
  private WebElement departureDatePicker;

  @FindBy(how = How.XPATH, using = "//div[contains(concat(' ',@class,' '),' returndate ')]/img")
  @CacheLookup
  private WebElement returnDatePicker;

  @FindBy(how = How.ID, using = "JourneyPlanner_btnSearch")
  @CacheLookup
  private WebElement searchButton;

  public WebElement getDateIcon(Integer date){
      return BrowserDriver.getCurrentDriver().findElement(By.xpath("//div[@id='ui-datepicker-div']//a[text()='"+date+"']"));
  }
  
  public WebElement getDepartureDate() {
    return departureDate;
  }

  public WebElement getDepartState() {
    return departState;
  }

  public WebElement getDepartCity() {
    return departCity;
  }

  public WebElement getArrivalCity() {
    return arrivalCity;
  }

  public WebElement getReturnDate() {
    return returnDate;
  }

  public WebElement getDepartureDatePicker() {
    return departureDatePicker;
  }

  public WebElement getReturnDatePicker() {
    return returnDatePicker;
  }

  public WebElement getSearchButton() {
    return searchButton;
  }
}
