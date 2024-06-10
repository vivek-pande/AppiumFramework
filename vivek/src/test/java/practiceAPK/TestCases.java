package practiceAPK;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.checkerframework.common.reflection.qual.GetMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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

	@Test
	public void sumOfCartValue() throws InterruptedException {

		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		ScrollTilltext("India");
		driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("vivek");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(3000);

		Double value_of_first_product = Double.parseDouble(driver
				.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText().substring(1));
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		System.out.println("<<<<<<<<<<<<<<<>>>>>" + value_of_first_product);
		Double value_of_Second_product = Double.parseDouble(driver
				.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText().substring(1));
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		System.out.println("<<<<<<<>>>>>>>" + value_of_Second_product);

		Thread.sleep(8000);

		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		String sum = String.valueOf(value_of_first_product + value_of_Second_product);
		System.out.println("<<<<<<>>>>>>" + sum);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Cart']")));

		Assert.assertEquals(
				driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().substring(2),
				sum);

		driver.findElement(By.xpath("//android.widget.CheckBox")).click();
		
		WebElement termAndCondition = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		longpressGesture(termAndCondition);
		Thread.sleep(2000);
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

	}
	
	@Test
	public void hybridSwitch() throws InterruptedException {

		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		ScrollTilltext("Angola");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Angola']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("vivek");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(3000);

		Double value_of_first_product = Double.parseDouble(driver
				.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText().substring(1));
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		System.out.println("<<<<<<<<<<<<<<<>>>>>" + value_of_first_product);
		Double value_of_Second_product = Double.parseDouble(driver
				.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText().substring(1));
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		System.out.println("<<<<<<<>>>>>>>" + value_of_Second_product);


		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		String sum = String.valueOf(value_of_first_product + value_of_Second_product);
		System.out.println("<<<<<<>>>>>>" + sum);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Cart']")));

		Assert.assertEquals(
				driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().substring(2),
				sum);

		driver.findElement(By.xpath("//android.widget.CheckBox")).click();
		
		WebElement termAndCondition = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		longpressGesture(termAndCondition);
		Thread.sleep(2000);
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(8000);

		Set<String> contexts=driver.getContextHandles();
		for(String context: contexts) {
			System.out.println("*************"+context);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("vivek");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(10000);

		

	}
}
