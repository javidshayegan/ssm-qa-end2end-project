@OfficeScheduler_CasePreferenceIndicator
Feature: Office Scheduler Case Preference Indicator

  Scenario: validate EDIT VIEW page
		Given "user" is on "https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc"
		When "user" clicks EDIT VIEW 
		Then "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/mySurgeons"
		And The left pane has following forms 
			| Account Settings | MY SURGEONS |
		And The right pane has following surgeons  
			| Cordovez MD, Leah G. | Dovan MD, Thomas T. | Elrod MD, Burton F.| Glattes MD, Rudolph C. | Martin DO, Amanda D. | Moore MD, David R. | Willers MD, Jeffrey D. |
		And the right pane has following buttons
			| Cancel | Save |
		And the Save button is disabled.
		And "user" clicks HOME button
	
	Scenario Outline: I want to set one surgeon
		Given "user" clicks EDIT VIEW 
		And "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/mySurgeons"
		And all surgeons should be unchecked
		When "user" clicks "<surgeon>" checkbox
		And "user" clicks Save
		And "user" clicks HOME button
		Then Case preference indicator should have "<surgeon name>"
    Examples:
		| surgeon       | surgeon name |
		| Leah Cordovez | Cordovez |

	Scenario Outline: I want to set two surgeons
		Given "user" clicks EDIT VIEW 
		And "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/mySurgeons"
		And all surgeons should be unchecked
		When "user" clicks "<surgeon1>" "<surgeon2>" checkbox
		And "user" clicks Save
		And "user" clicks Home button
		Then Case preference indicator should have "<surgeon name>"
  Examples:
		| surgeon1 | surgeon2 | surgeon name |
		| Leah Cordovez | Thomas Dovan | Cordovez, Dovan |
	
	Scenario Outline: I want to set three surgeons
		Given "user" clicks EDIT VIEW 
		And "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/mySurgeons"
		And all surgeons should be unchecked
		When "user" clicks "<surgeon1>" "<surgeon2>" "<surgeon3>" checkbox
		And "user" clicks Save
		And "user" clicks Home button
		Then Case preference indicator should have "<surgeon name>"
  Examples:
		| surgeon1 | surgeon2 | surgeon3 | surgeon name |
		| Leah Cordovez | Thomas Dovan | Burton Elrod | Cordovez, Dovan, Elrod |
		
	Scenario Outline: I want to set four surgeons
		Given "user" clicks EDIT VIEW 
		And "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/mySurgeons"
		And all surgeons should be unchecked
		When "user" clicks "<surgeon1>" "<surgeon2>" "<surgeon3>" "<surgeon4>" checkbox
		And "user" clicks Save
		And "user" clicks Home button
		Then Case preference indicator should have "<surgeon name>"
  Examples:
			| surgeon1 | surgeon2 | surgeon3 | surgeon4 | surgeon name |
			| Leah Cordovez | Thomas Dovan | Burton Elrod | Rudolph Glattes | Cordovez, Dovan, Elrod (+1 more) |

	Scenario Outline: I want to set five surgeons
		Given "user" clicks EDIT VIEW 
		And "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/mySurgeons"
		And all surgeons should be unchecked
		When "user" clicks "<surgeon1>" "<surgeon2>" "<surgeon3>" "<surgeon4>" "<surgeon5>" checkbox
		And "user" clicks Save
		And "user" clicks Home button
		Then Case preference indicator should have "<surgeon name>"
	Examples:
			| surgeon1 | surgeon2 | surgeon3 | surgeon4 | surgeon5 | surgeon name | 
			| Leah Cordovez | Thomas Dovan | Burton Elrod | Rudolph Glattes | Amanda Martin | Cordovez, Dovan, Elrod (+2 more) |

	Scenario Outline: I want to set six surgeons
		Given "user" clicks EDIT VIEW 
		And "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/mySurgeons"
		And all surgeons should be unchecked
		When "user" clicks "<surgeon1>" "<surgeon2>" "<surgeon3>" "<surgeon4>" "<surgeon5>" "<surgeon6>" checkbox
		And "user" clicks Save
		And "user" clicks Home button
		Then Case preference indicator should have "<surgeon name>"
	Examples:
			| surgeon1 | surgeon2 | surgeon3 | surgeon4 | surgeon5 | surgeon6 | surgeon name | 
			| Leah Cordovez | Thomas Dovan | Burton Elrod | Rudolph Glattes | Amanda Martin | David Moore | Cordovez, Dovan, Elrod (+3 more) |

	Scenario Outline: I want to set seven surgeons
		Given "user" clicks EDIT VIEW 
		And "user" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/mySurgeons"
		And all surgeons should be unchecked
		When "user" clicks "<surgeon1>" "<surgeon2>" "<surgeon3>" "<surgeon4>" "<surgeon5>" "<surgeon6>" "<surgeon7>" checkbox
		And "user" clicks Save
		And "user" clicks Home button
		Then Case preference indicator should have "<surgeon name>"
	Examples:
			| surgeon1 | surgeon2 | surgeon3 | surgeon4 | surgeon5 | surgeon6 | surgeon7 | surgeon name | 
			| Leah Cordovez | Thomas Dovan | Burton Elrod | Rudolph Glattes | Amanda Martin | David Moore | Jeffrey Willers | Cordovez, Dovan, Elrod (+4 more) |
	Scenario: Browser quits
		Then Browser quits
