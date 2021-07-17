package bases;

import java.util.ArrayList;
import java.util.Properties;
import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.TakesScreenshot;

import stragies.TestDataStrategy;
import stragies.ExcelData;
import data.models.RegistrationDataModel;
import constants.registrationExcelIndices;

public class Base {

	protected Properties prop = new Properties();
	protected FileInputStream inputStream = null;
	protected File filePath = null;
	protected WebDriver wDriver;
	protected TestDataStrategy testDataStragy;
	protected static ExtentReports eReport;
	protected static ExtentTest eTest;

	
	
	
	@DataProvider(name = "registrationTDProvider")
	public Object[][] provideTestDataForRegistration(Method method) throws Exception
	{
		filePath = new File("src/main/Resources/config.properties");
		inputStream = new FileInputStream(filePath);
		prop.load(inputStream);
		String filePath= prop.getProperty("registrationExcelPath");
		String dataType= prop.getProperty("dataType");
			
			if(dataType.equals("Excel"))
			{
		       testDataStragy = (TestDataStrategy) Class.forName("stragies.ExcelData").newInstance();
			}

		ArrayList<ArrayList<Object>> resultArray = testDataStragy.loadTestData(filePath);
		Object[][] result = new Object[resultArray.size()][1];
		
		for(int i=0; i<resultArray.size(); i++)
		{
			RegistrationDataModel registrationDataModel = new RegistrationDataModel();
			registrationDataModel.seFirstName(resultArray.get(i).get(registrationExcelIndices.FIRST_NAME).toString());
			registrationDataModel.setLastName(resultArray.get(i).get(registrationExcelIndices.LAST_NAME).toString());
			registrationDataModel.setPassword(resultArray.get(i).get(registrationExcelIndices.PASSWORD).toString());
			registrationDataModel.setEmail(resultArray.get(i).get(registrationExcelIndices.EMAIL).toString());
			registrationDataModel.setTelephone(resultArray.get(i).get(registrationExcelIndices.TELEPHONE).toString());
			
			result[i][0] = registrationDataModel;		
		}
		return result;	
    }
	
	
	
	
	@BeforeSuite
	public void prepareReport() throws Exception {
		
		inputStream = new FileInputStream("src/main/Resources/config.properties");
		prop.load(inputStream);

		eReport = new ExtentReports("reports/Summary Report.html");

		eReport.addSystemInfo("Application Name : ", prop.getProperty("aplicationName"));
		eReport.addSystemInfo("Executer : ", prop.getProperty("softwateTester"));		
	}
	
	@AfterSuite
	public void closeReport()
	{	
		eReport.flush();
		eReport.close();
	}
	
	
	
	@BeforeMethod
	public void reportStartTest(Method method) {
		eTest = eReport.startTest(method.getName());
	}

	//@AfterMethod
	public void addScreenshots(Method method, ITestResult iTestResult) throws Exception {
	
		Date date = new Date();
		String dateString = String.format("%tc", date );
		dateString= dateString.replaceAll(":", "-");
		dateString= dateString.replaceAll(" ", "-");
							
		File sShots = ((TakesScreenshot)wDriver).getScreenshotAs(OutputType.FILE);	
		FileUtils.copyFile(sShots, new File("screenshots/" + method.getName() + dateString + ".jpg"));
		
		String fullScreenPath = "C:\\Users\\"+System.getProperty("user.name").toString()+"\\eclipse-workspace\\demoshop24\\"+ "screenshots\\" + method.getName() + dateString + ".jpg";
		
		
		if (iTestResult.getStatus() == iTestResult.SUCCESS) 
		{
			eTest.log(LogStatus.PASS, eTest.addScreenCapture(fullScreenPath));	
		} 
		
		else if (iTestResult.getStatus() == iTestResult.FAILURE)
		{			
			eTest.log(LogStatus.FAIL, iTestResult.getThrowable());
			eTest.log(LogStatus.FAIL, eTest.addScreenCapture(fullScreenPath));
		}
		
		else if (iTestResult.getStatus() == iTestResult.SKIP) 
		{
			eTest.log(LogStatus.SKIP, iTestResult.getThrowable());
			eTest.log(LogStatus.SKIP, eTest.addScreenCapture(fullScreenPath));
		}
		eReport.endTest(eTest);
	}
	
	
	public void addScreenshots(String name, String Status) throws Exception 
	{
		
		Date date = new Date();
		String dateString = String.format("%tc", date );
		dateString= dateString.replaceAll(":", "-");
		dateString= dateString.replaceAll(" ", "-");
							
		File sShots = ((TakesScreenshot)wDriver).getScreenshotAs(OutputType.FILE);	
		FileUtils.copyFile(sShots, new File("screenshots/" + name + dateString + ".jpg"));
		
		String fullScreenPath = "C:\\Users\\"+System.getProperty("user.name").toString()+"\\eclipse-workspace\\demoshop24\\"+ "screenshots\\" + name + dateString + ".jpg";
		
		
		if (Status.equals("Success")) 
		{
			eTest.log(LogStatus.PASS, eTest.addScreenCapture(fullScreenPath));	
		} 
		
		else
		{	
			eTest.log(LogStatus.FAIL, eTest.addScreenCapture(fullScreenPath));	
		} 				
	}
	
	
	
	
//	@Parameters({"browserType","url"})
	@BeforeClass
	public void openWebsite() throws Exception
	{	
		filePath = new File("src/main/Resources/config.properties");
		inputStream = new FileInputStream(filePath);
		prop.load(inputStream);
		
			switch (prop.getProperty("browser")) 
			{
				case ("Chrome"):
					System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			        wDriver=new ChromeDriver();
				    wDriver.manage().window().maximize();
				    wDriver.get(prop.getProperty("demoshopURL"));
					break;
					
				case ("Firefox"):
					System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		            wDriver=new FirefoxDriver();
			        wDriver.manage().window().maximize();
			        wDriver.get(prop.getProperty("demoshopURL"));
			        break;
			}
	}
	
	

}
