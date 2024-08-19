package acshomepage.tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import acshomepage.testcomponents.AcsBaseTest;

@Test(priority = 1)
public class AcsHomePageValidations extends AcsBaseTest {
	SoftAssert softAssert = new SoftAssert();
	String acsHomePageTitle = "Donate Today | The American Cancer Society";
	String acsHomePageBackgroundtext = "Donate today and help us end cancer as we know it, for everyone.";

	public void testAcsHomePageTitle() {
		// This method will validate title of the home page
		acsHomePage.goTo();
		String title = driver.getTitle();
		Assert.assertEquals(acsHomePageTitle, title);

	}

	@Test(priority = 2)
	public void testAcsBackgroundImage() {
		// This method will validate the background image
		Boolean b = acsHomePage.imageDisplayed();
		softAssert.assertTrue(b);
		softAssert.assertAll();

	}

	@Test(priority = 3)
	public void testAcsHomePageBackgroundText() {
		// This method will validate the background text
		String currentText = acsHomePage.getHomePageBackgroundText();
		Assert.assertEquals(currentText, acsHomePageBackgroundtext);
	}

	@Test(priority = 4)
	public void testAcsHomePageLinks() throws IOException {
		// This method will validate the links on the home page with the "a" tag
		List<WebElement> links = acsHomePage.getAllLinks();
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			if (url != null && !url.isEmpty()) {
				if (url.startsWith("tel:")) {
					System.out.println("Telephone link found: " + url);
				} else if (url.startsWith("http")) {
					HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
					conn.setRequestMethod("HEAD");
					conn.connect();
					int respCode = conn.getResponseCode();
					softAssert.assertTrue(respCode < 400, "The link with text" + url + "broken" + respCode);
					System.out.println(url + " " + respCode);

				}
			}
			softAssert.assertAll();
		}

	}

	@Test(priority = 5)
	public void testAcsHomePageButtons() {
		// This method will validate the buttons on the home page with the "button" tag
		// (One Time, Monthly, Credit Card, Gpay,Live Chat Offline.
		int count = 0;
		List<WebElement> buttons = acsHomePage.getAllButtons();
		int totalButtons = buttons.size();
		for (WebElement button : buttons) {
			if (button.isEnabled() && button.isDisplayed()) {
				button.click();
				count++;
			}

		}
		softAssert.assertEquals(totalButtons, count);
		softAssert.assertAll();
	}

	@Test(priority = 6, dataProvider = "getDataOneTime")
	public void testAcsHomePageOneTimeAmount(List<String> matchValue) {
		// This method will validate the one-time donation amounts
		int i = 0;
		List<WebElement> amounts = acsHomePage.getAllOneTimeAmounts();
		for (WebElement amount : amounts) {
			String name = amount.getText();
			String ExpectedValue = matchValue.get(i);
			softAssert.assertEquals(name, ExpectedValue);
			i++;
		}
		softAssert.assertAll();
	}

	@Test(priority = 7, dataProvider = "getDataMonthly")
	public void testAcsHomePageMontnlyAmounts(List<String> monthlyamountsList) {
		// This method will validate the monthly donation amounts
		int i = 0;
		List<WebElement> amounts = acsHomePage.getAllMonthlyAmounts();
		for (WebElement amount : amounts) {
			String name = amount.getText();
			String ExpectedValue = monthlyamountsList.get(i);
			softAssert.assertEquals(name, ExpectedValue);
			// System.out.println("Current value" + name);
			// System.out.println("Expected value" + ExpectedValue);
			i++;
		}
		softAssert.assertAll();
	}

	@DataProvider
	public Object[] getDataOneTime() throws IOException {
		// This method provides the expected data for the one time donation amounts from
		// the the OneTimeAmount.json
		List<String> oneTimeAmount = getOneTimeAmountsfromJson(
				System.getProperty("user.dir") + "\\src\\test\\java\\acshomepage\\data\\OneTimeAmount.json");

		return new Object[] { oneTimeAmount };
	}

	@DataProvider
	public Object[] getDataMonthly() throws IOException {
		// This method provides the expected data for the monthly donation amounts from
		// the MonthlyAmounts.json
		List<String> monthlyAmounts = getMonthlyAmountsfromJson(
				System.getProperty("user.dir") + "\\src\\test\\java\\acshomepage\\data\\MonthlyAmounts.json");
		return new Object[] { monthlyAmounts };
	}
	
}
