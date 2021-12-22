@OfficeScheduler_EditSurgeryRequest
Feature: As Office Scheduler, I want to edit surgery request initiated by Office Scheduler

  Scenario: validate request surgery page contents
    Given "user" is on "https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc"
    When "user" clicks "Request a surgery" on the top right corner of the page
    Then "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/request"
    And the following forms are available
      | Appointment Details| Patient Details | Surgery Information | Pre-Admit Testing Details|

  Scenario: filling out appointment details
    Given "user" is on appointment details form
    When "user" confirms the following fields are available
      | APPOINTMENT LOCATION | REQUESTED SURGERY DATE AND TIME | TO FOLLOW | BLOCK TIME | ORDER STATUS/AUTH STATUS |
    Then "user" fills the form with the following information
      | APPOINTMENT LOCATION | DATE       | TIME  | MERIDIAN | TO FOLLOW | BLOCK TIME | ORDER STATUS/AUTH STATUS |
      | Ascension Saint Thomas Hospital Midtown BH CSC|            |       | A.M.     | Check	   |            | Inpatient                |

  Scenario: filling out patient details
    Given "user" is on the Patient Details form
    When "user" confirms the following fields are available
      | FIRST NAME      |LAST NAME   | DATE OF BIRTH  | SSN   | SEX   | PHONE NUMBER     | EMAIL    | LATEX ALLERGY |IODINE ALLERGY | Primary Insurance Details| Add Secondary Request |
    Then "user" fills the form with the following information in form
      | FIRST NAME | LAST NAME  | MIDDLE NAME   | DATE OF BIRTH | SSN        | SEX | PHONE NUMBER   | EMAIL          | LANGUAGE| LATEX ALLERGY | IODINE ALLERGY |
      | Brad		 | Pitts      | Test	  |01/01/1990    | 093-23-3914 | F              |(314)-323-3223 | ajkdh@email.com    | Spanish| YES  | YES |
    And "user" fills the Primary Insurance Details with the following information
      | PRIMARY INSURED LAST NAME | PRIMARY INSURED FIRST NAME | PRIMARY HEALTH PLAN NAME  | PRIMARY POLICY NUMBER | PRIMARY GROUP NUMBER | PRIMARY PRE-CERT NUMBER | PRIMARY PRE-AUTH IN PROGRESS |
      | Tom                       | Test                       | Blue cross                | 1023902139             | 12                   | 20349309                | YES                          |
    Then "user" clicks Add Secondary Insurance to provide the following inputs
      | SECONDARY INSURED LAST NAME | SECONDARY INSURED FIRST NAME | SECONDARY HEALTH PLAN NAME | SECONDARY POLICY NUMBER | SECONDARY GROUP NUMBER | SECONDARY PRE-CERT NUMBER | SECONDARY PRE_AUTH IN PROGRESS |
      | Bob                         | Test                         | eHealth                    | 23048324                | 2                      | 123434324                 | YES                            |

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

  Scenario: Adding a Procedure
    When "user" clicks Add manually button
    Then "user" confirms the following files are available in Add a Procedure
      | SURGEON       		|
      | CPT CODE(S) 		|
      | PROCEDURE NAME      |
      | MODIFIER 			|
      | PRIMARY PROCEDURE?	|
      | DURATION |
      | ANESTHESIA TYPE 	|
      |  IMPLANTS NEEDED 	|
      |  OTHER EQUIPMENT AND SUPPLIES |
      | ADDITIONAL PROCEDURE COMMENTS |
    And "user" adds surgical procedures information
      | SURGEON       | CPT CODE(S) | PROCEDURE NAME       | MODIFIER SIDE | MODIFIER APPROACH | PRIMARY PROCEDURE? | DURATION | ANESTHESIA TYPE | IMPLANTS NEEDED | OTHER EQUIPMENT AND SUPPLIES | ADDITIONAL PROCEDURE COMMENTS |
      | David Moore | 23410       | Shoulder Arthroscopy | Left          | Anterior          | 	YES                | 60       | General         | NO               | None                         | None                          |

  Scenario: filling pre-admit testing details
    Given "user" is on pre-admit testing form
    When "user" confirms the following fields are available in the pre-admit form
      | IS A PRE-HOSPITAL BED NEEDED? | IS PRE-ADMISSION TESTING NEEDED |
    Then "user" fills the form with the pre-admit form with the following information
      | IS A PRE-HOSPITAL BED NEEDED? | IS PRE-ADMISSION TESTING NEEDED |
      | Direct Admit                  | NO                              |
    And "user" clicks "Submit Surgery Request"

  Scenario: going back to the homepage and verify the request
    When "user" clicks HOME button
    And "user" search a request by name
    Then "user" should be able to see the request just made
      | Patient Name 	 | Procedure 			| Location 					| Surgeon 				|
      | Brad, Pitts Test | Shoulder Arthroscopy | Ascension Saint Thomas Hospital Midtown BH CSC | Cordovez MD, Leah G.  |

  Scenario: Editing surgery request
    Given Office Scheduler already requested a new surgery
    When Office Scheduler edits surgery request
    | OPTION | CHANGE_PROCEDURE_TIME | MISSING_INFORMATION | OTHER | MESSAGE |
    | YES    | V                     |                     |       | Change procedure time test |
    Then Alerts have REVISED
    | Change procedure time | Change procedure time test |
    Then Browser quits
    