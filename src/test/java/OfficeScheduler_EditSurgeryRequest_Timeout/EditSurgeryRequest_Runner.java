package OfficeScheduler_EditSurgeryRequest_Timeout;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = {"classpath:features/OfficeScheduler_Timeout.feature"},
        glue= {
                "Generics/RequestSurgery",
        		"OfficeScheduler_EditSurgeryRequest_Timeout",
                //"Generics/Shutdown",
                "Generics/Screenshot"
        })
        //tags = {"@Test4"})
public class EditSurgeryRequest_Runner {
}
