package OfficeScheduler_DeleteATemplate;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {
				"classpath:features/OfficeScheduler_Delete_MyTemplate.feature",
		},
        glue= {
				"Generics/Screenshot",
				"Generics/Shutdown",
				"OfficeScheduler_DeleteATemplate"},
        tags = {"@DeleteFailed"})

public class DeleteATemplate_Runner {

}

