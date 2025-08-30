package com.saucelabs.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.net.URL;
import java.time.Duration;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SauceLabsTest extends BaseClass {

   

    @Test
    @Order(1)
    public void testClickOnBackpack() {
        // Exemple : cliquer sur le produit "Sauce Labs Backpack"
        driver.findElement(By.xpath("//android.widget.TextView[@text='Sauce Labs Backpack']")).click();

        // Vérification simple : par exemple vérifier que le bouton "Add To Cart" existe
        boolean isDisplayed = driver.findElement(By.xpath("//android.widget.TextView[@text='Add To Cart']")).isDisplayed();
        Assertions.assertTrue(isDisplayed, "Le bouton Add To Cart n'est pas affiché !");
    }
    
    @Test
    @Order(2)
    public void testAddToCart() {
       
        driver.findElement(By.xpath("//android.widget.TextView[@text='Add To Cart']")).click();
        WebElement cartBadge = driver.findElement(AppiumBy.accessibilityId("cart badge"));
      
      
    }
    
    @Test
    @Order(3)
    public void testValid() {

        // Création d'un WebDriverWait (attend max 10s)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Attendre que l’élément avec content-desc="cart badge" soit visible
        WebElement cartIcon = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.view.ViewGroup[@content-desc='cart badge']/android.widget.ImageView")
            )
        );

        // Cliquer dessus
        cartIcon.click();
    }
 
    
    @Test
    @Order(4)
    public void testCheckout() {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	 // 1. Attendre que le bouton soit visible
    	    By checkoutBtn = By.xpath("//android.widget.TextView[@text='Proceed To Checkout']");
    	    wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));

    	    // 2. Cliquer sur le bouton
    	    driver.findElement(checkoutBtn).click();
    }
    
    
    @Test
    @Order(5)
    public void loginWithCredentials() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	By username = AppiumBy.accessibilityId("Username input field");
    	
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(username));
        usernameField.clear();
        usernameField.sendKeys("bob@example.com");
    
By password = AppiumBy.accessibilityId("Password input field");
    	
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(password));
        passwordField.clear();
        passwordField.sendKeys("10203040");
        
        By loginBtn = By.xpath("(//android.widget.TextView[@text='Login'])[2]");
      
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
