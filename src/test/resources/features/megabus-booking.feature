@run
Feature: Test Megabus.com booking process

@firefox
Scenario: Open firefox browser
	Given I open a firefox browser
	
@chrome	
Scenario: Open chrome broswser
	Given I open a chrome browser
	
@chrome	@firefox	 	
Scenario: input departure and return info 
	Given I navigate to the megabus
	When I try to set departure state as 'Massachusetts'
	   And I try to set departure city as 'Boston, MA'
	   And I try to set arrival city as 'New York, NY'
	   And I try to set departure date as '10/11/2016'
	   And I try to set return date as '11/23/2016'
	   And I click the search button   
	Then I should get bus information for my input

@chrome	@firefox	
Scenario: select itinerary of best time and fare
	 Given I try to find departure time between 8:00AM and 12:30PM 
	    And I try to find return time between 11:30AM and 2:30PM
	 When I click add to journey button
	    And I click continue button   
	    And I click continue button again
	    And Input my mobile phone number 702-350-8903 
	    And Check agree checkbox
	 Then Click submit button 
	 	And Click visa icon