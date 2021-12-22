@OfficeScheduler_CaseFilter_FILTER_BY
Feature: As Office Scheduler, I want to validate FILTER BY filter
  Scenario: Office scheduler is on case tracker
    Given "user" is on case tracker landing page
  
  @CaseListFilter0 
  Scenario Outline: User selects surgeon(s) to filter cases under FILTER BY filter
    When User clicks FILTER BY filter
    And User selects "<surgeon>" in the filter by filter
    Then Surgeon Name in Case Card should be "<surgeon>"
    And Case Count will display the total number of cases as per filters applied
    Examples:
    | surgeon |
    | CORDOVEZ MD, LEAH G.	 |
    | DOVAN MD, THOMAS T.	 |
    | ELROD MD, BURTON F.	 |
    | GLATTES MD, RUDOLPH C. |
    | MARTIN DO, AMANDA D.	 |
    | MOORE MD, DAVID R.   	 |
    | WILLERS MD, JEFFREY D. |
    
  Scenario: Office scheduler closes the browser
    When Browser quits
