@OfficeScheduler_CaseListFilter
Feature: As Office Scheduler, I want to validate Filters
   @CaseListFilter1
    Scenario: Office scheduler is on case tracker
       Given "user" is on case tracker landing page
       Then User should see following filters
       | SORT BY |
       | FILTER BY |
       | OTHER FILTERS |
       | PROCEDURE DATE |
    @CaseListFilter1
    Scenario: Validate SORT BY list
        When User clicks SORT BY filter
        Then SORT BY filter should have following sub topics
        | PROCEDURE DATE |
        | LOCATION |
        | PATIENT NAME |
        | DATE CREATED |
        And User should see following list in SORT BY filter
        | PROCEDURE DATE | LOCATION     | PATIENT NAME | DATE CREATED |
        | NEAREST        | LOCATION A-Z | PATIENT A-Z  | OLDEST       |
        | FURTHEST       | LOCATION Z-A | PATIENT Z-A  | NEWEST       |
	
	@CaseListFilter1
    Scenario: Validate FILTER BY list
        When User clicks FILTER BY filter
        Then User should see following list in FILTER BY filter
        | ALL PREFERRED          |
        |CORDOVEZ MD, LEAH G.    |
        |Dovan MD, Thomas T.     |
        |Elrod MD, Burton F.     |
        |Glattes MD, Rudolph C.  |
        |Martin DO, Amanda D.    |
        |Moore MD, David R.      |
        |Willers MD, Jeffrey D.  |
   
    @CaseListFilter1
    Scenario: Validate OTHER FILTERS filter
        When User clicks OTHER FILTERS filter
        Then User should see following list in OTHER FILTERS filter
        | ALL |
        | BOOKMARKED |
        | UNSCHEDULED |
        | SCHEDULED |
        | CANCELLED |
        | PENDING_CANCELLED |
        | NEEDS REVISION |
        | REVISED |
        | WARNINGS |
        | INPATIENT |
        | OUTPATIENT |
    
    @CaseListFilter1
    Scenario: Validate PROCEDURE DATE filter
        When User clicks PROCEDURE DATE filter
        Then User should see following list in PROCEDURE DATE filter
        | ANY |
        | TODAY |
        | TOMORROW |
        | NEXT WEEK |
        | NEXT MONTH |
        | CUSTOM |
	
	@CaseListFilter1
    Scenario: Browser quits
        Then Browser quits