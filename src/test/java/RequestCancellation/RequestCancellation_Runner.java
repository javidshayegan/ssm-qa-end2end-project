package RequestCancellation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {"classpath:features/All_RequestCancellation.feature"},
        glue= {
                "Generics/Screenshot",
                "Generics/Shutdown",
               // "RequestASurgery",
                "RequestCancellation"              
        })
public class RequestCancellation_Runner { 
}
