package com.megabus.views;

import org.openqa.selenium.support.PageFactory;

import com.megabus.containers.SeatSelectContainer;
import com.steve.utils.BrowserDriver;

public class SeatSelect {

  private final SeatSelectContainer CONTAINER = PageFactory.initElements(
      BrowserDriver.getCurrentDriver(), SeatSelectContainer.class);

  public void clickContinueButton() {
    CONTAINER.getContinueButton().click();
  }

  public void typeMobilephone(String rawPhoneNumber) {
    String normalizedNumber = rawPhoneNumber.replaceAll("[^\\d]", "");
    CONTAINER.getMobilePhoneField().sendKeys(normalizedNumber);
    CONTAINER.getAddPhoneNumberButton().click();
  }

  public void checkTermBox() {
    CONTAINER.getTermCheckBox().click();
  }

  public void clickSubmitButton() {
    CONTAINER.getSubmitButton().click();
  }

  public void clickVisaIcon() {
    CONTAINER.getVisaIcon().click();
  }
}
