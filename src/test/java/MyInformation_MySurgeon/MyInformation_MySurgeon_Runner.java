package MyInformation_MySurgeon;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {"classpath:features/All_MyInformationMySurgeon.feature"},
        glue= {"MyInformation_MySurgeon", "Generics/Screenshot", "Generics/Shutdown"})
public class MyInformation_MySurgeon_Runner {

}

