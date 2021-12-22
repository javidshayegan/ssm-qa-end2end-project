@ORScheduler_CasePreferenceIndicator
Feature: OR Scheduler Case Preference Indicator

  Scenario: validate EDIT VIEW page
    Given "user" is on "https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc"
    When "user" clicks EDIT VIEW
    Then "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myUnits"
    And The left pane has following forms
      | Account Settings | MY UNITS |
    And The right pane has following units
      | BH CSC | BH JRI | BH OR | STM CATH | STM ENDO |
    And the right pane has following buttons
      | Cancel | Save |
    And the Save button is disabled.
    And "user" clicks HOME button

  Scenario Outline: I want to set one unit
    Given "user" clicks EDIT VIEW
    And "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myUnits"
    And all units should be unchecked
    When "user" clicks "<unit>" checkbox
    And "user" clicks Save
    And "user" clicks HOME button
    Then Case preference indicator should have "<unit name>"
    Examples:
      | unit | unit name |
      | BH CSC | BH CSC |

  Scenario Outline: I want to set two units
    Given "user" clicks EDIT VIEW
    And "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myUnits"
    And all units should be unchecked
    When "user" clicks "<unit1>" "<unit2>" checkbox
    And "user" clicks Save
    And "user" clicks HOME button
    Then Case preference indicator should have "<unit name>"
    Examples:
      | unit1 | unit2 | unit name |
      | BH CSC| BH JRI | BH JRI, BH CSC |

  Scenario Outline: I want to set three units
    Given "user" clicks EDIT VIEW
    And "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myUnits"
    And all units should be unchecked
    When "user" clicks "<unit1>" "<unit2>" "<unit3>" checkbox
    And "user" clicks Save
    And "user" clicks HOME button
    Then Case preference indicator should have "<unit name>"
    Examples:
      | unit1 | unit2 | unit3 | unit name |
      | BH CSC | BH JRI | BH OR | BH JRI, BH OR, BH CSC |

  Scenario Outline: I want to set four units
    Given "user" clicks EDIT VIEW
    And "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myUnits"
    And all units should be unchecked
    When "user" clicks "<unit1>" "<unit2>" "<unit3>" "<unit4>" checkbox
    And "user" clicks Save
    And "user" clicks HOME button
    Then Case preference indicator should have "<unit name>"
    Examples:
      | unit1 | unit2 | unit3 | unit4 | unit name |
      | BH CSC | BH JRI | BH OR | STM CATH | STM CATH, BH JRI, BH OR (+1 more) |

  Scenario Outline: I want to set five units
    Given "user" clicks EDIT VIEW
    And "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myUnits"
    And all units should be unchecked
    When "user" clicks "<unit1>" "<unit2>" "<unit3>" "<unit4>" "<unit5>" checkbox
    And "user" clicks Save
    And "user" clicks HOME button
    Then Case preference indicator should have "<unit name>"
    Examples:
      | unit1 | unit2 | unit3 | unit4 | unit5 | unit name |
      | BH CSC | BH JRI | BH OR | STM CATH | STM ENDO | STM CATH, STM ENDO, BH JRI (+2 more) |

  Scenario: Browser quits
    Then Browser quits
