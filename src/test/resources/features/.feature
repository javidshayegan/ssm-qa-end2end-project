@OfficeScheduler_MyTemplate_Create_DSSC-847
Feature: As an office Scheduler, I should be able to create a new template from "My Templates" launch control in My Accounts display.

@Regression1 @smoke @DSSC-847
Scenario: Office scheduler is on case tracker
	Given "user" is on Case Tracker landing page
	
@Regression1 @smoke @DSSC-838
Scenario: Validate "Account Setting/My Account"page exist and has the required fields
	Given "user" is logged in on Case Tracker homepage       
	When "user" clicks on Account Setting/My Account button on user username  
	And "user" is taken to "My Information" page 
	Then User verifies the following field exists:
		
		|MY INFORMATION| 
		|MY PREFERENCES|
		#|SCHEDULING PREFERENCES| NOTE: this is not implemented yet
		|MY TEMPLATES | 	
	
@Regression @smoke @DSSC-838
Scenario: Validate "My Templates" launch control exist and has the required fields
	Given "user" on My Information Page
	When "user" clicks on "My Templates" button
	Then "user" is taken to "My Template" page
	And "user" verifies the following fields:
		
		|My Templates		|
		|CREATE TEMPLATE	|
		#|Surgeons dropdown	| NOTE: this is not implemented yet
	
@Regression @DSSC-838
Scenario: Validate the fields on "CREATE TEMPLATE" page
	Given "user" on "My Template" page
	When "user" clicks on "CREATE TEMPLATE" button
	Then "user" verifies Create Template modal popup
	And "user" verifies the following field exists:
		
		|TEMPLATE NAME*					|
		|SURGEON			 			|
		|CPT CODE  						|
		|FREQUENTLY USED click to add 	|
		|PROCEDURE*				|
		|MODIFIER				|
		|PRIMARY PROCEDURE?		|
		|DURATION			|
		|ANESTHESIA TYPE		|
		|IMPLANTS NEEDED?		|
		|OTHER EQUIPMENT AND SUPPLIES:	|
		|ADDITIONAL PROCEDURE COMMENTS	|
		|Cancel button			|
		|Create Button			|	
		|TEMPLATE NAME*			|
		|SURGEON			|
		|CPT CODE  			|
		|FREQUENTLY USED click to add 	|
		|PROCEDURE*			|
		|MODIFIER			|
		|PRIMARY PROCEDURE?		|
		|DURATION			|
		|ANESTHESIA TYPE		|
		|IMPLANTS NEEDED?		|
		|OTHER EQUIPMENT AND SUPPLIES:	|
		|ADDITIONAL PROCEDURE COMMENTS	|
		|Cancel button			|
		|Create Button			|
@Regression @DSSC-838
Scenario: Create a new template using "CREATE TEMPLATE" launch control
	Given "user" is on "Create Template" dashboard
	When "user" verifies template dashboard title
	Then "user" enters the following data:
|TEMPLATE NAME*			| Standard Cholosectomy JE	|
|SURGEON			| Select a Surgeon		|
|CPT CODE			| 234342			| 
|FREQUENTLY USED click to add 	| click add			|
|PROCEDURE *			| Type Procedure name		|
|MODIFIER			| 
	|Side dropdown		| 
	|Approach dropdown	|

|PRIMARY PROCEDURE?		|Yes 				|
|DURATION			|120				|
|ANESTHESIA TYPE		|Select Anestheisia Type	|
|IMPLANTS NEEDED? 		|Yes				|
|OTHER EQUIPMENT AND SUPPLIES	|Enter text here		|
|ADDITIONAL PROCEDURE COMMENTS	|Enter text here 		|

	And "user" click on "Create" button 
	And "user" verifies a Success Toaster is displayed

@Regression @DSSC-838
Scenario: Validate the newly created Template is displayed as tile on MY Template page and has the required fields
	Given "user" is on My Template page
	When "user" verifies the the newly created template exist on the tile's list
	Then "user" verifies following fields in newly created Templates tile:
		|Template name		|Standard Cholosectomy JE| 
		#|Surgeon Name		| 			|NOTE: Not required but may exist
		#|Procedure Name	|			|NOTE: Not required but may exist
		|Duration 		|	120		|
		#|Last Used		|			|NOTE: Not required but may exist
		|Anesthesia Type		|	Enter text here|
		|Additional Procedure Comments	| 	Enter text here|
	#And user verfies a green "NEW" tag displays on tile |NOTE: Not implemented but may exist later
	And "user" logs out of the Office Scheduler 

