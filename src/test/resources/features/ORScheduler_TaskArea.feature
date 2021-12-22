@ORScheduler_TaskArea
Feature: As OR scheduler, I want to validate the task area

Scenario: User Login and validate task cards
	Given "user" is on case tracker landing page
	And the followling cards are available in the task area
	| Warnings | Unscheduled | Revised | Messages |

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

Scenario: Validate Revised Review All Grey Color if 0 warnings
	Given There is 0 Revised
	Then Review All Under Revised should be of grey color
	| rgba?\\(153, 153, 153(, 1)?\\) |

Scenario: Validate Revised Review All Blue Color if greater than 0 warnings
	Given There is greater than 0 Revised
	Then Review All Under Revised should be of blue color
	| rgba?\\(30, 105, 210(, 1)?\\) |

Scenario: validate warnings card
	Given "user" can see the total number of cases on the warnings card
	When "user" clicks on the warnings card
	Then "user" sees a red warning label with the following tabs
	| APPOINTMENT REQUEST | PATIENT INFO | SURGERY INFO | PAT INFO |
	And clicks "END QUEUE" to compare the total number of filtered warning cases to the number on the warning card

Scenario: validate unscheduled card
	Given "user" can see the total number of cases on the unscheduled card
	When "user" clicks on the unscheduled card
	Then "user" sees an orange unscheduled label with the following tabs
	| APPOINTMENT REQUEST | PATIENT INFO | SURGERY INFO | PAT INFO |
	And clicks "END QUEUE" to compare the total number of filtered unscheduled cases to the number on the unscheduled card

Scenario: validate Revised card
	Given "user" can see the total number of cases on the Revised card
	When "user" clicks on the Revised card
	Then "user" sees a purple Revised label with the following tabs
	| APPOINTMENT REQUEST | PATIENT INFO | SURGERY INFO | PAT INFO |
	And clicks "END QUEUE" to compare the total number of filtered Revised to the number on the Revised card

Scenario: validate messages card
	Given "user" can see the "COMING SOON" subtitle on the messages card
	When "user" clicks on the messages card
	Then "user" sees a popup message "Review Messages Coming Soon!"
	And Browser quits
