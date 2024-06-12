package practiceAPK;


import org.testng.annotations.Test;

import learnAppiumFramework.vivek.android.homePage;

public class homePageTest extends BaseClass {

	@Test(priority = 1,groups = {"Smoke"})
	public void verifyheaders() throws InterruptedException {

		homePage home = new homePage(driver);
		home.verifyHeader();
		home.verifyMainheading();

	}

	@Test(priority = 2,groups = {"Smoke"})
	public void validateFormFill() throws InterruptedException {

		homePage home = new homePage(driver);
		home.formFill("Bhutan", "Somya", "female");

	}

	@Test(priority = 3,groups = {"Smoke"})
	public void validateToast() {

		homePage home = new homePage(driver);
		home.validateToastMessage();

	}

}
