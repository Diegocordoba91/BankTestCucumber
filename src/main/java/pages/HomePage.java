package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private String titleHomePage = "ParaBank | Welcome | Online Banking";
    private By buttonRegister = By.xpath("//a[text()='Register']");

    public HomePage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
        //TODO Auto-generated constructor stub
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


    
}
