package com.megabus.views;

import org.openqa.selenium.support.PageFactory;

import com.megabus.containers.PaymentContainer;
import com.steve.utils.BrowserDriver;

public class Payment {
	private static final PaymentContainer CONTAINER = PageFactory.initElements(BrowserDriver.getCurrentDriver(), PaymentContainer.class);
	
	public static void input_payment_info(){
		
	}
	
	public static void pay(){
		
	}
	
	
}
