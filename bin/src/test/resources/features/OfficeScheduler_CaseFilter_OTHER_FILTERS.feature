@OfficeScheduler_CaseFilter_OTHER_FILTERS
Feature: As Office Scheduler, I want to validate OTHER FILTERS filter
  
  @CaseListFilter1
  Scenario: Office scheduler is on case tracker
    Given "user" is on case tracker landing page
  @CaseListFilter1	
  Scenario Outline: User selects other filters
    When User clicks OTHER FILTERS filter
    And User selects "<filter>" in the other filters filter
    Then Case Card should have "<filter>" tag
    And Case Count will display the total number of cases as per filters applied
    Examples:
    | filter |
    | BOOKMARKED |
    | UNSCHEDULED |
    | SCHEDULED |
    | CANCELLED |
    | PENDING CANCELLATION |
    | NEEDS REVISION |
    | REVISED |
    | WARNING |
    | INPATIENT |
    | OUTPATIENT |
 @CaseListFilter1
  Scenario: Office scheduler closes the browser
    When Browser quits
