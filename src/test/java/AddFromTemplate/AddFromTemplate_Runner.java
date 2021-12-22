package AddFromTemplate;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {
				"classpath:features/OfficeScheduler_AddFromTemplate.feature",
		},
        glue= {
				"Generics/Screenshot",
				"Generics/Shutdown",
				"AddFromTemplate"})
		
		//tags = {"@AddTestF1"})

public class AddFromTemplate_Runner {

}
