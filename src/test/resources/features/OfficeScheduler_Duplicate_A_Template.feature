Feature: As Office Scheduler, I want to make a duplicate of a template

@Duplicate @smoke @DSSC-932
	Scenario: User logs in with Office Manager credential on case tracker
	    Given "user" is on Case Tracker landing page	

@Duplicate @smoke @DSSC-973
	Scenario: User validate "Account Setting-My Account"page exist and has the required fields
		Given "user" is logged in on Case Tracker homepage       
		When "user" clicks on Account Setting/My Account button on user username  
		And "user" is taken to "My Information" page 
		Then User verifies the following field exists
			|MY INFORMATION	| 
			|MY PREFERENCES	|
			|My SURGEONs	|
			|MY TEMPLATES 	|

@Duplicate @DSSC-973
	Scenario: Office scheduler user try to Duplicate a template already created
		Given "user" is on "My Template" page
		When "user" clicks on three dotted icon then on "Duplicate" button on the top right corner of a Template Tile
		Then "user" verifies Duplicate Template modal popup
		#	|Duplicate Template |
			#|TEMPLATE NAME*		 |
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
		And "user" enters the following information
		|TEMPLATE NAME			 |CPT CODES |OTHER EQUIPMENT AND SUPPLIES|ADDITIONAL PROCEDURE COMMENTS					|
		|Duplicated Test Template|56781		|	No other supplies needed |This is for duplicated option testing purpose |
		And "user" clicks on "Update" Button
		#Then "user" is automatically paginates to the duplicated template page  
		And "user" verifies Duplicated template name on Template page
		|TEMPLATE NAME			 |	
		|Duplicated Test Template|
		Then "user" deletes the duplicated template and logs out



