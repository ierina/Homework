package stepdefinitions;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import pages.AssignLeavePage;
import pages.LoginPage;
import pages.PimPage;
import java.util.List;
import java.util.Map;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssignLeaveSteps {
Playwright playwright;
Browser browser;
Page page;
BrowserContext context;
LoginPage loginPage;
AssignLeavePage assignLeavePage;
PimPage pimPage;
private String createdEmployeeFirstName;
private String createdEmployeeLastName;

@Given("user is logged into OrangeHRM with the valid credentials")
public void userLogsIn(DataTable dataTable) {
playwright = Playwright.create();
context = support.BrowserFactory.createEnglishContext(playwright);
page = context.newPage();

loginPage = new LoginPage(page);
assignLeavePage = new AssignLeavePage(page);
pimPage = new PimPage(page);

List<Map<String, String>> credentialsList = dataTable.asMaps();
Map<String, String> credentials = credentialsList.get(0);

loginPage.navigate();
loginPage.login(credentials.get("username"), credentials.get("password"));
}

@And("a new employee is created for the test")
public void createTestEmployee() {
createdEmployeeFirstName = "Auto" + System.currentTimeMillis();
createdEmployeeLastName = "Test";
pimPage.goToEmployeeList();
pimPage.createNewEmployee(createdEmployeeFirstName, createdEmployeeLastName);
System.out.println("Created test employee: " + createdEmployeeFirstName + " " + createdEmployeeLastName);
}

@When("user navigates to the {string} page via Leave > Assign Leave")
public void navigateToAssignLeave(String pageName) {
assignLeavePage.navigateToAssignLeave();
}


@When("user enters the test employee name in the {string} field and selects from the dropdown")
public void enterEmployeeName(String field) {
String fullName = createdEmployeeFirstName + " " + createdEmployeeLastName;
String partial = createdEmployeeFirstName.substring(0, 3);
assignLeavePage.enterEmployeeName(partial, fullName);
}

@When("user selects {string} from the {string} dropdown")
public void selectLeaveType(String type, String dropdownName) {
assignLeavePage.selectLeaveType(type);
}


@When("user select a past date from the date calendar")
public void selectPastDate() {
assignLeavePage.selectPastDate();
}

@When("user presses the {string} button")
public void pressAssign(String buttonName) {
assignLeavePage.clickAssign();
}


@Then("the system prevents the leave from being assigned")
public void leaveNotAssigned() {
boolean blocked = assignLeavePage.isLeaveAssignmentBlocked();

if (!blocked) {
page.screenshot(new Page.ScreenshotOptions()
.setPath(Paths.get("screenshots/leave-assigned-unexpectedly.png"))
.setFullPage(true));
System.out.println("❗ Screenshot saved: leave assigned with past date.");
}

assertTrue(blocked, "❗ Leave assignment unexpectedly passed — possible bug.");
}

@After
public void cleanup() {
if (createdEmployeeFirstName != null) {
pimPage.deleteEmployeeByName(createdEmployeeFirstName);
System.out.println("Deleted test employee: " + createdEmployeeFirstName);
}

if (page != null) page.close();
if (browser != null) browser.close();
if (playwright != null) playwright.close();
}
}
