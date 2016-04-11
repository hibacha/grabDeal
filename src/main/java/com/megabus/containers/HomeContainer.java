package com.megabus.containers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomeContainer {

  @FindBy(how = How.ID, using = "JourneyPlanner_ddlLeavingFromState")
  private WebElement departState;

  @FindBy(how = How.ID, using = "JourneyPlanner_ddlOrigin")
  private WebElement departCity;

  @FindBy(how = How.ID, using = "JourneyPlanner_ddlDest")
  private WebElement arrivalCity;

  @FindBy(how = How.ID, using = "JourneyPlanner_txtOutboundDate")
  private WebElement departureDate;

  @FindBy(how = How.ID, using = "JourneyPlanner_txtReturnDate")
  private WebElement returnDate;

  @FindBy(how = How.XPATH, using = "//div[contains(concat(' ',@class,' '),' departdate ')]/img")
  private WebElement departureDatePicker;

  @FindBy(how = How.XPATH, using = "//div[contains(concat(' ',@class,' '),' returndate ')]/img")
  private WebElement returnDatePicker;

  @FindBy(how = How.ID, using = "JourneyPlanner_btnSearch")
  private WebElement searchButton;

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
