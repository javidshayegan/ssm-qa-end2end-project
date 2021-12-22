package OfficeScheduler_Edit_MyTemplate;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = { "classpath:features/OfficeScheduler_Edit_MyTemplate.feature"},

        glue= {
        		"OfficeScheduler_Edit_MyTemplate",
        		"Generics/Screenshot"	
        },
        tags = {"@Edit"})
public class MyTemplate_Edit_Runner {
}
