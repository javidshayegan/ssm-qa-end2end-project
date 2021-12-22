package OfficeScheduler_RemoveATemplate;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {
				"classpath:features/OfficeScheduler_Remove_A_Template.feature",
		},
        glue= {
				"Generics/Screenshot",
				"Generics/Shutdown",
				"OfficeScheduler_RemoveATemplate"},
        tags = {"@Remove1"})

public class Remove_A_Template_Runner {

}

