package learnAppiumFramework.vivek.android;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import learnAppiumFramework.vivek.AndroidActions;

public class ProductPage extends AndroidActions {

	AndroidDriver driver;

	public ProductPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void searchproduct(String productName) {
		ScrollTilltext(productName);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	private List<WebElement> displayproducts;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> productAddCart;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> prices;

	public void addtocart(String productName) {
		String productPrice = null;
		int arraySize = displayproducts.size();

		for (int i = 0; i < arraySize; i++) {

			String NameofProduct = displayproducts.get(i).getText();

			if (NameofProduct.equalsIgnoreCase(productName)) {
				productPrice = prices.get(i).getText();
				productAddCart.get(i).click();
				break;
			}
		}
		cartButton.click();

	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement btnProceed;

	public void validateCartItems(String productName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(btnProceed));
		Assert.assertEquals(displayproducts.get(0).getText(), productName);
		

	}


}
