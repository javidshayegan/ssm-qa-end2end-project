package CasePagination;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {"classpath:features/OfficeScheduler_CasePagination.feature"},
        glue= {"CasePagination", "Generics/Screenshot", "Generics/Shutdown"})
public class CasePagination_Runner {
}
