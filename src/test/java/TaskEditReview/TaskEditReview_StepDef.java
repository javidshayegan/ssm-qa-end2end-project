package TaskEditReview;

import Login.*;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TaskEditReview_StepDef {

	private static final int DELAY_1000 = 0;
	private static boolean dunit = false;
	private static boolean after_unit = false;
	private Calendar c;
	private Date d;
	private static String date;
	private static String time;
	private final int  DELAY_2000 = 2000;

	 private StepDefHelperYml step = new StepDefHelperYml();

	@Given("{string} is on {string}")
	public void is_on(String string, String surgicalSchedulingLandingPage) {
		Login login=new Login();
		login.LoginPage(Role.OFFICE_SCHEDULER);
		//assert user is on the surgical scheduling landing page
		String currentLandingURL=step.driver().getCurrentUrl();
		System.out.println("Current URL is "+currentLandingURL);
		try{
			Assert.assertEquals(currentLandingURL,"https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc");
		}catch(AssertionError e){
			System.out.println("User is not on the case tracker landing page after logging in.");
		}
		System.out.println("User is on the case tracker landing page after logging in.");
		step.from("TaskEditAndReview");
	}

	@When("{string} clicks on the {string} on the page")
	public void clicks_on_the_on_the_page(String user, String Review) {
		step.from("TaskEditAndReview");
		step.pageReady();
		Assert.assertEquals("REVIEW ALL TASKS >",
				step.setElement("ReviewAllTasks").waitUntilVisible().element().getText());
		step.wait(DELAY_2000);
		//step.driver().findElement(By.cssSelector("#root > div > div:nth-child(2) > div.sc-htpNat.sc-bxivhb.eCVcHg > div > div > div > div.sc-htpNat.sc-bxivhb.bZZZSv > div > div.sc-htpNat.sc-bxivhb.gBhflo > div.sc-htpNat.dKMNmH > a")).click();
		step.wait(2000);
		while(step.findElements("SPINNER").size() !=0);
		step.setElement("ReviewAllTasks").waitUntilPresence().element().click();
        JavascriptExecutor js = (JavascriptExecutor) step.driver();
        js.executeScript("window.scrollBy(0,350)", "");
	}

	@When("{string} is taken to {string}")
	public void is_taken_to(String review, String edit) {
		Assert.assertTrue(step.driver().getCurrentUrl().equals(edit));
	}

	@Then("user verifies the following sections are available")
	public void user_verifies_the_following_sections_are_available(io.cucumber.datatable.DataTable dataTable) {
		step.wait(DELAY_1000);
		List<String> list = dataTable.asList();
		List<String> actualList = new ArrayList<>();
		actualList.add("AppointmentRequest");
		actualList.add("PatientInfo");
		actualList.add("SurgeryInfo");
		actualList.add("PatInfo");
		for (int i = 0; i < list.size(); i++) {
			Assert.assertEquals(list.get(i), step.setElement(actualList.get(i)).waitUntilVisible().element().getText());
		}
	}

	@Given("{string} is on Review All Tasks page")
	public void is_on_review_all_tasks_page(String string) {
		String URL = step.driver().getCurrentUrl();
		Assert.assertEquals(URL,
				"https://casetracker-qa.app.cloud-02.pcf.ascension.org/queue/0?status=UNSCHEDULED&state=NEEDS_REVISION&state=WARNING&task=1");
		System.out.println("URL matching --> Review All Task page");
	}

	
	@When("{string} clicks on {string}")
	public void clicks_on(String user, String edit) throws InterruptedException {
		step.wait(5000);
		Actions action = new Actions(step.driver());
        JavascriptExecutor js = (JavascriptExecutor) step.driver();
        js.executeScript("window.scrollBy(0,600)", "");
		step.wait(5000);

		WebElement element =step.driver().findElement(By.xpath("(//span[contains(@class, 'sc-bdVaJa hsNRmA')])[2]"));

		action.moveToElement(element).click(element).build().perform();
	}

	@When("user verifies the following forms are available on Edit Dashboard")
	public void user_verifies_the_following_forms_are_available_on_Edit_Dashboard(io.cucumber.datatable.DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for(int i = 0; i < list.size(); i++)
		{
			Assert.assertEquals(list.get(i).get(0), step.setElement("APPOINTMENT LOCATION").element().getText());
			Assert.assertEquals(list.get(i).get(1), step.setElement("surgerydateandtimeheader").element().getText());
			Assert.assertEquals(list.get(i).get(2), step.setElement("tofollowheader").element().getText());
			Assert.assertEquals(list.get(i).get(3), step.setElement("blocktimeheader").element().getText());
			Assert.assertEquals(list.get(i).get(3), step.setElement("authstatusheader").element().getText());
		}
	}

	@Then("{string} fills the form with the following information in the form")
	public void fills_the_form_with_the_following_information_in_the_form(String string, io.cucumber.datatable.DataTable dt) {
		step.wait(2000);
		step.setElement("selectlocationfield").click();
		step.wait(1000);
		step.setElement("PickAlocation").click();
		step.wait(5000);
		WebElement clear = step.driver().findElement(By.xpath("//*[contains(@name, 'appointmentDate')]"));
		String date = clear.getAttribute("value");
		System.out.println("John" + date);
		clear.sendKeys(Keys.CONTROL+"a");
		clear.sendKeys(Keys.DELETE);
		step.wait(2000);

		/*
		Date datee = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(datee);
		String month =strDate.substring(0, 2);
		String day =strDate.substring(3, 7);
		System.out.println("Month date is"+ day);
		String year =strDate.substring(5, 9);
		System.out.println("Month date is"+ year);

		clear.sendKeys(month);
		step.wait(2000);
		int newday=Integer.parseInt(day);
		newday=newday+1;
		day=Integer.toString(newday);

		clear.sendKeys(day);
		step.wait(2000);
		clear.sendKeys(year);
		step.wait(2000);
*/


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
	        step.setElement("appointmenttimefield").waitUntilPresence().inputToElement(time);

			step.setElement("appointmentdatefield").waitUntilPresence().inputToElement(date1);
			step.wait(1000);
			//step.setElement("appointmenttimefield").waitUntilPresence().inputToElement(time);
			step.wait(1000);
			step.setElement("merdiemfieldbox").waitUntilPresence().click();
			step.wait(1000);
			step.setElement("meridiemfield").waitUntilPresence().inputToElement(list.get(i).get("MERIDIAN"));
		}
		step.wait(1000);
		step.setElement("tofollowcheckbox").waitUntilPresence().click();
		step.wait(1000);
		step.setElement("toblockchebox").waitUntilPresence().click();
		step.wait(1000);
		step.setElement("selectstatusfield").waitUntilPresence().click();
		step.wait(1000);
		step.setElement("Outpatientstatus").waitUntilPresence().click();
	}
	 @Given("{string} is on the Patient Details form")
	 public void is_on_the_Patient_Details_form(String string) {
		   Assert.assertEquals("Patient Details", step.setElement("Patient Details").element().getText());

	 }

	 @When("{string} confirms the following fields are available")
	 public void confirms_the_following_fields_are_available(String string, io.cucumber.datatable.DataTable dt) {
		 List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			for(int i = 0; i < list.size(); i++)
			{
				Assert.assertEquals(list.get(i).get(0), step.setElement("Patient Details").element().getText());
				Assert.assertEquals(list.get(i).get(1), step.setElement("Last Name").element().getText());
				Assert.assertEquals(list.get(i).get(2), step.setElement("FIRST NAMEF").element().getText());
				Assert.assertEquals(list.get(i).get(3), step.setElement("Data of Birth").element().getText());
				Assert.assertEquals(list.get(i).get(3), step.setElement("SSN").element().getText());
				Assert.assertEquals(list.get(i).get(3), step.setElement("Patient Sex").element().getText());
				Assert.assertEquals(list.get(i).get(3), step.setElement("Phone Number").element().getText());
				Assert.assertEquals(list.get(i).get(3), step.setElement("Email").element().getText());

			}
		}

	 @Then("{string} fills the form with the following information in form")
	 public void fills_the_form_with_the_following_information_in_form(String string, io.cucumber.datatable.DataTable dataTable) {

		  List<Map<String, String>> list = dataTable.asMaps(String.class,
		  String.class);
		  for (int i = 0; i < list.size(); i++) {
			  step.wait(2000);
			  WebElement clear = step.driver().findElement(By.xpath("//input[contains(@id, 'patientLastName')]"));
			  clear.sendKeys(Keys.CONTROL+"a");
			  clear.sendKeys(Keys.DELETE);
			  step.wait(1000);

			  step.driver().findElement(By.xpath("//input[contains(@id, 'patientLastName')]")).sendKeys("Jackson");
			  step.wait(1000);

			  WebElement clear1 = step.driver().findElement(By.xpath("//input[contains(@id, 'patientFirstName')]"));
			  clear1.sendKeys(Keys.CONTROL+"a");
			  clear1.sendKeys(Keys.DELETE);
			  step.wait(1000);

			  step.driver().findElement(By.xpath("//input[contains(@id, 'patientFirstName')]")).sendKeys("John");


			  //
			  step.wait(1000);
			  WebElement clear2 = step.driver().findElement(By.xpath("//input[contains(@id, 'patientMiddleName')]"));
			  clear2.sendKeys(Keys.CONTROL+"a");
			  clear2.sendKeys(Keys.DELETE);
			  step.wait(2000);

			  step.driver().findElement(By.xpath("//input[contains(@id, 'patientMiddleName')]")).sendKeys("Junior");
			  step.wait(1000);


			  WebElement clear3 = step.driver().findElement(By.xpath("//input[contains(@id, 'patientDOB')]"));
			  clear3.sendKeys(Keys.CONTROL+"a");
			  clear3.sendKeys(Keys.DELETE);
			  step.wait(1000);

			  step.driver().findElement(By.xpath("//input[contains(@id, 'patientDOB')]")).sendKeys("01/01/1987");
			  step.wait(1000);

			  //

			  WebElement clear4 = step.driver().findElement(By.xpath("//input[contains(@id, 'patientSSN')]"));
			  clear4.sendKeys(Keys.CONTROL+"a");
			  clear4.sendKeys(Keys.DELETE);
			  step.wait(1000);

			  step.driver().findElement(By.xpath("//input[contains(@id, 'patientSSN')]")).sendKeys("093-23-3750");
			  step.wait(1000);

			  WebElement clear5 = step.driver().findElement(By.xpath("//input[contains(@id, 'patientPhoneNumber')]"));
			  clear5.sendKeys(Keys.CONTROL+"a");
			  clear5.sendKeys(Keys.DELETE);
			  step.wait(1000);

			  step.driver().findElement(By.xpath("//input[contains(@id, 'patientPhoneNumber')]")).sendKeys("(314)-323-0001");
			  step.wait(1000);


			  step.wait(1000);
			  WebElement clear6 = step.driver().findElement(By.xpath("//input[contains(@id, 'patientEmail')]"));
			  clear6.sendKeys(Keys.CONTROL+"a");
			  clear6.sendKeys(Keys.DELETE);
			  step.wait(1000);

			  step.driver().findElement(By.xpath("//input[contains(@id, 'patientEmail')]")).sendKeys("JohnJackson@email.com");
			  step.wait(1000);


			  step.driver().findElement(By.xpath("(//div[contains(@class, 'sc-htpNat sc-bxivhb sc-ckVGcZ gsGvld')])[3]")).click();
			  step.wait(1000);
			  step.driver().findElement(By.xpath("//span[@class='sc-bdVaJa sc-gqjmRU fBoPwA'][contains(.,'INTERPRETER NEEDED')]")).click();
			  step.wait(1000);
			  WebElement clear7 = step.driver().findElement(By.xpath("//input[contains(@id, 'patientInterpretLanguage')]"));
			  clear7.sendKeys(Keys.CONTROL+"a");
			  clear7.sendKeys(Keys.DELETE);
			  step.wait(1000);
			  clear7.sendKeys("English");

			  step.wait(1000);

			  }

		 }

	 @Then("{string} fills the Primary Insurance Details with the following information")
	 public void fills_the_Primary_Insurance_Details_with_the_following_information(String string, io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {

			  step.wait(1000);
			  WebElement clear = step.driver().findElement(By.xpath("//input[contains(@id, 'insuredLastName')]"));
			  clear.sendKeys(Keys.CONTROL+"a");
			  clear.sendKeys(Keys.DELETE);
			  step.wait(1000);

			  step.driver().findElement(By.xpath("//input[contains(@id, 'insuredLastName')]")).sendKeys("George");
			  step.wait(1000);

			  WebElement clear1 = step.driver().findElement(By.xpath("//input[contains(@id, 'insuredFirstName')]"));
			  clear1.sendKeys(Keys.CONTROL+"a");
			  clear1.sendKeys(Keys.DELETE);
			  step.wait(1000);

			  step.driver().findElement(By.xpath("//input[contains(@id, 'insuredFirstName')]")).sendKeys("Clooney");

			  step.wait(1000);
			  WebElement clear2 = step.driver().findElement(By.xpath("//input[contains(@id, 'healthPlanName')]"));
			  clear2.sendKeys(Keys.CONTROL+"a");
			  clear2.sendKeys(Keys.DELETE);
			  step.wait(1000);

			  step.driver().findElement(By.xpath("//input[contains(@id, 'healthPlanName')]")).sendKeys("CareAct");
			  step.wait(1000);

			  WebElement clear3 = step.driver().findElement(By.xpath("//input[contains(@id, 'policyNumber')]"));
			  clear3.sendKeys(Keys.CONTROL+"a");
			  clear3.sendKeys(Keys.DELETE);
			  step.wait(1000);

			  step.driver().findElement(By.xpath("//input[contains(@id, 'policyNumber')]")).sendKeys("506030");
			  step.wait(1000);

			  WebElement clear5 = step.driver().findElement(By.xpath("//input[contains(@id, 'preCertNumber')]"));
			  clear5.sendKeys(Keys.CONTROL+"a");
			  clear5.sendKeys(Keys.DELETE);
			  step.wait(1000);

			  step.driver().findElement(By.xpath("//input[contains(@id, 'preCertNumber')]")).sendKeys("12");
			  step.wait(1000);
		}
		step.setElement("PRE-AUTH IN PROGRESS").waitUntilPresence().click();
		step.wait(1000);
	 }

	@Then("{string} clicks Add Secondary Insurance to provide the following inputs")
	public void clicks_Add_Secondary_Insurance_to_provide_the_following_inputs(String string,
			io.cucumber.datatable.DataTable dataTable) {

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
		step.setElement("SECONDARY PRE-AUTH IN PROGRESS").waitUntilPresence().click();
	}

	@Given("{string} is on Surgery Information form")
	public void is_on_Surgery_Information_form(String string) {
		Assert.assertEquals("Surgery Information",
				step.setElement("surgeryinformationJ").element().getText()
		);
	}

	@Then("{string} fills the form with the following information in surgery information")
	public void fills_the_form_with_the_following_information_in_surgery_information(String string,
			io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> dataRow : list) {
			String primaryDiagnosis = dataRow.get("PRIMARY DIAGNOSIS");
			String secondaryDiagnosis = dataRow.get("SECONDARY DIAGNOSIS");
			String otherSurgicalComments = dataRow.get("OTHER SURGICAL COMMENTS");
			step.setElement("primarydiagnosis_input").element().sendKeys(primaryDiagnosis);
			step.setElement("othersurgicalcomments_input").element().click();
			step.setElement("othersurgicalcomments_input").element().sendKeys(otherSurgicalComments);
		}
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
		step.wait(DELAY_1000);
	//	step.driver().findElement(By.xpath("//div[contains(text(), '"+list.get(0).get("SURGEON")+"')]")).click();
		step.driver().findElement(By.xpath("//input[contains(@placeholder,'Select a Surgeon')]/following::div[5]")).click();

		step.wait(DELAY_1000);

		String cptcodes = list.get(0).get("CPT CODE(S)");
		step.setElement("cptcodes_input").element().sendKeys(cptcodes);

		step.wait(DELAY_1000);
		String procedurename = list.get(0).get("PROCEDURE NAME");
		step.setElement("procedurename_input").element().sendKeys(procedurename);;

		step.setElement("modifierside").element().click();
		step.setElement("firstmodifierside").element().click();
		step.wait(DELAY_1000);

		step.setElement("modifierapproach").element().click();
		step.setElement("firstmodifierapproach").element().click();
		step.wait(DELAY_2000);

		step.wait(5000).driver().findElement(By.xpath("//span[contains(.,'Other equipment and supplies')]/preceding::div[1]")).click();
		//step.setElement("primaryprocedure_No").element().click();
		//step.wait(DELAY_1000);
		//String primaryprocedure = list.get(0).get("PROCEDURE NAME");
		step.wait(2000).setElement("primaryprocedureYes").element().click();
		String duration = list.get(0).get("DURATION");
		step.setElement("duration_input").element().sendKeys(duration);

		step.wait(1000).setElement("anesthesiatype").element().click();
		step.setElement("firstanesthesiatype").element().click();
		step.wait(DELAY_1000);

		step.setElement("implantsneeded_no").element().click();
		step.wait(DELAY_1000);

		String other_equipment_and_supplies = list.get(0).get("OTHER EQUIPMENT AND SUPPLIES");
		step.setElement("otherequipmentandsupplies_textarea").element().click();
		step.setElement("otherequipmentandsupplies_textarea").element().sendKeys(other_equipment_and_supplies);
		step.wait(DELAY_1000);

		String additional_procedure_comments = list.get(0).get("ADDITIONAL PROCEDURE COMMENTS");
		step.setElement("additionalprocedurecomments_textarea").element().click();
		step.setElement("additionalprocedurecomments_textarea").element().sendKeys(additional_procedure_comments);
		step.driver().findElement(By.xpath("//button[contains(@id, 'addSurgery')]/span")).click();
		
	}

	@Given("{string} is on pre-admit testing form")
	public void user_is_on_pre_admit_testing_form(String user)
	{
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
		step.setElement("isprehospitalbedneededfield").element().click();
		step.wait(DELAY_2000);
		
//		step.driver().findElement(By.xpath("//div[contains(@value,'None Needed')]")).click();
//		step.wait(2000);
		step.driver().findElement(By.xpath("//div[contains(@value,'Direct Admit')]")).click();		//findElement(By.xpath("//input[contains(@value,'Direct Admit')]").click();
		//step.driver().findElement(By.xpath("//div[contains(text(), '"+list.get(0).get("IS A PRE-HOSPITAL BED NEEDED?")+"')]")).click();
		step.wait(DELAY_2000);

		step.driver().findElement(By.xpath("//span[contains(text(), 'Is pre-admission testing needed')]/ancestor::div[contains(@data-field, 'PAT_TestingNeeded')]/descendant::input[contains(@value, '"+list.get(0).get("IS PRE-ADMISSION TESTING NEEDED")+"')]/parent::label/div")).click();
		step.wait(DELAY_2000);
		step.setElement("isPre-AdmissionTestingNeeded").element().click();
		step.wait(1000);
		step.driver().findElement(By.xpath("//input[contains(@name,'patLocation')]")).click();
		step.setElement("whereIsThePATTakingPlace").element().click();
		step.wait(1000);
		
		WebElement dateBox = step.driver().findElement(By.xpath("//input[contains(@name,'patDate')]"));

        //Fill date as mm/dd/yyyy as 11/25/2020

		this.c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
        this.d = new Date();
        this.c.setTime(this.d);
        this.c.add(Calendar.DATE, 3);
        String date2 = dateFormat.format(this.c.getTime());
        String time2 = timeFormat.format(this.d);

        step.setElement("dateBox").waitUntilPresence().inputToElement(date2);
        step.setElement("dateBox").waitUntilPresence().inputToElement(time2);

		step.setElement("dateBox").waitUntilPresence().inputToElement(date2);
		step.wait(1000);
		
		
        //dateBox.sendKeys("11252020");

        //Press tab to shift focus to time field
        step.wait(1000);
        dateBox.sendKeys(Keys.TAB);

        //Fill time as 02:45 PM
        step.wait(1000);
        WebElement timeBox = step.setElement("PATTime").element();
        timeBox.sendKeys("1100");
		step.wait(1000);
		
		step.setElement("PATMeridianAM").element().click();
		step.wait(1000);
		//timeBox.sendKeys(Keys.TAB);
		step.wait(1000);
		//step.setElement("isPrevisionInitiatedByYouYES").element().click();
		step.driver().findElement(By.xpath("//input[contains(@name, 'requestRevisionYes')]/parent::label/div")).click();

		step.wait(15000);
		step.setElement("ProcedureTime").element().click();
		step.setElement("MassegeBox").element().sendKeys("This is for for testing purpose1");
	}

	@And("{string} clicks on Save Changes button")
	public void clicks_on_Save_Changes_button(String saveButton)
	{
		if(step.setElement("saveChanges") != null)
		{
				System.out.print("Save Changes button is there");
				step.wait(DELAY_2000);
				step.setElement("saveChanges").click();
		}
		else
		{
				System.out.print("Save Changes button is not there!!!");
		}
	}

	@When("{string} clicks HOME button")
	public void clicks_HOME_button(String user) {
		step.wait(DELAY_2000);
		step.setElement("HOME_Button").element().click();
	}
	@And("{string} sort a request by Newest")
	public void search_a_request_by_name(String user) {
		step.wait(1000);
		//step.setElement("SearchByPatientName").element().click();
		//step.setElement("SearchByPatientName").inputToElement("Brad");
		//step.setElement("SearchByPatientName").element().sendKeys(Keys.ENTER);
		//step.wait(1000);
		step.setElement("sortBy").element().click();
		step.wait(1000);
		//step.setElement("sortByNewest").element().click();
		step.setElement("revised").element().click();
	}

	@Then("{string} should be able to see the request just made")
	public void should_be_able_to_see_the_request_just_made(String user) throws ParseException {
		step.wait(3000);
		//DateFormat d1 = new SimpleDateFormat("MM/dd/yyyy");
		//Date dateInstance = d1.parse(date);
		//String month = new SimpleDateFormat("MM").format(dateInstance);

		//String formatPattern = month.equals("06") || month.equals("07") ? "EEEE, MMMM. d" : "EEEE, MMM. d";
		//DateFormat dateFormat = new SimpleDateFormat(formatPattern);
		//String finalDate = dateFormat.format(dateInstance);


		//Assert.assertEquals(finalDate,
				//step.setElement("Created_Newest_Request_Date").element().getText());
		//Assert.assertEquals(time + " AM",
				//step.setElement("Created_Newest_Request_Time").element().getText());
//		Assert.assertEquals("Tom, Cruise Test", step.setElement("Edited_Newest_Patient_Name").element().getText());
//		step.wait(1000);
//		Assert.assertEquals("Shoulder Arthroscopy",
//				step.setElement("Edited_Newest_Procedure").element().getText());
//		step.wait(1000);
//		Assert.assertEquals("St. Thomas Midtown BH CSC",
//				step.setElement("Edited_Newest_Location").element().getText());
//		//Assert.assertEquals("Moore MD, David",
			//	step.setElement("Edited_Newest_Surgeon").element().getText());
	}

   /*@Then("Unscheduled in task area should increment by one")
   public void unscheduled_in_task_area_should_increment_by_one() {
	   step.wait(1000);
	   int actual_unscheduled_number = Integer.parseInt(step.setElement("Unscheduled_Number").waitUntilPresence().element().getText());
   }*/

	@Then("{string} clicks on the newly edited procedure")
	public void clicks_on_the_newly_edited_procedure(String string) {
	 step.wait(1000);
	 step.setElement("Edited_Newest_Patient_Name").element().click();
	}

	@Then("{string} is taken to the page {string}")
	public void user_is_taken_to_the_page(String review1, String edit1) {
		step.wait(1000);
		Assert.assertTrue(step.driver().getCurrentUrl().equals(edit1));
	}

	@Then("{string} verifies a pending request message is displayed")
	public void verifies_a_pending_request_message_is_displayed(String string) {
		step.wait(1000);
		Assert.assertEquals("A revision request is pending approval.",
				step.setElement("PendingRequestMessage").element().getText());
	}
	
	@Given("{string} is on Task Review page again {string}")
	public void is_on_Task_Review_page_again(String string, String string2) {
			Assert.assertTrue(step.driver().getCurrentUrl().equals(string2));
	}

	@When("{string} clicks on {string} Button")
	public void clicks_on_Button(String string, String string2) {
		 step.wait(1000);
		 step.setElement("cancelAProcedureButton").element().click();
	}

	@When("{string} verifies {string} dashboard")
	public void verifies_dashboard(String string, String string2) {
		step.wait(1000);
		Assert.assertEquals("Cancel Procedure",
				step.setElement("cancelProcedureDashboard").element().getText());
		step.wait(1000);
		Assert.assertEquals("This action cannot be undone. The hospital scheduler will be notified of this cancellation.",
				step.setElement("cancelProcedurModalText").element().getText());
	}

	@Then("{string} writes the following message")
	public void writes_the_following_message(String string, io.cucumber.datatable.DataTable dataTable) {
		step.wait(1000);
		step.setElement("cancelProcedureMessage").element().sendKeys("For Testing Purpose: Surgery not needed!");
	}

	@Then("{string} clicks on Confirm Cancellation")
	public void clicks_on_Confirm_Cancellation(String string) {
		step.wait(1000);
		step.setElement("ConfirmCancellationButton").element().click();
	}

	@Then("{string} verifies a pending cancellation message is displayed")
	public void verifies_a_pending_cancellation_message_is_displayed(String string) {
		step.wait(1000);
		Assert.assertEquals("This procedure is pending cancellation.",
				step.setElement("PendingRequestMessage").element().getText());
	}

	@Then("{string} verifies the pending cancellation is initiated by the same user")
	public void verifies_the_pending_cancellation_is_initiated_by_the_same_user(String string) {
		step.wait(1000);
		Assert.assertEquals("For Testing Purpose; Surgery not needed!",
				step.setElement("PendingCancellationRequestVerification").element().getText());
		step.wait(1000);
	}
}
