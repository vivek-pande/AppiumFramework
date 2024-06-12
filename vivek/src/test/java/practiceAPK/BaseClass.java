package practiceAPK;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
//import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseClass {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public WebDriverWait wait;

	@BeforeMethod(alwaysRun = true)
	public void appiumConfig() throws URISyntaxException, IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\resources\\data.properties");

		prop.load(fis);
		String ipAddress = System.getProperty("ip") != null ? System.getProperty("ip") : prop.getProperty("ip");
		int port = Integer.parseInt(prop.getProperty("port"));
		String device_name = prop.getProperty("device_name");

		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C://Program Files//nodejs//node_modules//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress(ipAddress).usingPort(port).build();
		service.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(device_name);
		options.setApp("D:\\AppiumFramework\\vivek\\src\\test\\java\\resources\\General-Store.apk");
//		options.setCapability("browserName", "Chrome");
		options.setAppWaitDuration(Duration.ofSeconds(30));
		options.setAutoGrantPermissions(true);
		options.setCapability("session-override", true);
		options.setCapability("autoAcceptAlerts", true);
//		options.setCapability("appPackage", "com.androidsample.generalstore");
//		options.setCapability("appActivity", "com.androidsample.generalstore.MainActivity");

		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		wait.until(ExpectedConditions
//				.visibilityOfElementLocated((By.id("com.androidsample.generalstore:id/toolbar_title"))));

	}
//	@BeforeMethod
//	public void launchApp() {
//		  driver.terminateApp("com.androidsample.generalstore");
//		  driver.activateApp("com.androidsample.generalstore");	
//	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}

	public void longpressGesture(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
	}

	public WebElement ScrollTilltext(String text) {
		return driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + text
						+ "\"));"));

	}

	public void scrollTOEnd() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 1.0));
		} while (canScrollMore);
	}

	public void swipe(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "direction", "left", "percent", 0.75));
	}

	public void dragAndDrop(WebElement ele, int endX, int endY) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "endX", endX, "endY", endY));
	}

	public List<HashMap<String, String>> getJsonData(String JsonFilePath) throws IOException {
		@SuppressWarnings("deprecation")
		String jsonContent = FileUtils.readFileToString(new File(JsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

}
