package acshomepage.pagobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import acshomepage.abstractcomponents.AcsAbstractComponent;

public class AcsReceiptPage extends AcsAbstractComponent {
	WebDriver driver;

	public AcsReceiptPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//label[text()='Thank you for your donation!']")
	private WebElement donationThanksMessage;

	public String thankYouMessage() {
		String text = donationThanksMessage.getText();
		return text;

	}
}