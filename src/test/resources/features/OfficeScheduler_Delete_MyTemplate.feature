Feature: As an Office Scheduler, I need to be able to remove a surgical procedure template that is not in use, so I can more efficiently manage procedure templates

@Delete  @smoke @DSSC-932
	Scenario: User logs in with Office Manager credential on case tracker
	Given "user" is on Case Tracker landing page		
	
@Delete @smoke @DSSC-932
	Scenario: User validate "Account Setting-My Account"page exist and has the required fields
		Given "user" is logged in on Case Tracker homepage       
		When "user" clicks on Account Setting/My Account button on user username  
		And "user" is taken to "My Information" page 


@Delete @smoke @DSSC-932
	Scenario: Validate "My Templates" launch control exist and has the required fields
		Given "user" on My Information Page
		When "user" clicks on "My Templates" button
		Then "user" is taken to "My Template" page
		And "user" verifies the following fields
			|My Templates		|
			|CREATE TEMPLATE	|
			|Surgeons dropdown	|

@Delete @DSSC-932
	Scenario: Office manager user try to edit a template already created
		Given "user" is on "My Template" page
		When "user" clicks on three dotted icon then on "EDIT" button on the top right corner of a Template Tile
		Then "user" verifies Edit Template modal popup
			|Edit Template Test	|
			|Edit Template		|

@Delete @DSSC-932
	Scenario: User is on Edit template modal and waits for Office Scheduler user to login and see the lock sign on the template being edited
		Given "user" is on "EDIT Template" modal

@Delete @DSSC-932
	Scenario: User logs in with Office Scheduler credential 
		Given "user" is on Case Tracker landing page
	
@Delete @DSSC-932
	Scenario: User validate a lock sign is ON for the template being edited by Office Manager user 
		Given "user" is logged in on Case Tracker homepage       
		When "user" clicks on Account Setting/My Account button on user username  
		And "user" is taken to "My Information" page 
		And "user" is on My Template page
		And "user" verifies a lock sign is ON "Template locked" on the template being edited by the Office Manager user
		Then "user" logs out of the Office Scheduler

@Delete @DSSC-932
	Scenario: User logs in with Office Scheduler credential again to delete the latest template 
		Given "user" is on Case Tracker landing page

@Delete @DSSC-932
	Scenario: User validate the deletion and cancelling of a deteting process of a template
		Given "user" is logged in on Case Tracker homepage       
		When "user" clicks on Account Setting/My Account button on user username  
		And "user" is taken to "My Information" page
		And "user" is on My Template page
		And "user" clicks on "Cancel" button to varifies cancel button works on deleting a template process
		Then "user" verifies the template still exist 
		Then "user" clicks on "Delete" button on the last template created and delete the selected template
		Then "user" logs out of the Office Scheduler