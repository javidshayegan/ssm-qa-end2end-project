@WarningsAndAlertSystem
Feature: Warning and alert system using OR Scheduler and Office Scheduler

@Warnings
Scenario:  Requesting a revision process with OR
	Given "user" is on "https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc"
	When "user" clicks on "Other Filters" and select "Unscheduled" from the dropdown
	And "user" clicks on an "unscheduled" filtered case

@Warnings
Scenario:  Requesting a revision process
	Given "user" sees unschedled cases "https://mit-surgical-ui-qa.app.cloud-02.pcf.ascension.org/cases/5f07b49f02145b004364342e" 
	When "user" clicks on "REQUEST REVISION" button
	And "user" verifies "Request Revision" dashboard is open
	And "user" selects "Time not available" Option
	Then "user" enters a message on "Message Box"
	|		Write A Message		  		|
	| Cancel a procedure is requested!  			|
	And "user" clicks on a "Request Revision" button
@Warnings
Scenario: Verify a revision is requested
	Given "user" is on warnings page
	When "user" verifies NEEDS REVISION section contains
	|NEEDS REVISION				| Message 			|
	|A revision request is pending approval.|Cancel a procedure is requested!|
	Then "user" logs out of OR
  	And Browser quits
@Warnings
Scenario: Respond to Request of revision or mark as resolved with Office Scheduler 
	Given "user" logs in with Office Scheduler "https://casetracker-qa.app.cloud-02.pcf.ascension.org/"
	When "user" clicks on "Needs Revision" on homepage to review
	And "user" is taken to Needs Revision page "https://casetracker-qa.app.cloud-02.pcf.ascension.org/queue/0?state=NEEDS_REVISION"
	Then "user" verifies NEEDS REVISION section contains
	|NEEDS REVISION				| Message 			 |
	|A revision request is pending approval.|Cancel a procedure is requested!|
	And "user" click on Mark as resolved button
	Then "user" verify "Mark as Resolved" dashboard is open
	And "user" clicks on "Change procedure time" circle button
	And "user" enters the following message on "Message Box"
	|	Write this Message	        |
	|   This Case is resolved!  		|
	And "user" clicks on "Mark as Resolved" button on dashboard
	And "user" is taken to Needs Revision page "https://casetracker-qa.app.cloud-02.pcf.ascension.org/queue/0?state=NEEDS_REVISION"
	Then "user" verifies Revised section contains
	|REVISED					| Message 			 |
	| A revision request is pending approval.	  | This Case is resolved! 	 |
	And "user" logs out of Office Scheduler

@Warnings
Scenario:  Accepting Pending Approvals with OR Scheduler 
	Given "user" is on "https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc"
	When "user" clicks on Revised "Revised" button
	And "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/queue/0?state=REVISED"
	And "user" verifies "REVISED" section contains
	|		REVISED				| Message 			 |
	| A revision request is pending approval.	| This Case is resolved! 	 |
	And "user" clicks on Approve Button
	Then "user" logs out of OR
	And Browser quits
