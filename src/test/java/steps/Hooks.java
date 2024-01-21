package steps;

import helper.FileHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends TestSetupPage{
    @Before
    public void startDriverSet(Scenario scenario) {
        scenarioName = scenario.getName();
        startDriver();
    }

    @After
    public void quitDriver() {
        FileHelper.take_screenshot();
        stopDriver();
    }
}
