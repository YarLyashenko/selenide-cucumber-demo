package williamHill.steps;

import cucumber.api.java8.En;
import williamHill.pages.BettingPage;
import williamHill.pages.panel.BetSlipPanel;
import williamHill.pages.panel.CompetitionMatchesListPanel;
import williamHill.pages.panel.FootballEventLinePanel;

import static com.codeborne.selenide.Selenide.page;

public class WilliamHillStepDefinitions implements En {

    BettingPage bettingPage = page(BettingPage.class);
    BetSlipPanel betSlip = page(BetSlipPanel.class);
    CompetitionMatchesListPanel competitionTab;
    FootballEventLinePanel footballEventLinePanel;

    public WilliamHillStepDefinitions() {
        Given("^open page \"([^\"]*)\"$", (String pageClassName) -> {
            if (BettingPage.class.getSimpleName().equals(pageClassName)) {
                bettingPage.navigate();
            }
        });

        And("^login with user \"([^\"]*)\" and password \"([^\"]*)\"$", (String username, String password) -> {
            bettingPage.login(username, password);
        });
        And("^navigate to football championship \"([^\"]*)\"$", (String championshipName) -> {
            bettingPage.openFootballTab()
                    .openFootballCompetitionsPage()
                    .openCompetitionsTab(championshipName);
            competitionTab = new CompetitionMatchesListPanel(championshipName);
        });
        When("^bet on \"([^\"]*)\" event$", (String eventOrderingNumber) -> {
            if ("first".equalsIgnoreCase(eventOrderingNumber)) {
                footballEventLinePanel = competitionTab.getFirstEvent();
            }
        });
        And("^bet for \"([^\"]*)\"$", (String team) -> {
            switch (team) {
                case "home team":
                    footballEventLinePanel.betOnHomeTeam();
                    break;
                case "guest team":
                    footballEventLinePanel.betOnGuestTeam();
                    break;
                case "draw":
                    footballEventLinePanel.betOnDraw();
                    break;
                default:
                    break;
            }
        });
        And("^bet amount \"([^\"]*)\"$", (String amount) -> {
            betSlip.fillBetAmount(amount);
        });
        And("^bet is placed after 'place bet' button is clicked\\.$", () -> {
            betSlip.clickPlaceBet();
            betSlip.validateIsBetsPlaced();
        });
        Then("^validate bet amount$", () -> {
            betSlip.validateBetReturnAmount();
        });
        And("^validate total bet amount$", () -> {
            betSlip.validateTotalBetReturnAmount();
        });
        And("^validate total stake amount$", () -> {
            betSlip.validateTotalStakeAmount();
        });
    }
}
