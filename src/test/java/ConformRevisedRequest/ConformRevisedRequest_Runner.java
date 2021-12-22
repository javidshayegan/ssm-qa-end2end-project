package ConformRevisedRequest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = {
        		"classpath:features/ORScheduler_ConformRevisedRequestApprove.feature",
                "classpath:features/ORScheduler_ConformRevisedRequestDecline.feature",
                "classpath:features/ORScheduler_ConformRevisedRequestDecline2.feature",
                "classpath:features/ORScheduler_ConformRevisedRequestApprove1.feature",
                "classpath:features/ORScheduler_ConformRevisedRequestDecline1.feature"
        },
        glue= {
        		"RequestASurgery",
        		//"Generics/RequestSurgery",
                "EditSurgeryRequest",
                "ConformRevisedRequest",
                "Generics/RequestRevision",
                "Generics/MarkAsResolved",
                "Generics/Shutdown",
                "Generics/Screenshot"})
                //tags = {"@Approve1"})
public class ConformRevisedRequest_Runner {
}
