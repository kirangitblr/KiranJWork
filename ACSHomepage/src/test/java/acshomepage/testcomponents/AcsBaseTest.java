package acshomepage.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import acshomepage.pagobjects.AcsHomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AcsBaseTest {
	public WebDriver driver;
	public AcsHomePage acsHomePage;

	public WebDriver initializeDriver() throws IOException {
		// This method initialize the driver based on the "GloablData.properties" value
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\acshomepage\\resources\\GloablData.properties");
		prop.load(file);
		String browserName = prop.getProperty("browser");
		if (browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.contains("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod
	public void launchApplication() throws IOException {
		driver = initializeDriver();
		acsHomePage = new AcsHomePage(driver);
		acsHomePage.goTo();
	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		// This method will provide the screenshot
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//acsreports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//acsreports//" + testCaseName + ".png";
	}

	public List<String> getOneTimeAmountsfromJson(String reportName) throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\acshomepage\\data\\OneTimeAmount.json"),
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<String> data = mapper.readValue(jsonContent, new TypeReference<List<String>>() {
		});
		return data;

	}

	public List<String> getMonthlyAmountsfromJson(String monthAmounts) throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\acshomepage\\data\\MonthlyAmounts.json"),
				StandardCharsets.UTF_8);
		ObjectMapper mapperMonth = new ObjectMapper();
		List<String> dataMonth = mapperMonth.readValue(jsonContent, new TypeReference<List<String>>() {
		});
		return dataMonth;

	}

	public List<HashMap<String, String>> getOneTimeAmountsfromJsonToValidate(String monthAmounts) throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir")
						+ "\\src\\test\\java\\acshomepage\\data\\OneTimeDonationValidationTestData.json"),
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> dataOneTime = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return dataOneTime;

	}

	public List<HashMap<String, String>> getMontlhlyAmountsfromJsonToValidate(String monthAmounts) throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir")
						+ "\\src\\test\\java\\acshomepage\\data\\MothlyDonationValidatonTestData.json"),
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> dataMonthly = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return dataMonthly;

	}
}