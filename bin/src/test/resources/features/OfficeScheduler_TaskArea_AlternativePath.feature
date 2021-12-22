@OfficeScheduler_TaskArea_AlternativePath
Feature: Office Scheduler Task Area Alternative Path

Scenario: Validate Task Area 
	Given "user" is on "https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc"
	Then Task Area should have four cards
	| Warnings | Unscheduled | Needs Revision | Messages |
				
Scenario: Validate Warning Review All Grey Color if 0 warnings
	Given There is 0 warning
	Then Review All Under Warning should be of grey color
	| rgba(153, 153, 153, 1) |

Scenario: Validate Warning Review All Blue Color if greater than 0 warnings
	Given There is greater than 0 warnings
	Then Review All Under Warning should be of blue color
	| rgba(30, 105, 210, 1) |

Scenario: Validate Unscheduled Review All Grey Color if 0 warnings
	Given There is 0 unscheduled  
	Then Review All Under Unscheduled should be of grey color
	| rgba(153, 153, 153, 1) |

Scenario: Validate Unscheduled Review All Blue Color if greater than 0 warnings
	Given There is greater than 0 unscheduled  
	Then Review All Under Unscheduled should be of blue color
	| rgba(30, 105, 210, 1) |

Scenario: Validate Needs Revision Review All Grey Color if 0 warnings
	Given There is 0 Needs Revision 
	Then Review All Under Needs Revision should be of grey color
	| rgba(153, 153, 153, 1) |

Scenario: Validate Needs Revision Review All Blue Color if greater than 0 warnings
	Given There is greater than 0 Needs Revision 
	Then Review All Under Needs Revision should be of blue color
	| rgba(30, 105, 210, 1) |
	And Browser quits
