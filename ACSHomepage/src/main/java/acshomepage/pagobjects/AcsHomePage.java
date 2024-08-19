package acshomepage.pagobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import acshomepage.abstractcomponents.AcsAbstractComponent;

public class AcsHomePage extends AcsAbstractComponent {
	WebDriver driver;
	JavascriptExecutor js;

	public AcsHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
		
	}

	@FindBy(xpath = ("//*[local-name()='svg']//*[local-name()='image' and @id='svg_header_image']"))
	private WebElement homeBackGroundImage;
	@FindBy(xpath = "//p[text()='Donate today and help us end cancer as we know it, for everyone.']")
	private WebElement homeBackGroundText;
	@FindBy(tagName = "a")
	private List<WebElement> links;
	@FindBy(tagName = "button")
	private List<WebElement> buttons;
	@FindBy(css = "#One-Time-Button")
	private WebElement oneTimeButton;
	@FindBy(xpath = "//div[@class='_gift_container_apm18_265']//div[@tabindex='0']")
	private List<WebElement> onetimeAmount;
	@FindBy(css = "#Monthly-Button")
	private WebElement monthlyButton;
	@FindBy(css = "._gift_container_apm18_265 div[tabindex='0']")
	private List<WebElement> monthlyAmounts;
	@FindBy(xpath = "//input[@placeholder='Enter Donation Amount']")
	private WebElement enterDonationAmount;
	@FindBy(xpath = "//div[@data-type='card']")
	private WebElement crediCardButton;
	@FindBy(xpath = "//iframe[@id='braintree-hosted-field-number']")
	private WebElement creditCardIframe;
	@FindBy(xpath = "//form//input[@id='credit-card-numberr']")
	private WebElement creditCardInput;
	@FindBy(xpath = "//iframe[@id='braintree-hosted-field-expirationMonth']")
	private WebElement creditCardExpiryMonthIframe;
	@FindBy(xpath = "//iframe[@id='braintree-hosted-field-expirationYear']")
	private WebElement creditCardExpiryYearIframe;
	@FindBy(xpath = "//iframe[@id='braintree-hosted-field-cvv']")
	private WebElement creditCardCvvIframe;
	@FindBy(xpath = "//input[@name='contact_first_name']")
	private WebElement cFirstName;
	@FindBy(xpath = "//input[@name='contact_last_name']")
	private WebElement cLastName;
	@FindBy(xpath = "//input[@name='contact_email']")
	private WebElement cEmail;
	@FindBy(xpath = "//input[contains(@placeholder,'Address Line 1')]")
	private WebElement cBillingAddressL1;
	@FindBy(xpath = "//input[contains(@placeholder,'Address Line 2')]")
	private WebElement cBillingAddressL2;
	@FindBy(name = "contact_address_city")
	private WebElement cBillingAddressCity;
	@FindBy(name = "contact_address_zip")
	private WebElement cBillingAddresZip;
	@FindBy(xpath = "//div[@class='_section_container_1nphv_477']//button")
	private WebElement donateButton;
	@FindBy(css = "._loader_container_apm18_3917")
	private WebElement loader;

	private WebElement getCreditCardNumberField() {
		return (WebElement) js.executeScript("return document.getElementById('credit-card-number');");
	}

	private WebElement getCrediCardNumberExpiryMonthField() {
		return (WebElement) js.executeScript("return document.getElementById('expiration-month');");
	}

	private WebElement getCrediCardNumberExpiryYearField() {
		return (WebElement) js.executeScript("return document.getElementById('expiration-year');");
	}

	private WebElement getCrediCardNumberCVVField() {
		return (WebElement) js.executeScript("return document.getElementById('cvv');");
	}

	private WebElement dropdownElement() {
		return (WebElement) js.executeScript("return document.querySelector('select');");
	}

	public void goTo() {
		driver.get("https://qa.donate.cancer.org/");
	}

	public Boolean imageDisplayed() {
		Boolean b = homeBackGroundImage.isDisplayed();
		return b;

	}

	public String getHomePageBackgroundText() {
		String text = homeBackGroundText.getText();
		return text;
	}

	public List<WebElement> getAllLinks() {
		waitForWebElementsToAppear(links);
		return links;

	}

	public List<WebElement> getAllButtons() {
		waitForWebElementsToAppear(buttons);
		return buttons;

	}

	public List<WebElement> getAllOneTimeAmounts() {
		oneTimeButton.click();
		waitForWebElementsToAppear(onetimeAmount);
		return onetimeAmount;

	}

	public List<WebElement> getAllMonthlyAmounts() {
		waitForWebElementToAppear(monthlyButton);
		monthlyButton.click();
		waitForWebElementsToAppear(monthlyAmounts);
		return monthlyAmounts;

	}

	public void clickOnOneTimeButton() {
		oneTimeButton.click();
	}

	public void enterDonationAmount(String donationAmount) {
		enterDonationAmount.sendKeys(donationAmount);
	}

	public void clickOnTheCreditCardButton() {
		waitForWebElementToAppear(crediCardButton);
		crediCardButton.click();

	}

	public void scrollHomePage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}

	public void enterCreditCardDetails(String cNumber, String cvv) {

		driver.switchTo().frame(creditCardIframe);
		waitForWebElementToAppear(getCreditCardNumberField());
		WebElement CreditCNumberField = getCreditCardNumberField();
		CreditCNumberField.sendKeys(cNumber);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(creditCardExpiryMonthIframe);
		waitForWebElementToAppear(getCrediCardNumberExpiryMonthField());
		WebElement CreditCExpiryMonthField = getCrediCardNumberExpiryMonthField();
		Select sMonth = new Select(CreditCExpiryMonthField);
		sMonth.selectByIndex(9);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(creditCardExpiryYearIframe);
		waitForWebElementToAppear(getCrediCardNumberExpiryYearField());
		WebElement CreditCExpiryYearField = getCrediCardNumberExpiryYearField();
		Select sYear = new Select(CreditCExpiryYearField);
		sYear.selectByIndex(1);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(creditCardCvvIframe);
		waitForWebElementToAppear(getCrediCardNumberCVVField());
		WebElement CreditCExpiryCVVField = getCrediCardNumberCVVField();
		CreditCExpiryCVVField.sendKeys(cvv);
		driver.switchTo().defaultContent();
	}

	public void enterCreditCardYourInformation(String firstName, String lastName, String email)

	{
		waitForWebElementToAppear(cFirstName);
		cFirstName.sendKeys(firstName);
		waitForWebElementToAppear(cLastName);
		cLastName.sendKeys(lastName);
		waitForWebElementToAppear(cEmail);
		cEmail.sendKeys(email);
	}

	public void enterCreditCardBillingAddress(String cAddL1, String cAddL2, String bCity, String bZip) {
		cBillingAddressL1.sendKeys(cAddL1);
		cBillingAddressL2.sendKeys(cAddL2);
		cBillingAddressCity.sendKeys(bCity);
		waitForWebElementToAppear(dropdownElement());
		WebElement cBillingAddSt = dropdownElement();
		cBillingAddSt.isEnabled();
		Select s = new Select(cBillingAddSt);
		s.selectByIndex(5);
		cBillingAddresZip.sendKeys(bZip);
	}

	public AcsReceiptPage clickOnDonateButton() {
		waitForWebElementToAppear(donateButton);
		donateButton.click();
		waitForWebElementToDisappear(loader);
		return new AcsReceiptPage(driver);
	}

	public void clickOntheMonthlyButton() {
		waitForWebElementToAppear(monthlyButton);
		monthlyButton.click();

	}

}
