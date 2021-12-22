package Generics.Screenshot;

import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Screenshot {
    private StepDefHelperYml step = new StepDefHelperYml();
    @AfterStep
    public void addScreenshot(Scenario sc){
        if(sc.isFailed()) {
            byte[] srcBytes = ((TakesScreenshot) step.driver()).getScreenshotAs(OutputType.BYTES);
            sc.embed(srcBytes, "image/png");
        }
    }
}
