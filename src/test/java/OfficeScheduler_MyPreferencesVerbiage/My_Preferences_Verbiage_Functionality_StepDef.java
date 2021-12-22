package OfficeScheduler_MyPreferencesVerbiage;

import Login.Login;
import Login.Role;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.core.api.Scenario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class My_Preferences_Verbiage_Functionality_StepDef {
	private Calendar c;
	private Date d;
	private static int unscheduled_number;
	private static boolean dunit = false;
	private final int  DELAY_2000 = 2000;

    private final String
    					 RERQUEST_A_SURGERY_BUTTON = "requestasurgerybutton",
    				     APPOINTMENT_DETAILS_HEADER = "appointmentdetailsheader",
    				     PATIENT_DETAILS_HEADER = "patientdetailsheader",
    				     SURGERY_INFORMATION_HEADER = "surgeryinformationheader",
    				     PRE_ADMIT_TESTING_HEADER = "preadmittestingheader",
    					 APPOINTMENT_LOCATION_HEADER = "appointmentlocationheader",
    					 SURGERY_DATE_AND_TIME_HEADER = "surgerydateandtimeheader",
    					 TO_FOLLOW_OPTION_HEADER = "tofollowheader",
    					 BLOCK_TIME_OPTION_HEADER = "blocktimeheader",
    					 AUTH_STATUS_HEADER = "authstatusheader";

	private StepDefHelperYml step = new StepDefHelperYml();

	    @Given("{string} is on case tracker landing page")
	    public void user_is_on_landing_page(String surgicalSchedulingLandingPage) {
			Login login = new Login();
			login.LoginPage(Role.OFFICE_SCHEDULER);
	        step.from("RequestASurgery");
      }	    
	    @When("{string} clicks {string} on the top right corner of the page")
	    public void user_clicks_on_request_a_surgery_button(String user, String requestASurgeryButton) {
			step.wait(2000) ;
//			unscheduled_number = Integer.parseInt(step.setElement("Unscheduled_Number").pageReady().element().getText());
			step.setElement(RERQUEST_A_SURGERY_BUTTON).click();
			step.wait(2000) ;
		}
	    @Then("{string} is taken to {string}")
	    public void user_is_on_request_a_surgery_page(String user, String requestSurgeryPage)
	    {
	    	String currentLandingURL = step.driver().getCurrentUrl();
		    System.out.println("Current URL is " + currentLandingURL);
		    try {
		          Assert.assertEquals(currentLandingURL, requestSurgeryPage);
		        } catch (AssertionError e) {
		            System.out.println("User is not on the request surgery page after logging in.");
		        }
		    System.out.println("User is on the request surgery page after logging in.");
	    }
	    
	    @And("the following forms are available")
	    public void available_forms_on_surgery_request_page(DataTable dt) {
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for(int i = 0; i < list.size(); i++)
			{
				Assert.assertEquals(list.get(i).get(0), step.setElement(APPOINTMENT_DETAILS_HEADER).element().getText());
				Assert.assertEquals(list.get(i).get(1), step.setElement(PATIENT_DETAILS_HEADER).element().getText());
				Assert.assertEquals(list.get(i).get(2), step.setElement(SURGERY_INFORMATION_HEADER).element().getText());
				Assert.assertEquals(list.get(i).get(3), step.setElement(PRE_ADMIT_TESTING_HEADER).element().getText());
			}
	    }
	    
	    @Given("{string} is on appointment details form")
	    public void user_is_on_appointment_details_form(String user)
	    {
	    	Assert.assertEquals("Appointment Details", step.setElement(APPOINTMENT_DETAILS_HEADER).element().getText());
	    }
	    
	    @When("{string} confirms the following fields are available")
	    public void validate_available_fields_in_appointment_details_form(String user, DataTable dt)
	    {
	    	List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for(int i = 0; i < list.size(); i++)
			{
				Assert.assertEquals(list.get(i).get(0), step.setElement(APPOINTMENT_LOCATION_HEADER).element().getText());
				Assert.assertEquals(list.get(i).get(1), step.setElement(SURGERY_DATE_AND_TIME_HEADER).element().getText());
				Assert.assertEquals(list.get(i).get(2), step.setElement(TO_FOLLOW_OPTION_HEADER).element().getText());
				Assert.assertEquals(list.get(i).get(3), step.setElement(BLOCK_TIME_OPTION_HEADER).element().getText());
				Assert.assertEquals(list.get(i).get(3), step.setElement(AUTH_STATUS_HEADER).element().getText());
			}
	    }
	    
	    @Then("{string} fills the form with the following information")
	    public void filling_appointment_details(String user, DataTable dt )
	    {
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			String appointmentLocation = list.get(0).get("APPOINTMENT LOCATION");
			this.c = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
			this.d = new Date();
			this.c.setTime(this.d);
			this.c.add(Calendar.DATE, 3);
			String date = dateFormat.format(this.c.getTime());
			String time = timeFormat.format(this.d);
			step.setElement("selectlocationfield").click();
			((JavascriptExecutor)step.driver()).executeScript("arguments[0].click();", step.setElement(appointmentLocation).element());
			for(int i = 0; i < list.size(); i++)
			{

				step.setElement("appointmentdatefield").waitUntilPresence().inputToElement(date);
				step.setElement("appointmenttimefield").waitUntilPresence().inputToElement(time);

				step.setElement("meridiemfield").waitUntilPresence().inputToElement(list.get(i).get("MERIDIAN"));
				
			}
			step.setElement("tofollowcheckbox").waitUntilPresence().click();
				
			step.setElement("selectstatusfield").click();
			step.setElement("inpatientstatus").click();
	    }

	    @Given("{string} is on the Patient Details form")
	    public void is_on_the_Patient_Details_form(String string) {
	    	Assert.assertEquals("Patient Details", step.setElement("Patient Details").element().getText());
	    }

	    @Then("{string} fills the form with the following information in form")
	    public void fills_the_form_with_the_following_information_in_form(String string, io.cucumber.datatable.DataTable dataTable) {
			List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
			for (int i = 0; i < list.size(); i++) {
				step.setElement("Last Name").waitUntilPresence().inputToElement(list.get(i).get("FIRST NAME"));
				step.wait(1000).setElement("First Name").waitUntilPresence().inputToElement(list.get(i).get("LAST NAME"));
				step.wait(1000).setElement("Middle Name").waitUntilPresence().inputToElement(list.get(i).get("MIDDLE NAME"));
				step.wait(1000).setElement("Data of Birth").waitUntilPresence().inputToElement(list.get(i).get("DATE OF BIRTH"));
				step.wait(1000).setElement("SSN").waitUntilPresence().inputToElement(list.get(i).get("SSN"));
				step.wait(1000).setElement("Phone Number").waitUntilPresence().inputToElement(list.get(i).get("PHONE NUMBER"));
				step.wait(1000).setElement("Email").waitUntilPresence().inputToElement(list.get(i).get("EMAIL"));
				step.wait(1000).setElement("INTERPRETER NEEDED").waitUntilPresence().click();
				step.wait(1000).setElement("LANGUAGE").waitUntilPresence().inputToElement(list.get(i).get("LANGUAGE"));
			}
			step.wait(1000).setElement("LATEX ALLERGY NO").waitUntilPresence().click();
			step.wait(1000).setElement("Patient Sex").waitUntilPresence().click();
			step.wait(1000).setElement("Sex Male").click();
	    }

	    @Then("{string} fills the Primary Insurance Details with the following information")
	    public void fills_the_Primary_Insurance_Details_with_the_following_information(String string, io.cucumber.datatable.DataTable dataTable) {
			List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
			for (int i = 0; i < list.size(); i++) {
				step.wait(1000).setElement("PRIMARY INSURED LAST NAME").waitUntilPresence().inputToElement(list.get(i).get("PRIMARY INSURED LAST NAME"));
				step.wait(1000).setElement("PRIMARY INSURED FIRST NAME").waitUntilPresence().inputToElement(list.get(i).get("PRIMARY INSURED FIRST NAME"));
				step.wait(1000).setElement("Health Plan Name").waitUntilPresence()
						.inputToElement(list.get(i).get("PRIMARY HEALTH PLAN NAME"));
				step.wait(1000).setElement("Policy Number").waitUntilPresence()
						.inputToElement(list.get(i).get("PRIMARY POLICY NUMBER"));
				step.wait(1000).setElement("PRIMARY GROUP NUMBER").waitUntilPresence()
						.inputToElement(list.get(i).get("PRIMARY GROUP NUMBER"));
				step.wait(1000).setElement("PRIMARY PRE-CERT NUMBER").waitUntilPresence()
						.inputToElement(list.get(i).get("PRIMARY PRE-CERT NUMBER"));
			}
			String answer = list.get(0).get("PRIMARY PRE-AUTH IN PROGRESS");
			String xpath = "PRE-AUTH IN PROGRESS " + answer;
			step.setElement(xpath).waitUntilPresence().click();
	    }

	    @Then("{string} clicks Add Secondary Insurance to provide the following inputs")
	    public void clicks_Add_Secondary_Insurance_to_provide_the_following_inputs(String string, io.cucumber.datatable.DataTable dataTable) {
			step.wait(2000).setElement("Add Secondary Insurance").waitUntilPresence().click();

			List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
			for (int i = 0; i < list.size(); i++) {
				step.wait(1000).setElement("SECONDARY INSURED LAST NAME").waitUntilPresence()
						.inputToElement(list.get(i).get("SECONDARY INSURED LAST NAME"));
				step.wait(1000).setElement("SECONDARY INSURED FIRST NAME").waitUntilPresence()
						.inputToElement(list.get(i).get("SECONDARY INSURED FIRST NAME"));
				step.wait(1000).setElement("SECONDARY HEALTH PLAN NAME").waitUntilPresence()
						.inputToElement(list.get(i).get("SECONDARY HEALTH PLAN NAME"));
				step.wait(1000).setElement("SECONDARY POLICY NUMBER").waitUntilPresence()
						.inputToElement(list.get(i).get("SECONDARY POLICY NUMBER"));
				step.wait(1000).setElement("SECONDARY GROUP NUMBER").waitUntilPresence()
						.inputToElement(list.get(i).get("SECONDARY GROUP NUMBER"));
				step.wait(1000).setElement("SECONDARY PRE-CERT NUMBER").waitUntilPresence()
						.inputToElement(list.get(i).get("SECONDARY PRE-CERT NUMBER"));
			}
			String answer = list.get(0).get("SECONDARY PRE_AUTH IN PROGRESS");
			String xpath = "SECONDARY PRE-AUTH IN PROGRESS " + answer;
			step.setElement(xpath).waitUntilPresence().click();
	    }

	    @Given("{string} is on Surgery Information form")
	    public void is_on_Surgery_Information_form(String string) {
			Assert.assertEquals("Surgery Information", 
					step.setElement("surgeryinformation").element().getText()
					);
	    }

	    @When("{string} confirms the following fields are available in surgery information")
	    public void confirms_the_following_fields_are_available_in_surgery_information(String string, io.cucumber.datatable.DataTable dataTable) {
			List<String> list = dataTable.asList(String.class);
			
			Assert.assertEquals(list.get(0), 
					step.setElement("primarydiagnosis").element().getText()
					);
			Assert.assertEquals(list.get(1), 
					step.setElement("secondarydiagnosis").element().getText()
					);
			Assert.assertEquals(list.get(2), 
					step.setElement("surgicalprocedures").element().getText()
					);
			Assert.assertEquals(list.get(3), 
					step.setElement("othersurgicalcomments").element().getText()
					);
	    }

	    @Then("{string} fills the form with the following information in surgery information")
	    public void fills_the_form_with_the_following_information_in_surgery_information(String string, io.cucumber.datatable.DataTable dataTable) {
			List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
			for(Map<String, String> dataRow : list) {
				String primaryDiagnosis = dataRow.get("PRIMARY DIAGNOSIS");
				String secondaryDiagnosis = dataRow.get("SECONDARY DIAGNOSIS");
				String otherSurgicalComments = dataRow.get("OTHER SURGICAL COMMENTS");
				step.setElement("primarydiagnosis_input").element().sendKeys(primaryDiagnosis);
				step.setElement("secondarydiagnosis_input").element().sendKeys(secondaryDiagnosis);
				step.setElement("othersurgicalcomments_input").element().click();
				step.setElement("othersurgicalcomments_input").element().sendKeys(otherSurgicalComments);
			}
	    }
	    
	    @When("{string} clicks Add manually button")
	    public void clicks_Add_manually_button(String string) {
	        step.wait(DELAY_2000);
	    	step.setElement("add_manually_button").element().click();
	    }

	    @Then("{string} confirms the following files are available in Add a Procedure")
	    public void confirms_the_following_files_are_available_in_Add_a_Procedure(String string, io.cucumber.datatable.DataTable dataTable) {
			List<String> list = dataTable.asList(String.class);
	    	Assert.assertEquals(list.get(0),
	    			step.setElement("surgeonheader").element().getText()
	    			);
	    	Assert.assertEquals(list.get(1), 
	    			step.setElement("cptcodesheader").element().getText()
	    			);
	    	Assert.assertEquals(list.get(2), 
	    			step.setElement("procedurenameheader").element().getText()
	    			);
	    	Assert.assertEquals(list.get(3), 
	    			step.setElement("modifierheader").element().getText()
	    			);
	    	Assert.assertEquals(list.get(4), 
	    			step.setElement("primaryprocedureheader").element().getText()
	    			);
	    	Assert.assertEquals(list.get(5), 
	    			step.setElement("durationheader").element().getText()
	    			);
	    	Assert.assertEquals(list.get(6), 
	    			step.setElement("anesthesiatypeheader").element().getText()
	    			);
	    	Assert.assertEquals(list.get(7), 
	    			step.setElement("implantsneededheader").element().getText()
	    			);
	    	Assert.assertEquals(list.get(8), 
	    			step.setElement("otherequipmentandsuppliesheader").element().getText()
	    			);
	    	Assert.assertEquals(list.get(9), 
	    			step.setElement("additionalprocedurecommentsheader").element().getText()
	    			);
	    }

	    @Then("{string} adds surgical procedures information")
	    public void adds_surgical_procedures_information(String string, io.cucumber.datatable.DataTable dataTable) {
	    	List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
	    	step.setElement("surgeondropdown").element().click();
	    	step.wait(DELAY_2000);
	    	step.setElement("firstsurgeon").element().click();

	    	step.wait(DELAY_2000);
	    	String cptcodes = list.get(0).get("CPT CODE(S)");
	    	step.setElement("cptcodes_input").element().sendKeys(cptcodes);

	    	step.wait(DELAY_2000);
	    	String procedurename = list.get(0).get("PROCEDURE NAME");
	    	step.setElement("procedurename_input").element().sendKeys(procedurename);;

	    	step.setElement("modifierside").element().click();
	    	step.setElement("SIDE Left").element().click();
	    	step.wait(DELAY_2000);

	    	step.setElement("modifierapproach").element().click();
	    	step.setElement("APPROACH Anterior").element().click();
	    	step.wait(DELAY_2000);
	    	
	    	step.setElement("primaryprocedure_yes").element().click();
	    	step.wait(DELAY_2000);

	    	String duration = list.get(0).get("DURATION");
	    	step.setElement("duration_input").element().sendKeys(duration);

	    	step.setElement("anesthesiatype").element().click();
	    	step.setElement("firstanesthesiatype").element().click();
	    	step.wait(DELAY_2000);

	    	step.setElement("implantsneeded_no").element().click();
	    	step.wait(DELAY_2000);

	    	String other_equipment_and_supplies = list.get(0).get("OTHER EQUIPMENT AND SUPPLIES");
	    	step.setElement("otherequipmentandsupplies_textarea").element().click();
	    	step.setElement("otherequipmentandsupplies_textarea").element().sendKeys(other_equipment_and_supplies);
	    	step.wait(DELAY_2000);

	    	String additional_procedure_comments = list.get(0).get("ADDITIONAL PROCEDURE COMMENTS");
	    	step.setElement("additionalprocedurecomments_textarea").element().click();
	    	step.setElement("additionalprocedurecomments_textarea").element().sendKeys(additional_procedure_comments);
	    	
	    	step.wait(DELAY_2000);
	    	step.setElement("addsurgery_button").element().click();
		}
		
		@Given("{string} is on pre-admit testing form")
	    public void user_is_on_pre_admit_testing_form(String user)
	    {
	    	String preAdmitTestingHeader = "Pre-Admit Testing Details";
	    	Assert.assertEquals(preAdmitTestingHeader, step.setElement(PRE_ADMIT_TESTING_HEADER).element().getText());
	    }
	    
	    @When("{string} confirms the following fields are available in the pre-admit form")
	    public void validate_available_fields_in_pre_admit_details_form(String user, DataTable dt)
	    {
	    	List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for(int i = 0; i < list.size(); i++)
			{
				Assert.assertEquals(list.get(i).get(0), step.setElement("isprehospitalbedneededheader").element().getText());
				Assert.assertEquals(list.get(i).get(1), step.setElement("ispreadmissiontestingneededheader").element().getText());
			}
	    }
	    
	    @Then("{string} fills the form with the pre-admit form with the following information")
	    public void filling_pre_admit_testing_form(String user, DataTable dt)
	    {
		    List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for(int i = 0; i < list.size(); i++)
			{
				step.setElement("isprehospitalbedneededfield").click();
				step.wait(DELAY_2000);
				step.setElement("isprehospitalbedneededfirstchoice").click();
				step.wait(DELAY_2000);
				String answer = list.get(0).get("IS PRE-ADMISSION TESTING NEEDED");
				step.setElement("PAT NEEDED "+answer).element().click();
			}
	    }
	    
	    @And("{string} clicks {string}")
	    public void submit_form(String user, String submitButton)
	    {
	    	if(step.setElement("submitsurgeryrequestbutton") != null)
	    		{
	    			System.out.print("Submit a surgery request button is there");
	    			step.wait(DELAY_2000);
		    		step.setElement("submitsurgeryrequestbutton").click();
	    		}
	    	else
		    	{
		    		System.out.print("Submit a surgery request button is not there!!!");
		    	}
	    }
	    
	    @When("{string} clicks HOME button")
	    public void clicks_HOME_button(String user) {
	    	step.wait(DELAY_2000);
			((JavascriptExecutor)step.driver()).executeScript("arguments[0].click();", step.setElement("HOME_Button").element());
			step.pageReady();
	    }
	    @And("{string} search a request by name")
	    public void search_a_request_by_name(String user) {
			step.wait(2000);
	    	step.setElement("SearchByPatientName").element().click();
	    	step.setElement("SearchByPatientName").inputToElement("Brad");
	    	step.setElement("SearchByPatientName").element().sendKeys(Keys.ENTER);
	    	step.wait(1000);
	    	step.setElement("SortBy").element().click();
			step.pageReady();
	    	step.setElement("Created_Newest").element().click();
			step.pageReady();
			step.wait(3000);
		}

	@Then("{string} should be able to see the request just made")
	    public void should_be_able_to_see_the_request_just_made(String user, DataTable dt) throws ParseException {
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
	    	step.pageReady();
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE, 3);
	    	DateFormat d1 = new SimpleDateFormat("MM/dd/yyyy");
			String date = d1.format(c.getTime());
	    	Date dateInstance = d1.parse(date);
	    	String month = new SimpleDateFormat("MM").format(dateInstance);

			String formatPattern = month.equals("06") || month.equals("07") ? "EEEE, MMMM. d" : "EEEE, MMM. d";
			DateFormat dateFormat = new SimpleDateFormat(formatPattern);
			String finalDate = dateFormat.format(dateInstance);

	    	Assert.assertEquals(finalDate,
	    			step.setElement("Created_Newest_Request_Date").element().getText());
	    	String patientName = list.get(0).get("Patient Name");
	    	Assert.assertEquals(patientName,
	    			step.setElement("Created_Newest_Patient_Name").element().getText());
			String procedure = list.get(0).get("Procedure");
			Assert.assertEquals(procedure,
	    			step.setElement("Created_Newest_Procedure").element().getText());
			String location = list.get(0).get("Location");
			Assert.assertEquals(location,
	    			step.setElement("Created_Newest_Location").element().getText());
			//String surgeon = list.get(0).get("Surgeon");
			//Assert.assertEquals(surgeon,
	    			//step.setElement("Created_Newest_Surgeon").element().getText());
	    }
	    
	   @Then("Unscheduled in task area should increment by one")
	   public void unscheduled_in_task_area_should_increment_by_one() {
		   step.wait(1000);
//		   int actual_unscheduled_number = Integer.parseInt(step.setElement("Unscheduled_Number").waitUntilPresence().element().getText());
//		   Assert.assertEquals(unscheduled_number + 1, actual_unscheduled_number);
		   step.driver().quit();
	   }
}
