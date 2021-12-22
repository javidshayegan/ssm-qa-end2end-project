package MyInformation_MySurgeon;

import Login.*;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyInformation_MySurgeon_OS_StepDef {

	private static final int DELAY_1000 = 0;

	 private StepDefHelperYml step = new StepDefHelperYml();

	@Given("{string} is on {string}")
	public void is_on(String credential, String surgicalSchedulingLandingPage) {
		//Role role = credential.contains("Office") ? Role.OFFICE_SCHEDULER1 : Role.OR_SCHEDULER_001;
		//Login login=new Login();
		//login.LoginPage(role);
        Login login = new Login();
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
	}

	@When("{string} clicks on the {string} then clicks on Account Setting")
	public void clicks_on_the_then_clicks_on_Account_Setting(String user, String Review) {
		step.from("MyInformationMySurgeon");
	    step.pageReady();

		Actions action = new Actions(step.driver());
        JavascriptExecutor js = (JavascriptExecutor) step.driver();
        js.executeScript("window.scrollBy(0,600)", "");
		step.wait(5000);
		WebElement element =step.driver().findElement(By.xpath("//span[@data-field='userName']"));
		action.moveToElement(element).click(element).build().perform();
		step.wait(5000);
		step.setElement("AccountSettings").waitUntilPresence().element().click();
		step.wait(1000);
	}

	@When("{string} is taken to {string}")
	public void is_taken_to(String review, String edit) {
		Assert.assertTrue(step.driver().getCurrentUrl().equals(edit));
	}

	@When("{string} wants to change password")
	public void changepassword(String user){
		step.setElement("CHANGE_PASSWORD").click();
	}
	@Then("{string} verifies the following sections are available")
	public void verifies_the_following_sections_are_available(String string, io.cucumber.datatable.DataTable dataTable) {
		step.wait(DELAY_1000);
		List<String> list = dataTable.asList();
		List<String> actualList = new ArrayList<>();
		actualList.add("AccountSettingsText");
		actualList.add("MyInformationDashboardText");
		actualList.add("ChangePassword");
		for (int i = 0; i < list.size(); i++) {
			Assert.assertEquals(list.get(i), step.setElement(actualList.get(i)).waitUntilVisible().element().getText());
		}
	}

	@Then("{string} verifies the following information")
	public void verifies_the_following_information(String string, io.cucumber.datatable.DataTable dataTable) {
		step.wait(DELAY_1000);
		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		Assert.assertEquals(list.get(0).get("FULL NAME"),
				step.setElement("FullName_Value").waitUntilVisible().element().getText());
		Assert.assertEquals(list.get(0).get("OFFICE PHONE NUMBER"),
				step.setElement("OFFICEPHONENUMBER_Value").waitUntilVisible().element().getText());
		Assert.assertEquals(list.get(0).get("EMAIL"),
				step.setElement("EMAIL_Value").waitUntilVisible().element().getText());
		if(string.contains("Office")) {
			Assert.assertEquals(list.get(0).get("PRACTICE NAME"),
					step.setElement("PRACTICENAME_Value").waitUntilVisible().element().getText());
		}
	}
	@Given("{string} clicks on {string} button")
	public void clicks_on_button(String string, String string2) {
		step.from("MyInformationMySurgeon");
		step.wait(2000);
		step.setElement("MySurgeonsButton").waitUntilPresence().element().click();
		step.wait(2000);
	}

	@When("{string} verifies the following information is available")
	public void verifies_the_following_information_is_available(String string, io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		for(int i = 0; i < list.size(); i++)
		{
			Assert.assertEquals(list.get(i).get(0), step.setElement("MySurgeonsText").element().getText());
			Assert.assertEquals(list.get(i).get(1), step.setElement("SurgeonsList").element().getText());
			Assert.assertEquals(list.get(i).get(1), step.setElement("MySurgeonInstructionText").element().getText());
			step.wait(2000);
		}
	}
	@When("{string} selects or deselects two surgeons")
	public void selects_or_deselects_two_surgeons(String string) {
		step.wait(2000);
		step.setElement("FirstSurgeon").waitUntilPresence().element().click();
		step.wait(2000);
		step.setElement("SecondSurgeon").waitUntilPresence().element().click();
		step.wait(1000);
		step.setElement("FifthSurgeon").waitUntilPresence().element().click();
	}

	@Then("{string} clicks on Save button")
	public void clicks_on_Save_button(String string) {
		step.wait(2000);
		step.setElement("SaveButton").waitUntilPresence().element().click();
	}

	@Then("user logs out of Office Scheduler")
	public void user_logs_out_of_Office_Scheduler() {
		step.wait(2000);

		Actions action = new Actions(step.driver());
        JavascriptExecutor js = (JavascriptExecutor) step.driver();
        js.executeScript("window.scrollBy(0,600)", "");
		step.wait(5000);

		WebElement element =step.driver().findElement(By.xpath("//span[contains(.,'Hi, Office')]"));

		action.moveToElement(element).click(element).build().perform();
		step.wait(2000);
		step.setElement("LogoutButton").waitUntilPresence().element().click();
	
		step.wait(5000);
	
	}
	@When("{string} clicks on {string} then clicks on Account Setting")
	public void clicks_on_then_clicks_on_Account_Setting(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("{string} is taken to the {string}")
	public void is_taken_to_the(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

}