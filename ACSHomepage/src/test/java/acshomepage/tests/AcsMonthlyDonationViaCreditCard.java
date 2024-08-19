package acshomepage.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import acshomepage.pagobjects.AcsReceiptPage;
import acshomepage.testcomponents.AcsBaseTest;

public class AcsMonthlyDonationViaCreditCard extends AcsBaseTest {
	SoftAssert softAssert = new SoftAssert();
	
	String expectedThanksMessage = "Thank you for your donation!";
	@Test(priority=9,dataProvider ="getDataForMonthlyDonation")
	
	public void testAcsMonthlyDonationFunctionViaCreditCard(HashMap<String, String> input) {
		// This method will validate the monthly donation via credit card functionality.
		acsHomePage.clickOntheMonthlyButton();
		acsHomePage.enterDonationAmount(input.get("donationAmount"));
		acsHomePage.scrollHomePage();
		acsHomePage.clickOnTheCreditCardButton();
		acsHomePage.scrollHomePage();
		acsHomePage.enterCreditCardDetails(input.get("creditCardNumbers"), input.get("creditCardCvv"));
		acsHomePage.enterCreditCardYourInformation(input.get("firstName"),input.get("lastName"),input.get("email") );
		acsHomePage.enterCreditCardBillingAddress(input.get("cAddL1"),input.get("cAddL2"),input.get("bCity"),input.get("bZip"));
		AcsReceiptPage acsReceiptPage = acsHomePage.clickOnDonateButton();
        String actualMessage = acsReceiptPage.thankYouMessage();
        softAssert.assertEquals(actualMessage,expectedThanksMessage);
        softAssert.assertAll();
}
	@DataProvider
	public Object[][] getDataForMonthlyDonation() throws IOException {
		
		List<HashMap<String, String>> dataMonthly = getMontlhlyAmountsfromJsonToValidate(
				System.getProperty("user.dir") + "\\src\\test\\java\\acshomepage\\data\\OneTimeDonationValidationTestData.json");
		return new Object[][] { { dataMonthly.get(0) }, { dataMonthly.get(1) } };
	}
}

