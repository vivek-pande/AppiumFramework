package practiceAPK;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases extends BaseClass {

	@Test
	public void openApplication() throws InterruptedException {


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated((By.id("com.androidsample.generalstore:id/toolbar_title"))));
		String actualHeader = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText();
		Assert.assertEquals(actualHeader, "General Store");

		String mainheading = driver
				.findElement(By.xpath("//android.widget.TextView[@text='Select the country where you want to shop']"))
				.getText();
		System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>" + mainheading);

	}

}
