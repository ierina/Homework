package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PimPage {
private final Page page;

public PimPage(Page page) {
this.page = page;
}

public void goToEmployeeList() {
page.locator("span:has-text('PIM')").click();
page.locator("a:has-text('Employee List')").click();
}
public void createNewEmployee(String firstName, String lastName) {

Locator addButton = page.locator("button:has-text('Add')");
addButton.waitFor(new Locator.WaitForOptions().setTimeout(10000));
addButton.scrollIntoViewIfNeeded();
addButton.click();

page.waitForSelector("h6:has-text('Add Employee')");

page.fill("input[name='firstName']", firstName);
page.fill("input[name='lastName']", lastName);
page.click("button:has-text('Save')");

page.waitForSelector("h6:has-text('Personal Details')");
}
public void deleteEmployeeByName(String firstName) {
goToEmployeeList();

page.fill("input[placeholder='Type for hints...']", firstName);
page.click("button:has-text('Search')");
page.waitForSelector(".oxd-table-row");

Locator deleteIcon = page.locator("i.bi-trash").first();
deleteIcon.click();

page.locator("button:has-text('Yes, Delete')").click();
page.waitForTimeout(2000);
}
}
