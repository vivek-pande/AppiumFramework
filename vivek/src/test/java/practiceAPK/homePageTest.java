package practiceAPK;


import org.testng.annotations.Test;

import extentReport.MyRetry;
import learnAppiumFramework.vivek.android.homePage;

public class homePageTest extends BaseClass {

	@Test(priority = 1,groups = {"Smoke"},retryAnalyzer = MyRetry.class)
	public void verifyheaders() throws InterruptedException {

		homePage home = new homePage(driver);
		home.verifyHeader();
		home.verifyMainheading();

	}

	@Test(priority = 2,groups = {"Smoke"},retryAnalyzer = MyRetry.class)
	public void validateFormFill() throws InterruptedException {

		homePage home = new homePage(driver);
		home.verifyHeader();
		home.formFill("Bhutan", "Somya", "female");

	}

	@Test(priority = 3,groups = {"Smoke"},retryAnalyzer = MyRetry.class)
	public void validateToast() {

		homePage home = new homePage(driver);
		home.validateToastMessage();

	}

}
