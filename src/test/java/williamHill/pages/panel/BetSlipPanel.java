package williamHill.pages.panel;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.Double.parseDouble;

public class BetSlipPanel {
    private static final String PANEL_CSS_PATH = "div#betslip-content ";

    @FindBy(css = PANEL_CSS_PATH + "input[data-ng-model='bet.stake']")
    private SelenideElement betInput;
    @FindBy(css = "button.betslip-numberpad__button")
    private ElementsCollection numpadKeys;
    @FindBy(css = PANEL_CSS_PATH + "div.betslip-footer__to-return-price-container")
    private SelenideElement totalBetReturnAmount;
    @FindBy(css = PANEL_CSS_PATH + "div.betslip-footer__total-stake-price-container")
    private SelenideElement totalStakeAmount;
    @FindBy(css = PANEL_CSS_PATH + "div.betslip-selection__data " +
            "span.betslip-selection__estimated-returns-amount")
    private SelenideElement betReturnAmount;

    private DecimalFormat decimalFormat;

    public BetSlipPanel() {
        decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
    }

    public void fillBetAmount(String value) {
        openBetSlip();
        if (betInput.is(readonly)) {
            dialBetAmountViaKeyboard(value);
        } else {
            betInput.setValue(value);
        }
    }

    private void openBetSlip() {
        if (!$(PANEL_CSS_PATH).isDisplayed() && $("div.toolbar__wrapper").isDisplayed()) {
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
        return betReturnAmount.getText();
    }

    public double getOdds() {
        String odds = $(PANEL_CSS_PATH + "selection-price span.betslip-selection__price").getText();
        String[] digits = odds.split("/");
        return parseDouble(digits[0]) / parseDouble(digits[1]);
    }

    private double calculateBetReturnAmount() {
        double stake = parseDouble(betInput.getValue());
        return stake + stake * getOdds();
    }

    public void validateBetReturnAmount() {
        double stake = parseDouble(betInput.getValue());
        betReturnAmount.shouldHave(text(decimalFormat.format(stake + stake * getOdds())));
    }

    public String getTotalBetReturnAmount() {
        return totalBetReturnAmount.getText();
    }

    public void validateTotalBetReturnAmount() {
        double stake = parseDouble(betInput.getValue());
        totalBetReturnAmount.shouldHave(text(decimalFormat.format(stake + stake * getOdds())));
    }

    public String getTotalStakeAmount() {
        return totalStakeAmount.getText();
    }

    public void validateTotalStakeAmount() {
        totalStakeAmount.shouldHave(text(betInput.getValue()));
    }

    public void clickPlaceBet() {
        $(PANEL_CSS_PATH + "input.js-place-bet-button").click();
    }

    public void clickClearBetSlip() {
        $(PANEL_CSS_PATH + "div.betslip-footer__clearslip a.clear").click();
    }

    public void validateIsBetsPlaced() {
        $("div#receipt-notice-box").shouldHave(text("Bets placed"));
    }
}
