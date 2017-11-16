package williamHill.pages.panel;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class BetSlipPanel {
    private static final String PANEL_XPATH = "div#betslip-content ";
    @FindBy(css = PANEL_XPATH + "input[data-ng-model='bet.stake']")
    private SelenideElement betInput;
    @FindBy(css = "button.betslip-numberpad__button")
    private ElementsCollection numpadKeys;

    public void fillBetAmount(String value) {
        openBetSlip();
        if (betInput.is(readonly)) {
            dialBetAmountViaKeyboard(value);
        } else {
            betInput.setValue(value);
        }
    }

    private void openBetSlip() {
        if (!$(PANEL_XPATH).isDisplayed() && $("div.toolbar__wrapper").isDisplayed()) {
            $(" a.toggle-betslip").click();
        }
    }

    private void dialBetAmountViaKeyboard(String value) {
        betInput.click();
        Stream.of(value.split(""))
                .forEach(character -> numpadKeys.filter(attribute("data-value", character))
                        .first().click());

        $("button[data-action='close']").click();
    }

    public String getBetReturnAmount() {
        return $(PANEL_XPATH + "div.betslip-selection__data " +
                "span.betslip-selection__estimated-returns-amount").getText();
    }

    public String getTotalBetReturnAmount() {
        return $(PANEL_XPATH + "div.betslip-footer__to-return-price-container").getText();
    }

    public String getTotalStakeAmount() {
        return $(PANEL_XPATH + "div.betslip-footer__total-stake-price-container").getText();
    }

    public void clickPlaceBet() {
        $(PANEL_XPATH + "input.js-place-bet-button").click();
    }

    public void clickClearBetSlip() {
        $(PANEL_XPATH + "div.betslip-footer__clearslip a.clear").click();
    }

    public boolean isBetsPlaced() {
        return $("div#receipt-notice-box").has(text("Bets placed"));
    }
}
