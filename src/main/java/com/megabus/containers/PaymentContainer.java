package com.megabus.containers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PaymentContainer {

	@FindBy(how=How.ID, using="cardNoInput")
	private WebElement cardNumber;
	
	@FindBy(how=How.ID, using="cardCVV")
	private WebElement cardCVV;
	
	
}
