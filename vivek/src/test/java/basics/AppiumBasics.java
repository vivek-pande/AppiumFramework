package basics;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

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
		// swipe
		swipe(pic1);
		Assert.assertEquals(pic1.getAttribute("focusable"), "false");

	}
}
