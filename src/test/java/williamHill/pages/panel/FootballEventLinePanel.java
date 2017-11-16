package williamHill.pages.panel;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FootballEventLinePanel {
    private SelenideElement panel;

    public FootballEventLinePanel(String eventTitle) {
        panel = $(By.xpath("//div[@class='event' and .//a[@title='" + eventTitle + "']]"));
    }

    public String homeTeam() {
        return panel.find("ul.btmarket__content-margins span:nth-child(1)").innerText();
    }

    public String guestTeam() {
        return panel.find("ul.btmarket__content-margins span:nth-child(2)").innerText();
    }

    public void betOnHomeTeam() {
        panel.find("button[data-player='" + homeTeam() + "']").click();
    }

    public void betOnGuestTeam() {
        panel.find("button[data-player='" + guestTeam() + "']").click();
    }

    public void betOnDraw() {
        panel.find("button[data-player=Draw]").click();
    }
}
