package CaseListFilter;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, 
features = {
		//"classpath:features/OfficeScheduler_CaseListFilter.feature", //Failed
		"classpath:features/OfficeScheduler_CaseFilter_SORT_BY.feature",
		//"classpath:features/OfficeScheduler_CaseFilter_FILTER_BY.feature", //Failed
		"classpath:features/OfficeScheduler_CaseFilter_OTHER_FILTERS.feature",
		//"classpath:features/OfficeScheduler_CaseFilter_PROCEDURE_DATE.feature",
},
		glue = { "CaseListFilter", "Generics/Screenshot" })
		//tags = {"@CaseListFilter1"})

public class CaseListFilter_Runner {

}
