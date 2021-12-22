package viewer_case_preference_indicator;

import Login.*;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class OfficeViewer_CasePreferenceIndicator_StepDef {

	private final int DELAY_1000 = 1000,
					  DELAY_2000 = 2000;
	private StepDefHelperYml step = new StepDefHelperYml();

	@Given("{string} is on {string}")
	public void is_on(String string, String surgicalSchedulingLandingPage) {
	    Login login = new Login();
	    login.LoginPage(Role.OFFICE_VIEWER);
	    step.from("OfficeViewerCasePreferenceIndicator");
	}

	@When("{string} clicks EDIT VIEW")
	public void clicks_EDIT_VIEW(String string) {
	    step.pageReady();
		Assert.assertEquals("EDIT VIEW", 
				step.setElement("editview").waitUntilVisible().element().getText());
		step.wait(2000);
		((JavascriptExecutor)step.driver()).executeScript("arguments[0].click();", step.setElement("editview").element());
	}

	@Then("{string} is taken to {string}")
	public void is_taken_to(String string, String mySurgeonUrl) {
		step.wait(2000) ;
		Assert.assertTrue(step.driver().getCurrentUrl().equals(mySurgeonUrl));
	}

	@Then("The left pane has following forms")
	public void the_left_pane_has_following_forms(io.cucumber.datatable.DataTable dataTable) {
		step.pageReady();
		List<String> list = dataTable.asList();
		List<String> actualList = new ArrayList<>();
		actualList.add("accountsettings");
		actualList.add("mysurgeons");
		for(int i = 0; i < list.size(); i++) {
			Assert.assertEquals(list.get(i), 
					step.setElement(actualList.get(i)).waitUntilVisible().element().getText());
		}
	}

	@Then("The right pane has following surgeons")
	public void the_right_pane_has_following_surgeons(io.cucumber.datatable.DataTable dataTable) {
		step.wait(DELAY_1000);
		List<String> surgeons = dataTable.asList();
		List<String> actualSurgeons = new ArrayList<>();
		actualSurgeons.add("Leah Cordovez");
		actualSurgeons.add("Thomas Dovan");
		actualSurgeons.add("Burton Elrod");
		actualSurgeons.add("Rudolph Glattes");
		actualSurgeons.add("Amanda Martin");
		actualSurgeons.add("David Moore");
		actualSurgeons.add("Jeffrey Willers");
		step.wait(DELAY_1000);
		for(int i = 0; i < surgeons.size(); i++) {
			Assert.assertEquals(surgeons.get(i), 
					step.setElement(actualSurgeons.get(i)).waitUntilPresence().element().getText());
		}
	}

	@Then("the right pane has following buttons")
	public void the_right_pane_has_following_buttons(io.cucumber.datatable.DataTable dataTable) {
		List<String> buttons = dataTable.asList();
		List<String> actualButtons = new ArrayList<>();
		actualButtons.add("Cancel");
		actualButtons.add("Save");
		for(int i = 0; i < buttons.size(); i++) {
			Assert.assertEquals(buttons.get(i), 
					step.setElement(actualButtons.get(i)).waitUntilPresence().element().getText());
		}
	}

	@Then("the Save button is disabled.")
	public void the_Save_button_is_disabled() {
		Assert.assertFalse(step.setElement("Cancel").element().isEnabled());
		Assert.assertFalse(step.setElement("Save").element().isEnabled());
	}

	@Then("{string} clicks HOME button")
	public void clicks_HOME_button(String string) {
		step.wait(DELAY_1000);
		step.setElement("HOME").waitUntilPresence().click();
		step.pageReady();
	}

	@Given("all surgeons should be unchecked")
	public void all_surgeons_should_be_unchecked() {
		step.wait(2000);
		List<WebElement> surgeonCheckboxes = step.findElements("Generic_Checkbox");
		for(WebElement checkbox : surgeonCheckboxes) {
			if(checkbox.isSelected()) {
				checkbox.click();
			}
		}

	}

	@When("{string} clicks {string} checkbox")
	public void clicks_checkbox_one_surgeon(String string, String surgeon) {
		surgeon = surgeon +"_checkbox";
		step.setElement(surgeon).waitUntilPresence().click();
	}

	@When("{string} clicks Save")
	public void clicks_Save(String string) {
		step.setElement("Save").waitUntilPresence().click();
		step.pageReady();
	}

	@Then("Case preference indicator should have {string}")
	public void case_preference_indicator_should_have(String surgeonNames) {
		Assert.assertEquals(surgeonNames, 
			step.setElement("surgeonNames").waitUntilPresence().element().getText());
	}

	@When("{string} clicks Home button")
	public void clicks_Home_button(String string) {
		step.wait(DELAY_2000);
		((JavascriptExecutor)step.driver()).executeScript("arguments[0].click();", step.setElement("HOME").element());
		step.pageReady();
	}
	
	@When("{string} clicks {string} {string} checkbox")
	public void clicks_checkbox_two_surgeons(String string, String surgeon1, String surgeon2) {
		step.wait(2000);
		surgeon1 = surgeon1 + "_checkbox";
		surgeon2 = surgeon2 + "_checkbox";
		step.setElement(surgeon1).waitUntilPresence().click();
		step.setElement(surgeon2).waitUntilPresence().click();
	}

	@When("{string} clicks {string} {string} {string} checkbox")
	public void clicks_checkbox_three_surgeons(String string, String surgeon1, String surgeon2, String surgeon3) {
		step.wait(DELAY_2000);
		surgeon1 = surgeon1 + "_checkbox";
		surgeon2 = surgeon2 + "_checkbox";
		surgeon3 = surgeon3 + "_checkbox";
		step.setElement(surgeon1).waitUntilPresence().click();
		step.setElement(surgeon2).waitUntilPresence().click();
		step.setElement(surgeon3).waitUntilPresence().click();
	}

	@When("{string} clicks {string} {string} {string} {string} checkbox")
	public void clicks_checkbox_four_surgeons(String string, String surgeon1, String surgeon2, String surgeon3, String surgeon4) {
		step.wait(DELAY_2000);
		surgeon1 = surgeon1 + "_checkbox";
		surgeon2 = surgeon2 + "_checkbox";
		surgeon3 = surgeon3 + "_checkbox";
		surgeon4 = surgeon4 + "_checkbox";
		step.setElement(surgeon1).waitUntilPresence().click();
		step.setElement(surgeon2).waitUntilPresence().click();
		step.setElement(surgeon3).waitUntilPresence().click();
		step.setElement(surgeon4).waitUntilPresence().click();
	}

	@When("{string} clicks {string} {string} {string} {string} {string} checkbox")
	public void clicks_checkbox_five_surgeons(String string, String surgeon1, String surgeon2, String surgeon3, String surgeon4, String surgeon5) {
		step.wait(DELAY_2000);
		surgeon1 = surgeon1 + "_checkbox";
		surgeon2 = surgeon2 + "_checkbox";
		surgeon3 = surgeon3 + "_checkbox";
		surgeon4 = surgeon4 + "_checkbox";
		surgeon5 = surgeon5 + "_checkbox";
		step.setElement(surgeon1).waitUntilPresence().click();
		step.setElement(surgeon2).waitUntilPresence().click();
		step.setElement(surgeon3).waitUntilPresence().click();
		step.setElement(surgeon4).waitUntilPresence().click();
		step.setElement(surgeon5).waitUntilPresence().click();
	}

	@When("{string} clicks {string} {string} {string} {string} {string} {string} checkbox")
	public void clicks_checkbox_six(String string, String surgeon1, String surgeon2, String surgeon3, String surgeon4, String surgeon5, String surgeon6) {
		step.wait(DELAY_2000);
		surgeon1 = surgeon1 + "_checkbox";
		surgeon2 = surgeon2 + "_checkbox";
		surgeon3 = surgeon3 + "_checkbox";
		surgeon4 = surgeon4 + "_checkbox";
		surgeon5 = surgeon5 + "_checkbox";
		surgeon6 = surgeon6 + "_checkbox";
		step.setElement(surgeon1).waitUntilPresence().click();
		step.setElement(surgeon2).waitUntilPresence().click();
		step.setElement(surgeon3).waitUntilPresence().click();
		step.setElement(surgeon4).waitUntilPresence().click();
		step.setElement(surgeon5).waitUntilPresence().click();
		step.setElement(surgeon6).waitUntilPresence().click();
	}

	@When("{string} clicks {string} {string} {string} {string} {string} {string} {string} checkbox")
	public void clicks_checkbox_seven(String string, String surgeon1, String surgeon2, String surgeon3, String surgeon4, String surgeon5, String surgeon6, String surgeon7) {
		step.wait(DELAY_2000);
		surgeon1 = surgeon1 + "_checkbox";
		surgeon2 = surgeon2 + "_checkbox";
		surgeon3 = surgeon3 + "_checkbox";
		surgeon4 = surgeon4 + "_checkbox";
		surgeon5 = surgeon5 + "_checkbox";
		surgeon6 = surgeon6 + "_checkbox";
		surgeon7 = surgeon7 + "_checkbox";
		step.setElement(surgeon1).waitUntilPresence().click();
		step.setElement(surgeon2).waitUntilPresence().click();
		step.setElement(surgeon3).waitUntilPresence().click();
		step.setElement(surgeon4).waitUntilPresence().click();
		step.setElement(surgeon5).waitUntilPresence().click();
		step.setElement(surgeon6).waitUntilPresence().click();
		step.setElement(surgeon7).waitUntilPresence().click();
		
	}
}