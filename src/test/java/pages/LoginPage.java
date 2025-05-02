package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {
private final Page page;

private static final String USERNAME_PLACEHOLDER = "Username";
private static final String PASSWORD_PLACEHOLDER = "Password";
private static final String LOGIN_BUTTON_NAME = "Login";

public LoginPage(Page page) {
this.page = page;
}
public void navigate() {
page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
}

public boolean isAtLoginPage() {
return page.title().contains("OrangeHRM");
}

public void enterUsername(String username) {
page.getByPlaceholder(USERNAME_PLACEHOLDER).fill(username);
}

public void enterPassword(String password) {
page.getByPlaceholder(PASSWORD_PLACEHOLDER).fill(password);
}

public void clickLoginButton() {
page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(LOGIN_BUTTON_NAME)).click();
}

public void login(String username, String password) {
enterUsername(username);
enterPassword(password);
clickLoginButton();
}
}

