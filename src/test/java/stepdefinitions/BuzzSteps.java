package stepdefinitions;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pages.LoginPage;
import pages.BuzzPage;
import support.BrowserFactory;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuzzSteps {
private final Page page;
private final LoginPage loginPage;
private final BuzzPage buzzPage;

public BuzzSteps() {
Playwright playwright = Playwright.create();
BrowserContext context = BrowserFactory.createEnglishContext(playwright);
page = context.newPage();

loginPage = new LoginPage(page);
buzzPage = new BuzzPage(page);
}

@Given("user is logged into OrangeHRM with the following credentials")
public void user_logs_in(DataTable dataTable) {
Map<String, String> credentials = dataTable.asMaps().get(0);
loginPage.navigate();
loginPage.login(credentials.get("username"), credentials.get("password"));
}

@When("user navigates to {string} from the left menu")
public void userNavigatesToFromLeftMenu(String section) {
if ("Buzz".equalsIgnoreCase(section)) {
buzzPage.navigateFromMenu();
}
}

@When("user leaves the post box empty")
public void userLeavesPostBoxEmpty() {
}

@When("user clicks the {string} button")
public void userClicksButton(String buttonName) {
if ("Post".equalsIgnoreCase(buttonName)) {
buzzPage.clickPostButton();
}
}

@Then("a validation message appears indicating that the post cannot be empty")
public void validationMessageAppears() {
boolean result = buzzPage.isValidationMessageVisibleSmart("cannot be empty");
assertTrue(result, "Expected error message was not found (via element or raw HTML).");
}
}
