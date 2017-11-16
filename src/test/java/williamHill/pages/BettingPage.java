package williamHill.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class BettingPage extends Page {
    public static final String PAGE_ADDRESS = Page.PAGE_ADDRESS + "/betting/en-gb";

    @Override
    public BettingPage navigate() {
        return Selenide.open(PAGE_ADDRESS, BettingPage.class);
    }

    public FootballPage openFootballTab() {
        $("ul#desktop-sidebar-quick-links li#nav-football , ul.forWeb div#nav-football").click();
        return page(FootballPage.class);
    }
}
