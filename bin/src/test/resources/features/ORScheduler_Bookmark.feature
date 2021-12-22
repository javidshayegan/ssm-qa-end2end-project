@ORScheduler_Bookmark
Feature: As OR Scheduler, I want to bookmark cases
  Scenario: OR scheduler is on case tracker
    Given "OR Scheduler" is on case tracker landing page

  Scenario: OR Scheduler unchecks existing bookmarked cases
    When "OR Scheduler" selects BOOKMARKED filter
    And "OR Scheduler" undo all bookmarked cases
    Then Bookmarked cases icon should be hollow

  Scenario: OR Scheduler bookmarks cases on the 1nd page
    Given "OR Scheduler" is on the 1st page
    When "OR Scheduler" bookmarks a case
    And "OR Scheduler" selects BOOKMARKED filter
    Then "OR Scheduler" should be able to see 1 bookmarked case

  Scenario: OR Scheduler bookmarks cases on the 2nd page
    Given "OR Scheduler" is on the 2nd page
    When "OR Scheduler" bookmarks a case
    And "OR Scheduler" selects BOOKMARKED filter
    Then "OR Scheduler" should be able to see 2 bookmarked cases
    And Browser quits