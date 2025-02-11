package com.automation;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class SauceDemoGeoLocation {
    static AndroidDriver driver;


    public static void main(String[] args) throws IOException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "22e6507f");
        capabilities.setCapability("app", "C:\\Users\\291494\\Downloads\\FilleShare\\saucedemo\\SacueDemo.apk");
        capabilities.setCapability("appPackage", "com.swaglabsmobileapp");
        capabilities.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
        capabilities.setCapability("chromedriverExecutable", "C:\\Users\\291494\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");

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

        WebElement geoLocation = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-GEO LOCATION\"]"));
        geoLocation.click();

        WebElement onlyOneTimeBtn = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_one_time_button\"]"));
        onlyOneTimeBtn.click();

        WebElement latitude = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"test-latitude\"]"));
        System.out.println("Latitude :-" + latitude.getText());

        WebElement longitude = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"test-longitude\"]"));
        System.out.println("Longitude :-" + longitude.getText());

    }
}
