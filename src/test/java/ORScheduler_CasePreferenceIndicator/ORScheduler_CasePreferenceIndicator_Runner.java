package ORScheduler_CasePreferenceIndicator;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		features= {"classpath:features/ORScheduler_CasePreferenceIndicator.feature"},
		glue= {
				"Generics/Shutdown",
				"ORScheduler_CasePreferenceIndicator",
				"Generics/Screenshot"
		})

public class ORScheduler_CasePreferenceIndicator_Runner {

}

