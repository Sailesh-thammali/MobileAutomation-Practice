package com.automation;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class Royalbrothers {
    static AppiumDriver driver;

    public static void main(String[] args) throws InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));

        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "22e6507f");
        capabilities.setCapability("app", "C:\\Users\\291494\\Downloads\\Ebay\\RoyalBrothers.apk");
        capabilities.setCapability("appPackage", "com.royalbrothers");
        capabilities.setCapability("appActivity", "com.royalbrothers.MainActivity");
        //capabilities.setCapability("orientation", "Landscape");

        driver = new AppiumDriver(capabilities);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        WebElement mayBeLaterBtn = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button2\"]"));
        mayBeLaterBtn.click();

        Thread.sleep(Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOf(mayBeLaterBtn));
        mayBeLaterBtn = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button2\"]"));

        mayBeLaterBtn.click();

        WebElement skipBtn = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"SKIP\"]"));
        skipBtn.click();

        WebElement searchBox = driver.findElement(By.xpath("//android.widget.EditText[@text=\"Select city to book your ride\"]"));
        searchBox.sendKeys("Trivandrum");

        WebElement city = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]"));
        city.click();

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc=\" Date \"]")));

        WebElement dateBox = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\" Date \"]"));
        dateBox.click();

        WebElement pickUpDate = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"30\"]"));
        pickUpDate.click();

        WebElement pickUpTime = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"05:30 AM\"]"));
        pickUpTime.click();

        WebElement dropOffDate = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"31\"]"));
        dropOffDate.click();

        WebElement dropOffTime = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"05:30 AM\"]"));
        dropOffTime.click();

        WebElement searchBtn = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"SEARCH\"]"));
        searchBtn.click();

        Thread.sleep(3000);


        WebElement frame1 = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup"));
        location(frame1, "scroll");
        WebElement bike = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView"));
        System.out.println(bike.getText());
        WebElement price = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.TextView"));
        System.out.println(price.getText());


    }

    public static void location(WebElement name, String action) {
        int x = name.getLocation().getX();
        int y = name.getLocation().getY();
        int width = name.getSize().getWidth();
        int height = name.getSize().getHeight();
        if (action == "scroll") {
            scroll(x + width / 2, (int) (y + height), x + width / 2, y);
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
