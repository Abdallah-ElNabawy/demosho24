package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginScreen {
	
	protected WebDriver wDriver;
	
	public LoginScreen(WebDriver wDriver)
	{		
		this.wDriver = wDriver;
		PageFactory.initElements(wDriver,this);	
	}
	
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement login_Button;
		
	@FindBy(id = "input-email")
	WebElement email_Textfield;
	
	
	@FindBy(id = "input-password")
	WebElement password_TextField;
	
	
	
	public void insertIntoEmail(String value)
	{
		email_Textfield.sendKeys(value);
	}
	
	public void insertIntoPassword(String value)
	{
		password_TextField.sendKeys(value);
	}
	
	public void clicOnLoginButton()
	{
		login_Button.click();
	}

}
