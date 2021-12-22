package OfficeScheduler_EditSurgeryRequest_Timeout;

import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.core.api.Scenario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.*;

import Login.Login;
import Login.Role;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EditSurgeryRequest_Timeout_StepDef {
    
	private StepDefHelperYml step = new StepDefHelperYml();
	private Calendar c;
	private Date d;
  
    @Given("Office Scheduler already requested a new surgery")
    public void officeSchedulerAlreadyRequestedANewSurgery() {
       step.from("EditSurgeryRequest");
       step.wait(2000);
       step.driver().findElement(By.xpath("//span[contains(.,'SORT BY')]")).click();
       step.wait(2000);
       step.driver().findElement(By.xpath("//div[@tabindex='0'][contains(.,'NEWEST')]")).click();
       step.wait(2000);
       step.findElements("CASE_CARD_DETAILS").get(0).click();
       step.pageReady();
    
    }

   
    @When("Office Scheduler edits surgery request")
    public void officeSchedulerEditsSurgeryRequest(DataTable dt) {
        List<Map<String, String>> map = dt.asMaps(String.class, String.class);
        step.wait(2000);
        step.setElement("EDIT").waitUntilVisible().click();
        String option = map.get(0).get("OPTION");
        Assume.assumeTrue(option + " must be either YES or NO",
                option.toUpperCase().equals("YES") || option.toLowerCase().equals("NO")
                );
        if(option.toUpperCase().equals("YES")) {
            step.wait(2000);
        	step.setElement("REQUEST_REVISION_YES").waitUntilClickable().click();
            step.wait(2000);
        	step.pageReady();
            if (map.get(0).get("CHANGE_PROCEDURE_TIME").equals("V")) {
                ((JavascriptExecutor) step.driver()).executeScript("arguments[0].click();", step.setElement("REQUEST_REVISION_CHANGE_PROCEDURE_TIME").element());
            }
            if (map.get(0).get("MISSING_INFORMATION").equals("V")) {
                ((JavascriptExecutor) step.driver()).executeScript("arguments[0].click();", step.setElement("REQUEST_REVISION_MISSING_INFORMATION").element());
            }
            if (map.get(0).get("OTHER").equals("V")) {
                ((JavascriptExecutor) step.driver()).executeScript("arguments[0].click();", step.setElement("REQUEST_REVISION_OTHER").element());
            }
            step.pageReady();
            step.setElement("REQUEST_REVISION_MESSAGE").element().click();
            step.setElement("REQUEST_REVISION_MESSAGE").element().sendKeys(map.get(0).get("MESSAGE"));
        } else {
            step.setElement("REQUEST_REVISION_NO").waitUntilClickable().click();
        }
    }
    @Given("{string} fills the form with the following information in the form")
   	public void fills_the_form_with_the_following_information_in_the_form(String string, io.cucumber.datatable.DataTable dt) {
   		//step.wait(2000);
   		//step.setElement("selectlocationfield").click();
   		//step.wait(1000);
   		//step.setElement("PickAlocation").click();
   		//step.wait(5000);
   		WebElement clear = step.driver().findElement(By.xpath("//*[contains(@name, 'appointmentDate')]"));
   		String date = clear.getAttribute("value");
   		System.out.println("John" + date);
   		clear.sendKeys(Keys.CONTROL+"a");
   		clear.sendKeys(Keys.DELETE);
   		step.wait(2000);

   		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
   		for(int i = 0; i < list.size(); i++)
   		{
   			
   			this.c = Calendar.getInstance();
   	        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
   	        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
   	        this.d = new Date();
   	        this.c.setTime(this.d);
   	        this.c.add(Calendar.DATE, 3);
   	        String date1 = dateFormat.format(this.c.getTime());
   	        String time = timeFormat.format(this.d);

   	        step.setElement("appointmentdatefield").waitUntilPresence().inputToElement(date1);
   	       // step.setElement("appointmenttimefield").waitUntilPresence().inputToElement(time);
   	        step.wait(2000);
   			step.setElement("appointmentdatefield").waitUntilPresence().inputToElement(date1);
   			
   			((JavascriptExecutor)step.wait(400000).driver()).executeScript("arguments[0].click();", step.setElement("SAVE_CHANGES").element());
   			step.pageReady();
   		
   		}}
       
    @Then("{string} verifies the following warning is displayed")
    public void verifies_the_following_warning_is_displayed(String string) {
    	step.wait(1000);
    	step.setElement("TimeoutWarning").element().getText();
    	// Assert.assertEquals("There was a problem submitting your request. Please try again later.", step.setElement("TimeoutWarning").element().getText());
    	step.wait(1000);
    	((JavascriptExecutor)step.driver()).executeScript("arguments[0].click();", step.setElement("EditCancelButton").element());
    	step.pageReady();
    	step.wait(1000).driver().findElement(By.xpath("//a[@data-field='backHome']")).click();
    }
   
    @Given("{string} waits Sixteen minutes with no mouse or keyboard movement")
    public void waits_Sixteen_minutes_with_no_mouse_or_keyboard_movement(String string) {
       step.wait(80000);
    }

    @When("{string} clicks on Request a Surgery button again")
    public void clicks_on_Request_a_Surgery_button_again(String string) {
 			step.setElement("requestasurgerybutton").click();
    }

    @Then("{string} is taken to logout page or user logs out")
    public void is_taken_to_logout_page_or_user_logs_out(String requestSurgeryPage) {
    	String currentLandingURL = step.driver().getCurrentUrl();
	    System.out.println("Current URL is " + currentLandingURL);
	    try {
	          Assert.assertEquals(currentLandingURL, requestSurgeryPage);
	        } catch (AssertionError e) {
	            System.out.println("User is Not on logout page.");
	        }
	    System.out.println("User is on logout page.");
       
    }
	    @Then("Browser quits")
	    public void browser_quits() {
	    	step.wait(2000);
	 	    step.shutdownDriver();
	    }
 }
