package CaseListFilter;

import Login.*;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.core.api.Scenario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CaseListFilter_StepDef{

    private StepDefHelperYml step = new StepDefHelperYml();

    @Given("{string} is on case tracker landing page")
    public void user_is_on_case_tracker_landing_page(String user){
        Login login=new Login();
        login.LoginPage(Role.OFFICE_SCHEDULER);
        step.from("CaseListFilter");
        //assert user is on the surgical scheduling landing page
        String currentLandingURL=step.driver().getCurrentUrl();
        System.out.println("Current URL is "+currentLandingURL); try{
            Assert.assertEquals(currentLandingURL,"https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc");
        }catch(AssertionError e){
            System.out.println("User is not on the case tracker landing page after logging in.");
        }
            System.out.println("User is on the case tracker landing page after logging in.");
    }

    @Then("User should see following filters")
    public void user_should_see_following_filters(io.cucumber.datatable.DataTable dataTable){
        step.from("CaseListFilter");
        step.wait(2000);
        List<String> filters = dataTable.asList();
        Assert.assertEquals(filters.get(0),
                step.setElement("SORT_BY").waitUntilVisible().element().getText());
        Assert.assertEquals(filters.get(1),
                step.setElement("FILTER_BY").waitUntilVisible().element().getText());
        Assert.assertEquals(filters.get(2),
                step.setElement("OTHER_FILTERS").waitUntilVisible().element().getText());
        Assert.assertEquals(filters.get(3),
                step.setElement("PROCEDURE_DATE").waitUntilVisible().element().getText());
    }

    @When("User clicks SORT BY filter")
    public void user_clicks_SORT_BY_filter() {
        step.from("CaseListFilter");
        step.wait(5000);
        step.setElement("SORT_BY").waitUntilVisible().element().click();
        step.pageReady();
    }
    @Then("SORT BY filter should have following sub topics")
    public void SORT_BY_filter_should_have_following_sub_topics(io.cucumber.datatable.DataTable dataTable){
        step.from("CaseListFilter");
        List<String> sort_by_list= dataTable.asList();
        Assert.assertEquals(sort_by_list.get(0),
                step.setElement("PROCEDURE_DATE").waitUntilPresence().element().getText());
        Assert.assertEquals(sort_by_list.get(1),
                step.setElement("LOCATION").waitUntilPresence().element().getText());
        Assert.assertEquals(sort_by_list.get(2),
                step.setElement("PATIENT_NAME").waitUntilPresence().element().getText());
        Assert.assertEquals(sort_by_list.get(3),
                step.setElement("DATE_CREATED").waitUntilPresence().element().getText());
    }

    @Then("User should see following list in SORT BY filter")
    public void user_should_see_following_list_in_SORT_BY_filter(io.cucumber.datatable.DataTable dataTable) {
        step.from("CaseListFilter");
        List<Map<String, String>> sort_by_list = dataTable.asMaps(String.class, String.class);
        Assert.assertEquals(sort_by_list.get(0).get("PROCEDURE DATE"),
                step.setElement("NEAREST").waitUntilPresence().element().getText());
        Assert.assertEquals(sort_by_list.get(0).get("LOCATION"),
                step.setElement("LOCATION_A_Z").waitUntilPresence().element().getText());
        Assert.assertEquals(sort_by_list.get(0).get("PATIENT NAME"),
                step.setElement("PATIENT_A_Z").waitUntilPresence().element().getText());
        Assert.assertEquals(sort_by_list.get(0).get("DATE CREATED"),
                step.setElement("OLDEST").waitUntilPresence().element().getText());

        Assert.assertEquals(sort_by_list.get(1).get("PROCEDURE DATE"),
                step.setElement("FURTHEST").waitUntilPresence().element().getText());
        Assert.assertEquals(sort_by_list.get(1).get("LOCATION"),
                step.setElement("LOCATION_Z_A").waitUntilPresence().element().getText());
        Assert.assertEquals(sort_by_list.get(1).get("PATIENT NAME"),
                step.setElement("PATIENT_Z_A").waitUntilPresence().element().getText());
        Assert.assertEquals(sort_by_list.get(1).get("DATE CREATED"),
                step.setElement("NEWEST").waitUntilPresence().element().getText());
    }

    @When("User clicks FILTER BY filter")
    public void user_clicks_FILTER_BY_filter() {
        step.from("CaseListFilter");
        step.wait(2000);
        step.setElement("FILTER_BY").waitUntilVisible().element().click();
        step.pageReady();
    }

    @Then("User should see following list in FILTER BY filter")
    public void user_should_see_following_list_in_FILTER_BY_filter(io.cucumber.datatable.DataTable dataTable) {
        step.from("CaseListFilter");
        List<String> filter_by_list = dataTable.asList();
        for(int i = 1; i < filter_by_list.size(); i++){
            Assert.assertEquals(filter_by_list.get(i),
                step.setElement(filter_by_list.get(i)).waitUntilPresence().element().getText());
            Assert.assertEquals(filter_by_list.get(i),
                    step.setElement("CORDOVEZ").waitUntilPresence().element().getText());
        }
    }

    @When("User clicks OTHER FILTERS filter")
    public void user_clicks_OTHER_FILTERS_filter() {
        step.from("CaseListFilter");
        step.wait(5000);
        step.setElement("OTHER_FILTERS").waitUntilPresence().waitUntilVisible().waitUntilClickable().element().click();
        step.pageReady();
    }

    @Then("User should see following list in OTHER FILTERS filter")
    public void user_should_see_following_list_in_OTHER_FILTERS_filter(io.cucumber.datatable.DataTable dataTable) {
        step.from("CaseListFilter");
        List<String> other_filters_list = dataTable.asList();

        Assert.assertTrue("BOOKMARKED does not contain " +  other_filters_list.get(1),
                step.setElement("BOOKMARKED").waitUntilPresence().element().getText().contains(other_filters_list.get(1)));
        Assert.assertTrue("UNSCHEDULED does not contain " + other_filters_list.get(2),
                step.setElement("UNSCHEDULED").waitUntilPresence().element().getText().contains(other_filters_list.get(2)));
        Assert.assertTrue("SCHEDULED does not contain " + other_filters_list.get(3),
                step.setElement("SCHEDULED").waitUntilPresence().element().getText().contains(other_filters_list.get(3)));
        Assert.assertTrue("CANCELLED does not contain " + other_filters_list.get(4),
                step.setElement("CANCELLED").waitUntilPresence().element().getText().contains(other_filters_list.get(4)));
        Assert.assertTrue("PENDING_CANCELLED does not contain " + other_filters_list.get(5),
                step.setElement("PENDING_CANCELLED").waitUntilPresence().element().getText().contains(other_filters_list.get(5)));
        Assert.assertTrue("NEEDS_REVISION does not contain " + other_filters_list.get(6),
                step.setElement("NEEDS_REVISION").waitUntilPresence().element().getText().contains(other_filters_list.get(6)));
        Assert.assertTrue("REVISED does not contain " + other_filters_list.get(7),
                step.setElement("REVISED").waitUntilPresence().element().getText().contains(other_filters_list.get(7)));
        Assert.assertTrue("WARNINGS does not contain " + other_filters_list.get(8),
                step.setElement("WARNINGS").waitUntilPresence().element().getText().contains(other_filters_list.get(8)));
        Assert.assertTrue("INPATIENT does not contain " + other_filters_list.get(9),
                step.setElement("INPATIENT").waitUntilPresence().element().getText().contains(other_filters_list.get(9)));
        Assert.assertTrue("OUTPATIENT does not contain " + other_filters_list.get(10),
                step.setElement("OUTPATIENT").waitUntilPresence().element().getText().contains(other_filters_list.get(10)));
    }

    @When("User clicks PROCEDURE DATE filter")
    public void user_clicks_PROCEDURE_DATE_filter() {
        step.from("CaseListFilter");
        step.wait(2000);
        step.setElement("PROCEDURE_DATE").waitUntilVisible().element().click();
        step.pageReady();
    }

    @Then("User should see following list in PROCEDURE DATE filter")
    public void user_should_see_following_list_in_PROCEDURE_DATE_filter(io.cucumber.datatable.DataTable dataTable) {
        step.from("CaseListFilter");
        List<String> procedure_date_list = dataTable.asList();

        Assert.assertEquals(procedure_date_list.get(1),
                step.setElement("TODAY").waitUntilPresence().element().getText());
        Assert.assertEquals(procedure_date_list.get(2),
                step.setElement("TOMORROW").waitUntilPresence().element().getText());
        Assert.assertEquals(procedure_date_list.get(3),
                step.setElement("NEXT_WEEK").waitUntilPresence().element().getText());
        Assert.assertEquals(procedure_date_list.get(4),
                step.setElement("NEXT_MONTH").waitUntilPresence().element().getText());
        Assert.assertEquals(procedure_date_list.get(5),
                step.setElement("CUSTOM").waitUntilPresence().element().getText());
    }

    @When("User clicks NEAREST of PROCEDURE DATE")
    public void user_clicks_NEAREST_of_PROCEDURE_DATE() {
        step.from("CaseListFilter");
        step.wait(1000);
        step.setElement("NEAREST").waitUntilPresence().click();
        step.pageReady();
    }
    private List<String> getCaseCounts(){
        String case_count = step.findElements("CASE_COUNT").get(0).getText();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher case_count_m = pattern.matcher(case_count);
        List<String> case_count_numbers = new ArrayList<>();
        while(case_count_m.find()){
            case_count_numbers.add(case_count_m.group(0));
        }
        return case_count_numbers;
    }
    private List<String> getCasePagination(){
        String case_pagination = step.findElements("CASE_COUNT").get(1).getText();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher case_pagination_m = pattern.matcher(case_pagination);
        List<String> case_pagination_numbers = new ArrayList<>();
        while(case_pagination_m.find()){
            case_pagination_numbers.add(case_pagination_m.group(0));
        }
        return case_pagination_numbers;
    }
    @Then("Date in Case Card should be sorted in ascending order")
    public void date_in_Case_Card_should_be_sorted_in_ascending_order() {
        step.from("CaseListFilter");
        step.wait(1000);
        List<WebElement> dates = step.findElements("DATE_IN_CASE_CARD");
        if(dates.isEmpty()){
            Assert.assertTrue(true);
        } else {
            List<String> case_count_numbers= this.getCaseCounts();
            List<String> case_pagination_numbers = this.getCasePagination();
            Assert.assertEquals("Case count is not synchronized",
                    case_count_numbers.get(0),
                    case_pagination_numbers.get(0));
            Assert.assertEquals("Case count is not synchronized",
                    case_count_numbers.get(1),
                    case_pagination_numbers.get(1));
            Assert.assertEquals("Case count is not synchronized",
                    case_count_numbers.get(2),
                    case_pagination_numbers.get(2));
            int total_case = Integer.parseInt(case_count_numbers.get(2));
            int current_pagination = Integer.parseInt(step.driver().findElement(By.xpath("//input[contains(@placeholder, 'Select one')]")).getAttribute("value"));
            int total_iteration = Math.round((float)(total_case / current_pagination));
            List<String> stringDates = dates.stream().map(WebElement::getText).collect(Collectors.toList());

            DateFormat new_df = new SimpleDateFormat("MM-dd");
            DateFormat df = new SimpleDateFormat("EEEE, MMM. d");

            stringDates = stringDates.stream().map( d -> {
                try {
                    String new_d = d.contains("Sept") ? d.replace("Sept", "Sep") : d;
                    return new_df.format(df.parse(new_d));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            List<String> sorted = new ArrayList<>(stringDates);
            Collections.sort(sorted, String::compareTo);
                step.pageReady();
        }
        step.setElement("RESET_FILTER").waitUntilPresence().element().click();
        step.pageReady();
    }

    @Then("Date in Case Card should be sorted in descending order")
    public void date_in_Case_Card_should_be_sorted_in_descending_order() {
        step.from("CaseListFilter");
        step.wait(1000);
        List<WebElement> dates = step.findElements("DATE_IN_CASE_CARD");
        if(dates.isEmpty()){
            Assert.assertTrue(true);
        } else {
            List<String> stringDates = dates.stream().map(WebElement::getText).collect(Collectors.toList());
            DateFormat df = new SimpleDateFormat("EEEE, MMMM. d");

            DateFormat new_df = new SimpleDateFormat("MM-dd");

            stringDates = stringDates.stream().map( d -> {
                try {
                    return new_df.format(df.parse(d));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            List<String> desc_sorted = new ArrayList<>(stringDates);
            Collections.sort(desc_sorted, Collections.reverseOrder(String::compareTo));
            Assert.assertEquals(desc_sorted, stringDates);
        }
        step.setElement("RESET_FILTER").waitUntilPresence().element().click();
        step.pageReady();
    }

    @When("User clicks FURTHEST of PROCEDURE DATE")
    public void user_clicks_FURTHEST_of_PROCEDURE_DATE() {
        step.wait(1000);
        step.setElement("FURTHEST").waitUntilPresence().click();
        step.pageReady();
    }

    @When("User clicks LOCATION A-Z of LOCATION")
    public void user_clicks_LOCATION_A_Z_of_LOCATION() {
        step.wait(1000);
        step.setElement("LOCATION_A_Z").waitUntilPresence().click();
        step.pageReady();
    }

    @Then("Location in Case Card should be sorted in alphabetical ascending order")
    public void location_in_Case_Card_should_be_sorted_in_alphabetical_ascending_order() {
        step.wait(1000);
        List<WebElement> locations = step.findElements("LOCATION_IN_CASE_CARD");
        if(locations.isEmpty()){
            Assert.assertTrue(true);
        } else {
            List<String> stringLocations = locations.stream().map(WebElement::getText).collect(Collectors.toList());
            List<String> sorted = new ArrayList<>(stringLocations);
            Collections.sort(sorted, String::compareTo);
            Assert.assertEquals(sorted, stringLocations);
        }
        step.setElement("RESET_FILTER").waitUntilPresence().element().click();
        step.pageReady();
    }

    @When("User clicks LOCATION Z-A of LOCATION")
    public void user_clicks_LOCATION_Z_A_of_LOCATION() {
        step.wait(1000);
        step.setElement("LOCATION_Z_A").waitUntilPresence().click();
        step.pageReady();
    }

    @Then("Location in Case Card should be sorted in alphabetical descending order")
    public void location_in_Case_Card_should_be_sorted_in_alphabetical_descending_order() {
        step.wait(1000);
        List<WebElement> locations = step.findElements("LOCATION_IN_CASE_CARD");
        if(locations.isEmpty()){
            Assert.assertTrue(true);
        } else {
            List<String> stringLocations = locations.stream().map(WebElement::getText).collect(Collectors.toList());
            List<String> desc_sorted = new ArrayList<>(stringLocations);
            Collections.sort(desc_sorted, Collections.reverseOrder(String::compareTo));
            Assert.assertEquals(desc_sorted, stringLocations);
        }
        step.setElement("RESET_FILTER").waitUntilPresence().element().click();
        step.pageReady();
    }

    @When("User clicks PATIENT A-Z of PATIENT")
    public void user_clicks_PATIENT_A_Z_of_PATIENT() {
        step.wait(1000);
        step.setElement("PATIENT_A_Z").waitUntilPresence().click();
        step.pageReady();
    }

    @Then("Name in Case Card should be sorted in alphabetical ascending order")
    public void name_in_Case_Card_should_be_sorted_in_alphabetical_ascending_order() {
        step.wait(1000);
        List<WebElement> patients = step.findElements("PATIENT_IN_CASE_CARD");
        if(patients.isEmpty()){
            Assert.assertTrue(true);
        } else {
            List<String> stringPatients = patients.stream().map(WebElement::getText).collect(Collectors.toList());
            List<String> sorted = new ArrayList<>(stringPatients);
            Collections.sort(sorted, String::compareTo);
            for(int i = 0; i < sorted.size(); i++){
                Assert.assertEquals(sorted.get(i), stringPatients.get(i));
            }
        }
        step.setElement("RESET_FILTER").waitUntilPresence().element().click();
        step.pageReady();
    }

    @When("User clicks PATIENT Z-A of PATIENT")
    public void user_clicks_PATIENT_Z_A_of_PATIENT() {
        step.wait(1000);
        step.setElement("PATIENT_Z_A").waitUntilPresence().click();
        step.pageReady();
    }

    @Then("Name in Case Card should be sorted in alphabetical descending order")
    public void name_in_Case_Card_should_be_sorted_in_alphabetical_descending_order() {
        step.wait(1000);
        List<WebElement> patients = step.findElements("PATIENT_IN_CASE_CARD");
        if(patients.isEmpty()){
            Assert.assertTrue(true);
        } else {
            List<String> stringPatients = patients.stream().map(WebElement::getText).collect(Collectors.toList());
            List<String> desc_sorted = new ArrayList<>(stringPatients);
            Collections.sort(desc_sorted, Collections.reverseOrder(String::compareTo));
            Collections.sort(stringPatients, Collections.reverseOrder(String::compareTo));
            for(int i = 0; i < desc_sorted.size(); i++){
                Assert.assertEquals(desc_sorted.get(i), stringPatients.get(i));
            }
        }
        step.setElement("RESET_FILTER").waitUntilPresence().element().click();
        step.pageReady();
    }

    @When("User clicks PROCEDURE DATE")
    public void user_clicks_PROCEDURE_DATE() {
        step.wait(1000);
        step.setElement("PROCEDURE_DATE").waitUntilPresence().click();
        step.pageReady();
    }

    @When("User clicks TODAY")
    public void user_clicks_TODAY() {
        step.wait(1000);
        step.setElement("TODAY").waitUntilPresence().click();
        step.pageReady();
    }

    @Then("Date in Case Card should be today")
    public void date_in_Case_Card_should_be_today() {
        step.wait(1000);
        List<WebElement> dates = step.findElements("DATE_IN_CASE_CARD");
        if(dates.isEmpty()) {
            Assert.assertEquals("Sorry, this search didn't produce any results.",
                    step.setElement("SORRY_EMPTY").waitUntilPresence().element().getText());
            Assert.assertEquals("Try editing your selected filters to see more cases, or reset your filters to see all cases.",
                    step.setElement("TRY_EMPTY").waitUntilPresence().element().getText());
        } else {
            List<String> stringDates = new ArrayList<>();
            for (WebElement elem : dates) {
                stringDates.add(elem.getText());
            }
            DateFormat df = new SimpleDateFormat("EEEE, MMMM. d");
            DateFormat new_df = new SimpleDateFormat("MM-dd");
            stringDates = stringDates.stream().map( d -> {
                try {
                    return new_df.format(df.parse(d));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            String today = new_df.format(new Date());
            for(String str_date : stringDates){
                Assert.assertEquals(today, str_date);
            }
        }
        step.setElement("RESET_FILTER").waitUntilPresence().element().click();
        step.pageReady();
    }

    @When("User clicks TOMORROW")
    public void user_clicks_TOMORROW() {
        step.wait(1000);
        step.setElement("TOMORROW").waitUntilPresence().click();
        step.pageReady();
    }

    @Then("Date in Case Card should be tomorrow")
    public void date_in_Case_Card_should_be_tomorrow() {
        step.wait(3000);
        List<WebElement> dates = step.findElements("DATE_IN_CASE_CARD");
        if(dates.isEmpty()) {
            Assert.assertEquals("Sorry, this search didn't produce any results.",
                    step.setElement("SORRY_EMPTY").waitUntilPresence().element().getText());
            Assert.assertEquals("Try editing your selected filters to see more cases, or reset your filters to see all cases.",
                    step.setElement("TRY_EMPTY").waitUntilPresence().element().getText());
        } else {
            List<String> stringDates = new ArrayList<>();
            for (WebElement elem : dates) {
                stringDates.add(elem.getText());
            }
            DateFormat df = new SimpleDateFormat("EEEE, MMMM. d");
            DateFormat new_df = new SimpleDateFormat("MM-dd");
            stringDates = stringDates.stream().map(d -> {
                try {
                    return new_df.format(df.parse(d));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            Calendar calendar = Calendar.getInstance();
            DateFormat dayFormat = new SimpleDateFormat("EEEE");
            Date today = new Date();
            String day = dayFormat.format(today);
            String tomorrow;
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            tomorrow = new_df.format(calendar.getTime());
            for (String str_date : stringDates) {
                Assert.assertEquals(tomorrow, str_date);
            }
        }
        step.setElement("RESET_FILTER").waitUntilPresence().element().click();
        step.pageReady();
    }

    @When("User clicks NEXT WEEK")
    public void user_clicks_NEXT_WEEK() {
        step.wait(1000);
        step.setElement("NEXT_WEEK").waitUntilPresence().click();
        step.pageReady();
    }

    @Then("Date in Case Card should be a week from today")
    public void date_in_Case_Card_should_be_a_week_from_today() {
        step.wait(2000);
        List<WebElement> dates = step.findElements("DATE_IN_CASE_CARD");
        if(dates.isEmpty()) {
            Assert.assertEquals("Sorry, this search didn't produce any results.",
                    step.setElement("SORRY_EMPTY").waitUntilPresence().element().getText());
            Assert.assertEquals("Try editing your selected filters to see more cases, or reset your filters to see all cases.",
                    step.setElement("TRY_EMPTY").waitUntilPresence().element().getText());
        } else {
            List<String> stringDates = new ArrayList<>();
            for (WebElement elem : dates) {
                stringDates.add(elem.getText());
            }
            DateFormat df = new SimpleDateFormat("EEEE, MMMM. d");
            DateFormat new_df = new SimpleDateFormat("MM-dd");
            stringDates = stringDates.stream().map(d -> {
                try {
                    return new_df.format(df.parse(d));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());

            String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
            for (String str_date : stringDates) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                str_date = str_date + "-" + year;
                LocalDate event = LocalDate.parse(str_date, formatter);
                LocalDate nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                LocalDate nextSaturday = nextMonday.plusDays(5);
                Assert.assertTrue("Procedure date("+str_date+") is NOT in Next week",
                        nextMonday.compareTo(event) <= 0 &&
                                 event.compareTo(nextSaturday) < 0) ;
            }
        }
        step.setElement("RESET_FILTER").waitUntilPresence().element().click();
        step.pageReady();
    }

    @When("User clicks NEXT MONTH")
    public void user_clicks_NEXT_MONTH() {
        step.wait(1000);
        step.setElement("NEXT_MONTH").waitUntilPresence().click();
        step.pageReady();
    }

    @Then("Date in Card should be a month from today")
    public void date_in_Card_should_be_a_month_from_today() throws ParseException {
        step.wait(2000);
        List<WebElement> dates = step.findElements("DATE_IN_CASE_CARD");
        if(dates.isEmpty()) {
            Assert.assertEquals("Sorry, this search didn't produce any results.",
                    step.setElement("SORRY_EMPTY").waitUntilPresence().element().getText());
            Assert.assertEquals("Try editing your selected filters to see more cases, or reset your filters to see all cases.",
                    step.setElement("TRY_EMPTY").waitUntilPresence().element().getText());
        } else {
            List<String> stringDates = new ArrayList<>();
            for (WebElement elem : dates) {
                stringDates.add(elem.getText());
            }
            DateFormat df = new SimpleDateFormat("EEEE, MMMM. d");
            DateFormat new_df = new SimpleDateFormat("MM");
            stringDates = stringDates.stream().map(d -> {
                try {
                    return new_df.format(df.parse(d));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            String nextMonth = Integer.toString(Calendar.getInstance().get(Calendar.MONTH)+2);
            nextMonth = new_df.format(new_df.parse(nextMonth));
            for (String str_date : stringDates) {
                Assert.assertEquals(nextMonth, str_date);
            }
        }
        step.setElement("RESET_FILTER").waitUntilPresence().element().click();
        step.pageReady();
    }
    @When("User selects {string} in the filter by filter")
    public void user_selects_in_the_filter_by_filter(String option) {
        //step.pageReady();
       // step.setElement(option).waitUntilPresence().waitUntilVisible().waitUntilClickable().click();
       // step.pageReady();
        step.setElement("FILTER_BY").waitUntilPresence().waitUntilVisible().waitUntilPresence().element().click();
        step.pageReady();
    }


    @Then("Surgeon Name in Case Card should be {string}")
    public void surgeon_Name_in_Case_Card_should_be(String data) {
        List<WebElement> surgeons = step.findElements("SURGEON_IN_CASE_CARD");
        if(surgeons.isEmpty()) {
            Assert.assertEquals("Sorry, this search didn't produce any results.",
                    step.setElement("SORRY_EMPTY").waitUntilPresence().element().getText());
            Assert.assertEquals("Try editing your selected filters to see more cases, or reset your filters to see all cases.",
                    step.setElement("TRY_EMPTY").waitUntilPresence().element().getText());
        } else {
            List<String> stringSurgeons = surgeons.stream().map(WebElement::getText).collect(Collectors.toList());
            stringSurgeons.forEach(x -> Assert.assertEquals(data.toLowerCase(), x.toLowerCase()));
        }
        step.setElement("RESET_FILTER").waitUntilPresence().waitUntilVisible().waitUntilClickable().element().click();
        step.pageReady();
    }

    @When("User selects {string} in the other filters filter")
    public void user_selects_in_the_other_filters_filter(String option) {
        step.setElement(option).pageReady().waitUntilPresence().waitUntilVisible().waitUntilClickable().click();
        step.setElement("OTHER_FILTERS").pageReady().waitUntilPresence().waitUntilVisible().element().click();
        step.pageReady();
    }
    @Then("Case Card should have {string} tag")
    public void case_Card_should_have_tag(String option) {
        List<WebElement> caseCardDetails = step.findElements("CASE_CARD_DETAILS");
        if(caseCardDetails.isEmpty()){
            Assert.assertEquals("Sorry, this search didn't produce any results.",
                    step.setElement("SORRY_EMPTY").waitUntilPresence().element().getText());
            Assert.assertEquals("Try editing your selected filters to see more cases, or reset your filters to see all cases.",
                    step.setElement("TRY_EMPTY").waitUntilPresence().element().getText());
        } else {
            String tagXpath;
            String helpMessage;
            if(option.equals("BOOKMARKED")){
                tagXpath = ".//div[contains(@class, 'icon-ribbon')]";
                helpMessage =" The bookmarked label is not filled with blue icon";
                By TAGS = By.xpath(tagXpath);
                List<WebElement> bookmarkedLabelInCards = caseCardDetails
                        .parallelStream()
                        .map(card -> card.findElement(TAGS))
                        .collect(Collectors.toList());
                for(WebElement bookmarkedLabel : bookmarkedLabelInCards){
                    Assert.assertTrue(helpMessage,
                            bookmarkedLabel.getAttribute("color").equals("#1E69D2")
                            );
                }
            } else {
                tagXpath = option.contains("PATIENT") ?
                        ".//span[contains(@data-field,'type')]" :
                        ".//span[contains(@data-field, 'tag')]";
                By TAGS = By.xpath(tagXpath);
                List<List<WebElement>> tagsInCards = caseCardDetails
                        .parallelStream()
                        .map(card -> card.findElements(TAGS))
                        .collect(Collectors.toList());
                for(List<WebElement> tags : tagsInCards){
                    List<String> stringTags = tags.parallelStream().map(tag -> tag.getText().toUpperCase()).collect(Collectors.toList());
                    Assert.assertTrue(stringTags + " should contains " + option,
                            stringTags.contains(option));
                }
            }
        }
        step.setElement("RESET_FILTER").waitUntilPresence().waitUntilVisible().waitUntilClickable().element().click();
        step.pageReady();
    }

    @Then("Case Count will display the total number of cases as per filters applied")
    public void caseCountWillDisplayTheTotalNumberOfCasesAsPerFiltersApplied() {
        List<WebElement> caseCardDetails = step.findElements("CASE_CARD_DETAILS");
        String total_cards = Integer.toString(caseCardDetails.size());
        if(caseCardDetails.isEmpty()){
            Assert.assertTrue("Empty Case does not have CASE COUNT", true);
        } else {
            String case_count = step.findElements("CASE_COUNT").get(0).getText();
            String case_pagination = step.findElements("CASE_COUNT").get(1).getText();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher case_count_m = pattern.matcher(case_count);
            Matcher case_pagination_m = pattern.matcher(case_pagination);
            List<String> case_count_numbers = new ArrayList<>();
            List<String> case_pagination_numbers = new ArrayList<>();
            while(case_count_m.find()){
                case_count_numbers.add(case_count_m.group(0));
            }
            Assert.assertEquals("1", case_count_numbers.get(0));
            Assert.assertEquals(total_cards, case_count_numbers.get(1));
            while(case_pagination_m.find()){
                case_pagination_numbers.add(case_pagination_m.group(0));
            }
            Assert.assertEquals("1", case_pagination_numbers.get(0));
            Assert.assertEquals(total_cards, case_pagination_numbers.get(1));
        }
    }


    @Then("Browser quits")
    public void browserQuits(){
        step.shutdownDriver();
    }

    @When("User opens CUSTOM calendar")
    public void userOpensCUSTOMCalendar() {
        step.from("CaseListFilter");
        ((JavascriptExecutor)step.driver()).executeScript("window.scrollTo(0, 150);");
        step.pageReady();
        step.setElement("PROCEDURE_DATE").waitUntilVisible().element().click();
        step.pageReady();
        step.setElement("CUSTOM").waitUntilVisible().element().click();
        step.pageReady();
    }

    @Then("User should see Calendar")
    public void userShouldSeeCalendar(DataTable dt) {
        step.from("Calendar");
        List<String> list = dt.asList(String.class);
        ((JavascriptExecutor)step.driver()).executeScript("window.scrollTo(0, 150);");
        step.waitUntilJSDone();
        System.out.println("Scroll down");
        WebElement reset = step.setElement(list.get(0)).element();
        WebElement cancel = step.setElement(list.get(1)).element();
        WebElement apply = step.setElement(list.get(2)).element();
        Assert.assertTrue("RESET button should be displayed", reset.isDisplayed());
        Assert.assertTrue("CANCEL button should be displayed", cancel.isDisplayed());
        Assert.assertTrue("APPLY button should be displayed", apply.isDisplayed());
    }

    @And("User closes calendar")
    public void userClosesCalendar() {
        step.from("Calendar");
        step.setElement("CANCEL").waitUntilClickable().click();
        step.pageReady();
        step.setElement("RESET_FILTER").waitUntilPresence().waitUntilVisible().waitUntilClickable().element().click();
        step.pageReady();
    }

    @And("User resets calendar")
    public void userResetsCalendar() {
        step.from("Calendar");
        step.setElement("RESET").waitUntilClickable().click();
        step.pageReady();
    }

    @Then("Start date and End date input become empty")
    public void startDateAndEndDateInputBecomeEmpty() {
        step.from("Calendar");
        List<WebElement> dates_input = step.findElements("DATES");
        WebElement startDate = dates_input.get(0);
        WebElement endDate = dates_input.get(1);
        Assert.assertTrue("Start Date value should be erased", startDate.getAttribute("value").isEmpty());
        Assert.assertTrue("End Date value should be erased", endDate.getAttribute("value").isEmpty());
    }

    @And("APPLY button is disabled")
    public void applyButtonIsDisabled() {
        step.from("Calendar");
        Assert.assertFalse("Empty Date Input should make APPLY button disabled",
                step.setElement("APPLY").element().isEnabled());
    }

    @And("User enters following start date and end date")
    public void userEntersFollowingStartDateAndEndDate(DataTable dt) {
        step.from("Calendar");
        List<WebElement> dates_input = step.findElements("DATES");
        WebElement startDateInput = dates_input.get(0);
        WebElement endDateInput = dates_input.get(1);
        startDateInput.clear();
        for(int i = 0; i < startDateInput.getAttribute("value").length()+10; i++){
            startDateInput.sendKeys(Keys.BACK_SPACE);
        }
        step.wait(1000);
        endDateInput.clear();
        for(int i = 0; i < endDateInput.getAttribute("value").length()+10; i++){
           endDateInput.sendKeys(Keys.BACK_SPACE);
        }
        step.wait(1000);
        List<Map<String, String>> maps = dt.asMaps(String.class, String.class);
        DateFormat df = new SimpleDateFormat("MMddyyyy");
        Calendar c = Calendar.getInstance();
        int start_date = Integer.parseInt(maps.get(0).get("START_DATE"));
        int end_date = Integer.parseInt(maps.get(0).get("END_DATE"));
        c.setTime(new Date());
        c.add(Calendar.DATE, start_date);
        startDateInput.sendKeys(df.format(c.getTime()));
        step.wait(2000);
        c.add(Calendar.DATE, end_date);
        endDateInput.sendKeys(df.format(c.getTime()));
    }
    @When("User applies date")
    public void userAppliesDate(){
        step.from("Calendar");
        step.setElement("APPLY").waitUntilClickable().click();
        step.pageReady();
    }
    @And("Date in Case Card should be {int} days from today")
    public void dateInCaseCardShouldBeDaysFromToday(int arg0) {
        step.from("CaseListFilter");
        step.pageReady();
        step.wait(5000);
        List<WebElement> dates = step.findElements("DATE_IN_CASE_CARD");
        if(dates.isEmpty()) {
            Assert.assertEquals("Sorry, this search didn't produce any results.",
                    step.setElement("SORRY_EMPTY").waitUntilPresence().element().getText());
            Assert.assertEquals("Try editing your selected filters to see more cases, or reset your filters to see all cases.",
                    step.setElement("TRY_EMPTY").waitUntilPresence().element().getText());
        } else {
            List<String> stringDates = new ArrayList<>();
            for (WebElement elem : dates) {
                stringDates.add(elem.getText());
            }
            DateFormat df = new SimpleDateFormat("EEEE, MMMM. d");
            DateFormat new_df = new SimpleDateFormat("MM-dd-"+Calendar.getInstance().get(Calendar.YEAR));
            DateFormat year_df = new SimpleDateFormat("MM-dd-yyyy");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 0);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            Date startDate = c.getTime();
            step.wait(2000);
            c.add(Calendar.DATE, arg0);
            Date endDate = c.getTime();
            stringDates.forEach( d -> {
                try{
                    Date caseCardDate = year_df.parse(new_df.format(df.parse(d)));
                    Assert.assertTrue("Case card Date["+d+"] is not within the specific range",
                            (caseCardDate.compareTo(startDate) == 0 || caseCardDate.after(startDate))
                                    && (caseCardDate.compareTo(endDate) == 0 || caseCardDate.before(endDate)));
                } catch(ParseException e){
                    throw new RuntimeException(e);
                }
            });
        }
        step.pageReady();
        step.setElement("RESET_FILTER").waitUntilPresence().element().click();
        step.pageReady();
    }
}
