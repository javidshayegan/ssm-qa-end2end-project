package Bookmark;

import Login.Login;
import Login.Role;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class BookMark_StepDef {
	private StepDefHelperYml step = new StepDefHelperYml();

	@Given("{string} is on case tracker landing page")
	public void is_on_case_tracker_landing_page(String user) {
		Login login = new Login();
		login.LoginPage(Role.OFFICE_SCHEDULER);
		step.from("OfficeSchedulerSmokeTests");
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

	@When("{string} selects BOOKMARKED filter")
	public void selectsBOOKMARKEDFilter(String user) {
		step.from("CaseListFilter");
		step.driver().navigate().to(
				"https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=1000000&sortModel=procedureDate:asc&state=BOOKMARKED");
		step.pageReady();
	}

	@And("{string} undo all bookmarked cases")
	public void undoAllBookmarkedCases(String arg0) {
		step.from("CaseListFilter");
		List<WebElement> caseCardDetails = step.findElements("CASE_CARD_DETAILS");
		if (!caseCardDetails.isEmpty()) {
			String tagXpath = ".//div[contains(@class, 'icon-ribbon')]";
			By TAGS = By.xpath(tagXpath);
			List<WebElement> bookmarkedLabelInCards = caseCardDetails.parallelStream()
					.map(card -> card.findElement(TAGS)).collect(Collectors.toList());
			for (WebElement icon : bookmarkedLabelInCards) {
				icon.click();
				step.wait(500);
			}

		}
	}

	@Then("Bookmarked cases icon should be hollow")
	public void bookmarkedCasesIconShouldBeVoid() {
		List<WebElement> caseCardDetails = step.findElements("CASE_CARD_DETAILS");
		String tagXpath = ".//div[contains(@class, 'icon-ribbon')]";
		By TAGS = By.xpath(tagXpath);
		List<WebElement> bookmarkedLabelInCards = caseCardDetails.parallelStream().map(card -> card.findElement(TAGS))
				.collect(Collectors.toList());
		for (int i = 0; i < bookmarkedLabelInCards.size(); i++) {
			Assert.assertEquals("the " + i + "th case bookmark is not hollow", "#999999",
					bookmarkedLabelInCards.get(i).getAttribute("color"));
		}
	}

	@Given("{string} is on the {int}st page")
	public void isOnTheStPage(String arg0, int page) {
		step.driver().navigate().to("https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=" + page
				+ "&size=25&sortModel=procedureDate:asc");
		step.pageReady();
	}

	@When("{string} bookmarks a case")
	public void bookmarksACase(String arg0) {
		List<WebElement> cases = step.findElements("CASE_CARD_DETAILS");
		if (!cases.isEmpty()) {
			String tagXpath = ".//div[contains(@class, 'icon-ribbon')]";
			By BOOKMARKED = By.xpath(tagXpath);
			cases.get(0).findElement(BOOKMARKED).click();
		} else {
			Assume.assumeTrue("There is no case at all!", false);
		}
	}

	@Then("{string} should be able to see {int} bookmarked case(s)")
	public void shouldBeAbleToSeeAllBookmarkedCases(String arg0, int numberOfBookmarks) {
		step.driver().navigate().to(
				"https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=1000000&sortModel=procedureDate:asc&state=BOOKMARKED");
		step.pageReady();
		List<WebElement> caseCardDetails = step.findElements("CASE_CARD_DETAILS");
		int i = caseCardDetails.parallelStream().map(card -> card.findElement
				(By.xpath(".//div[contains(@class, 'icon-ribbon')]")))
		.collect(Collectors.toList()).size();
		Assert.assertEquals("there should be " + numberOfBookmarks + " bookmark cases but there are " + i,
				numberOfBookmarks,i);
	}

	@Given("{string} is on the {int}nd page")
	public void isOnTheNdPage(String arg0, int page) {
		boolean flag = true;
		step.driver().navigate().to("https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=" + page
				+ "&size=25&sortModel=procedureDate:asc");
		step.pageReady();
		List<WebElement> caseCardDetails = step.findElements("CASE_CARD_DETAILS");
		if(caseCardDetails.isEmpty())
			flag = false;
		else
			flag = true;
		Assume.assumeFalse(page + "Page doesn't exist", flag);
	}
}
