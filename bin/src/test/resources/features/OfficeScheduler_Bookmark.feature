@OfficeScheduler_Bookmark
Feature: As Office Scheduler, I want to bookmark cases
  Scenario: Office scheduler is on case tracker
    Given "Office Scheduler" is on case tracker landing page

  Scenario: Office Scheduler unchecks existing bookmarked cases
    When "Office Scheduler" selects BOOKMARKED filter
    And "Office Scheduler" undo all bookmarked cases
    Then Bookmarked cases icon should be hollow

  Scenario: Office Scheduler bookmarks cases on the 1nd page
    Given "Office Scheduler" is on the 1st page
    When "Office Scheduler" bookmarks a case
    And "Office Scheduler" selects BOOKMARKED filter
    Then "Office Scheduler" should be able to see 1 bookmarked case

  Scenario: Office Scheduler bookmarks cases on the 2nd page
    Given "Office Scheduler" is on the 2nd page
    When "Office Scheduler" bookmarks a case
    And "Office Scheduler" selects BOOKMARKED filter
    Then "Office Scheduler" should be able to see 2 bookmarked cases
    And Browser quits

