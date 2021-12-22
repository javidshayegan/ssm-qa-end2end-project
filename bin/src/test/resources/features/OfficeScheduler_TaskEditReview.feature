@OfficeScheduler_TaskEditReview
Feature: Reviewing and editing all tasks created as an Office Scheduler 

@Test1
Scenario: User validate review task page contents Reviewing all tasks
	Given "user" is on "https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc"
	When "user" clicks on the "REVIEW ALL TASKS" on the page 
	When "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/queue/0?status=UNSCHEDULED&state=NEEDS_REVISION&state=WARNING&task=1"
	Then user verifies the following sections are available 
		| APPOINTMENT REQUEST | PATIENT INFO | SURGERY INFO |PAT INFO|

@Test1		
Scenario: User edit a task already created
	Given "user" is on Review All Tasks page 
	When "user" clicks on "Edit Button" 
	And user verifies the following forms are available on Edit Dashboard 
		| APPOINTMENT LOCATION | REQUESTED SURGERY DATE AND TIME | TO FOLLOW | BLOCK TIME | ORDER STATUS/AUTH STATUS |
	Then "user" fills the form with the following information in the form 
		| APPOINTMENT LOCATION | DATE       | TIME  | MERIDIAN | TO FOLLOW | BLOCK TIME | ORDER STATUS/AUTH STATUS  |
		| ST Midtown BH CSC    |            |       | P.M.     |   UnCheck   |  Check 	| Outpatient                |
		
@Test1
Scenario: filling out patient details
	Given "user" is on the Patient Details form 
	When "user" confirms the following fields are available 
	
		| FIRST NAME      |LAST NAME   | DATE OF BIRTH  | SSN   | SEX   | PHONE NUMBER     | EMAIL    | 
		
	Then "user" fills the form with the following information in form 
		| FIRST NAME | LAST NAME  | MIDDLE NAME   | DATE OF BIRTH      | SSN        | PHONE NUMBER   | EMAIL          		    | INTERPRETER NEEDED |
		| John		 | Jackson    | Junior	  	  | 01/01/1987         | 093-23-000 | (314)-323-000  | JohnJackson@email.com    | 		NO 	   |
	And "user" fills the Primary Insurance Details with the following information 
		| PRIMARY INSURED LAST NAME | PRIMARY INSURED FIRST NAME | PRIMARY HEALTH PLAN NAME | PRIMARY POLICY NUMBER | PRIMARY GROUP NUMBER | PRIMARY PRE-CERT NUMBER | PRIMARY PRE-AUTH IN PROGRESS |
		| Tom                       | Test                       | Blue cross               | 1023902139            | 12                   | 20349309                | Yes                          | 
	Then "user" clicks Add Secondary Insurance to provide the following inputs 
		| SECONDARY INSURED LAST NAME | SECONDARY INSURED FIRST NAME | SECONDARY HEALTH PLAN NAME | SECONDARY POLICY NUMBER | SECONDARY GROUP NUMBER | SECONDARY PRE-CERT NUMBER |
		| Jocob                        | Test                         | BlueCross                 | 230230000               | 3                      | 123430000                 |
@Test1
Scenario: filling surgery information
	Given "user" is on Surgery Information form 
	When "user" confirms the following fields are available in surgery information 
		| PRIMARY DIAGNOSIS       |
		| SECONDARY DIAGNOSIS     | 
		| SURGICAL PROCEDURES     |
		| OTHER SURGICAL COMMENTS | 
	Then "user" fills the form with the following information in surgery information 
		| PRIMARY DIAGNOSIS | SECONDARY DIAGNOSIS | OTHER SURGICAL COMMENTS			  | SURGICAL PROCEDURES |
		| ICD-11            | diagnosis  		  | This page is edited!              | Add Manually        | 

@Test1
Scenario: Adding a Procedure
	When "user" clicks Add manually button 
	Then "user" confirms the following files are available in Add a Procedure 
		| SURGEON       		|
		| CPT CODE(S) 			| 
		| PROCEDURE NAME        | 
		| MODIFIER SIDE			|
		| PRIMARY PROCEDURE? 	| 
		| DURATION 				| 
		| ANESTHESIA TYPE 		|
		|  IMPLANTS NEEDED 	|
		|  OTHER EQUIPMENT AND SUPPLIES |
		| ADDITIONAL PROCEDURE COMMENTS | 
	And "user" adds surgical procedures information
		| SURGEON       | CPT CODE(S) | PROCEDURE NAME      | MODIFIER SIDE     | MODIFIER APPROACH | PRIMARY PROCEDURE? | DURATION | ANESTHESIA TYPE | IMPLANTS NEEDED | OTHER EQUIPMENT AND SUPPLIES | ADDITIONAL PROCEDURE COMMENTS | 
		| Cordovez MD   | 23410       | Shoulder Arthroscopy | Left          	| Anterior          | 	YES              | 60       | General         | No              | None                        | None                         |

@Test1		
Scenario: Filling pre-admit testing details
	Given "user" is on pre-admit testing form 
	When "user" confirms the following fields are available in the pre-admit form 
		| IS A PRE-HOSPITAL BED NEEDED? | IS PRE-ADMISSION TESTING NEEDED |
	Then "user" fills the form with the pre-admit form with the following information
		| IS A PRE-HOSPITAL BED NEEDED? | IS PRE-ADMISSION TESTING NEEDED | WHERE IS PAT TAKING PLACE?	|REQUESTED PAT APPOINTMENT DATE AND TIME (IF KNOWN) |
		| Direct Admit                  | Yes                             | PAT							|													|			
	And "user" clicks on Save Changes button
	
#Test1
#Scenario: Going back to the homepage and verify the request
#	When "user" clicks HOME button 
#	And "user" sort a request by Newest 
#	Then "user" should be able to see the request just made
#	And "user" clicks on the newly edited procedure
#	Then "user" is taken to the page "https://casetracker-qa.app.cloud-02.pcf.ascension.org/cases/5f75141cbc8f2b000e80cba0"
#	And "user" verifies a pending request message is displayed

@Test1
Scenario: office scheduler cancels a procedure
	Given "user" is on Task Review page again "https://casetracker-qa.app.cloud-02.pcf.ascension.org/queue/0?status=UNSCHEDULED&state=NEEDS_REVISION&state=WARNING&task=1"
	When "user" clicks on "Cancel Procedure" Button 
	And "user" verifies "Cancel Procedure" dashboard 
	Then "user" writes the following message
		| Message				|
		| Surgery not needed! 	|
	And "user" clicks on Confirm Cancellation
	And "user" verifies a pending cancellation message is displayed
	And "user" verifies the pending cancellation is initiated by the same user 
	And Browser quits
