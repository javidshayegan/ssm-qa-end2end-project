Feature: As an office Scheduler, I should be able to create a new template from "My Templates" launch control in My Accounts display.

@Create  @smoke @DSSC-847
	Scenario: Office scheduler is on case tracker
		Given "user" is on Case Tracker landing page
	
@Create @smoke @DSSC-838
	Scenario: A Validate "Account Setting-My Account"page exist and has the required fields
		Given "user" is logged in on Case Tracker homepage       
		When "user" clicks on Account Setting/My Account button on user username  
		And "user" is taken to "My Information" page 
		Then User verifies the following field exists
			|MY INFORMATION	 | 
			|MY NOTIFICATIONS|
			|My SURGEONs	 |
			|MY TEMPLATES 	 |
			#|SCHEDULING PREFERENCES| NOTE: this is not implemented yet

@Create @smoke @DSSC-838
	Scenario: B Validate "My Templates" launch control exist and has the required fields
		Given "user" on My Information Page
		When "user" clicks on "My Templates" button
		Then "user" is taken to "My Template" page
		And "user" verifies the following fields
		|My Templates		|
		|CREATE TEMPLATE	|
		#|Surgeons dropdown	| NOTE: this is not implemented yet

@Create @DSSC-838
	Scenario: C Validate the fields on "CREATE TEMPLATE" page
		Given "user" on "My Template" page
		When "user" clicks on the "CREATE TEMPLATE" button
		Then "user" verifies Create Template modal popup
		And "user" verifies the following field exists
		|TEMPLATE NAME*					|
		|SURGEON						|
		|CPT CODE  						|
		#|FREQUENTLY USED click to add 	|NOTE: this is not implemented yet
		|PROCEDURE NAME*				|
		|MODIFIER						|
		|PRIMARY PROCEDURE?				|
		|DURATION						|
		|ANESTHESIA TYPE				|
		|IMPLANTS NEEDED?				|
		|OTHER EQUIPMENT AND SUPPLIES:	|
		|ADDITIONAL PROCEDURE COMMENTS	|
		|Cancel button					|
		#|Create Button					|
		
@Create @DSSC-838
	Scenario: D Create a new template using "CREATE TEMPLATE" launch control
		Given "user" is on "Create Template" modal
		When "user" verifies template Modal title
		Then "user" enters the following data
		|TEMPLATE NAME*				|SURGEON	|CPT CODE(S)|FREQUENTLY USED click to add 	|PROCEDURE NAME*|Type Procedure name |Side|Approach dropdown	| PRIMARY PROCEDURE	|DURATION	|ANESTHESIA TYPE			|IMPLANTS NEEDED?	|VENDOR  |OTHER EQUIPMENT AND SUPPLIES	|ADDITIONAL PROCEDURE COMMENTS		|	
		| Standard Cholosectomy JE	|			|23410		|								|				|Shoulder Arthroscopy|Left|Anterior				|			yes		|120		|Select Anestheisia Type	|Yes				|Hospital|No Additional supplies needed	|No Comment is needed, just a test	|
		And "user" click on "Create" button 
		#And "user" verifies a Success Toaster is displayed

@Create @DSSC-838
	Scenario: E Validate the newly created Template is displayed as tile on MY Template page and has the required fields
		Given "user" is on My Template page
		When "user" verifies the the newly created template exist on the tile's list
		#Then "user" verifies following fields in newly created Templates tile
		#|Template name	   |#Procedure Name	     	| #Surgeon Name						|DURATION|#Last Used			   			|Anesthesia Type|Additional Procedure Comments	|		
		#|Standard Cholosectomy JE|Standard Cholosectomy JE|NOTE: Not required but may exist  	|120     |NOTE: Not required but may exist 	|		   		| 								|
		#|Procedure Name|NOTE: Not required but may exist
		#And "user" verifies a green "NEW" tag displays on tile
		And "user" logs out of the Office Scheduler 		
	
		
#@Create1 F @DSSC-838
	#Scenario: Validate the newly created Template is displayed as tile on MY Template page and has the required fields
		#Given "user" is on My Template page
		#When "user" verifies the the newly created template exist on the tile's list
		#Then "user" verifies following fields in newly created Templates tile
		#|Template name	   |#Procedure Name	     | #Surgeon Name			|DURATION|#Last Used			   |Anesthesia Type|Additional Procedure Comments|		
		#|Test Template 2020| Standard Cholosectomy JE|NOTE: Not required but may exist  |120     |NOTE: Not required but may exist |		   | 				|
		##|Procedure Name|NOTE: Not required but may exist
		#And user verfies a green "NEW" tag displays on tile |NOTE: Not implemented but may exist later
		#And "user" logs out of the Office Scheduler 