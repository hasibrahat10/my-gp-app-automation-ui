package steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.LoginPage;

public class LoginPageSteps extends TestSetupPage {
    @Then("I click on sign in button and navigate to sign in screen")
    public void i_click_on_sign_in_button_and_navigate_to_sign_in_screen() {
new LoginPage().clickSignIn();
    }
    @Then("I entered mobile phone number and click continue button")
    public void i_entered_mobile_phone_number_and_click_continue_button() {
        new LoginPage().setMobileNumber();

    }
    @Then("I want to check the verification screen text {string}")
    public void i_want_to_check_the_verification_screen_text(String expectedText) {
        Assert.assertEquals(expectedText, new LoginPage().verificationText());
    }

}
