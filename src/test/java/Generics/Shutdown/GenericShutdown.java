package Generics.Shutdown;

import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;

public class GenericShutdown {
    private StepDefHelperYml step = new StepDefHelperYml();
    @Then("Browser quits")
    public void browserQuits(){
        step.shutdownDriver();
    }
}
