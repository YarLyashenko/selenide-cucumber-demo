package williamHill;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
        plugin = {"html:target/cucumber-report/smoketest", "json:target/cucumber.json"},
        features = "src/test/resources/features",
        glue = "williamHill/steps",
        tags = "@SmokeTest")
public class SmokeTest extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.timeout = 20000;
    }

    @AfterClass
    private void closeDriver() {
        WebDriverRunner.closeWebDriver();
    }
}
