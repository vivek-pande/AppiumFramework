package practiceAPK;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.Activity;
import learnAppiumFramework.vivek.android.ProductPage;
import learnAppiumFramework.vivek.android.homePage;

public class ProductPageTest extends BaseClass {

//	@BeforeMethod
//	public void firstScreen() {
//		// set the homepage at start
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//
//		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
//				"ea63de7 u0 com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));
//		
//	}

	@Test(dataProvider = "getData")
	public void addTOCart(String country, String name, String gender, String productName) throws InterruptedException {

		homePage home = new homePage(driver);
		home.formFill(country, name, gender);
//		String productName = "Jordan 6 Rings";
		ProductPage pp = new ProductPage(driver);
		pp.searchproduct(productName);
		pp.addtocart(productName);
		pp.validateCartItems(productName);

	}

	@Test(dataProvider = "getDataFromjson")
	public void addTOCarts(HashMap<String, String> input) throws InterruptedException {

		homePage home = new homePage(driver);
		home.formFill(input.get("country"), input.get("name"), input.get("gender"));
//		String productName = "Jordan 6 Rings";
		ProductPage pp = new ProductPage(driver);
		pp.searchproduct(input.get("product"));
		pp.addtocart(input.get("product"));
		pp.validateCartItems(input.get("product"));

	}

	@Test
	public void sumOfCartValue() throws InterruptedException {

		homePage home = new homePage(driver);
		home.formFill("Angola", "vivek", "male");

		Double value_of_first_product = Double.parseDouble(driver
				.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText().substring(1));
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		System.out.println("<<<<<<<<<<<<<<<>>>>>" + value_of_first_product);
		Double value_of_Second_product = Double.parseDouble(driver
				.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText().substring(1));
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		System.out.println("<<<<<<<>>>>>>>" + value_of_Second_product);

		Thread.sleep(3000);

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

	@DataProvider
	public Object[][] getData() throws IOException {
		return new Object[][] { { "Angola", "vivek", "male,", "Jordan 6 Rings" },
				{ "Argentina", "somya", "female", "Nike Blazer Mid '77" } };
	}

	@DataProvider
	public Object[][] getDataFromjson() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				"D:\\AppiumFramework\\vivek\\src\\test\\java\\testdata\\homepageData.json");
		// { {hash}, {hash}
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };

	}
}
