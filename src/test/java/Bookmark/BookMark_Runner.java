package Bookmark;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features= {
                "classpath:features/OfficeScheduler_Bookmark.feature",
                "classpath:features/ORScheduler_Bookmark.feature",
        },
        glue= {"Bookmark", "Generics/Screenshot", "Generics/Shutdown"})
public class BookMark_Runner {
}
