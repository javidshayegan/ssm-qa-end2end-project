Feature: Add a template from Add From Template modal

@AddTest1  @smoke @DSSC-847
 Scenario: Create a new template using "CREATE TEMPLATE" launch control
		Given "user" is on Case Tracker landing page
		And "user" is logged in on Case Tracker homepage
		When "user" clicks on Account Setting/My Account button on user username  
		And "user" is taken to "My Information" page 
		And "user" clicks on "My Templates" button
		And "user" clicks on the "CREATE TEMPLATE" button
		Then "user" enters the following data
		|TEMPLATE NAME*				|SURGEON	|CPT CODE(S)|FREQUENTLY USED click to add 	|PROCEDURE NAME*|Type Procedure name |Side|Approach dropdown	| PRIMARY PROCEDURE	|DURATION	|ANESTHESIA TYPE			|IMPLANTS NEEDED?	|VENDOR  |OTHER EQUIPMENT AND SUPPLIES	|ADDITIONAL PROCEDURE COMMENTS		|	
		| Standard Cholosectomy JE	|			|23410		|								|				|Shoulder Arthroscopy|Left|Anterior				|			yes		|120		|Select Anestheisia Type	|Yes				|Hospital|No Additional supplies needed	|No Comment is needed, just a test	|
		And "user" click on "Create" button 

@AddTest1
  Scenario: validate request surgery page contents
	Given "user" clicks "Request a surgery" on the top right corner of the page
	Then "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/request"
	And the following forms are available
	| Appointment Details| Patient Details | Surgery Information | Pre-Admit Testing Details|

@AddTest1
  Scenario: filling out appointment details
    Given "user" is on appointment details form
    When "user" confirms the following fields are available
    | APPOINTMENT LOCATION | REQUESTED SURGERY DATE AND TIME | TO FOLLOW | BLOCK TIME | ORDER STATUS/AUTH STATUS |
    Then "user" fills the form with the following information
    | APPOINTMENT LOCATION | DATE       | TIME  | MERIDIAN | TO FOLLOW | BLOCK TIME | ORDER STATUS/AUTH STATUS |
    |Ascension Saint Thomas Hospital Midtown, BH CSC    |            |       | A.M.     | Check	   |            | Inpatient                |

@AddTest1
  Scenario: filling out patient details
		Given "user" is on the Patient Details form
		When "user" confirms the following fields are available
		| FIRST NAME      |LAST NAME   | DATE OF BIRTH  | SSN   | SEX   | PHONE NUMBER     | EMAIL    | LATEX ALLERGY | Primary Insurance Details| Add Secondary Request |
		Then "user" fills the form with the following information in form
		| FIRST NAME | LAST NAME  | MIDDLE NAME   | DATE OF BIRTH | SEX | SSN        | PHONE NUMBER   | EMAIL          | LANGUAGE| LATEX ALLERGY |
		| Brad		 | Pitts      | Test	  |01/01/1990    | M               |093-23-3914 | (314)-323-3223 | ajkdh@email.com    | Spanish| YES        |
		And "user" fills the Primary Insurance Details with the following information
		| PRIMARY INSURED LAST NAME | PRIMARY INSURED FIRST NAME | PRIMARY HEALTH PLAN NAME | PRIMARY POLICY NUMBER | PRIMARY GROUP NUMBER | PRIMARY PRE-CERT NUMBER | PRIMARY PRE-AUTH IN PROGRESS |
		| Tom                       | Test                       | Blue cross               | 1023902139            | 12                   | 20349309                | YES                          |
		Then "user" clicks Add Secondary Insurance to provide the following inputs
		| SECONDARY INSURED LAST NAME | SECONDARY INSURED FIRST NAME | SECONDARY HEALTH PLAN NAME | SECONDARY POLICY NUMBER | SECONDARY GROUP NUMBER | SECONDARY PRE-CERT NUMBER | SECONDARY PRE_AUTH IN PROGRESS |
		| Bob                         | Test                         | eHealth                    | 23048324                | 2                      | 123434324                 | YES                            |

