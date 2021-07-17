package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import data.models.RegistrationDataModel;

public class RegistrationScreen {
	
	protected WebDriver wDriver;
	
	public RegistrationScreen(WebDriver wDriver)
	{		
		this.wDriver = wDriver;
		PageFactory.initElements(wDriver,this);
	}

	
	
	@FindBy(id = "input-firstname")
	WebElement firstName_Textfield;
	
	@FindBy(id = "input-lastname")
	WebElement lastName_Textfield;
	
	@FindBy(id = "input-email")
	WebElement email_Textfield;
	
	@FindBy(id = "input-telephone")
	WebElement telephone_Textfield;
	
	@FindBy(id = "input-password")
	WebElement password_Textfield;
	
	@FindBy(id = "input-confirm")
	WebElement confirmationPassword_Textfield;
	
	@FindBy(name = "newsletter")
	WebElement newsLetter_YesRadioButton;
	
	@FindBy(name = "agree")
	WebElement Conditions_Checkbox;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement Continue_Button;
	
	
	
	
	
	public void insertIntoFirstName(String value)
	{
		firstName_Textfield.sendKeys(value);
	}
	
	public void insertIntoLasttName(String value)
	{
		lastName_Textfield.sendKeys(value);
	}
	
	public void insertIntoEmail(String value)
	{
		email_Textfield.sendKeys(value);
	}
	
	public void insertIntoTelephone(String value)
	{
		telephone_Textfield.sendKeys(value);
	}
	
	public void insertIntoPassword(String value)
	{
		password_Textfield.sendKeys(value);
	}
	
	public void insertIntoConfirmationPassword(String value)
	{
		confirmationPassword_Textfield.sendKeys(value);
	}
	
	public void selectYesNewsLetter()
	{
		newsLetter_YesRadioButton.click();
	}
	
	public void checkConditionsCheckbox()
	{
		Conditions_Checkbox.click();
	}
	
	public void clickOnContinueButton()
	{
		Continue_Button.click();
	}
	
	public void fillRegistrationForm(RegistrationDataModel registrationDataModel)
	{
		firstName_Textfield.sendKeys(registrationDataModel.getFirstName());
		lastName_Textfield.sendKeys(registrationDataModel.getLastName());
		email_Textfield.sendKeys(registrationDataModel.getEmail());
		telephone_Textfield.sendKeys(registrationDataModel.getTelephone());
		password_Textfield.sendKeys(registrationDataModel.getPassword());
		confirmationPassword_Textfield.sendKeys(registrationDataModel.getPassword());		
	}
	
	
}
