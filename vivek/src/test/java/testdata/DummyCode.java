package testdata;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import learnAppiumFramework.vivek.AndroidActions;

public class DummyCode {

	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		// TODO Auto-generated method stub

		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("")).withIPAddress("")
				.usingPort(979).build();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("");
		options.setApp("");

		AndroidDriver driver = new AndroidDriver(new URI("https://127.0,0,1:8080").toURL(), options);

		driver.getClipboardText();
		driver.setClipboardText("");
		driver.pressKey(new KeyEvent(AndroidKey.HOME));

		driver.terminateApp("app package");
		driver.activateApp("");

		Set<String> name = driver.getContextHandles();
		for(String getname : name) {
			System.out.println(name);
		}
		driver.context("sasd");
		
		
		
		
	}


	
}

