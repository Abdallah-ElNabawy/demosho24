package testcases;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.ITestResult;

import bases.Base;
import data.models.RegistrationDataModel;

import pages.*;


public class RegisterCustomers extends Base {
	
	
	
	@Test(dataProvider="registrationTDProvider", priority = 1)
	public void fullRegistrationFlow(RegistrationDataModel registrationDataModel) throws Exception
	{

		String fullName= registrationDataModel.getFirstName()+" "+registrationDataModel.getLastName();
		// move to registration screen from home screen
		HomeScreen homeScreen = new HomeScreen(wDriver);			
		wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		homeScreen.clickOnMyAccountDroplist();
		homeScreen.clickOnRegisterButton();
		
		
		
		
		// fill required data in registration screen
		RegistrationScreen registrationScreen = new RegistrationScreen(wDriver);
		wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
		registrationScreen.fillRegistrationForm(registrationDataModel);	
		registrationScreen.selectYesNewsLetter();
		registrationScreen.checkConditionsCheckbox();
		registrationScreen.clickOnContinueButton();
		
		
		
		
		// validate that successful confirmation message be shown normally
		ConfirmationRegistrationScreen confirmationScreen = new ConfirmationRegistrationScreen(wDriver);
		wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		

		if(confirmationScreen.fetchContentText().contains("Congratulations! Your new account has been successfully created!"))
		{
			eTest.log(LogStatus.PASS, fullName);
			eTest.log(LogStatus.PASS, "Successful Confirmation Message be shown Normally");
			addScreenshots(fullName+" -"+" Confirmation Screen ","Success");
		}
		else
		{
			eTest.log(LogStatus.FAIL, fullName);
			eTest.log(LogStatus.FAIL, "Successful Confirmation Message wasn't shown");
			addScreenshots(fullName+" -"+" Confirmation Screen ","Fail");
		}
		
		confirmationScreen.clickOnContinueButton();
		
	
			
		
		// validate that account screen be shown normally after registration
		AccountScreen accountScreen = new AccountScreen(wDriver);
		wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(accountScreen.fetchContentText().contains("My Affiliate Account"))
		{
			eTest.log(LogStatus.PASS, "Account screen be shown normally after registration");
			addScreenshots(fullName+" -"+" Account Screen After Registration ","Success");
		}
		else
		{
			eTest.log(LogStatus.FAIL, "account screen wasn't shown after registration");
			addScreenshots(fullName+" -"+" Account Screen After Registration ","Fail");
		}
		
		
		
		
		
		// Validate that confirmation logout message be shown normally after logout
		accountScreen.clickOnMyAccountDroplist();
		accountScreen.clickOnlogoutButton();
		
		LogoutScreen logoutScreen =  new LogoutScreen(wDriver);
		wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		if(logoutScreen.fetchContentText().contains("You have been logged off your account. It is now safe to leave the computer."))
		{
			eTest.log(LogStatus.PASS, "confirmation logout message be shown normally");
			addScreenshots(fullName+" -"+" Logout Screen ","Success");
		}
		else
		{
			eTest.log(LogStatus.FAIL, "confirmation logout message wasn't shown normally");
			addScreenshots(fullName+" -"+" Logout Screen ","Fail");
		}
		
		logoutScreen.clickOnContinueButton();
		
		
		
		
		
		
		// login by new register user
		homeScreen = new HomeScreen(wDriver);			
		wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		homeScreen.clickOnMyAccountDroplist();
		homeScreen.clickOnLoginButton();
		
		LoginScreen loginScreen = new LoginScreen(wDriver);
		wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		loginScreen.insertIntoEmail(registrationDataModel.getEmail());
		loginScreen.insertIntoPassword(registrationDataModel.getPassword());
		loginScreen.clicOnLoginButton();
		
		
		accountScreen = new AccountScreen(wDriver);
		wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		System.out.print("Account content after login : "+accountScreen.fetchContentText() + "\n");
		if(accountScreen.fetchContentText().contains("My Affiliate Account"))
		{
			eTest.log(LogStatus.PASS, "Account screen be shown normally after login");
			addScreenshots(fullName+" -"+" Account Screen After Login ","Success");
		}
		else
		{
			eTest.log(LogStatus.FAIL, "account screen wasn't shown after login");
			addScreenshots(fullName+" -"+" Account Screen After Login ","Fail");
		}
		
		// navigate to home screen to start new test
		accountScreen.clickOnlogoutButton();
		
		logoutScreen =  new LogoutScreen(wDriver);
		wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logoutScreen.clickOnContinueButton();
		
		eReport.endTest(eTest);
	}

}
