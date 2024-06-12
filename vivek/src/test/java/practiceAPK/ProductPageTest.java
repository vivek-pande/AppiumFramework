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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.Activity;
import learnAppiumFramework.vivek.android.ProductPage;
import learnAppiumFramework.vivek.android.homePage;

public class ProductPageTest extends BaseClass {

	@Test(dataProvider = "getData", groups = {"Smoke"})
	public void addTOCart(String country, String name, String gender, String productName) throws InterruptedException {

		homePage home = new homePage(driver);
		home.formFill(country, name, gender);
		ProductPage pp = new ProductPage(driver);
		pp.searchproduct(productName);
		pp.addtocart(productName);
		pp.validateCartItems(productName);

	}

	@Test(dataProvider = "getDataFromjson")
	public void addTOCarts(HashMap<String, String> input) throws InterruptedException {

		homePage home = new homePage(driver);
		home.formFill(input.get("country"), input.get("name"), input.get("gender"));
		ProductPage pp = new ProductPage(driver);
		pp.searchproduct(input.get("product"));
		pp.addtocart(input.get("product"));
		pp.validateCartItems(input.get("product"));

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
