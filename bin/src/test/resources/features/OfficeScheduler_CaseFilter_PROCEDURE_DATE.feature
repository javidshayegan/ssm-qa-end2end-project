@OfficeScheduler_CaseFilter_PROCEDURE_DATE
Feature: As Office Scheduler, I want to validate PROCEDURE DATE filter
 
 @CaseListFilter1
 Scenario: Office scheduler is on case tracker
    Given "user" is on case tracker landing page
 
 @CaseListFilter1 
 Scenario: User clicks TODAY under PROCEDURE DATE
    When User clicks PROCEDURE DATE
    And User clicks TODAY
    Then Date in Case Card should be today
    And Case Count will display the total number of cases as per filters applied
 
 @CaseListFilter0
 Scenario: User clicks TOMORROW under PROCEDURE DATE
    When User clicks PROCEDURE DATE
    And User clicks TOMORROW
    Then Date in Case Card should be tomorrow
    And Case Count will display the total number of cases as per filters applied

@CaseListFilter1
 Scenario: User clicks NEXT WEEK under PROCEDURE DATE
    When User clicks PROCEDURE DATE
    And User clicks NEXT WEEK
    Then Date in Case Card should be a week from today
    And Case Count will display the total number of cases as per filters applied

@CaseListFilter1  
  Scenario: User clicks NEXT MONTH under PROCEDURE DATE
    When User clicks PROCEDURE DATE
    And User clicks NEXT MONTH
    Then Date in Card should be a month from today
    And Case Count will display the total number of cases as per filters applied

@CaseListFilter1
Scenario: Office scheduler wants to open custom calendar
    When User opens CUSTOM calendar
    Then User should see Calendar
      | RESET | CANCEL | APPLY |
    And User closes calendar

@CaseListFilter1
  Scenario: Office scheduler wants to reset current date range
    When User opens CUSTOM calendar
    And User resets calendar
    Then Start date and End date input become empty
    And APPLY button is disabled
    And User closes calendar

@CaseListFilter0
Scenario: Office scheduler wants to set 3 days from today
    When User opens CUSTOM calendar
    And User enters following start date and end date
      | START_DATE | END_DATE |
      | 0          | 3        |
    And User applies date
    Then Date in Case Card should be 3 days from today

@CaseListFilter0
  Scenario: Office scheduler wants to set 10 days from today
    When User opens CUSTOM calendar
    And User enters following start date and end date
      | START_DATE | END_DATE |
      | 0          | 10       |
    And User applies date
    Then Date in Case Card should be 10 days from today
	And Browser quits
