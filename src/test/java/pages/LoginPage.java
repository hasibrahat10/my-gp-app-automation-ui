package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    By signInButton = By.id("com.portonics.mygp.debug:id/tvText");
    By phone = By.id("com.portonics.mygp.debug:id/etMsisdn");
    By continueButton = By.id("com.portonics.mygp.debug:id/btn");
    By verificationScreenText = By.id("com.portonics.mygp.debug:id/tvVerificationInstruction");


    public void clickSignIn(){
        waitAndClick(signInButton);
    }

    public void setMobileNumber(){
        setValue(phone, "01720381823");
       waitAndClick(continueButton);
    }

    public String verificationText(){
        return waitForElement(verificationScreenText).getText();
    }

}
