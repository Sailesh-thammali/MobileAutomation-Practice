package com.automation;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SaucedemoWebView {
    static AndroidDriver driver;


    public static void main(String[] args) throws IOException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "22e6507f");
        capabilities.setCapability("app", "C:\\Users\\291494\\Downloads\\FilleShare\\saucedemo\\SacueDemo.apk");
        capabilities.setCapability("appPackage", "com.swaglabsmobileapp");
        // capabilities.setCapability("chromedriver_autodownload","true");
        capabilities.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
        capabilities.setCapability("chromedriverExecutable", "C:\\Users\\291494\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");

        //capabilities.setCapability("chromedriverExecutable", Optional.ofNullable(null));
        // AppiumDriver driver = new AppiumDriver(capabilities);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        WebElement usernameInput = driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]"));
        usernameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"LOGIN\"]"));
        loginBtn.click();

        WebElement menu = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Menu\"]"));
        menu.click();

        WebElement webView = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-WEBVIEW\"]"));
        webView.click();

        WebElement searchBox = driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-enter a https url here...\"]"));
        searchBox.sendKeys("www.google.com");

        WebElement goToSiteBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"GO TO SITE\"]"));
        goToSiteBtn.click();

        Thread.sleep(5000);

        Set<String> contexts = driver.getContextHandles();

        for (String context : contexts) {
            System.out.println("Context: " + context);
        }


        for (String context : contexts) {
            if (context.contains("WEBVIEW_com")) {
                driver.context(context);
                break;
            }
        }


        WebElement webElement = driver.findElement(By.name("q"));
        webElement.sendKeys("Bike");

        driver.context("NATIVE_APP");

        menu.click();

        WebElement allItemsBtn = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-ALL ITEMS\"]"));
        allItemsBtn.click();

        List<WebElement> itemsList = driver.findElements(By.xpath("//android.widget.TextView[@content-desc=\"test-Item title\"]"));
        while (!itemsList.get(0).getText().contains("Sauce Labs Onesie")) {
            WebElement itemCard = driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"test-Item\"])/android.view.ViewGroup"));
            location(itemCard, "scroll");
            itemsList = driver.findElements(By.xpath("//android.widget.TextView[@content-desc=\"test-Item title\"]"));
        }
        itemsList.get(0).click();

        menu.click();

        WebElement logoutBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"LOGOUT\"]"));
        logoutBtn.click();


    }

    public static void location(WebElement name, String action) {
        int x = name.getLocation().getX();
        int y = name.getLocation().getY();
        int width = name.getSize().getWidth();
        int height = name.getSize().getHeight();
        if (Objects.equals(action, "scroll")) {
            scroll(x + width / 2, (int) (y + height * 1.5), x + width / 2, y);
        } else {
            swipe(x + width, y + height / 2, x, y + height / 2);
        }
    }

    public static void scroll(int startX, int startY, int endX, int endY) {
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger1.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));

    }

    public static void swipe(int startX, int startY, int endX, int endY) {
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence sequence = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger2.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));

    }
}
