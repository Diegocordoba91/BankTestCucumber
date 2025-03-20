package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Global;
import utilities.GlobalVariables;

public class OpenAccountPage extends BasePage{

    GlobalVariables globalVariables = GlobalVariables.getInstance();

    private final By openNewAccount = By.xpath("//ul/li/a[text()='Open New Account']");
    private final By accountsOverview = By.xpath("//ul/li/a[text()='Accounts Overview']");
    private final By billPay = By.xpath("//ul/li/a[text()='Bill Pay']");
    private final By findTransactions = By.xpath("//ul/li/a[text()='Find Transactions']");
    private final By updateContactInfo = By.xpath("//ul/li/a[text()='Update Contact Info']");
    private final By requestLoan = By.xpath("//ul/li/a[text()='Request Loan']");
    private final By logout = By.xpath("//ul/li/a[text()='Log Out']");
    private final By buttonOpenNewAccount = By.cssSelector("input[value=\"Open New Account\"]");
    private final By messageAccountOpened = By.xpath("//h1[@class=\"title\" and contains(text(),'Account Opened!')]");
    private final By selectTypeAccount = By.cssSelector("select[id=\"type\"]");
    private final By numberAccount = By.id("newAccountId");
    private final By openAccountForm = By.id("openAccountForm");
    
    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    public void clicOpenNewAccount() throws InterruptedException{
        this.clickElement(openNewAccount);
    }

    public void selectAccount(String account) throws InterruptedException{
     this.selectionDropdownItem(selectTypeAccount, SelectionDropdown.TEXT, account);
        
    }

    public boolean verifyCreateAccount() throws InterruptedException{
        return this.elementDispaleyed(messageAccountOpened); 
    }

    public boolean verifyOpenAccountForm() throws InterruptedException{
        return this.elementDispaleyed(openAccountForm); 
    }

    public int getNumberAccount() throws InterruptedException{
        return Integer.parseInt(this.getTextElement(numberAccount));
    }
    
    public void clicOpenAccount() throws InterruptedException{
        this.clickElement(buttonOpenNewAccount);
    }

    public boolean verifyMessageAccountOpened() throws InterruptedException{
        return this.elementDispaleyed(messageAccountOpened);
    }

    public void setNumberAccount(String typeAccount,int numberAccount) throws InterruptedException{
        
        switch (typeAccount) {
            case "SAVINGS":
                globalVariables.setNewAccountSaving(numberAccount);
            case "CHECKING":
                globalVariables.setNewAccountChecking(numberAccount);
        
            default:
                new RuntimeException("Tipo de cuenta no valido");
        }
        
        
    }

    public int  getAccount(String typeAccount){

        switch (typeAccount) {
            case "SAVINGS":
                return globalVariables.getNewAccountSaving();
            case "CHECKING":
                return globalVariables.getNewAccountChecking();
            default: 
             throw new IllegalArgumentException("Tipo de cuenta invalida: "+typeAccount);       
        }
    }
    

    


    
}
