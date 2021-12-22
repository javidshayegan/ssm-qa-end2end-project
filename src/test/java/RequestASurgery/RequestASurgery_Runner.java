package RequestASurgery;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {
				"classpath:features/OfficeScheduler_RequestASurgery.feature",
		},
        glue= {
				"Generics/Screenshot",
				"Generics/Shutdown",
				"RequestASurgery"})
				 //tags = {"@failedneedsfixing"})
public class RequestASurgery_Runner {

}

