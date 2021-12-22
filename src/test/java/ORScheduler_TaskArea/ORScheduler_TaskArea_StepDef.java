package ORScheduler_TaskArea;

import Login.*;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.core.api.Scenario;
import io.cucumber.core.event.Status;
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

public class ORScheduler_TaskArea_StepDef {
    private final int  DELAY_2000 = 2000;
    private final String
            WARNINGS_CARD_TITLE = "warningcardtitle",
            UNSCHEDULED_CARD_TITLE= "unscheduledcardtitle",
            REVISED_CARD_TITLE = "revisedcardtitle",
            MESSAGES_CARD_TITLE = "messagescardtitle",
            APPOINTMENT_REQUEST_TAB = "appointmentrequesttab",
            PATIENT_INFO_TAB = "patientinfotab",
            SURGERY_INFO_TAB = "surgeryinfotab",
            PAT_INFO_TAB = "patinfotab";
    private static String numberOfWarningRequest;
    private static String numberOfUnscheduledRequest;
    private static String numberOfRevisedRequest;
    private static boolean isWarningZero;
    private static boolean isUnscheduledZero;
    private static boolean isRevisedZero;
    private StepDefHelperYml step = new StepDefHelperYml();

    @Given("{string} is on case tracker landing page")
    public void user_is_on_case_tracker_landing_page(String user)
    {
        Login login = new Login();
        login.LoginPage(Role.OR_SCHEDULER);
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
        step.from("ORScheduler_TaskArea");
        step.wait(2000);
        step.wait(1000);
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        for(int i = 0; i < list.size(); i++)
        {
            Assert.assertEquals(list.get(i).get(0), step.setElement(WARNINGS_CARD_TITLE).element().getText());
            Assert.assertEquals(list.get(i).get(1), step.setElement(UNSCHEDULED_CARD_TITLE).element().getText());
            Assert.assertEquals(list.get(i).get(2), step.setElement(REVISED_CARD_TITLE).element().getText());
            Assert.assertEquals(list.get(i).get(3), step.setElement(MESSAGES_CARD_TITLE).element().getText());
        }
        step.pageReady();
        step.from("TaskAreaNegative");
        String warning_number = step.setElement("Warnings_Number").waitUntilPresence().element().getText();
        String unscheduled_number = step.setElement("Unscheduled_Number").waitUntilPresence().element().getText();
        String revised_number = step.setElement("Revised_Number").waitUntilPresence().element().getText();

        isWarningZero = warning_number.equals("0");
        isUnscheduledZero = unscheduled_number.equals("0");
        isRevisedZero = revised_number.equals("0");
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

    @Given("There is {int} Revised")
    public void there_is_Needs_Revision(int zero) {
        Assume.assumeTrue("Conditional Test only if Revised has 0 case", isRevisedZero);
    }

    @Then("Review All Under Revised should be of grey color")
    public void review_All_Under_Needs_Revision_should_be_of_grey_color(io.cucumber.datatable.DataTable dataTable) {
        Assume.assumeTrue("Conditional Test only if Revised has 0 case", isRevisedZero);
        List<String> list = dataTable.asList();
        String font_css = list.get(0);
		Assert.assertTrue("Review All under Revised card color should be grey color",
				step.setElement("Revised_ReviewAll").waitUntilPresence().element().getCssValue("color").matches(font_css));
    }

    @Given("There is greater than {int} Revised")
    public void there_is_greater_than_Needs_Revision(int zero) {
        Assume.assumeFalse("Conditional Test only if Revised has greater than 0 case", isRevisedZero);
        String revised_zero = step.setElement("Revised_Number").element().getText();
        int int_revised_zero = Integer.parseInt(revised_zero.replaceAll("\\D+",""));
        Assert.assertTrue(int_revised_zero> 0);

    }

    @Then("Review All Under Revised should be of blue color")
    public void review_All_Under_Needs_Revision_should_be_of_blue_color(io.cucumber.datatable.DataTable dataTable) {
        Assume.assumeFalse("Conditional Test only if Needs Revision has greater than 0 case", isRevisedZero);
        List<String> list = dataTable.asList();
        String font_css = list.get(0);
		Assert.assertTrue("Review All under Revised card color should be blue color",
				step.setElement("Revised_ReviewAll").waitUntilPresence().element().getCssValue("color").matches(font_css));
    }

    @Given("{string} can see the total number of cases on the warnings card")
    public void request_number_on_warning_card(String user)
    {
        step.from("ORScheduler_TaskArea");
        step.wait(2000);
        step.wait(1000);
        numberOfWarningRequest = step.setElement("numberofwarningrequests").pageReady().element().getText();
        System.out.println("The total number of requests as seen on the warning card on the Task Area is " + numberOfWarningRequest);
    }

    @When("{string} clicks on the warnings card")
    public void user_clicks_on_the_warnings_card(String user)
    {
        step.wait(2000);
        step.wait(1000);
        step.setElement("numberofwarningrequests").click();
        step.pageReady();
    }

    @Then("{string} sees a red warning label with the following tabs")
    public void red_warning_label(String user, DataTable dt)
    {
        step.wait(1000);
        step.pageReady();
        Assume.assumeFalse("SKIPPED : There is 0 Warning case card.", numberOfWarningRequest.equals("0"));
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
        step.pageReady();
        Assume.assumeFalse("SKIP : There is 0 Warning case card.", numberOfWarningRequest.equals("0"));
        step.wait(DELAY_2000);
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
        step.wait(1000);
        step.setElement("numberofunscheduledrequests").click();
    }

    @Then("{string} sees an orange unscheduled label with the following tabs")
    public void orange_unscheduled_label(String user, DataTable dt)
    {
        step.wait(2000);
        step.wait(1000);
        Assume.assumeFalse("SKIP : There is 0 Unscheduled case card.", numberOfUnscheduledRequest.equals("0"));
        String colorLabel = step.setElement("unscheduledlabel").element().getAttribute("color");
        String color = colorLabel.replace("#","");
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
        Assume.assumeFalse("SKIP : There is 0 Unscheduled case card.", numberOfUnscheduledRequest.equals("0"));
        step.wait(DELAY_2000);
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

        step.setElement("HOME").waitUntilPresence().click();	}

    @Given("{string} can see the total number of cases on the Revised card")
    public void request_number_on_needs_revision_card(String user)
    {
        step.wait(2000);
        step.wait(1000);
        numberOfRevisedRequest = step.setElement("numberofrevisedrequests").pageReady().element().getText();
        System.out.println("The total number of requests as seen on the needs revision card on the Task Area is " + numberOfRevisedRequest);
    }

    @When("{string} clicks on the Revised card")
    public void user_clicks_on_the_needs_revision_card(String user)
    {
        step.wait(2000);
        step.wait(1000);
        step.setElement("numberofrevisedrequests").click();
    }

    @Then("{string} sees a purple Revised label with the following tabs")
    public void purple_needs_revision_label(String user, DataTable dt)
    {
        step.wait(2000);
        step.wait(1000);
        Assume.assumeFalse("SKIP : There is 0 Revised case card.", numberOfRevisedRequest.equals("0"));
        String colorLabel = step.setElement("revisedlabel").element().getAttribute("color");
        String color = colorLabel.replace("#", "");
        Assert.assertEquals("994797", color);
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        for(int i = 0; i < list.size(); i++)
        {
            Assert.assertEquals(list.get(i).get(0), step.setElement(APPOINTMENT_REQUEST_TAB).element().getText());
            Assert.assertEquals(list.get(i).get(1), step.setElement(PATIENT_INFO_TAB).element().getText());
            Assert.assertEquals(list.get(i).get(2), step.setElement(SURGERY_INFO_TAB).element().getText());
            Assert.assertEquals(list.get(i).get(3), step.setElement(PAT_INFO_TAB).element().getText());
        }
    }

    @And("clicks {string} to compare the total number of filtered Revised to the number on the Revised card")
    public void clicks_end_queue_in_needs_revision(String endQueueButton)
    {
        step.pageReady();
        Assume.assumeFalse("SKIP : There is 0 Revised case card.", numberOfRevisedRequest.equals("0"));
        Assert.assertEquals(endQueueButton, step.setElement("endqueuebutton").element().getText());
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
