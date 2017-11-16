package williamHill;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.webdriver.WebDriverBinaryManager;
import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

@CucumberOptions(
        plugin = {"html:target/cucumber-report/smoketest", "json:target/cucumber.json"},
        features = "src/test/resources/features",
        glue = "williamHill/steps",
        tags = "@smoketest")
public class SmokeTest extends AbstractTestNGCucumberTests {
    @BeforeClass
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.timeout = 20000;
    }

//    @Before
//    public void clearBrowserCache() {
//        WebDriverRunner.clearBrowserCache();
//    }

    @AfterClass
    public void tearDown() {
        WebDriverRunner.closeWebDriver();
    }

    @Before("@SetupMobile")
    private void setupMobileWebdriver() {
        new WebDriverBinaryManager().setupBinaryPath();
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Nexus 5");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();
    }
}
