package williamHill.pages;

import static com.codeborne.selenide.Selenide.$;

public abstract class Page {
    public static final String PAGE_ADDRESS = "http://sports.williamhill.com";

    abstract <T extends Page> T navigate();

    public void login(String login, String password) {
        if (isLoged()) {
            return;
        }
        $(" a#accountTabButton").click();
        $("input#loginUsernameInput").val(login);
        $("input#loginPasswordInput").val(password);
        $("button#loginButton").click();
    }

    public boolean isLoged() {
        return $("a#account_balance_refresh").isDisplayed();
    }
}
