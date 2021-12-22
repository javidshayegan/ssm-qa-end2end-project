@OfficeScheduler_CasePagination
Feature: As Office Scheduler, I want to navigate pages with pagination feature
  Scenario: Office scheduler is on case tracker
    Given "Office Scheduler" is on case tracker landing page

  Scenario: Office scheduler wants to go to the last page
    Given "LAST" button is not disabled
    When Office scheduler clicks "LAST"
    Then Office scheduler can see case cards on the "last" page

  Scenario: Office scheduler wants to go to the first page
    Given "FIRST" button is not disabled
    When Office scheduler clicks "FIRST"
    Then Office scheduler can see case cards on the "first" page

  Scenario: Office scheduler wants to go to the next page
    Given "NEXT" button is not disabled
    When Office scheduler clicks "NEXT"
    Then Office scheduler can see case cards on the "next" page

  Scenario: Office scheduler wants to go to the previous page
    Given "PREV" button is not disabled
    When Office scheduler clicks "PREV"
    Then Office scheduler can see case cards on the "previous" page

  Scenario Outline: Office scheduler wants to see the specific page
    Given There is "<page number>" page
    And Office scheduler is not at "<page number>" page
    When Office scheduler clicks "<page number>" page on the case pagination
    Then Office scheduler can see case cards on the "<page number>" page
    Examples:
    | page number |
    | 1 |
    | 2 |
    | 5 |
    | 10 |

  Scenario Outline: Office scheduler wants to change the value of page
    When User clicks "<page>" in Cases per page
    Then Case cards should have less than or equal to "<page>"
    Examples:
    | page |
    | 25   |
    | 50   |
    | 100  |
    | ALL  |