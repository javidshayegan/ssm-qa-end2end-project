package ORScheduler_RequestRevision;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = {"classpath:features/ORScheduler_RequestRevision.feature"},
        glue= {
                "Generics/RequestSurgery",
                "ORScheduler_RequestRevision",
                "Generics/Screenshot",
                "Generics/Shutdown"},
        		 tags = {"@failedneedsfixing"})
public class ORScheduler_RequestRevision_Runner {
}
