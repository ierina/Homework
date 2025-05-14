package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AssignLeavePage {
private final Page page;

public AssignLeavePage(Page page) {
this.page = page;
}

public void navigateToAssignLeave() {
page.locator("span:has-text('Leave')").click();
page.locator("a:has-text('Assign Leave')").click();
page.waitForSelector("h6:has-text('Assign Leave')");
}

public void enterEmployeeName(String partialName, String fullName) {
Locator input = page.locator("input[placeholder='Type for hints...']");
input.fill(partialName);
page.waitForSelector(".oxd-autocomplete-option:has-text('" + fullName + "')");
page.locator(".oxd-autocomplete-option:has-text('" + fullName + "')").click();
}

public void selectLeaveType(String type) {
Locator dropdown = page.locator("div.oxd-select-text:below(label:has-text('Leave Type'))");
dropdown.waitFor(new Locator.WaitForOptions().setTimeout(5000));
dropdown.scrollIntoViewIfNeeded();
dropdown.click();

if (type.equalsIgnoreCase("first")) {
page.locator("div[role='option']").first().click();
} else {
page.locator("div[role='option']:has-text('" + type + "')").click();
}
}

public void selectPastDate() {
String pastDate = LocalDate.now().minusDays(1)
.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
Locator fromDateInput = page.locator("input.oxd-input[placeholder='yyyy-dd-mm']").nth(0);
fromDateInput.waitFor(new Locator.WaitForOptions().setTimeout(10000));
fromDateInput.scrollIntoViewIfNeeded();
fromDateInput.click();
fromDateInput.fill(pastDate);
}

public void clickAssign() {
Locator assignButton = page.locator("button[type='submit']:has-text('Assign')");
assignButton.waitFor(new Locator.WaitForOptions().setTimeout(5000));
assignButton.scrollIntoViewIfNeeded();
assignButton.click();
}

public boolean isLeaveAssignmentBlocked() {
try {
Locator toast = page.locator("text=insufficient")
                .or(page.locator("text=cannot assign"))
                .or(page.locator("text=invalid"))
                .or(page.locator("text=past"));

toast.waitFor(new Locator.WaitForOptions().setTimeout(5000));
String found = toast.textContent().trim().toLowerCase();
System.out.println("❗ Toast found with message: " + found);
return true;
} catch (com.microsoft.playwright.PlaywrightException e) {
System.out.println("❗ Toast not shown, possibly no error triggered.");
return false;
}
}
}
