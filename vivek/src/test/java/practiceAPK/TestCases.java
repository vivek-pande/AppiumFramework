package practiceAPK;

import java.time.Duration;
import java.util.Iterator;

import org.checkerframework.common.reflection.qual.GetMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases extends BaseClass {

	@Test
	public void verifyheaders() throws InterruptedException {

		String actualHeader = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText();
		Assert.assertEquals(actualHeader, "General Store");

		String mainheading = driver
				.findElement(By.xpath("//android.widget.TextView[@text='Select the country where you want to shop']"))
				.getText();
		System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>" + mainheading);

	}

	@Test
	public void validateFormFill() throws InterruptedException {

		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		ScrollTilltext("India");
		driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("vivek");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(3000);

	}

	@Test
	public void validateToast() {
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("text");
		Assert.assertEquals(toastMessage, "Please enter your name");

	}

	@Test
	public void addTOCart() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		ScrollTilltext("India");
		driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("vivek");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(3000);
		String productName = "Jordan 6 Rings";

		ScrollTilltext(productName);
		String productPrice = null;

		int arraySize = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		System.out.println("<<<<<<<<<<<<<<>>>>>>>>>>>>>" + arraySize);
		for (int i = 0; i < arraySize; i++) {

			String NameofProduct = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i)
					.getText();
			System.out.println("*****************" + NameofProduct);

			if (NameofProduct.equalsIgnoreCase("Jordan 6 Rings")) {
				productPrice = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i)
						.getText();
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.androidsample.generalstore:id/btnProceed")));

		Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText(),
				productName);

		Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/productPrice")).getText(),
				productPrice);
	}

}
