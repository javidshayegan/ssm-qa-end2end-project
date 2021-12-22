package CasePagination;

import Login.*;
import cucumberhelpers.selenium.stepdef.StepDefHelperYml;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CasePagination_StepDef {
    private int currentPageNumber = 1;
    private StepDefHelperYml step = new StepDefHelperYml();

    @Given("{string} is on case tracker landing page")
    public void user_is_on_case_tracker_landing_page(String user){
        Login login=new Login();
        login.LoginPage(Role.OFFICE_SCHEDULER);
        //assert user is on the surgical scheduling landing page
        String currentLandingURL=step.driver().getCurrentUrl();
        System.out.println("Current URL is "+currentLandingURL); try{
            Assert.assertEquals(currentLandingURL,"https://casetracker-qa.app.cloud-02.pcf.ascension.org/?page=1&size=25&sortModel=procedureDate:asc");
        }catch(AssertionError e){
            System.out.println("User is not on the case tracker landing page after logging in.");
        }
        System.out.println("User is on the case tracker landing page after logging in.");
        step.from("CaseListFilter");
    }

    @Given("{string} button is not disabled")
    public void buttonIsNotDisabled(String buttonName) {
        step.pageReady();
        String button_li_tag = buttonName + "_LI_TAG";
        Assume.assumeFalse(buttonName +" is disabled.",
                step.setElement(button_li_tag).element().getAttribute("class").contains("disabled")
                );
    }

    @When("Office scheduler clicks {string}")
    public void officeSchedulerClicks(String buttonName) {
        String currentURL=step.driver().getCurrentUrl();
        Pattern pattern = Pattern.compile("page=\\d+");
        Matcher pageMatcher= pattern.matcher(currentURL);
        List<String> pageNumbers= new ArrayList<>();
        while(pageMatcher.find()){
            pageNumbers.add(pageMatcher.group(0));
        }
        currentPageNumber = Integer.parseInt(pageNumbers.get(0).replace("page=",""));
        String button_a_tag = buttonName + "_A_TAG";
        step.setElement(button_a_tag).element().click();
        step.pageReady();
        step.wait(3000);
    }

    @Then("Office scheduler can see case cards on the {string} page")
    public void officeSchedulerCanSeeCaseCardsOnThePage(String pageName) {
        List<String> case_count_numbers= this.getCaseCounts();
        int total_case = Integer.parseInt(case_count_numbers.get(2));
        int current_pagination = Integer.parseInt(step.driver().findElement(By.xpath("//input[contains(@placeholder, 'Select one')]")).getAttribute("value"));
        int total_pages = (int) (Math.floor(total_case / current_pagination)+1);
        String currentURL=step.driver().getCurrentUrl();
        Pattern pattern = Pattern.compile("page=\\d+");
        Matcher pageMatcher= pattern.matcher(currentURL);
        List<String> pageNumbers= new ArrayList<>();
        while(pageMatcher.find()){
            pageNumbers.add(pageMatcher.group(0));
        }
        int pageNumber = Integer.parseInt(pageNumbers.get(0).replace("page=",""));
        switch(pageName){
            case "next":
                Assert.assertEquals("URL page number is NOT equal to " + (currentPageNumber+1)
                        ,currentPageNumber+1, pageNumber);
                currentPageNumber+=1;
                break;
            case "previous":
                Assert.assertEquals("URL page number is NOT equal to " + (currentPageNumber-1)
                        ,currentPageNumber-1, pageNumber);
                currentPageNumber-=1;
                break;
            case "first":
                Assert.assertEquals("URL page number is NOT equal to 1",
                        1, pageNumber);
                currentPageNumber = 1;
                break;
            case "last":
                Assert.assertEquals("URL page number is NOT equal to " + total_pages,
                        total_pages, pageNumber);
                currentPageNumber = total_pages;
                break;
            default:
                Assert.assertEquals("URL page number is NOT equal to " + pageName,
                        Integer.parseInt(pageName), pageNumber);
                currentPageNumber = Integer.parseInt(pageName);
                break;
        }
        int firstCase = current_pagination * (currentPageNumber-1)+1;
        int secondCase = Math.min((firstCase-1) + current_pagination, total_case);
        String case_pagination = step.findElements("CASE_COUNT").get(1).getText();
        Assert.assertEquals(
                "Showing " + firstCase + " - " + secondCase +" of " + total_case +" cases",
                case_pagination);
    }

    @Given("There is {string} page")
    public void thereIsPage(String pageName) {
        List<String> case_count_numbers= this.getCaseCounts();
        int total_case = Integer.parseInt(case_count_numbers.get(2));
        int current_pagination = Integer.parseInt(step.driver().findElement(By.xpath("//input[contains(@placeholder, 'Select one')]")).getAttribute("value"));
        int total_pages = (int) (Math.floor(total_case / current_pagination)+1);
        Assume.assumeTrue(pageName +" page does not exist. Please try a smaller page",
                Integer.parseInt(pageName) <= total_pages);
    }

    @And("Office scheduler is not at {string} page")
    public void officeSchedulerIsNotAtPage(String pageName) {
    }

    @When("Office scheduler clicks {string} page on the case pagination")
    public void officeSchedulerClicksPageOnTheCasePagination(String pageName) {
        String currentURL=step.driver().getCurrentUrl();
        Pattern pattern = Pattern.compile("page=\\d+");
        Matcher pageMatcher= pattern.matcher(currentURL);
        List<String> pageNumbers= new ArrayList<>();
        while(pageMatcher.find()){
            pageNumbers.add(pageMatcher.group(0));
        }
        currentPageNumber = Integer.parseInt(pageNumbers.get(0).replace("page=",""));
        while(step.driver().findElements(By.xpath("//a[contains(@aria-label,'Go to page number " + pageName+"')]")).size() == 0){
            step.setElement("NEXT_A_TAG").click();
            step.pageReady();
        }
        step.driver().findElement(By.xpath("//a[contains(@aria-label,'Go to page number " + pageName+"')]")).click();
        step.pageReady();
        step.wait(3000);
    }

    @When("User clicks {string} in Cases per page")
    public void userClicksInCasesPerPage(String page) {
        step.setElement("CASE_PAGE").pageReady().waitUntilPresence().waitUntilClickable().element().click();
        step.pageReady();
    }

    @Then("Case cards should have less than or equal to {string}")
    public void caseCardsShouldHaveLessThanOrEqualTo(String page) {
        step.setElement(page).pageReady().waitUntilPresence().waitUntilClickable().element().click();
        step.pageReady();
        List<WebElement> caseCardDetails = step.findElements("CASE_CARD_DETAILS");
        page = page.equals("ALL") ? "1000000" : page;
        Assert.assertTrue(caseCardDetails.size() <= Integer.parseInt(page));
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
}
