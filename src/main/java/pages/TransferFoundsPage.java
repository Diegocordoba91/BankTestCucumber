package pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.fge.uritemplate.vars.values.ValueType;

public class TransferFoundsPage extends BasePage {

    private Random random = new Random();
    private final By transferFunds = By.xpath("//ul/li/a[text()='Transfer Funds']");
    private final By transferFormApp = By.id("transferApp");
    private final By inputAmount = By.id("amount");
    private final By selectFromAccount = By.id("fromAccountId");
    private final By selectToAccount = By.id("toAccountId");
    private final By buttonTransfer = By.cssSelector("input[type=\"submit\"]");
    private final By messageTransferComplete = By.xpath("//h1[@class=\"title\" and contains(text(),'Complete')]");
    
    
    
    
    public TransferFoundsPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
        //TODO Auto-generated constructor stub
    }

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

        this.selectionDropdownItem(selectFromAccount, SelectionType.VALUE, String.valueOf(numberAccount));

        

    }

    public void selectToAccount(){
        int numberAccount = globalVariables.getAccounts().get(random.nextInt(globalVariables.getAccounts().size())).accountNumber();

        this.selectionDropdownItem(selectToAccount, SelectionType.VALUE, String.valueOf(numberAccount));

    }

    public void clickButtonTransfer() throws InterruptedException{
        this.clickElement(buttonTransfer);
    }

    public boolean verifyMessageTransferComplete() throws InterruptedException {
        return this.elementDispaleyed(messageTransferComplete);
    }








    
}
