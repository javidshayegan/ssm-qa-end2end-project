package EditSurgeryRequest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = {"classpath:features/OfficeScheduler_EditSurgeryRequest.feature"},
        glue= {
        		"RequestASurgery",
                "EditSurgeryRequest",
                "Generics/Shutdown",
                "Generics/Screenshot"})
               //tags = {"@Approve1"})

public class EditSurgeryRequest_Runner {
}
