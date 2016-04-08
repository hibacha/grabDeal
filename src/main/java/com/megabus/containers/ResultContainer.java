package com.megabus.containers;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ResultContainer {

  @FindBy(how = How.XPATH,
      using = "//div[@id='JourneyResylts_OutboundList_main_div']/ul[position()>1]")
  private List<WebElement> departureTripResult;

  @FindBy(how = How.XPATH,
      using = "//div[@id='JourneyResylts_InboundList_main_div']/ul[position()>1]")
  private List<WebElement> returnTripResult;

  @FindBy(how = How.XPATH, using = "//input[@id = 'JourneyResylts_btnAdd']")
  private WebElement addToJourneyButton;

  public List<WebElement> getDepartureTripResult() {
    return departureTripResult;
  }

  public List<WebElement> getReturnResult() {
    return returnTripResult;
  }

  public WebElement getAddtoJourneyButton() {
    return addToJourneyButton;
  }
}
