package williamHill.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FootballCompetitionsPage extends Page {
    public static final String PAGE_ADDRESS = FootballPage.PAGE_ADDRESS + "/competitions";

    @Override
    public FootballCompetitionsPage navigate() {
        return Selenide.open(PAGE_ADDRESS, FootballCompetitionsPage.class);
    }

    public void openCompetitionsTab(String competitionName) {
        SelenideElement competitionTitlePanel = $(By.xpath("//li[@class='-expanded']//li[@data-content-type='competition' and a[contains(text(),'"
                + competitionName + "')]]"));
        if (!competitionTitlePanel.attr("class").contains("-expanded")) {
            competitionTitlePanel.click();
        }
    }


}
