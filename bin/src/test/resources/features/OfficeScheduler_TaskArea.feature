@OfficeScheduler_TaskArea
Feature: As office schedulder, I want to validate the task area

Scenario: User Login and validate task cards
	Given "user" is on case tracker landing page
	And the followling cards are available in the task area
	| Warnings | Unscheduled | Needs Revision | Messages |

Scenario: Validate Warning Review All Grey Color if 0 warnings
	Given There is 0 warning
	Then Review All Under Warning should be of grey color
	| rgba?\\(153, 153, 153(, 1)?\\) |

Scenario: Validate Warning Review All Blue Color if greater than 0 warnings
	Given There is greater than 0 warnings
	Then Review All Under Warning should be of blue color
	| rgba?\\(30, 105, 210(, 1)?\\) |

Scenario: Validate Unscheduled Review All Grey Color if 0 warnings
	Given There is 0 unscheduled
	Then Review All Under Unscheduled should be of grey color
	| rgba?\\(153, 153, 153(, 1)?\\) |

Scenario: Validate Unscheduled Review All Blue Color if greater than 0 warnings
	Given There is greater than 0 unscheduled
	Then Review All Under Unscheduled should be of blue color
	| rgba?\\(30, 105, 210(, 1)?\\) |

Scenario: Validate Needs Revision Review All Grey Color if 0 warnings
	Given There is 0 Needs Revision
	Then Review All Under Needs Revision should be of grey color
	| rgba?\\(153, 153, 153(, 1)?\\) |

Scenario: Validate Needs Revision Review All Blue Color if greater than 0 warnings
	Given There is greater than 0 Needs Revision
	Then Review All Under Needs Revision should be of blue color
	| rgba?\\(30, 105, 210(, 1)?\\) |

Scenario: validate warnings card
	Given "user" can see the total number of cases on the warnings card
	When "user" clicks on the warnings card
	Then "user" sees a red warning label with the following tabs
	| APPOINTMENT REQUEST | PATIENT INFO | SURGERY INFO | PAT INFO | 
	And clicks "END QUEUE" to compare the total number of filtered warning cases to the number on the warning card
#	
Scenario: validate unscheduled card
	Given "user" can see the total number of cases on the unscheduled card
	When "user" clicks on the unscheduled card
	Then "user" sees an orange unscheduled label with the following tabs
	| APPOINTMENT REQUEST | PATIENT INFO | SURGERY INFO | PAT INFO | 
	And clicks "END QUEUE" to compare the total number of filtered unscheduled cases to the number on the unscheduled card
	
Scenario: validate needs revision card
	Given "user" can see the total number of cases on the needs revision card
	When "user" clicks on the needs revision card
	Then "user" sees a purple needs revision label with the following tabs
		| APPOINTMENT REQUEST | PATIENT INFO | SURGERY INFO | PAT INFO | 
	And clicks "END QUEUE" to compare the total number of filtered needs revision cases to the number on the needs revision card
	
	Scenario: validate messages card
	Given "user" can see the "COMING SOON" subtitle on the messages card
	When "user" clicks on the messages card
	Then "user" sees a popup message "Review Messages Coming Soon!"
	And Browser quits
