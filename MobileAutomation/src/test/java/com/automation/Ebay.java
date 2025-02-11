package com.automation;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ebay {
    static AppiumDriver driver;

    public static void main(String[] args) throws InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "22e6507f");
        capabilities.setCapability("app", "C:\\Users\\291494\\Downloads\\Ebay\\Ebay.apk");

        driver = new AppiumDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        WebElement closeBtn = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"));
        closeBtn.click();

        WebElement searchIcon = driver.findElement(By.id("com.ebay.mobile:id/search_box"));
        searchIcon.click();

        WebElement searchBox = driver.findElement(By.id("com.ebay.mobile:id/search_src_text"));
        String item = "laptop";
        searchBox.sendKeys(item);

        WebElement searchItem = driver.findElement(By.xpath(String.format("//android.widget.TextView[@resource-id=\"com.ebay.mobile:id/search_suggestion_text\" and @text=\"%s\"]", item)));
        searchItem.click();

        Thread.sleep(Duration.ofSeconds(10));
        WebElement msg = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"When you save a search, we'll let you know when a new matching item is listed double-tap to dismiss\"]"));
        msg.click();
        WebElement save = driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.ebay.mobile:id/recyclerview_items\"]"));
        location(save, "scroll");

        List<WebElement> itemsList = driver.findElements(By.xpath("(//android.widget.TextView[@resource-id=\"com.ebay.mobile:id/textview_header_0\"])"));
        while (!itemsList.get(0).getText().contains("Lenovo")) {
            WebElement itemCard = driver.findElement(By.xpath("(//android.view.ViewGroup[@resource-id=\"com.ebay.mobile:id/cell_collection_item\"])"));
            location(itemCard, "scroll");
            itemsList = driver.findElements(By.xpath("(//android.widget.TextView[@resource-id=\"com.ebay.mobile:id/textview_header_0\"])"));
        }
        itemsList.get(0).click();
        WebElement image = driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ebay.mobile:id/imageview_image\"]"));
        image.click();

        WebElement img = driver.findElement(By.xpath("//android.widget.ImageView"));
        img.click();

        Dimension size = driver.manage().window().getSize();
        int centreX = size.width / 2;
        int centreY = size.height / 2;
        zoomIn(centreX, centreY);

        WebElement close = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Close\"]"));
        close.click();

        image = driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ebay.mobile:id/imageview_image\"]"));
        location(image, "scroll");

        // WebElement frame1= driver.findElement(By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View"));


        WebElement frame2 = driver.findElement(By.xpath("(//android.widget.LinearLayout[@resource-id=\"com.ebay.mobile:id/vertical_container_inner_viewgroup\"])[3]"));
        location(frame2, "scroll");

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

    public static void zoomIn(int centerX, int centerY) {
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
        Sequence zoomIn1 = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX - 100, centerY - 100))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        Sequence zoomIn2 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger2.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX + 100, centerY + 100))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(zoomIn1, zoomIn2));
    }

    public static void zoom(int centreX, int centreY) {
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence sequence1 = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centreX, centreY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centreX - 100, centreY - 100))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        Sequence sequence2 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centreX, centreY))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger2.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centreX + 100, centreY + 100))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(sequence1, sequence2));

    }
}
