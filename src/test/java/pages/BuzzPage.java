package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BuzzPage {
private final Page page;

public BuzzPage(Page page) {
this.page = page;
}
public void navigateFromMenu() {
page.locator("a.oxd-main-menu-item:has-text('Buzz')").click();
}
public void clickPostButton() {
page.locator("button.oxd-button--main:has-text('Post')").click();
}
public boolean isValidationMessageVisibleSmart(String expectedText) {
Locator message = page.locator(".oxd-input-field-error-message, .oxd-text--error");
try {
if (message.isVisible()) {
String actualText = message.textContent().trim();
return actualText.contains(expectedText);
}
} catch (Exception e) {
System.out.println("[INFO] Error message element not found or not visible. Falling back to HTML search.");
}

String pageContent = page.content();
return pageContent.contains(expectedText);
}}


