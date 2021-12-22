package ConformSurgeryRequest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = {"classpath:features/ORScheduler_ConformSurgeryRequest.feature"},
        glue= {
                "RequestASurgery",
        		//"Generics/RequestSurgery",
                "ConformSurgeryRequest",
                "Generics/Shutdown",
                "Generics/Screenshot"})
                //tags = {"@Approve1"})
public class ConformSurgeryRequest_Runner {
}
