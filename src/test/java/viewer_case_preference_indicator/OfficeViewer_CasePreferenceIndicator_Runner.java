package viewer_case_preference_indicator;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {"classpath:features/OfficeViewer_CasePreferenceIndicator.feature"},
        glue= {
				"Generics/Shutdown",
				"Generics/Screenshot",
				"viewer_case_preference_indicator"
		})

public class OfficeViewer_CasePreferenceIndicator_Runner {

}

