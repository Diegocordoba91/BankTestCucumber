package pages;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;



import models.Account;

public class TransferFoundsPage extends BasePage {

    public TransferFoundsPage(WebDriver driver) {
        super(driver);
        //TODO Auto-generated constructor stub
    }

    private Random random = new Random();
    private final By transferFunds = By.xpath("//ul/li/a[text()='Transfer Funds']");
    private final By transferFormApp = By.id("transferApp");
    private final By inputAmount = By.id("amount");
    private final By selectFromAccount = By.cssSelector("select#fromAccountId");
    private final By selectToAccount = By.id("toAccountId");
    private final By buttonTransfer = By.cssSelector("input[type=\"submit\"]");
    private final By messageTransferComplete = By.xpath("//h1[@class=\"title\" and contains(text(),'Complete')]");
    private final By selectedAccountFrom = By.xpath("//select[@id=\"fromAccountId\"]//option[@selected=\"selected\"]");
    private final By selectedAccountTo = By.xpath("//select[@id=\"toAccountId\"]//option[@selected=\"selected\"]");
    
    
    
    

    public void clickButtonTransferFounds() throws InterruptedException{
        this.clickElement(transferFunds);
    }

    public boolean isTransferFormPresent() throws InterruptedException {
        return this.elementDispaleyed(transferFormApp);
    }

    public void inputAmount(String amount) throws InterruptedException{
        this.sendText(inputAmount, String.valueOf(amount));
    }

    public void selectFromAccount(){

        int numberAccount = globalVariables.getAccounts().get(random.nextInt(globalVariables.getAccounts().size())).accountNumber();

        this.selectionDropdownItem(selectFromAccount, SelectionDropdown.TEXT, Integer.toString(numberAccount));

        

    }

    public void selectToAccount(){
        int numberAccount = globalVariables.getAccounts().get(random.nextInt(globalVariables.getAccounts().size())).accountNumber();

        this.selectionDropdownItem(selectToAccount, SelectionDropdown.TEXT, Integer.toString(numberAccount));

    }

    public void clickButtonTransfer() throws InterruptedException{
        this.clickElement(buttonTransfer);
    }

    public boolean verifyMessageTransferComplete() throws InterruptedException {
        return this.elementDispaleyed(messageTransferComplete);
    }

    public Optional<Account> getBalanceSelectedAccountFrom() throws InterruptedException {

        int numberAccount = Integer.parseInt(this.getTextElement(selectedAccountFrom));
        
        return globalVariables.getAccounts().
                    stream().
                    filter(account -> account.accountNumber()==numberAccount).
                    findFirst();
                       
    }

    public boolean isFondsAccountFromEnough(Double amount) throws NumberFormatException, InterruptedException{
        
        return getBalanceSelectedAccountFrom().get().balance()>=amount?true:false;
    
    }


    public String verifySelectsPresents(){

        List<WebElement> list = this.driver.findElements(By.tagName("option"));
        String result="";
        for (WebElement webElement : list){
                result += "\n"+webElement.getDomAttribute("value");
        } 

        return result;

    }
    
    







    
}
