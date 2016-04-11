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
  private Home home = new Home();
  private Result result = new Result();
  private SeatSelect seatSelect = new SeatSelect();

  @Given("^I navigate to the megabus$")
  public void given_navigate_to_megabus() {
    LOGGER.info("Entering: I navigate to the megabus");
    LOGGER.info(this.toString());
    BrowserDriver.getCurrentDriver().get("http://us.megabus.com/");
    
  }

  @When("^I try to set departure state as '(.+)'")
  public void when_set_departure_state(String state) {
    home.selectDepartureState(state);
  }

  @And("^I try to set departure city as '(.+)'")
  public void when_set_departure_city(String city) {
    home.selectDepartureCity(city);
  }

  @And("^I try to set arrival city as '(.+)'")
  public void when_set_arrival_city(String city) {
    home.selectArrivalCity(city);
  }

  @And("^I try to set departure date as '(\\d{1,2})/(\\d{1,2})/(\\d{4})'")
  public void when_set_departure_date(int month, int day, int year) {
    home.selectDepartureDate(LocalDate.of(year, month, day));
  }

  @And("^I try to set return date as '(\\d{1,2})/(\\d{1,2})/(\\d{4})'")
  public void when_set_return_date(int month, int day, int year) {
    home.selectReturnDate(LocalDate.of(year, month, day));
  }

  @And("^I click the search button$")
  public void when_click_search_button() {
    home.clickSearchButton();
  }

  @Then("^I should get bus information for my input$")
  public void then_get_bus_info_successfully() {
    result.isResultShown();
    LOGGER.info("successfully get result");
  }

  @Given("^I try to find departure time between (.*?) and (.*?)$")
  public void find_departure_between(String earlierTime, String laterTime) {
    Map<Itinerary, WebElement> results = result.loadTripResultByType(TRIP.DEPARTURE);
    results = result.findBetween(results, earlierTime, laterTime);
    Itinerary cheapest = result.findCheapest(results.keySet());
    results.get(cheapest).findElement(By.xpath("li//input[@type='radio']")).click();
  }

  @And("^I try to find return time between (.*?) and (.*?)$")
  public void find_return_between(String earlierTime, String laterTime) {
    Map<Itinerary, WebElement> results = result.loadTripResultByType(TRIP.RETURN);
    results = result.findBetween(results, earlierTime, laterTime);
    Itinerary cheapest = result.findCheapest(results.keySet());
    results.get(cheapest).findElement(By.xpath("li//input[@type='radio']")).click();
  }

  @When("^I click add to journey button$")
  public void click_add_to_journey_button() {
    result.addToJourney();
  }

  @And("^I click continue button$")
  public void click_continue_button_outbound() {
    seatSelect.clickContinueButton();
  }

  @And("^I click continue button again$")
  public void click_continue_button_inbound() {
    seatSelect.clickContinueButton();
  }

  @And("^Input my mobile phone number ([\\d-]+)$")
  public void type_mobile_phone(String rawPhoneNumber) {
    seatSelect.typeMobilephone(rawPhoneNumber);
  }

  @And("^Check agree checkbox$")
  public void check_agree_checkbox() {
    seatSelect.checkTermBox();
  }

  @Then("^Click submit button$")
  public void click_submit() {
    seatSelect.clickSubmitButton();
  }

  @Then("^Click visa icon$")
  public void click_visa() {
    // TODO move to new view later
    seatSelect.clickVisaIcon();
    BrowserDriver.close();
  }
}
