package TaskEditReview;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {"classpath:features/OfficeScheduler_TaskEditReview.feature"},
        tags = {"@failed"},
        glue= {"TaskEditReview","Generics/Shutdown"})
		
public class TaskEditReview_Runner {

}

