Feature: As an office scheduler, I need to edit templates so that the procedure details are up to date.

@Edit  @smoke @DSSC-847
Scenario: Office scheduler is on case tracker
	Given "user" is on Case Tracker landing page		
	
@Edit @smoke @DSSC-838
	Scenario: Validate "Account Setting-My Account"page exist and has the required fields
		Given "user" is logged in on Case Tracker homepage       
		When "user" clicks on Account Setting/My Account button on user username  
		And "user" is taken to "My Information" page 
		Then User verifies the following field exists
			|MY INFORMATION	| 
			|MY PREFERENCES	|
			|My SURGEONs	|
			|MY TEMPLATES 	|
			#|SCHEDULING PREFERENCES| NOTE: this is not implemented yet

@Edit @smoke @DSSC-886
	Scenario: Validate "My Templates" launch control exist and has the required fields
		Given "user" on My Information Page
		When "user" clicks on "My Templates" button
		Then "user" is taken to "My Template" page
		And "user" verifies the following fields
			|My Templates		|
			|CREATE TEMPLATE	|
			|Surgeons dropdown	|

@Edit @DSSC-886
	Scenario: Validate the fields on "EDIT" modal
		Given "user" is on "My Template" page
		When "user" clicks on three dotted icon then on "EDIT" button on the top right corner of a Template Tile
		Then "user" verifies Edit Template modal popup
			|Edit Template Test	|
			|Edit Template		|
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
			#|Update Button					|

@Edit @DSSC-886
	Scenario: Edit a template using "Edit" launch control
		Given "user" is on "EDIT Template" modal
		And "user" enters the following data to Edit the Template
			|TEMPLATE NAME*								|SURGEON	|CPT CODE(S)|FREQUENTLY USED click to add 	|PROCEDURE NAME*|Type Procedure name 			|Side	|Approach dropdown	|PRIMARY PROCEDURE	|DURATION 	|ANESTHESIA TYPE		|IMPLANTS NEEDED?	|VENDOR  |OTHER EQUIPMENT AND SUPPLIES				|ADDITIONAL PROCEDURE COMMENTS		|	
			| Standard Cholosectomy JE Edited for test  |			|20000		|								|				|Shoulder Arthroscopy Test Edit	|Right	|Anterior			|	 				|40			|Select Anestheisia Type|Yes				|Hospital A|Editing, No Additional supplies needed	|Editing, No Comment is needed, just a test	|
		And "user" click on "Update" button 
		#And "user" verifies a Success Toaster is displayed

@Edit @DSSC-886
	Scenario: Validate the newly edited Template is displayed as tile on MY Template page and has the required fields
		Given "user" is on My Template page
		When "user" verifies the the newly edited template exist on the tile's list
		Then "user" verifies following fields in newly edited Templates tile
		|Template name	   						 |Procedure Name	     		|Surgeon Name						|DURATION|Last Used			   				|Anesthesia Type|Additional Procedure Comments	|		
		|Standard Cholosectomy JE Edited for test|Shoulder Arthroscopy Test Edit|NOTE: Not required but may exist  	|40      |NOTE: Not required but may exist 	|		   		| 								|
		Then  "user" verifies a green "EDIT" tag displays on tile
		And "user" logs out of the Office Scheduler 
		#Procedure Name NOTE: Not required but may exist		