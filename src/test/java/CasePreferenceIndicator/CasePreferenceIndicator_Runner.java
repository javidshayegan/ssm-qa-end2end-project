package CasePreferenceIndicator;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {"classpath:features/OfficeScheduler_CasePreferenceIndicator.feature"},
        glue= {
				"Generics/Shutdown",
				"Generics/Screenshot",
				"CasePreferenceIndicator"
		})

public class CasePreferenceIndicator_Runner {

}

