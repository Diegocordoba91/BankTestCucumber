package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import models.Accounts;
import utilities.GlobalVariables;



public class AccountOverviewPage extends BasePage{

    private final By accountsOverview = By.xpath("//ul/li/a[text()='Accounts Overview']");
    private final By tableAccount = By.id("accountTable");
    
    public AccountOverviewPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
        //TODO Auto-generated constructor stub
    }

    public void clickButtonAccountsOverview() throws InterruptedException{
        this.clickElement(accountsOverview);
    }   
    
    public void extractAccountsNumber() throws InterruptedException{
        
        this.elementDispaleyed(tableAccount);

        ArrayList<List<String>> table = readTable(tableAccount);
        

        for (List<String> row : table) {
            if (!row.get(0).equals("Total")) {
                Accounts account = new Accounts(
                    Integer.parseInt(row.get(0).trim()), 
                    Double.parseDouble(row.get(1).replace("$", "")), 
                    Double.parseDouble(row.get(2).replace("$", "")), 
                    null);
                    globalVariables.setAccount(account);        
           }
            
        }

       
    }

    public boolean verifyTableAccountIsDisplayed() throws InterruptedException{
        return this.elementDispaleyed(tableAccount);
    }



}
