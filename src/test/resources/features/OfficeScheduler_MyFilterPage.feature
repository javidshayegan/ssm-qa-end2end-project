Feature: My Filter page automation

  Scenario: Verify the filtering options are available
    Given "user" is on Case Tracker landing page
    And "user" is logged in on Case Tracker homepage
    When "user" clicks on the Edit Filters button
    Then "user" is on the My Filters page
    And the following filter options are available
      | SORT BY | PROCEDURE DATE | Surgeons | Other Filters |

  Scenario: select a Sort By and save the filter
    Given "user" selects a Sort By option
    And the Save button is available
    When "user" clicks Save button
    Then "user" can see the confirmation message

  Scenario: select a Procedure Date and save the filter
    Given "user" selects a procedure date
    And the Save button is available
    When "user" clicks Save button
    Then "user" can see the confirmation message

  Scenario: select a surgeon and save the filter
    Given "user" selects a surgeon
    And the Save button is available
    When "user" clicks Save button
    Then "user" can see the confirmation message

  Scenario: select another filter and save the filter
    Given "user" selects another filter
    And the Save button is available
    When "user" clicks Save button
    Then "user" can see the confirmation message