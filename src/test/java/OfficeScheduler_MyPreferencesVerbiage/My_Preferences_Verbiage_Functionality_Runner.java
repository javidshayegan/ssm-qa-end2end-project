package OfficeScheduler_MyPreferencesVerbiage;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {
				"classpath:features/OfficeScheduler_MyPreference_Verbiage.feature",
		},
        glue= {
				"Generics/Screenshot",
				"Generics/Shutdown",
				"OfficeScheduler_MyPreferencesVerbiage"},
        tags = {"@Verbiage1"})

public class My_Preferences_Verbiage_Functionality_Runner {

}

