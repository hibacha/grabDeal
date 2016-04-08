package com.megabus.containers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SeatSelectContainer {
  @FindBy(how = How.XPATH, using = "//input[@type='submit' and @class='btn-continue']")
  private WebElement continueButton;

  @FindBy(how = How.ID, using = "BasketView_txtPhone")
  private WebElement mobilePhoneField;

  @FindBy(how = How.ID, using = "BasketView_btnAddPhone")
  private WebElement addPhoneNumberButton;

  @FindBy(how = How.ID, using = "BasketView_cbTerms")
  private WebElement termCheckBox;

  @FindBy(how = How.ID, using = "BasketView_btnPay")
  private WebElement submitButton;

  @FindBy(how = How.XPATH, using = "//input[@alt='Visa']")
  private WebElement visaIcon;

  public WebElement getVisaIcon() {
    return visaIcon;
  }

  public WebElement getSubmitButton() {
    return submitButton;
  }

  public WebElement getTermCheckBox() {
    return termCheckBox;
  }

  public WebElement getAddPhoneNumberButton() {
    return addPhoneNumberButton;
  }

  public WebElement getMobilePhoneField() {
    return mobilePhoneField;
  }

  public WebElement getContinueButton() {
    return continueButton;
  }

}
