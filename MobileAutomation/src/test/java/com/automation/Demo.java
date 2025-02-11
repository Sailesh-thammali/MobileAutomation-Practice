package com.automation;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws IOException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "22e6507f");
        capabilities.setCapability("app", "C:\\Users\\291494\\Downloads\\MobileAutomationAPKs-master\\MobileAutomationAPKs-master\\EaseMyTrip.apk");
        capabilities.setCapability("appPackage", "com.easemytrip.android");
        capabilities.setCapability("appActivity", "com.easemytrip.android.SplashScreenActivity");
        capabilities.setCapability("autoDismissAlerts", true);
        AppiumDriver driver = new AppiumDriver(capabilities);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        WebElement doNotAllowBtn = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_deny_button\"]"));
        doNotAllowBtn.click();

        WebElement flightsBtn = driver.findElement(By.xpath("//android.widget.TextView[@text='Flights']"));
        flightsBtn.click();

        WebElement fromBtn = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/search_flight_origin_code\"]"));
        fromBtn.click();

        WebElement from = driver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"com.easemytrip.android:id/edt_depart_airport\"]"));
        from.click();
        from.sendKeys("Mumbai");

        List<WebElement> fromCity = driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/search_airport_list_item_name\"]"));
        fromCity.get(0).click();

        WebElement toBtn = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/search_flight_destination_code\"]"));
        toBtn.click();

        WebElement to = driver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"com.easemytrip.android:id/edt_arrival_airport\"]"));
        to.click();
        to.sendKeys("Delhi");

        List<WebElement> toCity = driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/search_airport_list_item_name\"]"));
        toCity.getFirst().click();

        WebElement departure = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/search_flight_departure_date\"]"));
        departure.click();
        Thread.sleep(2000);
        WebElement departureDate = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Date is 27 Feb 2025\"]"));
        departureDate.click();
        String date = "Date is 11 Feb 2025";
        //    WebElement departureDate= driver.findElement(By.xpath("//android.widget.TextView[@content-desc="+date+"]"));
        //   departureDate.click();

        WebElement travellers = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/textViewPaxCount\"]"));
        travellers.click();

        WebElement adults = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/adult_two\"]"));
        adults.click();

        WebElement children = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/child_one\"]"));
        children.click();

        WebElement doneBtn = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tvDone\"]"));
        doneBtn.click();

        WebElement searchBtn = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.easemytrip.android:id/button_flight_Search\"]"));
        searchBtn.click();

        List<WebElement> flights = driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tv_flight_name\"]"));
        List<WebElement> flightDurations = driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tv_travelling_duration\"]"));
        List<WebElement> flightPrice = driver.findElements(By.xpath("(//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/tv_flight_rate_discounted\"])"));

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("src/test/java/com/automation/Screenshot.png"));

        for (int i = 0; i < 2; i++) {
            System.out.println("Flight Name :- " + flights.get(i).getText() + " Flight Duration :- " + flightDurations.get(i).getText() + " Flight Price :-" + flightPrice.get(i).getText());

        }




















       /*

        // WebElement hotelsBtn= driver.findElement(By.xpath("//android.widget.TextView[@text='HOTELS']"));
      // WebElement busBtn = driver.findElement(By.xpath("//android.widget.TextView[@text='BUS']"));
      System.out.println(flightsBtn.isDisplayed());
        System.out.println(hotelsBtn.isDisplayed());
        System.out.println(busBtn.isDisplayed());

        WebElement hamBtn=driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.easemytrip.android:id/iv_drwer\"]"));
        hamBtn.click();

        WebElement rateUsBtn= driver.findElement(By.xpath("//android.widget.TextView[@text=\"Rate Us\"]"));
        rateUsBtn.click();

        WebElement ratingBar=driver.findElement(By.xpath("//android.widget.RatingBar[@resource-id=\"com.easemytrip.android:id/ratingBar\"]"));
        ratingBar.sendKeys("3");
        System.out.println(ratingBar.isEnabled());

        WebElement cancellationBtn=driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/cancellation\"]"));
        cancellationBtn.click();

        WebElement reasonBox= driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.easemytrip.android:id/reason\"]"));
        reasonBox.click();
        reasonBox.sendKeys("Not happy with the booking");

        WebElement submitBtn= driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.easemytrip.android:id/submit\"]"));
        submitBtn.click();

        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("src/test/java/com/automation/Screenshot.png"));*/


    }
}
