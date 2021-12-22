package OfficeScheduler_Edit_MyTemplate;

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

public class Edit_MyTemplate_StepDef {

	private StepDefHelperYml step = new StepDefHelperYml();
//	private Calendar c;
//private Date d;

	@Given("{string} is on Case Tracker landing page")
	public void is_on_case_tracker_landing_page(String user) {
		Login login = new Login();
		login.LoginPage(Role.OFFICE_SCHEDULER);
		step.from("MyTemplateCreate");
		// assert user is on the surgical scheduling landing page
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
		step.setElement("AccountSetting").waitUntilPresence().click();;
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
		// step.from("MyTemplateCreate");
	}

	@Then("User verifies the following field exists")
	public void user_verifies_the_following_field_exists(io.cucumber.datatable.DataTable dt) {
		Assert.assertEquals("MY INFORMATION", step.setElement("MyInformation").element().getText());
		Assert.assertEquals("MY NOTIFICATIONS", step.setElement("MyNotifications").element().getText());
		Assert.assertEquals("MY FILTERS", step.setElement("MyFilters").element().getText());
		Assert.assertEquals("My Information", step.setElement("MyInformationTitle").element().getText());
		Assert.assertEquals("MY TEMPLATES", step.setElement("MyTemplate").element().getText());
	}

	@Given("{string} on My Information Page")
	public void on_My_Information_Page(String string) {
		step.wait(1000).setElement("Home").waitUntilPresence().click();
		step.wait(1000).setElement("userName").waitUntilPresence().click();
		step.wait(2000).setElement("AccountSetting").waitUntilPresence().click();
		String currentLandingURL = step.driver().getCurrentUrl();
		System.out.println("Current URL is " + currentLandingURL);
		try {
			Assert.assertEquals(currentLandingURL,
					"https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myInformation");
		} catch (AssertionError e) {
			System.out.println("User is not on My Information page.");
		}
		System.out.println("User is on My Information Page.");
		step.from("MyTemplateCreate");
	}

	@When("{string} clicks on {string} button")
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

	@Given("{string} is on {string} page")
	public void on_page(String string, String string2) {

	}

