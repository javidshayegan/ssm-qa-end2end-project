Feature: As a case tracker user I should be able to provide feedback on the application so that I can express my ideas for product improvements

@Verbiage1
	Scenario: Office scheduler is on case tracker
    	Given "Office Scheduler" is on case tracker landing page
    
@Verbiage
	Scenario: Validating My Preferences verbiage and Functionality
		Given "user" clicks on User opens the header menu1
		When "user" clicks on Account Setting/My Account button on user username
		And "user" clicks on My Preferences
		And "user" is taken to my Preferences page
		And "user" verifies the following
		|My Preferences Title	|	My Preferences Text										|
		|						|Receive realtime updates of the activity within your cases |
		Then "user" check mark My Preferences Checkbox
		And "user" clicks on Save button 
		And "user" returns to Home page

@Verbiage	
	Scenario: Verifiying My Preferences notification functionality
		Given "user" is on My Preference Home Page
		When "user" clicks to uncheck the Checkbox
		And "user" clicks on Save Button
		Then "user" returns to the Home page

@Verbiage
	Scenario: Verifying the Send Feedback Functionality
		Given "user" clicks on User opens the header menu2
		When "user" clicks on Account Setting/My Account button on user username
		And  When the "Send Us Feedback" link is clicked, a new tab should open to the URL
		And "user" is taken to "https://ascensioncasetracker.ideas.aha.io/" URL
		Then "user" quite the browser