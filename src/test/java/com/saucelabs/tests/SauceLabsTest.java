package com.saucelabs.tests;

import io.appium.java_client.AppiumBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SauceLabsTest extends BaseClass {

    @Test
    @Order(1)
    public void testClickOnBackpack() {
        driver.findElement(By.xpath("//android.widget.TextView[@text='Sauce Labs Backpack']")).click();
        boolean isDisplayed = driver.findElement(By.xpath("//android.widget.TextView[@text='Add To Cart']")).isDisplayed();
        Assertions.assertTrue(isDisplayed, "Le bouton Add To Cart n'est pas affiché !");
    }

    @Test
    @Order(2)
    public void testAddToCart() {
        driver.findElement(By.xpath("//android.widget.TextView[@text='Add To Cart']")).click();

        // Ouvrir le panier et vérifier la présence du produit
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By cartIcon = By.xpath("//android.view.ViewGroup[@content-desc='cart badge']/android.widget.ImageView");
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();

        Assertions.assertTrue(
                driver.findElement(By.xpath("//android.widget.TextView[@text='Sauce Labs Backpack']")).isDisplayed(),
                "Le produit n'est pas dans le panier !");
    }

    @Test
    @Order(3)
    public void testOpenCartFromBadge() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartIcon = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//android.view.ViewGroup[@content-desc='cart badge']/android.widget.ImageView")
                )
        );
        cartIcon.click();
    }

    @Test
    @Order(4)
    public void testProceedToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By checkoutBtn = By.xpath("//android.widget.TextView[@text='Proceed To Checkout']");
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    @Test
    @Order(5)
    public void loginWithCredentials() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By username = AppiumBy.accessibilityId("Username input field");
        WebElement userField = wait.until(ExpectedConditions.elementToBeClickable(username));
        userField.clear();
        userField.sendKeys("bob@example.com");

        By password = AppiumBy.accessibilityId("Password input field");
        WebElement passField = wait.until(ExpectedConditions.elementToBeClickable(password));
        passField.clear();
        passField.sendKeys("10203040");

        By loginBtn = By.xpath("(//android.widget.TextView[@text='Login'])[2]");
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    @Test
    @Order(6)
    public void infoCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        // Full name
        By fullName = AppiumBy.accessibilityId("Full Name* input field");
        driver.findElement(
            AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().description(\"Full Name* input field\"))"
            )
        );
        WebElement fullNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(fullName));
        fullNameField.click();
        fullNameField.clear();
        fullNameField.sendKeys("James Brown");

        // Address line 1
        By address = AppiumBy.accessibilityId("Address Line 1* input field");
        driver.findElement(
            AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().description(\"Address Line 1* input field\"))"
            )
        );
        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(address));
        addressField.click();
        addressField.clear();
        addressField.sendKeys("24 avenue Jean Joseph");

        // City
        By city = AppiumBy.accessibilityId("City* input field");
        driver.findElement(
            AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().description(\"City* input field\"))"
            )
        );
        WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(city));
        cityField.click();
        cityField.clear();
        cityField.sendKeys("Paris");

        // Zip
        By zip = AppiumBy.accessibilityId("Zip Code* input field");
        driver.findElement(
            AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().description(\"Zip Code* input field\"))"
            )
        );
        WebElement zipField = wait.until(ExpectedConditions.visibilityOfElementLocated(zip));
        zipField.click();
        zipField.clear();
        zipField.sendKeys("95100");

        // Country
        By country = AppiumBy.accessibilityId("Country* input field");
        driver.findElement(
            AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().description(\"Country* input field\"))"
            )
        );
        WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(country));
        countryField.click();
        countryField.clear();
        countryField.sendKeys("France");

        // Si le clavier reste ouvert, cache-le (évite de bloquer le bouton suivant)
        try { driver.hideKeyboard(); } catch (Exception ignore) {}
    }


    @Test
    @Order(7)
    public void goToPayment() {
        var toPayment = scrollIntoViewByText("To Payment");
        safeClick(toPayment);
    }

    @Test
    @Order(8)
    public void methodPayment() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
    	driver.findElement(AppiumBy.accessibilityId("Full Name* input field")).sendKeys("James Brown");
    	driver.findElement(AppiumBy.accessibilityId("Card Number* input field")).sendKeys("1234 1234 1234 1234");

    	driver.findElement(AppiumBy.accessibilityId("Expiration Date* input field")).sendKeys("02/30");
    	driver.findElement(AppiumBy.accessibilityId("Security Code* input field")).sendKeys("123");
    	
    	 By reviewOrder = By.xpath("//android.widget.TextView[@text=\"Review Order\"]");
         wait.until(ExpectedConditions.elementToBeClickable(reviewOrder)).click();
         
    } 
    
    
    
    
    
    

	@Test
    @Order(9)
    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ouvrir le menu hamburger (vérifie l’accessibility id dans l’Inspector)
        By openMenu = AppiumBy.accessibilityId("open menu");
        wait.until(ExpectedConditions.elementToBeClickable(openMenu)).click();

        By logoutMenu = By.xpath("//android.widget.TextView[@text='Log Out']");
        wait.until(ExpectedConditions.elementToBeClickable(logoutMenu)).click();

        By confirmLogout = By.xpath("//android.widget.Button[@text='LOG OUT']");
        wait.until(ExpectedConditions.elementToBeClickable(confirmLogout)).click();
        
        By seconfirmLogout = By.xpath("//android.widget.Button[@text='OK']");
        wait.until(ExpectedConditions.elementToBeClickable(seconfirmLogout)).click();
        
       
        wait.until(ExpectedConditions.elementToBeClickable(openMenu)).click();
        
        By catalog = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]");
        wait.until(ExpectedConditions.elementToBeClickable(catalog)).click();
    }
}
