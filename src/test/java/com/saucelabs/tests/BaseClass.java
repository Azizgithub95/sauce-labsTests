package com.saucelabs.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseClass {

    protected AndroidDriver driver;

    @BeforeEach
    public void setup() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setApp("C:\\apk\\Android-MyDemoAppRN.apk")
                .setNoReset(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /** Fait défiler jusqu’à ce que le texte soit visible et retourne l’élément. */
    protected WebElement scrollIntoViewByText(String text) {
        By sel = AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
        );
        return new WebDriverWait(driver, Duration.ofSeconds(12))
                .until(ExpectedConditions.visibilityOfElementLocated(sel));
    }

    /** Clique de façon robuste (attend cliquable, sinon parent, sinon gesture). */
    protected void safeClick(WebElement el) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.elementToBeClickable(el))
                    .click();
        } catch (Exception ignore) {
            try {
                el.findElement(By.xpath("..")).click();
            } catch (Exception e) {
                Map<String, Object> args = new HashMap<>();
                args.put("elementId", ((org.openqa.selenium.remote.RemoteWebElement) el).getId());
                driver.executeScript("mobile: clickGesture", args);
            }
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
