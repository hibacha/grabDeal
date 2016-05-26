package steps;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.BrowserDriver;
import utils.Browsers;
import utils.ThreadUntils;

import com.megabus.views.Home;
import com.megabus.views.Payment;
import com.megabus.views.Result;
import com.megabus.views.Result.Itinerary;
import com.megabus.views.Result.TRIP;
import com.megabus.views.SeatSelect;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MegaBusSteps {

  private static final Logger LOGGER = Logger.getLogger(MegaBusSteps.class.getName());
  private Home home;
  private Result result;
  private SeatSelect seatSelect;
  private Payment payment;
  
  @Given("^I open a (.*) browser")
  public void open_browser_type(String browserType){
    System.out.println(browserType.toUpperCase());
    Browsers browser = Browsers.valueOf(browserType.toUpperCase());
    BrowserDriver.launchBrowserDriver(browser);
  }
  
  @Given("^I navigate to the megabus$")
  public void given_navigate_to_megabus() {
    BrowserDriver.getCurrentDriver().get("http://us.megabus.com/");
  }

  @When("^I try to set departure state as '(.+)'")
  public void when_set_departure_state(String state) {
    getHome().selectDepartureState(state);
  }

  @When("^I try to set departure city as '(.+)'")
  public void when_set_departure_city(String city) {
    getHome().selectDepartureCity(city);
  }

  @When("^I try to set arrival city as '(.+)'")
  public void when_set_arrival_city(String city) {
    getHome().selectArrivalCity(city);
  }

  @When("^I try to set departure date as '(\\d{1,2})/(\\d{1,2})/(\\d{4})'")
  public void when_set_departure_date(int month, int day, int year) {
    getHome().selectDepartureDate(LocalDate.of(year, month, day));
  }

  @When("^I try to set return date as '(\\d{1,2})/(\\d{1,2})/(\\d{4})'")
  public void when_set_return_date(int month, int day, int year) {
    getHome().selectReturnDate(LocalDate.of(year, month, day));
  }

  @When("^I click the search button$")
  public void when_click_search_button() {
    getHome().clickSearchButton();
  }

  @Then("^I should get bus information for my input$")
  public void then_get_bus_info_successfully() {
    getResult().isResultShown();
    LOGGER.info("successfully get result");
  }

  @Given("^I try to find departure time between (.*?) and (.*?)$")
  public void find_departure_between(String earlierTime, String laterTime) {
    Map<Itinerary, WebElement> results = getResult().loadTripResultByType(TRIP.DEPARTURE);
    results = getResult().findBetween(results, earlierTime, laterTime);
    Itinerary cheapest = getResult().findCheapest(results.keySet());
    results.get(cheapest).findElement(By.xpath("li//input[@type='radio']")).click();
  }

  @And("^I try to find return time between (.*?) and (.*?)$")
  public void find_return_between(String earlierTime, String laterTime) {
    Map<Itinerary, WebElement> results = getResult().loadTripResultByType(TRIP.RETURN);
    results = getResult().findBetween(results, earlierTime, laterTime);
    Itinerary cheapest = getResult().findCheapest(results.keySet());
    results.get(cheapest).findElement(By.xpath("li//input[@type='radio']")).click();
  }

  @When("^I click add to journey button$")
  public void click_add_to_journey_button() {
    getResult().addToJourney();
  }

  @And("^I click continue button$")
  public void click_continue_button_outbound() {
    LOGGER.info("first time click continue button");
    getSeatSelect().clickContinueButton();
    ThreadUntils.sleep(TimeUnit.SECONDS, 2);
  }

  @And("^I click continue button again$")
  public void click_continue_button_inbound() {
    LOGGER.info("second time click continue button");
    getSeatSelect().clickContinueButton();
    ThreadUntils.sleep(TimeUnit.SECONDS, 2);
  }

  @And("^Input my mobile phone number ([\\d-]+)$")
  public void type_mobile_phone(String rawPhoneNumber) {
    getSeatSelect().typeMobilephone(rawPhoneNumber);
    ThreadUntils.sleep(TimeUnit.SECONDS, 2);
    
  }

  @And("^Check agree checkbox$")
  public void check_agree_checkbox() {
    getSeatSelect().checkTermBox();
  }

  @Then("^Click submit button$")
  public void click_submit() {
    getSeatSelect().clickSubmitButton();
    ThreadUntils.sleep(TimeUnit.SECONDS, 2);
  }

  @Then("^Click visa icon$")
  public void click_visa() {
    // TODO move to new view later
    getSeatSelect().clickVisaIcon();
    //payment.input_payment_info();
    BrowserDriver.close();
  }
  
  public Home getHome() {
    return home = home == null ? new Home() : home;
  }

  public Result getResult() {
    return result = result == null ? new Result() : result;
  }

  public SeatSelect getSeatSelect() {
    return seatSelect = seatSelect == null ? new SeatSelect() : seatSelect;
  }

  public Payment getPayment() {
    return payment = payment == null ? new Payment() : payment;
  }
}
