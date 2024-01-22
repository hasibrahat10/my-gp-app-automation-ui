package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/features"},
        tags = "@signIn",
        glue = {"steps"},
        monochrome = true,
        plugin = {"pretty", "html:test-output"}
)

public class TestRunner extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = false)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

