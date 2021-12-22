package OfficeScheduler_MyFilterPage;

import Login.Login;
import Login.Role;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;


public class OfficeScheduler_MyFilterPage_StepDef {

    private final String
            SORT_BY_HEADER = "sortbyheader",
            PROCEDURE_DATE_HEADER = "proceduredateheader",
            SURGEONS_HEADER = "surgeonsheader",
            OTHER_FILTERS_HEADER = "otherfiltersheader";

    private StepDefHelperYml step = new StepDefHelperYml();

    @Given("{string} is on Case Tracker landing page")
    public void is_on_case_tracker_landing_page(String user) {
        Login login = new Login();
        login.LoginPage(Role.OFFICE_SCHEDULER);
        step.from("MyFiltersPage");
        // assert user is on the surgical scheduling landing page
        String homepageURL = step.driver().getCurrentUrl();
        System.out.println("Current URL is " + homepageURL);
        try {
            Assert.assertEquals(homepageURL,
                    "https://casetracker-qa.app.cloud-02.pcf.ascension.org/");
            System.out.println("User is on the case tracker homepage.");
        } catch (AssertionError e) {
            System.out.println("User is not on the case tracker homepage.");
        }
    }

    @And("{string} is logged in on Case Tracker homepage")
    public void is_logged_in_on_Case_Tracker_homepage(String string) {
        step.from("MyFiltersPage");
        String homepageURL = step.driver().getCurrentUrl();
        System.out.println("Current URL is " + homepageURL);
        try {
            Assert.assertEquals(homepageURL,
                    "https://casetracker-qa.app.cloud-02.pcf.ascension.org/");
            System.out.println("User is on the case tracker homepage.");
        } catch (AssertionError e) {
            System.out.println("User is not on the case tracker homepage.");
        }
    }

    @When("{string} clicks on the Edit Filters button")
    public void click_on_Edit_Filters_button(String string)
    {
        step.from("MyFiltersPage");
        step.wait(1000);
        step.setElement("editfilters").waitUntilPresence().click();
    }

    @Then("{string} is on the My Filters page")
    public void is_on_My_Filters_page(String string)
    {
        step.from("MyFiltersPage");
        String myFiltersURL = step.driver().getCurrentUrl();

        System.out.println("Current URL is " + myFiltersURL);
        try {
            Assert.assertEquals(myFiltersURL,
                    "https://casetracker-qa.app.cloud-02.pcf.ascension.org/accountSettings/myFilters");
        } catch (AssertionError e) {
            System.out.println("User is not on My Filters page");
        }
        System.out.println("User is on My Filters page");
    }

    @And("the following filter options are available")
    public void available_filter_options(DataTable dt)
    {
        step.from("MyFiltersPage");
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(list.get(i).get(0), step.setElement(SORT_BY_HEADER).element().getText());
            Assert.assertEquals(list.get(i).get(1), step.setElement(PROCEDURE_DATE_HEADER).element().getText());
            Assert.assertEquals(list.get(i).get(2), step.setElement(SURGEONS_HEADER).element().getText());
            Assert.assertEquals(list.get(i).get(3), step.setElement(OTHER_FILTERS_HEADER).element().getText());
        }
    }

    @Given("{string} selects a Sort By option")
    public void select_a_sort_by_option(String string)
    {
        step.from("MyFiltersPage");
        step.wait(1000);
        Boolean isPresent = step.driver().findElements(By.xpath("//span[@font-size='1'][contains(.,'NEAREST')]")).isEmpty();
        if(isPresent = true)
        {
            step.wait(1000);
            step.setElement("sortbyfurthest").waitUntilPresence().click();
            step.setElement("sortbynearest").waitUntilPresence().click();
            System.out.println("Clicked on nearest");
        }
        else{
            step.setElement("sortbynearest").waitUntilPresence().click();
            step.wait(1000);
            step.setElement("sortbyfurthest").waitUntilPresence().click();
            System.out.println("Clicked on furthest");
        }
    }

    @And("the Save button is available")
    public void the_Save_button_is_available()
    {
        step.from("MyFiltersPage");
        step.wait(1000);
        step.setElement("savebutton").waitUntilPresence();
    }

    @When("{string} clicks Save button")
    public void click_save_button(String string)
    {
        step.from("MyFiltersPage");
        step.wait(1000);
        step.setElement("savebutton").waitUntilPresence().click();
    }

    @Then("{string} can see the confirmation message")
    public void saved_filter_confirmation(String string)
    {
        step.from("MyFiltersPage");
        Assert.assertEquals("Success you saved your filters", step.setElement("filtersavedmessage").element().getText());
    }

    @Given("{string} selects a procedure date")
    public void select_procedure_date(String string)
    {
        step.from("MyFiltersPage");
        step.wait(1000);
        step.setElement("proceduredatedefault").waitUntilPresence().click();
        step.setElement("proceduredatetoday").waitUntilPresence().click();
    }

    @Given("{string} selects a surgeon")
    public void select_a_surgeon(String string)
    {
        step.from("MyFiltersPage");
        step.wait(1000);
        step.setElement("firstsurgeoncheckbox").waitUntilPresence().click();
    }

    @Given("{string} selects another filter")
    public void select_another_filter(String string)
    {
        step.from("MyFiltersPage");
        step.wait(1000);
        step.setElement("otherfilterbookmarked").waitUntilPresence().click();
    }

}
