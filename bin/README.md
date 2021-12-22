# QA-DSSC-End2End

[![Build Status](https://jenkins.ascension.org:8443/job/Software%20Engineering/job/QA/job/QA-DSSC-End2End/job/master-gecko-ios/badge/icon)](https://jenkins.ascension.org:8443/job/Software%20Engineering/job/QA/job/QA-DSSC-End2End/job/master-gecko-ios/)

## Useful Link
* [Google Drive](https://docs.google.com/spreadsheets/d/1jMm1ndqa1b2lvkNdGTeY4vxTiRCSOcEMf5xop2a_G0k/edit#gid=0)
* [Kanban Board](https://jira.ascension.org/secure/RapidBoard.jspa?rapidView=1153&projectKey=DSSC)
* [QA-Selenium 0.2.6.0-SNAPSHOT](https://confluence.ascension.org/display/STAUT/QA-Selenium-Framework+0.2.6.0-SNAPSHOT+Release)
## Login Usage
`login.LoginPage(Role <role>)` takes a role as enum `Role` argument. 
The list of roles is only allowed as if descried below.
* OFFICE_SCHEDULER1
* OFFICE_SCHEDULER2
* OR_SCHEDULER1
* OR_SCHEDULER2


### Examples

:warning: **Please make sure read it**: After calling `login.LoginPage(<role>)`, it is required to call `step.from(<pagename>)` in order to avoid page conflict.

``` java
import Login.Login;
import Login.Role;
@Given("{string} is on case tracker landing page")
public void user_is_on_case_tracker_landing_page(String user){
    Login login=new Login();
    login.LoginPage(Role.OFFICE_SCHEDULER1);
    ...
}
```
``` java
import Login.Login;
import Login.Role;
@Given("{string} is on case tracker landing page")
public void user_is_on_case_tracker_landing_page(String user){
    Login login=new Login();
    login.LoginPage(Role.OR_SCHEDULER2);
    ...
}
```


## Tasks
* Office Scheduler
  - [x] Request A Surgery
  - [x] Case Preference Indicator
  - [x] Task Area Negative ( 0 cases )
  - [x] Task Area Happy Path
  - [x] Case List - Filter
  - [x] Case List - Pagination
  - [x] Case List - Case Count
  - [x] Case List - Case Card Detail
* OR Scheduler
  - [x] Case Preference Indicator
  - [x] Conform a Surgery Request by OR Scheduler
  - [x] Request a Revision by OR Scheduler
  - [ ] Respond to a Revision Request by Office Scheduler (NEED TO COMBINE and REPHRASE)
  - [x] Conform the Revised Request from Office Scheduler by OR Scheduler (Approve or Decline)
  - [ ] Request a Revision initiated by Office Scheduler (NEED TO COMBINE and REPHRASE)
  - [ ] Respond to a Revision by OR Scheduler (Approve or Decline)
  - [ ] Warnings/Alerts system (Story point may be 8; too big)
## Directory Structure
```
📦 src
  - test
    - java
      - <Epic Directories>
        - <Epic Name>_Runner.java
        - <Epic Name>_StepDef.java
    - resources
      - Drivers
        - <Drivers>
      - features
        - <Credential>_<Epic Name>.feature
      - yml
        - <Epic Name>.yml
      - test_variables.yml
```
  
## Feature File Name Convention

```
<Credential>_<Epic Name>.feature
```

Credential should OfficeScheduler || OfficeManager || ORScheduler || All
