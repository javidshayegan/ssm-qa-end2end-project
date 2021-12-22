package OfficeScheduler_DuplicateATemplate;

import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;

import Login.Login;
import Login.Role;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Duplicate_A_Template_StepDef {

	private StepDefHelperYml step = new StepDefHelperYml();

	@Given("{string} is on Case Tracker landing page")
	public void is_on_case_tracker_landing_page(String user) {
		Login login = new Login();
		login.LoginPage(Role.OFFICE_SCHEDULER);
		step.from("MyTemplateDuplicate");
		String homepageURL = step.driver().getCurrentUrl();

		System.out.println("Current URL is " + homepageURL);
		try {
			Assert.assertEquals(homepageURL,
					"https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc");
		} catch (AssertionError e) {
			System.out.println("User is not on the case tracker homepage.");
		}
		System.out.println("User is on the case tracker homepage.");
	}

	@Given("{string} is logged in on Case Tracker homepage")
	public void is_logged_in_on_Case_Tracker_homepage(String string) {
		String homepageURL = step.driver().getCurrentUrl();

		System.out.println("Current URL is " + homepageURL);
		try {
			Assert.assertEquals(homepageURL,
					"https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc");
		} catch (AssertionError e) {
			System.out.println("User is not on the case tracker homepage.");
		}
		System.out.println("User is on the case tracker homepage.");
	}

	@When("{string} clicks on Account Setting\\/My Account button on user username")
	public void clicks_on_Account_Setting_My_Account_button_on_user_username(String string) {
		step.wait(1000);
		step.setElement("userName").waitUntilPresence().click();
		step.wait(1000);
		step.setElement("AccountSetting").waitUntilPresence().click();
	}

	@When("{string} is taken to {string} page")
	public void is_taken_to_page(String string, String string2) {

		String currentLandingURL = step.driver().getCurrentUrl();
		System.out.println("Current URL is " + currentLandingURL);
		try {
			Assert.assertEquals(currentLandingURL,
					"https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myInformation");
		} catch (AssertionError e) {
			System.out.println("User is not on My Information page.");
		}
		System.out.println("User is on My Information Page.");
		step.from("MyTemplateDuplicate");
	}

	@Then("User verifies the following field exists")
	public void user_verifies_the_following_field_exists(io.cucumber.datatable.DataTable dt) {
		Assert.assertEquals("MY INFORMATION", step.setElement("MyInformation").element().getText());
		Assert.assertEquals("MY NOTIFICATIONS", step.setElement("MyNotifications").element().getText());
		Assert.assertEquals("MY FILTERS", step.setElement("MyFilters").element().getText());
		Assert.assertEquals("My Information", step.setElement("MyInformationTitle").element().getText());
		Assert.assertEquals("MY TEMPLATES", step.setElement("MyTemplate").element().getText());
	}

	@When("{string} is on {string} page")
	public void clicks_on_button(String string, String string2) {
		step.wait(1000);
		step.setElement("MyTemplate").waitUntilPresence().click();
		step.wait(1000);
		String currentLandingURL = step.driver().getCurrentUrl();
		System.out.println("Current URL is " + currentLandingURL);
		try {
			Assert.assertEquals(currentLandingURL,
					"https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myTemplates?page=1&size=25");
		} catch (AssertionError e) {
			System.out.println("User is not on My Template page.");
		}
		System.out.println("User is on My Template Page.");
		// step.from("MyTemplateCreate");
	}

	@Then("{string} verifies the following fields")
	public void verifies_the_following_fields(String string, io.cucumber.datatable.DataTable dataTable) {

		Assert.assertEquals("My Templates", step.setElement("MyTemplateTitle").element().getText());
		Assert.assertEquals("CREATE TEMPLATE", step.setElement("CreateTemplate").element().getText());
	}

	@Given("{string} on {string} page")
	public void on_page(String string, String string2) {

	}

	@When("{string} clicks on three dotted icon then on {string} button on the top right corner of a Template Tile")
	public void clicks_on_three_dotted_icon_then_on_button_on_the_top_right_corner_of_a_Template_Tile(String string,
			String string2) {
		step.wait(1000).setElement("ThreeDotted").waitUntilPresence().click();
		step.wait(1000).setElement("Duplicate").waitUntilPresence().click();

	}

	@Then("{string} verifies Duplicate Template modal popup")
	public void verifies_Create_Template_modal_popup(String string) {
		step.wait(1000);
		Assert.assertEquals("Duplicate Template", step.setElement("DuplicateTemplateTitle").element().getText());
	}

	@Then("{string} verifies the following field exists")
	public void verifies_the_following_field_exists(String string, io.cucumber.datatable.DataTable dataTable) {
		Assert.assertEquals("TEMPLATE NAME", step.setElement("TemplateModalName").element().getText());
		Assert.assertEquals("SURGEONS", step.setElement("Surgeon").element().getText());
		Assert.assertEquals("CPT CODE(S)", step.setElement("CPTCode").element().getText());
		Assert.assertEquals("PROCEDURE NAME", step.setElement("ProcedureName").element().getText());
		Assert.assertEquals("MODIFIER", step.setElement("Modifier").element().getText());
		Assert.assertEquals("PRIMARY PROCEDURE?", step.setElement("PrirmaryProcedure").element().getText());
		Assert.assertEquals("DURATION", step.setElement("Duration").element().getText());
		Assert.assertEquals("IMPLANTS NEEDED", step.setElement("ImplantsNeeded").element().getText());
		Assert.assertEquals("OTHER EQUIPMENT AND SUPPLIES",
				step.setElement("OtherEquipmentAndSupplies").element().getText());
		Assert.assertEquals("ADDITIONAL PROCEDURE COMMENTS",
				step.setElement("AdditionalProcedureComments").element().getText());
		// Assert.assertEquals("Cancel",
		// step.setElement("CancelButton").element().getText());

	}

	@Then("{string} enters the following information")
	public void enters_the_following_data(String string, io.cucumber.datatable.DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			step.wait(2000);
			WebElement clear = step.driver().findElement(By.xpath("//input[@name='templateName']"));
			clear.sendKeys(Keys.CONTROL + "a");
			clear.sendKeys(Keys.DELETE);
			step.wait(1000);

			String tempName = list.get(i).get("TEMPLATE NAME");
			Random rand = new Random();
			// Generate random integers in range 0 to 999
			int num = rand.nextInt(10000000);
			tempName = tempName + num;

			step.setElement("TemplateNameField").waitUntilPresence().inputToElement(tempName);

			step.wait(1000);

			step.wait(1000).setElement("SurgeonField").waitUntilPresence().click();
			// step.wait(1000).setElement("SurgeonName").waitUntilPresence().click();
			step.driver().findElement(By.xpath("//div[contains(text(),'DMD, John R.')]")).click();

			WebElement clear4 = step.driver().findElement(By.xpath("//input[@name='cptCodesInner']"));
			clear4.sendKeys(Keys.CONTROL + "a");
			clear4.sendKeys(Keys.DELETE);
			step.wait(2000);
			step.wait(1000).setElement("CptCodeButton").waitUntilPresence().click();
			step.setElement("CPTCodeEnter").waitUntilPresence().inputToElement(list.get(i).get("CPT CODES"));

			
			WebElement clear5 = step.driver().findElement(By.xpath("//textarea[@name='otherEquipment']"));
			clear5.sendKeys(Keys.CONTROL + "a");
			clear5.sendKeys(Keys.DELETE);
			step.wait(2000);
			step.setElement("OtherEquipmentandSuppliesTextArea").waitUntilPresence()
					.inputToElement(list.get(i).get("OTHER EQUIPMENT AND SUPPLIES"));
			
			WebElement clear6 = step.driver().findElement(By.xpath("//textarea[@name='additionalComments']"));
			clear6.sendKeys(Keys.CONTROL + "a");
			clear6.sendKeys(Keys.DELETE);
			step.wait(2000);
			step.setElement("AdditionalProcedureCommentsField").waitUntilPresence()
					.inputToElement(list.get(i).get("ADDITIONAL PROCEDURE COMMENTS"));
		}
	}

	@Then("{string} clicks on {string} Button")
	public void click_on_button(String string, String string2) {
		step.wait(1000).setElement("UpdateButton").waitUntilPresence().click();
	}

	@Then("{string} is automatically paginates to the duplicated template page")
	public void is_automatically_paginates_to_the_duplicated_template_page(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("{string} is on My Template page")
	public void is_on_My_Template_page(String string) {
		String currentLandingURL = step.driver().getCurrentUrl();
		System.out.println("Current URL is " + currentLandingURL);
		try {
			Assert.assertEquals(currentLandingURL,
					"https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myTemplates?page=1&size=25");
		} catch (AssertionError e) {
			System.out.println("User is not on My Template page.");
		}
		System.out.println("User is on My Template Page.");
	}

	@When("{string} verifies Duplicated template name on Template page")
	public void verifies_Duplicated_template_name_on_Template_page(String string, io.cucumber.datatable.DataTable datTable) {
			step.wait(2000).driver().findElement(By.xpath("//span[contains(.,'SORT BY')]")).click();
			step.wait(1000).driver().findElement(By.xpath("//div[@tabindex='0'][contains(.,'NEWEST')]")).click();
			Assert.assertEquals("DUPLICATE",
					step.driver().findElement(By.xpath("//span[@data-field='tag'][contains(.,'Duplicate')]")).getText());
		}

	@Then("{string} deletes the duplicated template and logs out")
	public void deletes_the_duplicated_template_and_logs_out(String string) {
		step.wait(1000).setElement("OrderBy").waitUntilPresence().click();
		step.wait(1000).setElement("OrderByNewest").waitUntilPresence().click();
		step.wait(2000).setElement("ThreeDotted").waitUntilPresence().click();
		step.wait(2000).setElement("Delete").waitUntilPresence().click();
		step.wait(3000).setElement("DeleteButton").waitUntilPresence().click();
		step.wait(3000);
		step.driver().quit();
	}

}