@AddTest1
  Scenario: filling surgery information
  Given "user" is on Surgery Information form
  When "user" confirms the following fields are available in surgery information
		 | PRIMARY DIAGNOSIS       |
		 | SECONDARY DIAGNOSIS     |
		 | SURGICAL PROCEDURES     |
		 | OTHER SURGICAL COMMENTS |
		 Then "user" fills the form with the following information in surgery information
		 | PRIMARY DIAGNOSIS | SECONDARY DIAGNOSIS | OTHER SURGICAL COMMENTS | SURGICAL PROCEDURES |
		 | ICD-10            | diagnosis  		  | Be safe!                | Add Manually        |

@AddTest1  
  Scenario: Add a template from Add From Template modal
    Given "user" is on Add From Template modal
	When "user" verifies the following in Add Procedure
	|Add a Procedure Title	| #Select a template Title|
	|Add a Procedure		| #Select a template		 |
	Then "user" clicks on a Template Tile
	And "user" verifies the following contents
	|Add a Procedure text|Review my template text|
	|Add a Procedure 	 |Review my template	 |
	And "user" choose "VENDOR CONTACTED?" Yes option
	And "user" adds the following comment on ADDITIONAL PROCEDURE COMMENTS
	|ADDITIONAL PROCEDURE COMMENTS|
	|This template is added using Add From Template Modal|
	And "user" clicks add button

@AddTest1
  Scenario: Verify a template tile is added
	Given "user" verifies the following
	|Procedure Name			|Duration|ANESTHESIA TYPE|ADDITIONAL PROCEDURE COMMENTS	|EDIT button	|REMOVE button|
	|Shoulder Arthroscopy	|120	 |				 |								|EDIT		  	|REMOVE		  |
	When "user" clicks on Edit button
	Then "user" change the procedure with the following
	|PROCEDURE NAME						|
	| Shoulder Arthroscopy Test updated |	
	And "user" clicks on Add button to reflect the newest change on Added Template
	Then "user" verifies the Template Tile is updated with the following text
	|PROCEDURE NAME						|
	| Shoulder Arthroscopy Test updated	|

@AddTest1
  Scenario: Adding a Procedure
 	When "user" clicks Add manually button
 	Then "user" confirms the following files are available in Add a Procedure
   | SURGEON       |
   | CPT CODE(S) | 
   | PROCEDURE NAME       | 
   | MODIFIER |
   | PRIMARY PROCEDURE? | 
   | DURATION | 
   | ANESTHESIA TYPE |
   |  IMPLANTS NEEDED |
   |  OTHER EQUIPMENT AND SUPPLIES |
   | ADDITIONAL PROCEDURE COMMENTS | 
 	And "user" adds surgical procedures information
 	| SURGEON       | CPT CODE(S) | PROCEDURE NAME      				 | MODIFIER SIDE | MODIFIER APPROACH | PRIMARY PROCEDURE? | DURATION | ANESTHESIA TYPE | IMPLANTS NEEDED | OTHER EQUIPMENT AND SUPPLIES | ADDITIONAL PROCEDURE COMMENTS | 
 	| David Moore | 23410       | Shoulder Arthroscopy Test updated | Left          | Anterior          | 	YES                | 60       | General         | NO               | None                         | None                          |

@AddTest1
  Scenario: filling pre-admit testing details 
    Given "user" is on pre-admit testing form
  	When "user" confirms the following fields are available in the pre-admit form
  	| IS A PRE-HOSPITAL BED NEEDED? | IS PRE-ADMISSION TESTING NEEDED |
  	Then "user" fills the form with the pre-admit form with the following information
  	| IS A PRE-HOSPITAL BED NEEDED? | IS PRE-ADMISSION TESTING NEEDED | 
  	| Direct Admit                  | NO                              |
  	And "user" clicks "Submit Surgery Request"

@AddTest1
	Scenario: going back to the homepage and verify the request
  	When "user" clicks HOME button
  	And "user" search a request by name
  	Then "user" should be able to see the request just made
	| Patient Name 	   | Procedure 			 				| Location 					 | Surgeon 			|
	| Brad, Pitts Test | Shoulder Arthroscopy Test updated  | St. Thomas Midtown BH CSC  | Moore MD, David  |
	And Unscheduled in task area should increment by one
    And Browser quits
