package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountScreen {
	
	protected WebDriver wDriver;
	
	public AccountScreen(WebDriver wDriver)
	{		
		this.wDriver = wDriver;
		PageFactory.initElements(wDriver,this);
	}
	
	
	@FindBy(xpath= "//span[contains(.,'My Account')]")
	WebElement myAccount_Droplist;
	
	@FindBy(linkText = "Logout")
	WebElement logout_Button;

	@FindBy(id = "content")
	WebElement Content_Area;
	
	
	public void clickOnMyAccountDroplist()
	{
		myAccount_Droplist.click();
	}
	
	public void clickOnlogoutButton()
	{
		logout_Button.click();
	}
	
	public String fetchContentText()
	{
		return Content_Area.getText().toString();
	}
	
}
