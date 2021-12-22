package EditSurgeryRequest;

import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Map;

public class EditSurgeryRequest_StepDef {
    private StepDefHelperYml step = new StepDefHelperYml();
    @Given("Office Scheduler already requested a new surgery")
    public void officeSchedulerAlreadyRequestedANewSurgery() {
        step.from("EditSurgeryRequest");
        step.findElements("CASE_CARD_DETAILS").get(0).click();
        step.pageReady();
    }

    @When("Office Scheduler edits surgery request")
    public void officeSchedulerEditsSurgeryRequest(DataTable dt) {
        List<Map<String, String>> map = dt.asMaps(String.class, String.class);
        step.wait(2000);
        step.setElement("EDIT").waitUntilVisible().click();
        String option = map.get(0).get("OPTION");
        Assume.assumeTrue(option + " must be either YES or NO",
                option.toUpperCase().equals("YES") || option.toLowerCase().equals("NO")
                );
        if(option.toUpperCase().equals("YES")) {
            step.setElement("REQUEST_REVISION_YES").waitUntilClickable().click();
            step.pageReady();
            if (map.get(0).get("CHANGE_PROCEDURE_TIME").equals("V")) {
                ((JavascriptExecutor) step.driver()).executeScript("arguments[0].click();", step.setElement("REQUEST_REVISION_CHANGE_PROCEDURE_TIME").element());
            }
            if (map.get(0).get("MISSING_INFORMATION").equals("V")) {
                ((JavascriptExecutor) step.driver()).executeScript("arguments[0].click();", step.setElement("REQUEST_REVISION_MISSING_INFORMATION").element());
            }
            if (map.get(0).get("OTHER").equals("V")) {
                ((JavascriptExecutor) step.driver()).executeScript("arguments[0].click();", step.setElement("REQUEST_REVISION_OTHER").element());
            }
            step.pageReady();
            step.setElement("REQUEST_REVISION_MESSAGE").element().click();
            step.setElement("REQUEST_REVISION_MESSAGE").element().sendKeys(map.get(0).get("MESSAGE"));
        } else {
            step.setElement("REQUEST_REVISION_NO").waitUntilClickable().click();
        }
        ((JavascriptExecutor)step.driver()).executeScript("arguments[0].click();", step.setElement("SAVE_CHANGES").element());
        step.pageReady();
    }
    @Then("Alerts should not have REVISED")
    public void alertShouldNotHaveREVISED(){
        step.wait(2000) ;
        List<WebElement> revisionInfo = step.findElements("REVISED_INFO");
        Assert.assertEquals("It creates a REVISED alert, but should not",
                0, revisionInfo.size());
    }
    @Then("Alerts have REVISED")
    public void alertsHaveREVISED(DataTable dt) {
        step.wait(2000) ;
        List<WebElement> revisionInfo = step.findElements("REVISED_INFO");
        WebElement mostRecentRevisionInfo = revisionInfo.get(revisionInfo.size()-1);
        By information = By.xpath(".//span[contains(@class, 'sc-bdVaJa hrQhHh')]");
        List<WebElement> reasonAndMessage = mostRecentRevisionInfo.findElements(information);
        WebElement revisionReason = reasonAndMessage.get(0);
        WebElement message = reasonAndMessage.get(1);
        List<String> expected_list = dt.asList();
        Assert.assertEquals(expected_list.get(0), revisionReason.getText());
        Assert.assertEquals(expected_list.get(1), message.getText());
    }
}
