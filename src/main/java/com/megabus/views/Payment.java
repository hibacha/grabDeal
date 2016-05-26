package com.megabus.views;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.support.PageFactory;

import utils.BrowserDriver;
import utils.Utility;

import com.megabus.containers.PaymentContainer;

public class Payment {
  public Payment() {
    try {
      props = Utility.getProperty();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private final PaymentContainer CONTAINER = PageFactory.initElements(
      BrowserDriver.getCurrentDriver(), PaymentContainer.class);
  private Properties props = null;

  public void input_payment_info() {
    String cardNumber = (String) props.get("cardNumber");
    System.out.println("@@@@@" + cardNumber);
  }

  public void pay() {

  }


}
