@All_MyInformationMySurgeon
Feature: Reviewing and verifying the My Surgeon and My Information of Account Setting on Office Schedule

Scenario: Office Scheduler validates the My Information section of Account Setting and change password
	Given "Office Scheduler" is on "https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc"
	When "Office Scheduler" clicks on "Office" then clicks on Account Setting
	And "Office Scheduler" is taken to "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myInformation"
	Then "Office Scheduler" verifies the following sections are available
	| Account Settings | My Information | CHANGE PASSWORD |
	And "Office Scheduler" verifies the following information
	|FULL NAME| 	OFFICE PHONE NUMBER	|EMAIL				|PRACTICE NAME| 			
	|Office Practice 1|4696167777 Ext.12651|office_scheduler@practice1.com  |Elite Sports Med & Ortho Ctr-Woodmont|
	And "Office Scheduler" wants to change password
	Then "Office Scheduler" is taken to "https://id-ua.ascension.org/id-ua.ascension.org/oauth2/v2.0/authorize?p=B2C_1A_Password_Reset&client_id=e26e1cc8-d91b-455a-8f5c-f363cfadad5e&nonce=defaultNonce&redirect_uri=https%3A%2F%2Fcasetracker-qa.app.cloud-02.pcf.ascension.org%2F&state=defaultState&scope=openid&response_type=id_token&prompt=login"
	And Browser quits

Scenario: OR Scheduler validates the My Information section of Account Setting and change password
	Given "OR Scheduler" is on "https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc"
	When "OR Scheduler" clicks on "Or" then clicks on Account Setting
	And "OR Scheduler" is taken to the "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myInformation"
	Then "OR Scheduler" verifies the following sections are available
	| Account Settings | My Information | CHANGE PASSWORD |
	And "OR Scheduler" verifies the following information
	|FULL NAME			  	| 	OFFICE PHONE NUMBER		|EMAIL						  |
	|Or Scheduler 			|4696167777 Ext.12651		|or_scheduler1@ascension.org  |
	And "OR Scheduler" wants to change password
	Then "OR Scheduler" is taken to "https://id-ua.ascension.org/id-ua.ascension.org/oauth2/v2.0/authorize?p=B2C_1A_Password_Reset&client_id=e26e1cc8-d91b-455a-8f5c-f363cfadad5e&nonce=defaultNonce&redirect_uri=https%3A%2F%2Fcasetracker-qa.app.cloud-02.pcf.ascension.org%2F&state=defaultState&scope=openid&response_type=id_token&prompt=login"
	And Browser quits
