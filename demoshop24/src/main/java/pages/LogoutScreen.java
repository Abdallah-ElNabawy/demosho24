package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutScreen {
	
	protected WebDriver wDriver;
	
	public LogoutScreen(WebDriver wDriver)
	{		
		this.wDriver = wDriver;
		PageFactory.initElements(wDriver,this);	
	}
	
	
	@FindBy(id = "content")
	WebElement Content_Area;
	
	@FindBy(linkText = "Continue")
	WebElement Continue_Button;
	
	
	public void clickOnContinueButton()
	{	
		Continue_Button.click();
	}
	
	public String fetchContentText()
	{
		return Content_Area.getText().toString();
	}

}
