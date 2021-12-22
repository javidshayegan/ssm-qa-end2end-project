package OfficeScheduler_MyFilterPage;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {"classpath:features/OfficeScheduler_MyFilterPage.feature",
        },
        glue = { "OfficeScheduler_MyFilterPage", "Generics/Screenshot" })

//tags = {"@AddTestF1"})

public class OfficeScheduler_MyFilterPage_Runner {

}
