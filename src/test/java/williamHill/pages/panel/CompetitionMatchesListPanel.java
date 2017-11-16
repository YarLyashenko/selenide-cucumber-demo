package williamHill.pages.panel;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static java.util.stream.Collectors.toCollection;

public class CompetitionMatchesListPanel {

    private String titleXpath;

    public CompetitionMatchesListPanel(String competitionsName) {
        titleXpath = "//li[@class='-expanded']//li[@data-content-type='competition' and a[contains(text(),'" +
                competitionsName + "')]]";
    }

    public SelenideElement getTitle() {
        return $(By.xpath(titleXpath));
    }

    public SelenideElement getMatchesList() {
        return getTitle().find(By.xpath("following-sibling::li[1]"));
    }

    public ArrayList<FootballEventLinePanel> getCompetitionEventsList() {
        return getMatchesList()
                .findAll(By.xpath(".//a[contains(@class, 'btmarket__name btmarket__name--featured')]"))
                .stream()
                .map(selenideElement -> new FootballEventLinePanel(selenideElement.getAttribute("Title")))
                .collect(toCollection(ArrayList::new));
    }

    public FootballEventLinePanel getFirstEvent() {
        return getCompetitionEventsList().iterator().next();
    }
}
