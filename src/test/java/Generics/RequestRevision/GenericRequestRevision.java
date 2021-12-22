package Generics.RequestRevision;

import Login.*;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenericRequestRevision {
    private StepDefHelperYml step = new StepDefHelperYml();
    @Given("OR Scheduler is on case tracker landing page")
    public void orSchedulerIsOnCaseTrackerLandingPage(){
        Login login = new Login();
        login.LoginPage(Role.OR_SCHEDULER);
        step.from("ORScheduler_RequestRevision");
        Assert.assertTrue("OR Scheduler in on case tracker landing page", true);
    }

    @And("OR Scheduler is on case where is in need of revision")
    public void orSchedulerIsOnCaseWhereIsInNeedOfRevision(){
        step.setElement("SORT_BY").element().click();
        step.wait(1000);
        step.setElement("CREATED_NEWEST").element().click();
        step.pageReady();
        step.findElements("CASE_CARD_DETAILS").get(0).click();
        step.pageReady();
    }

    @When("OR Scheduler makes request revision")
    public void orSchedulerMakesRequestRevision(DataTable dt){
        List<Map<String, String>> map = dt.asMaps(String.class, String.class);
        step.setElement("REQUEST_REVISION").element().click();
        if(map.get(0).get("TIME_NOT_AVAILABLE").equals("V")){
            step.setElement("REQUEST_REVISION_TIME_NOT_AVAILABLE").element().click();
        }
        if(map.get(0).get("MISSING_INFORMATION").equals("V")){
            step.setElement("REQUEST_REVISION_MISSING_INFORMATION").element().click();
        }
        if(map.get(0).get("OTHER").equals("V")){
            step.setElement("REQUEST_REVISION_OTHER").element().click();
        }
        step.setElement("REQUEST_REVISION_MESSAGE").element().click();
        step.setElement("REQUEST_REVISION_MESSAGE").element().sendKeys(map.get(0).get("MESSAGE"));
        step.setElement("REQUEST_REVISION_BUTTON").element().click();
        step.pageReady();
    }

    @Then("the request has Needs Revision alert")
    public void theRequestHasNeedsRevisionAlert(DataTable dt){
        Assert.assertTrue("Needs Revision is displayed",
                step.setElement("NEEDS_REVISION_ALERT").element().isDisplayed());
        List<WebElement> headers = step.waitUntilPresence("NEEDS_REVISION_HEADERS").findElements("NEEDS_REVISION_HEADERS");
        Assert.assertEquals("NEEDS REVISION", headers.get(0).getText());
        Assert.assertEquals("A revision request is pending approval.", headers.get(1).getText());
        List<WebElement> requests = step.findElements("NEEDS_REVISION_INFO");
        WebElement recentRequest = requests.get(requests.size()-1);
        List<WebElement> recentRequestInfo = recentRequest.findElements(By.xpath(".//span[@font-size='14']"));
        List<String> recentRequestInfoStr = recentRequestInfo.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> list = dt.asList(String.class);
        String actual = recentRequestInfoStr.stream().reduce("", (info1, info2) -> info1+","+info2);
        String expected = list.stream().reduce("", (info1, info2) -> info1+","+info2);
        Assert.assertEquals(expected, actual);
    }
}
