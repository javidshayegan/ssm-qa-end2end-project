package TaskArea;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {"classpath:features/OfficeScheduler_TaskArea.feature"},
        glue= {"Generics/Screenshot", "Generics/Shutdown", "TaskArea"})

public class TaskArea_Runner {

}
