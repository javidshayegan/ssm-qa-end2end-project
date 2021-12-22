package WarningsAndAlertSystemORandOffice;

import Login.*;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class WarningsAndAlert_StepDef {
	private static boolean dunit = false;
	private Calendar c;
	private Date d;
	private final int  DELAY_2000 = 2000,
					   DELAY_5000 = 5000;

    private final String USER_ID = "userid",
    					 ASCENSIONID_LOGINBUTTON = "ascensionidloginbutton",
     					 ASCENSIONID_FIELD = "ascensionidfield",
     					 PASSWORD_FIELD = "ascensionidpasswordfield",
     					 LOGIN_BUTTON = "loginbutton";

	 private StepDefHelperYml step = new StepDefHelperYml();

	@Given("{string} is on {string}")
	public void user_is_on_landing_page(String user, String surgicalSchedulingLandingPage) {
		Login login = new Login();
		login.LoginPage(Role.OR_SCHEDULER);
		step.on("WarningsAndAlerts");
	}

	@When("{string} clicks on {string} and select {string} from the dropdown")
	    public void clicks_on_and_select_from_the_dropdown(String string, String string2, String string3) {
	    	step.wait(2000);
	    	step.setElement("OtherFiltersBox").waitUntilPresence().click();
	    	step.wait(2000);
	    	step.setElement("OtherFilter").waitUntilPresence().click();
	    }
	    
	    @Then("{string} is taken to {string}")
	    public void is_taken_to(String user, String requestARevisionPage)
	    {
	    	String currentLandingURL = step.driver().getCurrentUrl();
		    System.out.println("Current URL is " + currentLandingURL);
		    try {
		          Assert.assertEquals(currentLandingURL, requestARevisionPage);
		        } catch (AssertionError e) {
		            System.out.println("User is not on the request revision page.");
		        }
		    System.out.println("User is on the request a revision page.");
	    }
	    
	    @Then("{string} clicks on an {string} filtered case")
	    public void clicks_on_an_filtered_case(String user, String filtered) {
	    	step.wait(2000);
	    	step.driver().findElement(By.xpath("//span[contains(@data-field, 'tagUNSCHEDULED')]/ancestor::div[contains(@class, 'sc-htpNat sc-bxivhb sc-bRBYWo hQaXGP warning-card warning-card')]")).click();
	    	step.wait(2000);
	    }
	   
	    @Given("{string} sees unschedled cases {string}")
	    public void usees_unschedled_cases(String user, String unscheduledCases)
	    {
	    	step.wait(2000);
	    	String currentLandingURL = step.driver().getCurrentUrl();
		    System.out.println("Current URL is " + currentLandingURL);
		    try {
		          Assert.assertEquals(currentLandingURL, unscheduledCases);
		        } catch (AssertionError e) {
		            System.out.println("User does NOT see the unschedule cases.");
		        }
		    System.out.println("User sees the unscheduled cases");
		    step.wait(2000);
	    }
	    
	    @When("{string} clicks on {string} button")
	    public void clicks_on_button(String user, String RequestRevisionButton) {
	    	step.wait(5000);
	    	step.setElement("RequestARevisionLink").waitUntilPresence().click();
	    }
	    
	    @When("{string} clicks on Revised {string} button")
	    public void clicks_on_revised_button(String user, String RequestRevisionButton) {
	    	step.wait(5000);
	    	step.setElement("Revised").waitUntilPresence().click();
	    }

	    @When("{string} verifies {string} dashboard is open")
	    public void verifies_dashboard_is_open(String string, String string2) {
	    	Assert.assertEquals("Request a Revision", 
					step.setElement("RequestArevisionText").element().getText());
	    }

	    @When("{string} selects {string} Option")
	    public void selects_Option(String string, String string2) {
	    	step.wait(2000);
	    	step.setElement("TimeNotAvailable").waitUntilPresence().click();
	    	
	    }
	  
	    @Then("{string} enters a message on {string}")
	    public void enters_a_message_on(String string, String string2, io.cucumber.datatable.DataTable dataTable) {
	    	List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
			for (int i = 0; i < list.size(); i++) {
			step.wait(2000);
			step.setElement("MessageBox").waitUntilPresence().inputToElement(list.get(i).get("Write A Message"));}
			
	    }
	    
	    @Then("{string} clicks on a {string} button")
	    public void clicks_on_a_button(String string, String string2) {
	    	step.wait(3000);
	    	step.setElement("RequestARevisionButton").waitUntilPresence().click();
	    	step.wait(3000);
	    }
	    
	    
	    //New Work Flow: responding to a revision requested by OR Scheduler
	    
	    @Given("{string} logs in with Office Scheduler {string}")
	    public void logs_in_with_Office_Scheduler(String user, String surgicalSchedulingLandingPage) {
			Login login = new Login();
			login.LoginPage(Role.OFFICE_SCHEDULER);
			step.on("WarningsAndAlerts");
	        step.wait(DELAY_2000);
	        String currentLandingURL = step.driver().getCurrentUrl();
	        System.out.println("Current URL is " + currentLandingURL);
	        try {
	            Assert.assertEquals(currentLandingURL, surgicalSchedulingLandingPage);
	        } catch (AssertionError e) {
	            System.out.println("User is not on the surgical scheduling landing page after logging in.");
	        }
	        System.out.println("User is on the surgical scheduling landing page after logging in.");
	        step.wait(4000);
	        step.wait(4000);
	    }
		 
	    @When("{string} clicks on {string} on homepage")
	    public void clicks_on_on_homepage(String string, String string2) {
	    	step.wait(10000);
	    	step.setElement("NeedsRevision").waitUntilPresence().click();
	    	step.wait(3000);
	        
	    }
	    
	    @When("{string} clicks on {string} on homepage to review")
	    public void clicks_on_on_homepage_to_review(String string, String string2) {
	    	step.wait(10000);
	    	while(step.findElements("SPINNER").size() !=0);
	    	step.setElement("NeedsRevisiontoReview").waitUntilPresence().click();
	    	step.wait(3000);
	        
	    }
	    
	    @When("{string} is taken to Needs Revision page {string}")
	    public void is_taken_to_Needs_Revision_page(String user, String respondtoARevision) {
	    	  {
	  	    	String currentLandingURL = step.driver().getCurrentUrl();
	  		    System.out.println("Current URL is Test " + currentLandingURL);
	  		    try {
	  		          Assert.assertEquals(currentLandingURL, respondtoARevision);
	  		        } catch (AssertionError e) {
	  		            System.out.println("User is not on the Needs Revision page.");
	  		        }
	  		    System.out.println("User is on the Needs Revision page.");
	  	    }
	    }
	    
	    @Then("{string} click on Mark as resolved button")
	    public void click_on_Mark_as_resolved_button(String user) {
	    	step.wait(3000);

	    	
	    		step.driver().findElement(By.xpath("//a[contains(text(),'Mark as resolved')]")).click();
	    	step.wait(3000);
	    	
	    }

	    @Then("{string} clicks on Mark as resolved button")
	    public void clicks_on_Mark_as_resolved_button(String user) {

	    	List<WebElement> li = step.driver().findElements(By.xpath("//span[contains(text(), 'Cancel a procedure is requested!')]/ancestor::div[contains(@data-section, 'revisionInfo')]/descendant::a[contains(text(), 'Mark as resolved')]"));
	    	int size = li.size();
	    	if(size>0) {
	    		li.get(0).click();
	    	}
	    	else {
	    		System.out.println("Executing else part");
	    		step.driver().findElement(By.xpath("//a[contains(text(),'Mark as resolved')]")).click();
	    	step.wait(3000);
	    	}
	    }
	    	
	    	@Then("{string} verify {string} dashboard is open")
	    	public void verify_dashboard_is_open(String string, String string2) {
	        	step.wait(2000);
	    		Assert.assertEquals("Mark as Resolved",
						step.setElement("MarkasResolvedText").element().getText());
	    	}

	    	@Then("{string} clicks on {string} circle button")
	    	public void clicks_on_circle_button(String string, String string2) {
		    	step.setElement("Changeproceduretime").waitUntilPresence().click();
	    	}

	    	@Then("{string} enters the following message on {string}")
	    	public void enters_the_following_message_on(String string, String string2, io.cucumber.datatable.DataTable dataTable) {
	    		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
				for (int i = 0; i < list.size(); i++) {
				step.wait(2000);
				step.setElement("MarkAsResolvedMessageBox").waitUntilPresence().inputToElement(list.get(i).get("Write this Message"));}
				step.wait(5000);
	    	}
	    	@Then("{string} clicks on {string} button on dashboard")
	    	public void clicks_on_button_on_dashboard(String string, String string2) {
	    		step.driver().findElement(By.xpath("(//span[contains(text(), 'Mark as Resolved')])[2]")).click();
	    	}
	    	
	    	@Given("{string} is on warnings page")
	    	public void is_on_warnings_page(String string) {
	    	  
	    	}

	    	@When("{string} verifies NEEDS REVISION section contains")
	    	public void verifies_NEEDS_REVISION_section_contains(String string, io.cucumber.datatable.DataTable dataTable) {
	    	  
	    		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
				for (int i = 0; i < list.size(); i++) {
				step.wait(15000);
				System.out.println(list.get(i).get("NEEDS REVISION"));
				System.out.println(step.setElement("NeedsRevision").element().getText());

				
	    		Assert.assertEquals(list.get(i).get("NEEDS REVISION"), step.setElement("NeedsRevision").element().getText());
	    		Assert.assertEquals(list.get(i).get("Message"), step.setElement("Message").element().getText());
				}
	    	}

	    	@Then("{string} logs out of OR")
	    	public void logs_out_of_OR(String string) {
	    		step.setElement("LogoutMenu").waitUntilPresence().click();
	    		step.setElement("LogoutLink").element().click();
	    	}

	    	@Then("{string} verifies Revised section contains")
	    	public void verifies_Revised_section_contains(String string, io.cucumber.datatable.DataTable dataTable) {
	    		

	    		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
				for (int i = 0; i < list.size(); i++) {
				step.wait(15000);
				System.out.println(list.get(i).get("NEEDS REVISION"));
				System.out.println(step.setElement("NeedsRevision").element().getText());

				
	    		Assert.assertEquals(list.get(i).get("REVISED"), step.setElement("NeedsRevision").element().getText());
	    		Assert.assertEquals(list.get(i).get("Message"), step.setElement("RevisedMessage").element().getText());
				}

  
	    	}

	    	@Then("{string} logs out of Office Scheduler")
	    	public void logs_out_of_Office_Scheduler(String string) {
	    		step.setElement("LogoutMenu").element().click();
	    		step.setElement("LogoutLink").element().click();
	    	}

	    	@When("{string} verifies {string} section contains")
	    	public void verifies_section_contains(String string, String string2, io.cucumber.datatable.DataTable dataTable) {
  
	    	}

	    	@When("{string} clicks on Approve Button")
	    	public void clicks_on_Approve_Button(String string) {
				step.wait(5000);

	            JavascriptExecutor js = (JavascriptExecutor)step.driver();
	            js.executeScript("window.scrollBy(0,600)");
				step.wait(2000);
		    	step.setElement("Approve").waitUntilPresence().click();
				step.wait(10000);


	    	}



	    	
}
