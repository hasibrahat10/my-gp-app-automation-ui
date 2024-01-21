package steps;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestSetupPage {
    public static AndroidDriver<MobileElement> driver;
    public static String scenarioName;
    public static String appiumUrl = "http://0.0.0.0:4723/wd/hub";
    public static int IMPLICIT_WAIT = 10;
    public static int FLUENT_WAIT = 20;

    /**
     * Mobile Capabilities Setup
     * Appium Driver Decorated
     */
    protected static void startDriver() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S10");
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
            caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
            caps.setCapability("androidInstallTimeout", "120000");
            caps.setCapability(MobileCapabilityType.APP, "Users/hasib/Downloads/mygpapp.apk");
            caps.setCapability(MobileCapabilityType.FULL_RESET, true);
            caps.setCapability(MobileCapabilityType.NO_RESET, false);
            caps.setCapability("name", scenarioName);

            Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
            driver = new AndroidDriver<>(new URL(appiumUrl), caps);
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected static void stopDriver() {
        if (driver != null)
            driver.quit();
    }
}
