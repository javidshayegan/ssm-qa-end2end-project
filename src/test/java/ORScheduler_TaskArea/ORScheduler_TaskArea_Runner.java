package ORScheduler_TaskArea;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {"classpath:features/ORScheduler_TaskArea.feature"},
        glue= {
                "Generics/Screenshot",
                "Generics/Shutdown",
                "ORScheduler_TaskArea"
        })

public class ORScheduler_TaskArea_Runner {
}
