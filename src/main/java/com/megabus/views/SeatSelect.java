package com.megabus.views;

import org.openqa.selenium.support.PageFactory;

import com.megabus.containers.SeatSelectContainer;
import com.steve.utils.BrowserDriver;

public class SeatSelect {

	private final static SeatSelectContainer CONTAINER = PageFactory.initElements(BrowserDriver.getCurrentDriver(),
					SeatSelectContainer.class);

	public static void clickContinueButton() {
		CONTAINER.getContinueButton().click();
	}
	
	public static void typeMobilephone(String rawPhoneNumber){
		String normalizedNumber = rawPhoneNumber.replaceAll("[^\\d]", "");
		CONTAINER.getMobilePhoneField().sendKeys(normalizedNumber);
		CONTAINER.getAddPhoneNumberButton().click();
	}
	
	public static void checkTermBox(){
		CONTAINER.getTermCheckBox().click();
	}
	
	public static void clickSubmitButton(){
		CONTAINER.getSubmitButton().click();
	}
	
	public static void clickVisaIcon(){
		CONTAINER.getVisaIcon().click();
	}
}
