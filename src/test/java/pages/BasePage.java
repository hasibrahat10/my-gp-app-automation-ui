package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import steps.TestSetupPage;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class BasePage extends TestSetupPage {
    public static void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    public static void waitAndClick(By locator) {
        waitAndClick(locator, FLUENT_WAIT);
    }

    public static void waitAndClick(By locator, int timeout) {
        waitForElement(locator, timeout).click();
    }

    public static void setValue(By locator, String value) {
        MobileElement element = waitForElement(locator, FLUENT_WAIT);
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    public static MobileElement waitForElement(By locator) {
        return waitForElement(locator, FLUENT_WAIT);
    }

    public static List<MobileElement> waitForElementList(By locator) {
        return waitForElementList(locator, FLUENT_WAIT);
    }

    public static MobileElement waitForElement(By locator, int timeout) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            getFluentWait(timeout)
                    .withMessage(locator.toString())
                    .ignoring(NoSuchElementException.class)
                    .until(a -> driver.findElement(locator).isDisplayed());
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        } catch (Exception e) {
            Assert.fail(e.getLocalizedMessage());
        }
        return driver.findElement(locator);
    }

    public static List<MobileElement> waitForElementList(By locator, int timeout) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            getFluentWait(timeout)
                    .withMessage(locator.toString())
                    .ignoring(NoSuchElementException.class)
                    .until(a -> driver.findElements(locator).size() > 0 && driver.findElements(locator).get(0).isDisplayed());
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
        return driver.findElements(locator);
    }

    public static FluentWait<String> getFluentWait() {
        return new FluentWait<>("")
                .withTimeout(Duration.ofSeconds(FLUENT_WAIT))
                .pollingEvery(Duration.ofMillis(500));
    }

    public static FluentWait<String> getFluentWait(int timeout) {
        return new FluentWait<>("")
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(500));
    }

    public static void selectFromDropdown(By locator, String value) {
        waitAndClick(locator);
        for (MobileElement mobileElement : waitForElementList(locator)) {
            if (mobileElement.getText().contains(value)) {
                mobileElement.click();
                break;
            }
        }
    }

    public static void hideKeyboard() {
        driver.hideKeyboard();
    }

    public static void sleep(int seconds) {
        sleep(1000L * seconds);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    public boolean isPresent(By locator) {
        return isPresent(locator, IMPLICIT_WAIT);
    }

    public boolean isPresent(By locator, int timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        boolean result = driver.findElements(locator).size() > 0;
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        return result;
    }

    public void swipeUp() {
        Dimension d = driver.manage().window().getSize();
        moveTo(d.getWidth() / 2.0, d.getHeight() * 0.8, d.getWidth() / 2.0, d.getHeight() * 0.2);
        sleep(500L);
    }

    public void swipeRight() {
        Dimension d = driver.manage().window().getSize();
        moveTo(d.getWidth() * 0.2, d.getHeight() / 2.0, d.getWidth() / 0.8, d.getHeight() / 2.0);
        sleep(500L);
    }

    public void swipeLeft() {
        Dimension d = driver.manage().window().getSize();
        moveTo(d.getWidth() * 0.8, d.getHeight() / 2.0, d.getWidth() * 0.2, d.getHeight() / 2.0);
        sleep(500L);
    }

    public void swipeDown() {
        Dimension d = driver.manage().window().getSize();
        moveTo(d.getWidth() / 2.0, d.getHeight() * 0.5, d.getWidth() / 2.0, d.getHeight() * 0.8);
        sleep(500L);
    }

    public void patientlyScroll(String text) {
        patientlyScroll(By.xpath("//*[contains(@text, '" + text + "')]"));
    }

    public void patientlyScroll(By locator) {
        patientlyScroll(locator, 1);
    }

    public void patientlyScroll(By locator, int count) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        int i;
        for (i = 0; i < count; i++) {
            try {
                if (isPresent(locator))
                    break;
                else
                    swipeUp();
            } catch (Exception e) {
                swipeUp();
            }
        }
        if (i == count) {
            fail("Not found: " + locator.toString());
        }
    }

    private void moveTo(double x1, double y1, double x2, double y2) {
        new TouchAction<>(driver).press(PointOption.point((int) x1, (int) y1))
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(800)))
                .moveTo(PointOption.point((int) x2, (int) y2))
                .release()
                .perform();
    }
}
