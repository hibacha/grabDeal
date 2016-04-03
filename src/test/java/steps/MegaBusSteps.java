package steps;

import java.time.LocalDate;
import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.megabus.views.Home;
import com.megabus.views.Result;
import com.megabus.views.Result.Itinerary;
import com.megabus.views.Result.TRIP;
import com.megabus.views.SeatSelect;
import com.steve.utils.BrowserDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MegaBusSteps {

	private static final Logger LOGGER = Logger.getLogger(MegaBusSteps.class.getName());
	@Given("^I navigate to the megabus$")
	public void given_navigate_to_megabux() {
		LOGGER.info("Entering: I navigate to the megabus");
		BrowserDriver.getCurrentDriver().get("http://us.megabus.com/");
	}

	@When("^I try to set departure state as '(.+)'")
	public void when_set_departure_state(String state) {
		Home.selectDepartureState(state);
	}

	@And("^I try to set departure city as '(.+)'")
	public void when_set_departure_city(String city) {
		Home.selectDepartureCity(city);
	}

	@And("^I try to set arrival city as '(.+)'")
	public void when_set_arrival_city(String city) {
		Home.selectArrivalCity(city);
	}

	@And("^I try to set departure date as '(\\d{1,2})/(\\d{1,2})/(\\d{4})'")
	public void when_set_departure_date(int month, int day, int year) {
		Home.selectDepartureDate(LocalDate.of(year, month, day));
	}

	@And("^I try to set return date as '(\\d{1,2})/(\\d{1,2})/(\\d{4})'")
	public void when_set_return_date(int month, int day, int year) {
		Home.selectReturnDate(LocalDate.of(year, month, day));
	}

	@And("^I click the search button$")
	public void when_click_search_button() {
		Home.clickSearchButton();
	}

	@Then("^I should get bus information for my input$")
	public void then_get_bus_info_successfully() {
		Result.isResultShown();
		LOGGER.info("successfully get result");
	}

	@Given("^I try to find departure time between (.*?) and (.*?)$")
	public void find_departure_between(String earlierTime, String laterTime) {
		Map<Itinerary, WebElement> result = Result
				.loadTripResultByType(TRIP.DEPARTURE);
		result = Result.findBetween(result, earlierTime, laterTime);
		Itinerary cheapest = Result.findCheapest(result.keySet());
		result.get(cheapest).findElement(By.xpath("li//input[@type='radio']"))
				.click();
	}

	@And("^I try to find return time between (.*?) and (.*?)$")
	public void find_return_between(String earlierTime, String laterTime) {
		Map<Itinerary, WebElement> result = Result
				.loadTripResultByType(TRIP.RETURN);
		result = Result.findBetween(result, earlierTime, laterTime);
		Itinerary cheapest = Result.findCheapest(result.keySet());
		result.get(cheapest).findElement(By.xpath("li//input[@type='radio']"))
				.click();
	}

	@When("^I click add to journey button$")
	public void click_add_to_journey_button() {
		Result.addToJourney();
	}

	@And("^I click continue button$")
	public void click_continue_button_outbound() {
		SeatSelect.clickContinueButton();
	}

	@And("^I click continue button again$")
	public void click_continue_button_inbound() {
		SeatSelect.clickContinueButton();
	}

	@And("^Input my mobile phone number ([\\d-]+)$")
	public void type_mobile_phone(String rawPhoneNumber) {
		SeatSelect.typeMobilephone(rawPhoneNumber);
	}

	@And("^Check agree checkbox$")
	public void check_agree_checkbox() {
		SeatSelect.checkTermBox();
	}

	@Then("^Click submit button$")
	public void click_submit() {
		SeatSelect.clickSubmitButton();
	}
	
	@Then("^Click visa icon$")
	public void click_visa(){
		//TODO move to new view later
		SeatSelect.clickVisaIcon();
	}
}
