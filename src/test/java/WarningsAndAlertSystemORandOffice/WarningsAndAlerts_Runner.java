package WarningsAndAlertSystemORandOffice;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {"classpath:features/WarningsAndAlertSystem.feature"},
        tags = ("@Warningsfailed"),
        glue= {"WarningsAndAlertSystemORandOffice", "Generics/Screenshot", "Generics/Shutdown"})
public class WarningsAndAlerts_Runner {

	
}

