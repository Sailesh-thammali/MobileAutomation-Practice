package com.automation;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class ScrollAndSwipe {
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

        WebElement flightsBtn = driver.findElement(By.xpath("//android.widget.TextView[@text='Flights']"));
        flightsBtn.click();

      /* WebElement fromBtn= driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/search_flight_origin_code\"]"));
        fromBtn.click();

        WebElement from= driver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"com.easemytrip.android:id/edt_depart_airport\"]"));
        from.click();
        from.sendKeys("Mumbai");

        List<WebElement> fromCity= driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/search_airport_list_item_name\"]"));
        fromCity.get(0).click();

        WebElement toBtn= driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/search_flight_destination_code\"]"));
        toBtn.click();

        WebElement to= driver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"com.easemytrip.android:id/edt_arrival_airport\"]"));
        to.click();
        to.sendKeys("Delhi");

        List<WebElement> toCity= driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/search_airport_list_item_name\"]"));
        toCity.getFirst().click();

        WebElement departure= driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/search_flight_departure_date\"]"));
        departure.click();

        WebElement departureDate= driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Date is 11 Feb 2025\"]"));
        departureDate.click();*/

        WebElement searchBtn = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.easemytrip.android:id/button_flight_Search\"]"));
        searchBtn.click();

        WebElement flightPrice = driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tv_flight_rate_discounted\"])"));

        List<WebElement> flightCard = driver.findElements(By.xpath("(//android.widget.FrameLayout[@resource-id=\"com.easemytrip.android:id/top_id_card\"])"));
        int i = 0, y = 1;
        while (i != y) {
            i = y;
            for (WebElement card : flightCard) {
                int x = card.getLocation().getX();
                y = card.getLocation().getY();
                int width = card.getSize().getWidth();
                int height = card.getSize().getHeight();
                scroll(x + width / 2, y + height, x + width / 2, y);
                WebElement flightName = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tv_flight_name\"]"));
                System.out.println(flightName.getText());

            }
            flightCard = driver.findElements(By.xpath("(//android.widget.FrameLayout[@resource-id=\"com.easemytrip.android:id/top_id_card\"])"));
            i++;
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

}
/*  List<WebElement> flights=driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tv_flight_name\"]"));
        List<WebElement> flightDurations=driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tv_travelling_duration\"]"));
        List<WebElement> flightPrice=driver.findElements(By.xpath("(//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tv_flight_rate_discounted\"])"));

        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("src/test/java/com/automation/Screenshot.png"));

        for(int i=0;i<2;i++){
            System.out.println("Flight Name :- "+flights.get(i).getText()+" Flight Duration :- "+flightDurations.get(i).getText()+" Flight Price :-"+flightPrice.get(i).getText());

        }*/
