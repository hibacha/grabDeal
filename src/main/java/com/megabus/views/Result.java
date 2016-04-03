package com.megabus.views;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.megabus.containers.ResultContainer;
import com.steve.utils.BrowserDriver;
import com.steve.utils.ThreadUntils;
import com.steve.utils.Utility;

public class Result {
	public enum TRIP{
		DEPARTURE,RETURN;
	}
	public static class Itinerary {
		private float fare;
		private LocalTime time;

		public float getFare() {
			return fare;
		}

		public void setFare(float fare) {
			this.fare = fare;
		}

		public LocalTime getTime() {
			return time;
		}

		public void setTime(LocalTime time) {
			this.time = time;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Float.floatToIntBits(fare);
			result = prime * result + ((time == null) ? 0 : time.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Itinerary other = (Itinerary) obj;
			if (Float.floatToIntBits(fare) != Float.floatToIntBits(other.fare))
				return false;
			if (time == null) {
				if (other.time != null)
					return false;
			} else if (!time.equals(other.time))
				return false;
			return true;
		}
	}

	private static final ResultContainer CONTAINER = PageFactory.initElements(BrowserDriver.getCurrentDriver(), ResultContainer.class);

	public static void isResultShown() {
		WebDriverWait wait = new WebDriverWait(BrowserDriver.getCurrentDriver(), 10);
		wait.until(ExpectedConditions.urlContains("JourneyResults.aspx"));
	}

	public static Map<Itinerary, WebElement> loadTripResultByType(TRIP trip){
		List<WebElement> result = trip.equals(TRIP.DEPARTURE)?CONTAINER.getDepartureTripResult():CONTAINER.getReturnResult();
		Map<Itinerary, WebElement> map = new HashMap<Itinerary, WebElement>();
		for (WebElement option : result) {
			Itinerary it = new Itinerary();
			if(option.findElements(By.xpath("li")).size() == 2)
				break;
			it.setTime(parseLeaveTime(option.findElement(By.xpath("li[@class='two']"))));
			it.setFare(parseFare(option.findElement(By.xpath("li[@class='five']"))));
			map.put(it, option);
		}
		return map;
	}
	public static Map<Itinerary, WebElement> findBetween(Map<Itinerary, WebElement> itineraryMap, String start, String end) {
		LocalTime startTime = LocalTime.parse(start, DateTimeFormatter.ofPattern("h:mma"));
		LocalTime endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("h:mma"));
		return itineraryMap.entrySet().stream().filter(it->it.getKey().getTime().compareTo(startTime) >=0 && it.getKey().getTime().compareTo(endTime)<=0).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public static Itinerary findCheapest(Set<Itinerary> itineraries) {
		Itinerary cheapest = itineraries.stream().min((it1, it2) -> Float.compare(it1.getFare(), it2.getFare())).get();
		return cheapest;
	}

	public static void addToJourney(){
		WebElement button = CONTAINER.getAddtoJourneyButton();
		try {
			ThreadUntils.sleep(TimeUnit.SECONDS, 2);
			FileUtils.copyFile(Utility.captureBitMap(button), new File("/tmp/files/div.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		button.click();
	}
	
	private static LocalTime parseLeaveTime(WebElement option) {
		Pattern pattern = Pattern.compile("\\s*Departs\\s*(\\d?\\d+:\\d?\\d+\\s*(?:AM|PM))");
		Matcher match = pattern.matcher(option.getText());
		if (match.find()) {
			return LocalTime.parse(match.group(1),DateTimeFormatter.ofPattern("h:mm a"));
		}
		return null;
	}

	private static float parseFare(WebElement option) {
		Pattern pattern = Pattern.compile("From \\$(\\d+)");
		Matcher match = pattern.matcher(option.getText());
		Float price = 0.0f;
		if (match.find())
			price = Float.parseFloat(match.group(1));
		return price;
	}
	

}
