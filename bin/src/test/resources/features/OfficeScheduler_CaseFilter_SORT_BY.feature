Feature: As Office Scheduler, I want to validate SORT BY Filter
  
  @CaseListFilter1
  Scenario: Office scheduler is on case tracker
    Given "user" is on case tracker landing page
  
  @CaseListFilter1
  Scenario: User clicks NEAREST of PROCEDURE DATE under SORT BY filter
    When User clicks SORT BY filter
    And User clicks NEAREST of PROCEDURE DATE
    Then Date in Case Card should be sorted in ascending order
    And Case Count will display the total number of cases as per filters applied
  @CaseListFilter1
  Scenario: User clicks FURTHEST of PROCEDURE DATE under SORT BY filter
    When User clicks SORT BY filter
    And User clicks FURTHEST of PROCEDURE DATE
    Then Date in Case Card should be sorted in descending order
    And Case Count will display the total number of cases as per filters applied
  @CaseListFilter1
  Scenario: User clicks LOCATION A-Z of LOCATION under SORT BY filter
    When User clicks SORT BY filter
    And User clicks LOCATION A-Z of LOCATION
    Then Location in Case Card should be sorted in alphabetical ascending order
    And Case Count will display the total number of cases as per filters applied
  @CaseListFilter1
  Scenario: User clicks LOCATION Z-A of LOCATION under SORT BY filter
    When User clicks SORT BY filter
    And User clicks LOCATION Z-A of LOCATION
    Then Location in Case Card should be sorted in alphabetical descending order
    And Case Count will display the total number of cases as per filters applied
  @CaseListFilter1
  Scenario: User clicks PATIENT A-Z of PATIENT under SORT BY filter
    When User clicks SORT BY filter
    And User clicks PATIENT A-Z of PATIENT
    Then Name in Case Card should be sorted in alphabetical ascending order
    And Case Count will display the total number of cases as per filters applied
  @CaseListFilter1
  Scenario: User clicks PATIENT Z-A of PATIENT under SORT BY filter
    When User clicks SORT BY filter
    And User clicks PATIENT Z-A of PATIENT
    Then Name in Case Card should be sorted in alphabetical descending order
    And Case Count will display the total number of cases as per filters applied
    And Browser quits
