package williamHill.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class FootballPage extends Page {
    public static final String PAGE_ADDRESS = BettingPage.PAGE_ADDRESS + "/football";

    @Override
    public FootballPage navigate() {
        return Selenide.open(PAGE_ADDRESS, FootballPage.class);
    }

    public FootballCompetitionsPage openFootballCompetitionsPage() {
        $("ul#sidebar-left-context li#nav-football-competitions , li.navCompetitions").click();
        return page(FootballCompetitionsPage.class);
    }
}
