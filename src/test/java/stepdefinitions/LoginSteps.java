package stepdefinitions;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pages.LoginPage;
import support.BrowserFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {

private Playwright playwright;
private Browser browser;
private Page page;
private BrowserContext context;
private LoginPage loginPage;

@Before
public void setup() {
playwright = Playwright.create();
context = BrowserFactory.createEnglishContext(playwright);
browser = context.browser();
page = context.newPage();
loginPage = new LoginPage(page);
}

@Given("a user account exists with username {string} and password {string}")
public void userAccountExists(String username, String password) {
System.out.println("Assuming test user already exists: " + username);
}

@When("user opens the login page")
public void userOpensLoginPage() {
loginPage.navigate();
}

@When("user enters username {string}")
public void userEntersUsername(String username) {
loginPage.enterUsername(username);
}

@When("user enters password {string}")
public void userEntersPassword(String password) {
loginPage.enterPassword(password);
}

@When("user clicks the login button")
public void userClicksLoginButton() {
loginPage.clickLoginButton();
}

@Then("an error message appears indicating {string} above the login form")
public void errorMessageAppears(String expectedMessage) {
Locator errorMessage = page.locator(".oxd-alert-content-text");
errorMessage.waitFor(new Locator.WaitForOptions().setTimeout(5000));
String actualMessage = errorMessage.textContent().trim();
assertEquals(expectedMessage, actualMessage, "Unexpected error message");
}

@After
public void teardown() {
if (page != null) page.close();
if (browser != null) browser.close();
if (playwright != null) playwright.close();
}
}
