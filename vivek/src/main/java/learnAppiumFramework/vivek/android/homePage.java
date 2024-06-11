package learnAppiumFramework.vivek.android;

import java.time.Duration;

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

public class homePage extends AndroidActions {
	AndroidDriver driver;

	public homePage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@AndroidFindBy(id = "toolbar_title")
	private WebElement mainHeader;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Select the country where you want to shop']")
	private WebElement mainHeading;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement country;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement radioFemale;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement radioMale;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement btnLetsShop;

	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private WebElement toastMessage;

	public void verifyHeader() {

		String actualHeader = mainHeader.getText();
		String expectedheader = "General Store";
		Assert.assertEquals(actualHeader, expectedheader);

	}

	public void verifyMainheading() {
		String actualHeading = mainHeading.getText();
		String expectedHeading = "Select the country where you want to shop";
		Assert.assertEquals(actualHeading, expectedHeading);

	}

	public void formFill(String countryname, String userName, String gender) {
		country.click();
		ScrollTilltext(countryname);
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryname + "']")).click();
		nameField.sendKeys(userName);
		if (gender.equalsIgnoreCase("male")) {
			radioMale.click();

		} else {
			radioFemale.click();
		}

		btnLetsShop.click();

	}

	public void validateToastMessage() {
		btnLetsShop.click();
		String toastErrorMessage = toastMessage.getAttribute("text");
		String ActualToastErrorMessage = "Please enter your nam";
		Assert.assertEquals(toastErrorMessage, ActualToastErrorMessage);

	}

}
