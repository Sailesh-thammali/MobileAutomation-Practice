package com.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class EaseMyTrip_Swipe {
    static AppiumDriver driver;

    public static void main(String[] args) {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "22e6507f");
        capabilities.setCapability("app", "C:\\Users\\291494\\Downloads\\MobileAutomationAPKs-master\\MobileAutomationAPKs-master\\EaseMyTrip.apk");
        capabilities.setCapability("appPackage", "com.easemytrip.android");
        capabilities.setCapability("appActivity", "com.easemytrip.android.SplashScreenActivity");


        driver = new AppiumDriver(capabilities);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        WebElement doNotAllowBtn = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_deny_button\"]"));
        doNotAllowBtn.click();

        WebElement frame1 = driver.findElement(By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]"));
        location(frame1, "scroll");

        WebElement frame2 = driver.findElement(By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.HorizontalScrollView[2]/android.view.View[1]/android.view.View[1]/android.view.View"));
        location(frame2, "scroll");

        WebElement frame3 = driver.findElement(By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View"));
        location(frame3, "scroll");

        int i = 1;
        while (i < 5) {
            WebElement packages = driver.findElement(By.xpath(String.format("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]/android.view.View[%d]/android.view.View[2]/android.widget.TextView", i)));
            String place = packages.getText();
            WebElement price = driver.findElement(By.xpath(String.format("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]/android.view.View[%d]/android.widget.TextView", i)));
            System.out.println("Place:- " + place + " | Price:- " + price.getText());

            WebElement card = driver.findElement(By.xpath(String.format("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]/android.view.View[%d]", i)));
            location(card, "swipe");
            if (place.contains("Phuket & Krabi")) {
                break;
            }
            i = 2;
        }


    }

    public static void location(WebElement name, String action) {
        int x = name.getLocation().getX();
        int y = name.getLocation().getY();
        int width = name.getSize().getWidth();
        int height = name.getSize().getHeight();
        if (action == "scroll") {
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
 /*


        List<WebElement> packages=driver.findElements(By.xpath(String.format("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]/android.view.View[%d]",1)));
       WebElement place=driver.findElement(By.xpath(String.format("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]/android.view.View[%d]/android.view.View[2]/android.widget.TextView",1)));
         System.out.println(place.getText());int i=1;

        while(i<5){
            WebElement place=driver.findElement(By.xpath(String.format("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]/android.view.View[%d]/android.view.View[2]/android.widget.TextView",1)));
            System.out.println(place.getText());
            /*for(WebElement pack:packages){
                location(pack,"swipe");
            }
           WebElement packages=driver.findElement(By.xpath(String.format("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]/android.view.View[%d]",i)));
           place=driver.findElement(By.xpath(String.format("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]/android.view.View[%d]/android.widget.TextView",i)));
           if(place.getText()!="Phuket & Krabi")
           {
               location(packages,"swipe");
                i=2;
           }
        }*/


