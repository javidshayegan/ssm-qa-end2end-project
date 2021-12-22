package Generics.MarkAsResolved;

import Login.*;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenericMarkAsResolved {
    private StepDefHelperYml step = new StepDefHelperYml();
    @Given("Office Scheduler is on case tracker landing page")
    public void officeSchedulerIsOnCaseTrackerLandingPage() {
        Login login = new Login();
        login.LoginPage(Role.OFFICE_SCHEDULER);
    }

    @When("Office Scheduler marks as resolved")
    public void officeSchedulerMarksAsResolved(DataTable dt) {
        List<Map<String, String>> map = dt.asMaps(String.class, String.class);
        step.from("MarkAsResolved");
        step.pageReady();
        step.setElement("SORT_BY").element().click();
        step.wait(1000);
        step.setElement("CREATED_NEWEST").element().click();
        step.pageReady();
        step.findElements("CASE_CARD_DETAILS").get(0).click();
        step.pageReady();
        step.setElement("MARK_AS_RESOLVED").click();
        if(map.get(0).get("CHANGE_PROCEDURE_TIME").equals("V")){
            step.setElement("MARK_AS_RESOLVED_CHANGE_PROCEDURE_TIME").element().click();
        }
        else if(map.get(0).get("UPDATE_INFORMATION").equals("V")){
            step.setElement("MARK_AS_RESOLVED_UPDATED_INFORMATION").element().click();
        }
        else if(map.get(0).get("OTHER").equals("V")){
            step.setElement("MARK_AS_RESOLVED_OTHER").element().click();
        }
        step.setElement("MARK_AS_RESOLVED_MESSAGE").element().click();
        step.setElement("MARK_AS_RESOLVED_MESSAGE").element().sendKeys(map.get(0).get("MESSAGE"));
        step.setElement("MARK_AS_RESOLVED_BUTTON").click();
        step.pageReady();
    }

    @Then("the request has Revised alert")
    public void theRequestHasRevisedAlert(DataTable dt) {
        Assert.assertTrue("Revised is displayed",
                step.setElement("REVISED_ALERT").element().isDisplayed());
        List<WebElement> headers = step.findElements("REVISED_HEADERS");
        Assert.assertEquals("REVISED", headers.get(0).getText());
        Assert.assertEquals("A revision request is pending approval.", headers.get(1).getText());
        List<WebElement> requests = step.findElements("REVISED_INFO");
        WebElement recentRequest = requests.get(requests.size()-1);
        List<WebElement> recentRequestInfo = recentRequest.findElements(By.xpath(".//span[@font-size='14']"));
        List<String> recentRequestInfoStr = recentRequestInfo.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> list = dt.asList(String.class);
        String actual = recentRequestInfoStr.stream().reduce("", (info1, info2) -> info1+","+info2);
        String expected = list.stream().reduce("", (info1, info2) -> info1+","+info2);
        Assert.assertEquals(expected, actual);
    }
}
