package support;

import com.microsoft.playwright.*;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
public static BrowserContext createEnglishContext(Playwright playwright) {
Browser browser = playwright.chromium().launch(
new BrowserType.LaunchOptions().setHeadless(false)
);

Map<String, String> headers = new HashMap<>();
headers.put("Accept-Language", "en-US");

return browser.newContext(new Browser.NewContextOptions()
                          .setLocale("en-US")
                          .setExtraHTTPHeaders(headers)
);
}
}
