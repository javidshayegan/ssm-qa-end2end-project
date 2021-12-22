package OfficeScheduler_MyTemplate_Create;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = {"classpath:features/OfficeScheduler_MyTemplate_Create.feature"},
        glue= { "OfficeScheduler_MyTemplate_Create",
        		"Generics/Screenshot"
        },
        tags = {"@Create"})
public class MyTemplate_Create_Runner {
}
