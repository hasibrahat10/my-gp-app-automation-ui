package steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;

public class HomePageSteps extends TestSetupPage {

    @When("I click on the use as guest button")
    public void i_click_on_the_use_as_guest_button() {
        new HomePage().clickGuestBtn();

    }
    @When("I will find the text {string}")
    public void i_will_find_the_text(String expectedText) {
        Assert.assertEquals(expectedText, new HomePage().homeText());
    }
}
