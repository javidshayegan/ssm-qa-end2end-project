package requestAsurgery_name_prefilled_edit;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {
				"classpath:features/OfficeScheduler_RequestASurgery_Name_PreFill_Edit.feature",
		},
        glue= {
				"Generics/Screenshot",
				"Generics/Shutdown",
				"requestAsurgery_name_prefilled_edit"},
				 tags = {"@PrefilledName"})
public class RequestASurgery_Name_Prefilled_Edit_Runner {

}

