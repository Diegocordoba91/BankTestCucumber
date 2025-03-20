package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


import utilities.GlobalVariables;

public class HomePage extends BasePage {

    protected GlobalVariables globalVariables = GlobalVariables.getInstance();

    private String titleHomePage = "ParaBank | Welcome | Online Banking";
    private By buttonRegister = By.xpath("//a[text()='Register']");
    private By inputUsername = By.name("username");
    private By inputPassword = By.name("password");
    private By buttonLogin = By.cssSelector("input[value=\"Log In\"]");
    private By errorMessage = By.xpath("//h1[@class=\"title\" and (text()='Error!')]");
    private By messageLoginSuccessful = By.id("showOverview");

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public String getTitleHomePage() throws InterruptedException{
        return this.getTitle();
    }
    
    public boolean VerifyTitleHomePageDisplayed() throws InterruptedException{
        return this.getTitle().contentEquals(titleHomePage);
    }
    
    public void clickButtonRegister() throws InterruptedException{
        this.moveElementClick(this.findElement(buttonRegister));
    }

    public void findButtonRegister(){
        try {

            findElement(buttonRegister);
            System.out.println("Encontre el boton");
            
        } catch (Exception e) {
            System.out.println("Elemento no disponible: "+e);
        }
     
       
        
    }

    public void login() throws InterruptedException{
        this.sendText(inputUsername, globalVariables.getUserName());
        this.sendText(inputPassword, globalVariables.getPassword());
    

    }

    public void clickButtonLogin() throws InterruptedException{
        this.clickElement(buttonLogin);
    }

    public boolean loginSuccessful() throws InterruptedException{
        return this.elementDispaleyed(messageLoginSuccessful);
    }



    


    

    


    
}
