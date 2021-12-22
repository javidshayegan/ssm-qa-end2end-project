package ORScheduler_CasePreferenceIndicator;

import Login.Login;
import Login.Role;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class ORScheduler_CasePreferenceIndicator_StepDef {

	private final int DELAY_1000 = 1000,
					  DELAY_2000 = 2000;
	private StepDefHelperYml step = new StepDefHelperYml();

	@Given("{string} is on {string}")
	public void is_on(String string, String surgicalSchedulingLandingPage) {
	    Login login = new Login();
	    login.LoginPage(Role.OR_SCHEDULER);
	    step.from("CasePreferenceIndicator");
	}

	@When("{string} clicks EDIT VIEW")
	public void clicks_EDIT_VIEW(String string) {
		Assert.assertEquals("EDIT VIEW", 
				step.setElement("editview").waitUntilVisible().element().getText());
		step.wait(2000);
		((JavascriptExecutor)step.driver()).executeScript("arguments[0].click();", step.setElement("editview").element());
	}

	@Then("{string} is taken to {string}")
	public void is_taken_to(String string, String myUnitUrl) {
		step.wait(2000) ;
		Assert.assertTrue(step.driver().getCurrentUrl().equals(myUnitUrl));
	}

	@Then("The left pane has following forms")
	public void the_left_pane_has_following_forms(io.cucumber.datatable.DataTable dataTable) {
		step.wait(DELAY_1000);
		List<String> list = dataTable.asList();
		List<String> actualList = new ArrayList<>();
		actualList.add("accountsettings");
		actualList.add("myunits");
		for(int i = 0; i < list.size(); i++) {
			Assert.assertEquals(list.get(i), 
					step.setElement(actualList.get(i)).waitUntilVisible().element().getText());
		}
	}

	@Then("The right pane has following units")
	public void the_right_pane_has_following_units(io.cucumber.datatable.DataTable dataTable) {
		step.wait(DELAY_1000);
		List<String> units = dataTable.asList();
		List<String> actualUnits = new ArrayList<>();
		actualUnits.add("BH CSC");
		actualUnits.add("BH JRI");
		actualUnits.add("BH OR");
		actualUnits.add("STM CATH");
		actualUnits.add("STM ENDO");
		step.wait(DELAY_1000);
		for(int i = 0; i < units.size(); i++) {
			Assert.assertEquals(units.get(i),
					step.setElement(actualUnits.get(i)).waitUntilPresence().element().getText());
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
		step.pageReady();
		((JavascriptExecutor)step.driver()).executeScript("arguments[0].click();", step.setElement("HOME").element());
		step.pageReady();
	}

	@Given("all units should be unchecked")
	public void all_units_should_be_unchecked() {
		step.wait(2000);
		List<WebElement> unitCheckboxes = step.findElements("Generic_Checkbox");
		for(WebElement checkbox : unitCheckboxes) {
			if(checkbox.isSelected()) {
				checkbox.click();
			}
		}

	}

	@When("{string} clicks {string} checkbox")
	public void clicks_checkbox_one_unit(String string, String unit) {
		unit = unit +"_checkbox";
		step.setElement(unit).waitUntilPresence().click();
	}

	@When("{string} clicks Save")
	public void clicks_Save(String string) {
		step.setElement("Save").waitUntilPresence().click();
		step.wait(2000);
	}

	@Then("Case preference indicator should have {string}")
	public void case_preference_indicator_should_have(String unitNames) {
		Assert.assertEquals(unitNames,
			step.setElement("unitNames").waitUntilPresence().element().getText());
	}

	@When("{string} clicks Home button")
	public void clicks_Home_button(String string) {
		step.wait(DELAY_2000);
		((JavascriptExecutor)step.driver()).executeScript("arguments[0].click();", step.setElement("HOME").element());
		step.wait(2000);
	}
	
	@When("{string} clicks {string} {string} checkbox")
	public void clicks_checkbox_two_units(String string, String unit1, String unit2) {
		step.wait(2000);
		unit1 = unit1 + "_checkbox";
		unit2 = unit2 + "_checkbox";
		step.setElement(unit1).waitUntilPresence().click();
		step.setElement(unit2).waitUntilPresence().click();
	}

	@When("{string} clicks {string} {string} {string} checkbox")
	public void clicks_checkbox_three_units(String string, String unit1, String unit2, String unit3) {
		step.wait(DELAY_2000);
		unit1 = unit1 + "_checkbox";
		unit2 = unit2 + "_checkbox";
		unit3 = unit3 + "_checkbox";
		step.setElement(unit1).waitUntilPresence().click();
		step.setElement(unit2).waitUntilPresence().click();
		step.setElement(unit3).waitUntilPresence().click();
	}

	@When("{string} clicks {string} {string} {string} {string} checkbox")
	public void clicks_checkbox_four_units(String string, String unit1, String unit2, String unit3, String unit4) {
		step.wait(DELAY_2000);
		unit1 = unit1 + "_checkbox";
		unit2 = unit2 + "_checkbox";
		unit3 = unit3 + "_checkbox";
		unit4 = unit4 + "_checkbox";
		step.setElement(unit1).waitUntilPresence().click();
		step.setElement(unit2).waitUntilPresence().click();
		step.setElement(unit3).waitUntilPresence().click();
		step.setElement(unit4).waitUntilPresence().click();
	}

	@When("{string} clicks {string} {string} {string} {string} {string} checkbox")
	public void clicks_checkbox_five_units(String string, String unit1, String unit2, String unit3, String unit4, String unit5) {
		step.wait(DELAY_2000);
		unit1 = unit1 + "_checkbox";
		unit2 = unit2 + "_checkbox";
		unit3 = unit3 + "_checkbox";
		unit4 = unit4 + "_checkbox";
		unit5 = unit5 + "_checkbox";
		step.setElement(unit1).waitUntilPresence().click();
		step.setElement(unit2).waitUntilPresence().click();
		step.setElement(unit3).waitUntilPresence().click();
		step.setElement(unit4).waitUntilPresence().click();
		step.setElement(unit5).waitUntilPresence().click();
	}
}