	@When("{string} clicks on three dotted icon then on {string} button on the top right corner of a Template Tile")
	public void clicks_on_three_dotted_icon_then_on_button_on_the_top_right_corner_of_a_Template_Tile(String string,
			String string2) {
		step.wait(2000).setElement("OrderBy").waitUntilPresence().click();
		step.wait(2000).setElement("OrderByNewest").waitUntilPresence().click();
		step.wait(1000).setElement("ThreeDotted").waitUntilPresence().click();
		step.wait(1000).setElement("EditOption").waitUntilPresence().click();
	}
	@Then("{string} verifies Edit Template modal popup")
	public void verifies_Edit_Template_modal_popup(String string, io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			// Assert.assertEquals("Edit Template Test",
			// step.setElement("EditOption").element().getText());
		}
	}

	@Then("{string} verifies the following field exists")
	public void verifies_the_following_field_exists(String string, io.cucumber.datatable.DataTable dataT) {
		List<Map<String, String>> list = dataT.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			Assert.assertEquals("Edit Template",
					step.wait(1000).setElement("EditTemplateModalName").element().getText());
			// Assert.assertEquals("TEMPLATE NAME",
			// step.wait(1000).setElement("EditTemplateName").element().getText());
			Assert.assertEquals("SURGEONS", step.setElement("Surgeon").element().getText());
			Assert.assertEquals("CPT CODE(S)", step.setElement("CPTCode").element().getText());
			// Assert.assertEquals("PROCEDURE NAME",
			// step.setElement("ProcedureName").element().getText());
			// Assert.assertEquals("MODIFIER",
			// step.setElement("Modifier").element().getText());
			// Assert.assertEquals("PRIMARY PROCEDURE?",
			// step.setElement("PrirmaryProcedure").element().getText());
			// Assert.assertEquals("DURATION",
			// step.wait(1000).setElement("Duration").element().getText());
			// Assert.assertEquals("IMPLANTS NEEDED?",
			// step.setElement("ImplantsNeeded").element().getText());
			// Assert.assertEquals("OTHER EQUIPMENT AND SUPPLIES",
			// step.setElement("OtherEquipmentAndSupplies").element().getText());
			// Assert.assertEquals("ADDITIONAL PROCEDURE COMMENTS",
			// step.setElement("AdditionalProcedureComments").element().getText());
			Assert.assertEquals("Cancel", step.setElement("CancelButton").element().getText());

		}
	}

	@Given("{string} is on {string} modal")
	public void is_on_dashboard(String string, String string2) {
		// Assert.assertEquals("TEMPLATE NAME",
		// step.setElement("TemplateModalName").element().getText());
	}

	@Given("{string} enters the following data to Edit the Template")
	public void enters_the_following_data_to_Edit_the_Template(String string, io.cucumber.datatable.DataTable Dt) {
		List<Map<String, String>> list = Dt.asMaps(String.class, String.class);
		for (int i = 0; i < list.size(); i++) {
			step.wait(2000);
			WebElement clear = step.driver().findElement(By.xpath("//input[@name='templateName']"));
			clear.sendKeys(Keys.CONTROL + "a");
			clear.sendKeys(Keys.DELETE);
			step.wait(1000);

			String tempName = list.get(i).get("TEMPLATE NAME*");
			Random rand = new Random();
			// Generate random integers in range 0 to 999
			int num = rand.nextInt(10000000);
			tempName = tempName + num;

			step.setElement("TemplateNameField").waitUntilPresence().inputToElement(tempName);

			step.wait(1000);

			step.wait(1000).setElement("CptCodeButton").waitUntilPresence().click();

			step.setElement("CPTCodeEnter").waitUntilPresence().inputToElement(list.get(i).get("CPT CODE(S)"));

			step.wait(1000).setElement("ApproachDropdown").waitUntilPresence().click();
			step.wait(1000).setElement("ApproachValue").waitUntilPresence().click();
			step.wait(1000).setElement("PrimaryProcedure").waitUntilPresence().click();

			step.wait(1000);
			WebElement clear1 = step.driver().findElement(By.xpath("//input[@name='duration']"));
			clear1.sendKeys(Keys.CONTROL + "a");
			clear1.sendKeys(Keys.DELETE);

			step.wait(1000).setElement("DurationField").waitUntilPresence().inputToElement(list.get(i).get("DURATION"));

			step.wait(1000).setElement("EditAnesthesiaType").waitUntilPresence().click();
			step.wait(1000).setElement("AnestheisType").waitUntilPresence().click();
			step.wait(1000).setElement("EditAnesthesiaType").waitUntilPresence().click();

			step.wait(1000);
			WebElement clear2 = step.driver().findElement(By.xpath("//textarea[contains(@name,'otherEquipment')]"));
			clear2.sendKeys(Keys.CONTROL + "a");
			clear2.sendKeys(Keys.DELETE);
			step.wait(1000).setElement("OtherEquipmentandSuppliesTextArea").waitUntilPresence()
					.inputToElement(list.get(i).get("OTHER EQUIPMENT AND SUPPLIES"));

			step.wait(1000);
			WebElement clear3 = step.driver().findElement(By.xpath("//textarea[@name='additionalComments']"));
			clear3.sendKeys(Keys.CONTROL + "a");
			clear3.sendKeys(Keys.DELETE);
			step.wait(1000);
			step.setElement("AdditionalProcedureCommentsField").waitUntilPresence()
					.inputToElement(list.get(i).get("ADDITIONAL PROCEDURE COMMENTS"));
			step.wait(1000);
		}
	}

	@Given("{string} click on {string} button")
	public void click_on_button(String updateButton, String updateButton1) {
		step.wait(1000).setElement("UpdateButton").waitUntilPresence().click();
		step.wait(1000);
	}
	/*
	 * @Then("{string} verifies a Success Toaster is displayed") public void
	 * verifies_a_Success_Toaster_is_displayed(String string) { // Write code here
	 * that turns the phrase above into concrete actions throw new
	 * cucumber.api.PendingException(); }
	 */

	@Given("{string} is on My Template page")
	public void is_on_My_Template_page(String string) {
		String currentLandingURL = step.driver().getCurrentUrl();
		System.out.println("Current URL is " + currentLandingURL);
		try {
			Assert.assertEquals(currentLandingURL,
					"https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myTemplates?page=1&size=25&sortModel=dateCreated:desc,name:asc");
		} catch (AssertionError e) {
			System.out.println("User is not on My Template page.");
		}
		System.out.println("User is on My Template Page.");
	}

	@When("{string} verifies the the newly edited template exist on the tile's list")
	public void verifies_the_the_newly_created_template_exist_on_the_tile_s_list(String string) {
		step.wait(2000);
		step.wait(1000).setElement("OrderBy").waitUntilPresence().click();
		step.wait(1000).setElement("OrderByNewest").waitUntilPresence().click();
	}

	@Then("{string} verifies following fields in newly edited Templates tile")
	public void verifies_following_fields_in_newly_created_Templates_tile(String string,
			io.cucumber.datatable.DataTable dataTable) {
		step.wait(1000);
		Assert.assertEquals("Editing, No Comment is . . . . . . . . .",
				step.setElement("CommentSection").element().getText());

	}

	@Then("{string} verifies a green {string} tag displays on tile")
	public void verifies_a_green_tag_displays_on_tile(String string, String string2) {
		step.wait(2000);
		Assert.assertEquals("EDITED", step.setElement("GreenEditButton").element().getText());

	}

	@Then("{string} logs out of the Office Scheduler")
	public void logs_out_of_the_Office_Scheduler(String string) {
		step.wait(2000);
		step.wait(1000).driver().quit();
	}

}
