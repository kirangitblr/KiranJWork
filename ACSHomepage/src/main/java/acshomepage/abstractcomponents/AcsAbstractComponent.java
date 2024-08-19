package acshomepage.abstractcomponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AcsAbstractComponent {
	WebDriver driver;
	
public	AcsAbstractComponent(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
	
	public void waitForWebElementsToAppear(List<WebElement> webElements) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
		
	}

	public void waitForWebElementToAppear(WebElement webElement) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(webElement));
		
	}
	
	public void waitForWebElementToDisappear(WebElement webElement) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(webElement));
		
	}
}
