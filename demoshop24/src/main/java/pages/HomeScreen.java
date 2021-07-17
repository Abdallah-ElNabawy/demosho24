package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomeScreen {
	
	protected WebDriver wDriver;
	
	public HomeScreen(WebDriver wDriver)
	{		
		this.wDriver = wDriver;
		PageFactory.initElements(wDriver,this);
	}
	
	
	
	@FindBy(xpath= "//span[contains(.,'My Account')]")
	WebElement myAccount_Droplist;
	
	
	@FindBy(linkText = "Register")
	WebElement register_Button;
	
	@FindBy(linkText = "Login")
	WebElement login_Button;

	
	
	
	
	public void clickOnMyAccountDroplist()
	{
			myAccount_Droplist.click();
	}
	
	public void clickOnRegisterButton()
	{
			register_Button.click();
	}
	
	public void clickOnLoginButton()
	{
		login_Button.click();
	}
	
	
	
}
