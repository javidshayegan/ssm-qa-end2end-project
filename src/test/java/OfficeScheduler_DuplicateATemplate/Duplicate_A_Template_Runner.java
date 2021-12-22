package OfficeScheduler_DuplicateATemplate;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {
				"classpath:features/OfficeScheduler_Duplicate_A_Template.feature",
		},
        glue= {
				"Generics/Screenshot",
				"Generics/Shutdown",
				"OfficeScheduler_DuplicateATemplate"},
        tags = {"@Duplicate"})

public class Duplicate_A_Template_Runner {

}

