package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {
    By useGuest =By.id("com.portonics.mygp.debug:id/btnGuest");
    By homeText = By.id("com.portonics.mygp.debug:id/txtWelcomeText");


    public void clickGuestBtn(){
        waitAndClick(useGuest);
    }

    public String homeText() {
        return waitForElement(homeText).getText();
    }
}
