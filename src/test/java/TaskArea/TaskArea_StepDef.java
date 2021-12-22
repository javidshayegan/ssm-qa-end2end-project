package TaskArea;

import Login.Login;
import Login.Role;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.core.api.Scenario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class TaskArea_StepDef
{

	private static boolean dunit = false;
	private final int  DELAY_2000 = 2000;
	private final String USER_ID = "userid",
				 WARNINGS_CARD_TITLE = "warningcardtitle",
				 UNSCHEDULED_CARD_TITLE= "unscheduledcardtitle",
				 NEEDS_REVISION_CARD_TITLE = "needsrevisioncardtitle",
				 MESSAGES_CARD_TITLE = "messagescardtitle",
				 APPOINTMENT_REQUEST_TAB = "appointmentrequesttab",
			     PATIENT_INFO_TAB = "patientinfotab",
			     SURGERY_INFO_TAB = "surgeryinfotab",
			     PAT_INFO_TAB = "patinfotab";
	private static String numberOfWarningRequest;
	private static String numberOfUnscheduledRequest;
	private static String numberOfNeedsRevisionRequest;
	private static boolean isWarningZero;
	private static boolean isWarningMax;
	private static boolean isUnscheduledZero;
	private static boolean isUnscheduledMax;
	private static boolean isNeedsRevisionZero;
	private static boolean isNeedsRevisionMax;
	private StepDefHelperYml step = new StepDefHelperYml();

	@Given ("{string} is on case tracker landing page")
	public void user_is_on_case_tracker_landing_page(String user)
	{
		Login login = new Login();
		login.LoginPage(Role.OFFICE_SCHEDULER);
		step.pageReady();
		//assert user is on the surgical scheduling landing page
        String currentLandingURL = step.driver().getCurrentUrl();
        System.out.println("Current URL is " + currentLandingURL);
        try {
            Assert.assertEquals(currentLandingURL, "https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc");
        } catch (AssertionError e) {
            System.out.println("User is not on the case tracker landing page after logging in.");
        }
        System.out.println("User is on the case tracker landing page after logging in.");
	}
	
	@And("the followling cards are available in the task area")
	public void available_cards_in_task_area(DataTable dt)
	{
	    step.from("TaskAreaHappyPath");
	    step.pageReady();
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for(int i = 0; i < list.size(); i++)
		{
			Assert.assertEquals(list.get(i).get(0), step.setElement(WARNINGS_CARD_TITLE).element().getText());
			Assert.assertEquals(list.get(i).get(1), step.setElement(UNSCHEDULED_CARD_TITLE).element().getText());
			Assert.assertEquals(list.get(i).get(2), step.setElement(NEEDS_REVISION_CARD_TITLE).element().getText());
			Assert.assertEquals(list.get(i).get(3), step.setElement(MESSAGES_CARD_TITLE).element().getText());
		}
		step.pageReady();
		step.from("TaskAreaNegative");
		String warning_number = step.setElement("Warnings_Number").waitUntilPresence().element().getText();
		String unscheduled_number = step.setElement("Unscheduled_Number").waitUntilPresence().element().getText();
		String needs_revision_number = step.setElement("Needs Revision_Number").waitUntilPresence().element().getText();

		isWarningZero = warning_number.equals("0");
		isUnscheduledZero = unscheduled_number.equals("0");
		isNeedsRevisionZero = needs_revision_number.equals("0");
	}
	@Given("There is {int} warning")
	public void there_is_warning(int zero) {
		Assume.assumeTrue("Conditional Test only if Warning has 0 case", isWarningZero);
		int warning_number = Integer.parseInt(step.setElement("Warnings_Number").element().getText());
		Assert.assertEquals(zero, warning_number);
	}

	@Then("Review All Under Warning should be of grey color")
	public void review_All_Under_Warning_should_be_of_grey_color(io.cucumber.datatable.DataTable dataTable) {
		Assume.assumeTrue("Conditional Test only if Warning has 0 case", isWarningZero);
		List<String> list = dataTable.asList();
		String font_css = list.get(0);
		Assert.assertTrue("Review All under Warning card color should be grey color",
				step.setElement("Warnings_ReviewAll").waitUntilPresence().element().getCssValue("color").matches(font_css));
	}

	@Given("There is greater than {int} warnings")
	public void there_is_greater_than_warnings(Integer int1) {
		Assume.assumeFalse("Conditional Test only if Warning has greater than 0 case", isWarningZero);
		String warning_number = step.setElement("Warnings_Number").element().getText();
		int int_warning_number = Integer.parseInt(warning_number.replaceAll("\\D+",""));
		Assert.assertTrue(int_warning_number > 0);
	}

	@Then("Review All Under Warning should be of blue color")
	public void review_All_Under_Warning_should_be_of_blue_color(io.cucumber.datatable.DataTable dataTable) {
		Assume.assumeFalse("Conditional Test only if Warning has greater than 0 case", isWarningZero);
		List<String> list = dataTable.asList();
		String font_css = list.get(0);
		Assert.assertTrue("Review All under Warning card color should be blue color",
				step.setElement("Warnings_ReviewAll").waitUntilPresence().element().getCssValue("color").matches(font_css));
	}

	@Given("There is {int} unscheduled")
	public void there_is_unscheduled(int zero) {
		Assume.assumeTrue("Conditional Test only if Unscheduled has 0 case", isUnscheduledZero);
		int unscheduled_number = Integer.parseInt(step.setElement("Unscheduled_Number").element().getText());
		Assert.assertEquals(zero, unscheduled_number);
	}

	@Then("Review All Under Unscheduled should be of grey color")
	public void review_All_Under_Unscheduled_should_be_of_grey_color(io.cucumber.datatable.DataTable dataTable) {
		Assume.assumeTrue("Conditional Test only if Unscheduled has 0 case", isUnscheduledZero);
		List<String> list = dataTable.asList();
		String font_css = list.get(0);
		Assert.assertTrue("Review All under Unscheduled card color should be grey color",
				step.setElement("Unscheduled_ReviewAll").waitUntilPresence().element().getCssValue("color").matches(font_css));
	}

	@Given("There is greater than {int} unscheduled")
	public void there_is_greater_than_unscheduled(int zero) {
		Assume.assumeFalse("Conditional Test only if Unscheduled has greater than 0 case", isWarningZero);
		String unscheduled_number = step.setElement("Unscheduled_Number").element().getText();
		int int_unscheduled_number = Integer.parseInt(unscheduled_number.replaceAll("\\D+",""));
		Assert.assertTrue(int_unscheduled_number > 0);

	}

	@Then("Review All Under Unscheduled should be of blue color")
	public void review_All_Under_Unscheduled_should_be_of_blue_color(io.cucumber.datatable.DataTable dataTable) {
		Assume.assumeFalse("Conditional Test only if Unscheduled has greater than 0 case", isUnscheduledZero);
		List<String> list = dataTable.asList();
		String font_css = list.get(0);
		Assert.assertTrue("Review All under Unscheduled card color should be blue color",
				step.setElement("Unscheduled_ReviewAll").waitUntilPresence().element().getCssValue("color").matches(font_css));
	}

	@Given("There is {int} Needs Revision")
	public void there_is_Needs_Revision(int zero) {
		Assume.assumeTrue("Conditional Test only if Needs Revision has 0 case", isNeedsRevisionZero);
		int needs_revision_number = Integer.parseInt(step.setElement("Needs Revision_Number").element().getText());
		Assert.assertEquals(zero, needs_revision_number);
	}

	@Then("Review All Under Needs Revision should be of grey color")
	public void review_All_Under_Needs_Revision_should_be_of_grey_color(io.cucumber.datatable.DataTable dataTable) {
		Assume.assumeTrue("Conditional Test only if Needs Revision has 0 case", isNeedsRevisionZero);
		List<String> list = dataTable.asList();
		String font_css = list.get(0);
		Assert.assertTrue("Review All under Needs Revision card color should be grey color",
				step.setElement("Needs Revision_ReviewAll").waitUntilPresence().element().getCssValue("color").matches(font_css));
	}

	@Given("There is greater than {int} Needs Revision")
	public void there_is_greater_than_Needs_Revision(int zero) {
		Assume.assumeFalse("Conditional Test only if Needs Revision has greater than 0 case", isNeedsRevisionZero);
		String needs_revision_zero = step.setElement("Needs Revision_Number").element().getText();
		int int_needs_revision_zero = Integer.parseInt(needs_revision_zero.replaceAll("\\D+",""));
		Assert.assertTrue(int_needs_revision_zero > 0);

	}

	@Then("Review All Under Needs Revision should be of blue color")
	public void review_All_Under_Needs_Revision_should_be_of_blue_color(io.cucumber.datatable.DataTable dataTable) {
		Assume.assumeFalse("Conditional Test only if Needs Revision has greater than 0 case", isNeedsRevisionZero);
		List<String> list = dataTable.asList();
		String font_css = list.get(0);
		Assert.assertTrue("Review All under Needs Revision card color should be blue color",
				step.setElement("Needs Revision_ReviewAll").waitUntilPresence().element().getCssValue("color").matches(font_css));
	}

	@Given("{string} can see the total number of cases on the warnings card")
	public void request_number_on_warning_card(String user)
	{
		step.from("TaskAreaHappyPath");
		numberOfWarningRequest = step.setElement("numberofwarningrequests").pageReady().element().getText();
		System.out.println("The total number of requests as seen on the warning card on the Task Area is " + numberOfWarningRequest);
	}
	
	@When("{string} clicks on the warnings card")
	public void user_clicks_on_the_warnings_card(String user)
	{
		step.wait(2000);
		step.setElement("numberofwarningrequests").click();
	}
	
	@Then("{string} sees a red warning label with the following tabs")
	public void red_warning_label(String user, DataTable dt)
	{
		step.wait(2000);
		step.wait(1000);
		Assume.assumeFalse("SKIPPED : There is 0 Warning case card.", isWarningZero);
		String redColorLabel = step.setElement("warninglabel").element().getAttribute("color");
		String color = redColorLabel.replace("#", "");
		assertEquals("BF0000", color);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for(int i = 0; i < list.size(); i++)
		{
			Assert.assertEquals(list.get(i).get(0), step.setElement(APPOINTMENT_REQUEST_TAB).element().getText());
			Assert.assertEquals(list.get(i).get(1), step.setElement(PATIENT_INFO_TAB).element().getText());
			Assert.assertEquals(list.get(i).get(2), step.setElement(SURGERY_INFO_TAB).element().getText());
			Assert.assertEquals(list.get(i).get(3), step.setElement(PAT_INFO_TAB).element().getText());
		}
	}

	@Then("clicks {string} to compare the total number of filtered warning cases to the number on the warning card")
	public void clicks_to_compare_the_total_number_of_filtered_warning_cases_to_the_number_on_the_warning_card(String endQueueButton) {
		Assume.assumeFalse("SKIP : There is 0 Warning case card.", isWarningZero);
		Assert.assertEquals(endQueueButton, step.setElement("endqueuebutton").element().getText());
		step.wait(DELAY_2000);
	    step.setElement("endqueuebutton").waitUntilPresence().click();
		step.pageReady();
		String numbersInFilteredCases = step.setElement("numberinfilteredcases").waitUntilPresence().element().getText();
		Pattern pattern = Pattern.compile("\\d+");
		Matcher m = pattern.matcher(numbersInFilteredCases);
		List<String> numbers = new ArrayList<>();
		while(m.find()) {
			numbers.add(m.group(0));
		}
	    String firstNumber = numbers.get(0);
		String secondNumber = numbers.get(1);
		Assert.assertEquals("Clicking End queue should go to the end of the cases",
				firstNumber,
				secondNumber);

		step.setElement("HOME").waitUntilPresence().click();
		step.pageReady();
	}

	@Given("{string} can see the total number of cases on the unscheduled card")
	public void request_number_on_unscheduled_card(String user)
	{
		step.wait(2000);
		step.wait(1000);
		numberOfUnscheduledRequest = step.setElement("numberofunscheduledrequests").pageReady().element().getText();
		System.out.println("The total number of requests as seen on the unscheduled card on the Task Area is " + numberOfUnscheduledRequest);
	}
	
	@When("{string} clicks on the unscheduled card")
	public void user_clicks_on_the_unscheduled_card(String user)
	{
		step.wait(2000);
		step.setElement("numberofunscheduledrequests").click();
		step.pageReady();
	}
	
	@Then("{string} sees an orange unscheduled label with the following tabs")
	public void orange_unscheduled_label(String user, DataTable dt)
	{
		step.wait(2000);
		step.wait(1000);
		Assume.assumeFalse("SKIP : There is 0 Unscheduled case card.", isUnscheduledZero);
		String redColorLabel = step.setElement("unscheduledlabel").element().getAttribute("color");
		String color = redColorLabel.replace("#","");
		Assert.assertEquals("FEC78D", color);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for(int i = 0; i < list.size(); i++)
		{
			Assert.assertEquals(list.get(i).get(0), step.setElement(APPOINTMENT_REQUEST_TAB).element().getText());
			Assert.assertEquals(list.get(i).get(1), step.setElement(PATIENT_INFO_TAB).element().getText());
			Assert.assertEquals(list.get(i).get(2), step.setElement(SURGERY_INFO_TAB).element().getText());
			Assert.assertEquals(list.get(i).get(3), step.setElement(PAT_INFO_TAB).element().getText());
		}
	}
	
	@And("clicks {string} to compare the total number of filtered unscheduled cases to the number on the unscheduled card")
	public void clicks_end_queue_in_unscheduled(String endQueueButton)
	{
		Assume.assumeFalse("SKIP : There is 0 Unscheduled case card.", isUnscheduledZero);
		Assert.assertEquals(endQueueButton, step.setElement("endqueuebutton").element().getText());
		step.wait(DELAY_2000);
		step.setElement("endqueuebutton").waitUntilPresence().click();
		step.pageReady();
		String numbersInFilteredCases = step.setElement("numberinfilteredcases").waitUntilPresence().element().getText();
		Pattern pattern = Pattern.compile("\\d+");
		Matcher m = pattern.matcher(numbersInFilteredCases);
		List<String> numbers = new ArrayList<>();
		while(m.find()) {
			numbers.add(m.group(0));
		}
		String firstNumber = numbers.get(0);
		String secondNumber = numbers.get(1);
		Assert.assertEquals("Clicking End queue should go to the end of the cases",
				firstNumber,
				secondNumber);

		step.setElement("HOME").waitUntilPresence().click();
		step.pageReady();
	}
	
	@Given("{string} can see the total number of cases on the needs revision card")
	public void request_number_on_needs_revision_card(String user)
	{
		step.wait(2000);
		step.wait(1000);
		numberOfNeedsRevisionRequest = step.setElement("numberofneedsrevisionrequests").pageReady().element().getText();
		System.out.println("The total number of requests as seen on the needs revision card on the Task Area is " + numberOfNeedsRevisionRequest);
	}
	
	@When("{string} clicks on the needs revision card")
	public void user_clicks_on_the_needs_revision_card(String user)
	{
		step.wait(2000);
		step.wait(1000);
		step.setElement("numberofneedsrevisionrequests").click();
	}
	
	@Then("{string} sees a purple needs revision label with the following tabs")
	public void purple_needs_revision_label(String user, DataTable dt)
	{
		step.pageReady();
		step.wait(1000);
		Assume.assumeFalse("SKIP : There is 0 Needs Revision case card.", isNeedsRevisionZero);
		String redColorLabel = step.setElement("unscheduledlabel").element().getAttribute("color");
		String color = redColorLabel.replace("#", "");
		Assert.assertEquals("FEC78D", color);
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for(int i = 0; i < list.size(); i++)
		{
			Assert.assertEquals(list.get(i).get(0), step.setElement(APPOINTMENT_REQUEST_TAB).element().getText());
			Assert.assertEquals(list.get(i).get(1), step.setElement(PATIENT_INFO_TAB).element().getText());
			Assert.assertEquals(list.get(i).get(2), step.setElement(SURGERY_INFO_TAB).element().getText());
			Assert.assertEquals(list.get(i).get(3), step.setElement(PAT_INFO_TAB).element().getText());
		}
	}
	
	@And("clicks {string} to compare the total number of filtered needs revision cases to the number on the needs revision card")
	public void clicks_end_queue_in_needs_revision(String endQueueButton)
	{
		Assume.assumeFalse("SKIP : There is 0 Needs Revision case card.", isNeedsRevisionZero);
		Assert.assertEquals(endQueueButton, step.setElement("endqueuebutton").element().getText());
		step.wait(DELAY_2000);
		step.setElement("endqueuebutton").waitUntilPresence().click();
		while(step.findElements("SPINNER").size() > 0);
		step.pageReady();
		String numbersInFilteredCases = step.setElement("numberinfilteredcases").waitUntilPresence().element().getText();
		Pattern pattern = Pattern.compile("\\d+");
		Matcher m = pattern.matcher(numbersInFilteredCases);
		List<String> numbers = new ArrayList<>();
		while(m.find()) {
			numbers.add(m.group(0));
		}
		String firstNumber = numbers.get(0);
		String secondNumber = numbers.get(1);
		Assert.assertEquals("Clicking End queue should go to the end of the cases",
				firstNumber,
				secondNumber);

		step.setElement("HOME").waitUntilPresence().click();
		step.pageReady();
	}
	
	@Given("{string} can see the {string} subtitle on the messages card")
	public void messages_card_subtitle_validation(String user, String comingSoonSubtilte)
	{
		step.wait(2000);
		step.wait(1000);
		Assert.assertEquals(comingSoonSubtilte, step.setElement("comingsoonsubtitle").element().getText());
	}
	
	@When("{string} clicks on the messages card")
	public void user_clicks_on_messages_card(String user)
	{
		step.setElement("comingsoonsubtitle").element().click();
	}

	@Then("{string} sees a popup message {string}")
	public void messages_card_pop_up_message(String user, String popupMessage)
	{
		Assert.assertEquals(popupMessage, step.setElement("comingsoonmessage").element().getText());
	}
}
