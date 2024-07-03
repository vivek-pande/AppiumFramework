package stepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import learnAppiumFramework.vivek.android.homePage;
import practiceAPK.BaseClass;

public class StepDefinationImpli extends BaseClass {
	homePage home;

	@Given("user will open the application")
	public void openTheApplication() {
		home = new homePage(driver);

	}

	@Given("verify the header")
	public void headerVerification() {
		home.verifyHeader();
	}

	@When("^user enter the (.+), (.+) and (.+)$")
	public void userEnterDeatils(String country, String name, String gender) {
		home.formFill(country, name, gender);

	}

	@Then("validate toast message")
	public void validatemessage() {
		home.validateToastMessage();

	}

}
