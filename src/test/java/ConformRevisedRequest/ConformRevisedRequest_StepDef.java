package ConformRevisedRequest;

import Login.Login;
import Login.Role;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.core.api.Scenario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConformRevisedRequest_StepDef {
    private StepDefHelperYml step = new StepDefHelperYml();

    @Given("OR Scheduler is logged on")
    public void orSchedulerIsLoggedOn() {
        Login login = new Login();
        login.LoginPage(Role.OR_SCHEDULER);
        step.from("ConformRevisedRequest");
        step.wait(3000);
        step.setElement("SORTBY").waitUntilPresence().waitUntilVisible().click();
        step.setElement("NEWEST").pageReady().waitUntilPresence().click();
        step.pageReady();
        step.findElements("CASE_CARD_DETAILS").get(0).click();
    }

    @When("OR Scheduler approves the request revision initiated by office scheduler")
    public void orSchedulerApprovesTheRequestRevisionInitiatedByOfficeScheduler() {
        step.pageReady();
        step.setElement("APPROVE").waitUntilVisible().click();
        step.pageReady();
    }

    @Then("Alerts do not have REVISED")
    public void alertsDoNotHaveREVISED() {
        Assert.assertEquals(0,
                step.findElements("REVISED_ALERT").size());
    }

    @When("OR Scheduler declines the request revision initiated by office scheduler")
    public void orSchedulerDeclinesTheRequestRevisionInitiatedByOfficeScheduler(DataTable dt) {
        step.from("ConformRevisedRequest");
        step.pageReady();
        step.setElement("DECLINE").waitUntilVisible().click();
        step.pageReady();

        List<Map<String, String>> map = dt.asMaps(String.class, String.class);
        Assert.assertEquals(step.getValue("REQUEST_REVISION_HEADER"),
                step.setElement("REQUEST_REVISION_HEADER").waitUntilVisible().element().getText());
        Assert.assertEquals(step.getValue("REQUEST_REVISION_DESC"),
                step.setElement("REQUEST_REVISION_DESC").waitUntilVisible().element().getText());
        List<WebElement> declineReasonsCheckboxs = step.findElements("DECLINE_REASON_CHECKBOXS");
        if(map.get(0).get("TIME_NOT_AVAILABLE").equals("V")){
            declineReasonsCheckboxs.get(0).click();
        }
        if(map.get(0).get("MISSING_INFORMATION").equals("V")){
            declineReasonsCheckboxs.get(1).click();
        }
        if(map.get(0).get("OTHER").equals("V")){
            declineReasonsCheckboxs.get(2).click();
        }
        step.setElement("DECLINE_REASON_MESSAGE").pageReady().click();
        step.setElement("DECLINE_REASON_MESSAGE").waitUntilVisible().inputToElement(map.get(0).get("MESSAGE"));
        step.setElement("DECLINE_REQUEST_REVISION").waitUntilVisible().waitUntilPresence().click();
    }

    @Then("Alerts have NEEDS REVISION")
    public void alertsHaveNEEDSREVISION(DataTable dt) {
        step.pageReady();
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
