package basics;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AppiumBasics extends BaseTest {

	@Test
	public void runAppium() throws URISyntaxException, MalformedURLException, InterruptedException {

		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.RelativeLayout")));

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.RelativeLayout"))
				.getText());

		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle")));
		Assert.assertEquals(driver.findElement(By.id("android:id/alertTitle")).getText(), "WiFi settings");
		driver.findElement(By.id("android:id/edit")).sendKeys("vivek");
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(10000);

	}

	@Test
	public void longPressOnTab() {

		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Expandable Lists\"]")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		WebElement peopleName = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
		longpressGesture(peopleName);
		WebElement sampleMenu = driver.findElement(By.xpath("//android.widget.TextView[@text='Sample menu']"));
		Assert.assertTrue(sampleMenu.isDisplayed());
		Assert.assertEquals(sampleMenu.getText(), "Sample menu");
		System.out.println("****************************************************************");
	}

	@Test
	public void ScrollToElement() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		ScrollTilltext("WebView");
		Thread.sleep(2000);

	}

	@Test
	public void ScrollTillEnd() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		scrollTOEnd();
		Thread.sleep(2000);

	}

	@Test
	public void swipeTONext() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();

		WebElement pic1 = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		Assert.assertEquals(pic1.getAttribute("focusable"), "true");

		swipe(pic1);

		Assert.assertEquals(pic1.getAttribute("focusable"), "false");

	}

	@Test
	public void dragTODesiredLocation() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		WebElement dragItem = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));

		dragAndDrop(dragItem, 622, 546);

		WebElement drop = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text"));
		Assert.assertEquals(drop.getText(), "Dropped!");

	}

	@Test
	public void rotateDevice() throws InterruptedException {

		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

		DeviceRotation landscape = new DeviceRotation(0, 0, 90);
		driver.rotate(landscape);

		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle")));
		Assert.assertEquals(driver.findElement(By.id("android:id/alertTitle")).getText(), "WiFi settings");
		driver.findElement(By.id("android:id/edit")).sendKeys("vivek");
		driver.findElement(By.id("android:id/button1")).click();

	}

	@Test
	public void ClipBoardUses() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		Assert.assertEquals(driver.findElement(By.id("android:id/alertTitle")).getText(), "WiFi settings");

		driver.setClipboardText("text From CLipboard");
		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
		Thread.sleep(10000);
		driver.findElement(By.id("android:id/button1")).click();

	}

	@Test
	public void keyPress() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		Assert.assertEquals(driver.findElement(By.id("android:id/alertTitle")).getText(), "WiFi settings");
		driver.findElement(By.id("android:id/edit")).sendKeys("vivek");
		driver.findElement(By.id("android:id/edit")).clear();
		Actions action = new Actions(driver);

		action.click(driver.findElement(By.id("android:id/edit"))).sendKeys("v").sendKeys("i").perform();

		Thread.sleep(10000);
		driver.findElement(By.id("android:id/button1")).click();

		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));

	}

	@Test
	public void appPackageAndActivity() {

		// App Package ANd App Activity

		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));

		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		Assert.assertEquals(driver.findElement(By.id("android:id/alertTitle")).getText(), "WiFi settings");
		driver.findElement(By.id("android:id/edit")).sendKeys("vivek");

	}

}
