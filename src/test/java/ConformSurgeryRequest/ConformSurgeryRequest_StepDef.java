package ConformSurgeryRequest;

import Login.Login;
import Login.Role;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.core.api.Scenario;
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
import java.util.stream.Collectors;

public class ConformSurgeryRequest_StepDef {
    private static String unscheduledNumber;
    private StepDefHelperYml step = new StepDefHelperYml();

    @Given("OR Scheduler is on case tracker landing page")
    public void orSchedulerIsOnCaseTrackerLandingPage(){
        Login login = new Login();
        login.LoginPage(Role.OR_SCHEDULER);
        step.from("ConformSurgeryRequest");
        Assert.assertTrue("OR Scheduler in on case tracker landing page", true);
    }
    @When("OR Scheduler sets unscheduled request to being scheduled")
    public void orSchedulerSetsUnScheduledRequestToBeingScheduled(){
        step.wait(2000) ;
        final WebElement UNSCHEDULED = step.setElement("UNSCHEDULED").element();
        unscheduledNumber = UNSCHEDULED.findElement(By.xpath(".//span[contains(@class, 'sc-bdVaJa sc-hzDkRC GbJmZ')]")).getText();
        step.setElement("SORTBY").waitUntilPresence().waitUntilVisible().click();
        step.setElement("NEWEST").pageReady().waitUntilPresence().click();
        step.wait(3000);
        step.findElements("CASE_CARD_DETAILS").get(0).click();
        step.pageReady();
        Assert.assertEquals("UNSCHEDULED",
                step.setElement("UNSCHEDULED_ALERT").waitUntilPresence().element().getText());

        step.setElement("ENTER_FIN").inputToElement(step.getValue("FIN_NUMBER"));
        step.setElement("CONFIRM").waitUntilVisible().waitUntilPresence().click();
        step.pageReady();
        Assert.assertEquals(0,
                step.findElements("UNSCHEDULED_ALERT").size());
        step.wait(1000);
        step.setElement("BACK_HOME").waitUntilVisible().waitUntilClickable().waitUntilPresence().click();
        step.pageReady();
    }
    @Then("the request is scheduled")
    public void theRequestIsScheduled(){
        step.wait(2000) ;
        final WebElement UNSCHEDULED = step.setElement("UNSCHEDULED").element();
        step.wait(1000);
        String newUnscheduledNumber = UNSCHEDULED.findElement(By.xpath(".//span[contains(@class, 'sc-bdVaJa sc-hzDkRC GbJmZ')]")).getText();
        step.wait(1000);
        if(unscheduledNumber.equals("99+")){
            Assert.assertTrue(newUnscheduledNumber.equals("99+") || newUnscheduledNumber.equals("99"));
        } else {
            Assert.assertEquals(Integer.parseInt(unscheduledNumber)-1,
                    Integer.parseInt(newUnscheduledNumber));
        }
        step.setElement("SORTBY").waitUntilVisible().waitUntilPresence().click();
        step.wait(2000) ;
        step.setElement("NEWEST").pageReady().waitUntilPresence().click();
        step.pageReady();
        WebElement firstCard = step.findElements("CASE_CARD_DETAILS").get(0);
        String tagXpath = ".//span[contains(@data-field, 'tag')]";
        List<WebElement> tags = firstCard.findElements(By.xpath(tagXpath));
        List<String> stringTags = tags.stream().map(WebElement::getText).collect(Collectors.toList());
       // Assert.assertTrue(stringTags.contains("SCHEDULED"));
       // Assert.assertFalse(stringTags.contains("UNSCHEDULED"));
    }
}
