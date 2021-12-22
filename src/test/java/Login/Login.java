package Login;

import org.junit.Assert;

import cucumberhelpers.selenium.stepdef.StepDefHelperYml;

public class Login {

	// private final int DELAY_2000 = 2000,
	// DELAY_5000 = 5000;
	// private final String USER_ID = "userid"
	private final String loginPageURL = "LOGIN_URL", LOGINBUTTON = "ascensionidloginbutton", ASCENSIONID_FIELD = "ascensionidfield",
			PASSWORD_FIELD = "ascensionidpasswordfield", LOGIN_BUTTON = "loginbutton";

	private StepDefHelperYml step = new StepDefHelperYml();

	public Login() {

	}
	
	public void LoginPage(Role role) {
		String[] options = {"-headless"};
		step.setUpBrowser("chrome", options);
		//step.setUpBrowser("chrome");
		step.driver().manage().window().fullscreen();
		step.on("Login");
		String idURL = step.driver().getCurrentUrl();
		Assert.assertEquals(idURL, step.getValue(loginPageURL));
		step.setElement(LOGINBUTTON).waitUntilVisible().click();
		// robot.keyRelease(KeyEvent.VK_ENTER);
		// step.driver().switchTo().alert().accept();
		step.wait(2000);
		step.setElement(ASCENSIONID_FIELD).waitUntilVisible().inputToElement(step.getValue(role.name()));
		switch (role.name()) {
		case "OFFICE_SCHEDULER":
			step.setElement(PASSWORD_FIELD).waitUntilVisible().inputToElement(step.getValue("OFFICE_SCHEDULER_PASSWORD"));
			break;
		case "OR_SCHEDULER":
			step.setElement(PASSWORD_FIELD).waitUntilVisible().inputToElement(step.getValue("OR_PASSWORD"));
			break;
		case "OFFICE_MANAGER":
			step.setElement(PASSWORD_FIELD).waitUntilVisible().inputToElement(step.getValue("OFFICE_MANAGER_PASSWORD"));
			break;
		case "OFFICE_VIEWER":
			step.setElement(PASSWORD_FIELD).waitUntilVisible().inputToElement(step.getValue("OFFICE_VIEWER_PASSWORD"));
			break;
		case "OR_VIEWER":
			step.setElement(PASSWORD_FIELD).waitUntilVisible().inputToElement(step.getValue("OR_VIEWER_PASSWORD"));
			break;
		default:
			System.out.println("No matching username has been found");
		}
		step.setElement(LOGIN_BUTTON).click();
		while (step.findElements("HEADER_SECTION_APP_TITLE").size() == 0);
		step.pageReady();
		step.wait(3000);
	}

	public void shutdown() {
		step.shutdownDriver();
	}
}
