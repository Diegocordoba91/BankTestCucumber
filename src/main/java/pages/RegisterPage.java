package pages;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import io.cucumber.java.it.Ma;
import models.User;
import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Global;
import utilities.GlobalVariables;


public class RegisterPage extends BasePage{

    //utilities
    private final Faker faker = new Faker();

    protected static User userGlobalVariable;
    protected GlobalVariables globalVariables = GlobalVariables.getInstance();
    private String typeUser;
    

       

    //Locators
    private By titleRegisterPage = By.xpath("//h1[@class=\"title\" and contains(text(),'Signing')]");
    private By formRegisterUser = By.cssSelector("form[id=\"customerForm\"]");
    private String urlRegisterPage = "https://parabank.parasoft.com/parabank/register.htm";
    private By inputFirstName = By.id("customer.firstName");
    private By inputLastaName = By.id("customer.lastName");
    private By inputAdress = By.id("customer.address.street");
    private By inputCity = By.id("customer.address.city");
    private By inputState = By.id("customer.address.state");
    private By inputZipCode = By.id("customer.address.zipCode");
    private By inputPhone = By.id("customer.phoneNumber");
    private By inputSSN = By.id("customer.ssn");
    private By inputUserName = By.id("customer.username");
    private By inputPassword = By.id("customer.password");
    private By inputConfirmPassword = By.id("repeatedPassword");
    private By buttonRegisterForm = By.cssSelector("input[value=\"Register\"]");
    private By messageAccountSuccessfly = By.xpath("//p[contains(text(),'successfully')]");
    private By messageErrorUserExist = By.xpath("//span[text()='This username already exists.']");
    private By inputLoginUserName = By.name("username");
    private By inputLogisPassword = By.name("password");
    private By buttonLoginIn = By.cssSelector("input[value='Log In']");
    private By messageAcoountCreated = By.id("rightPanel");
    private By home = By.cssSelector("li[class='home']");
    private By usernameExists = By.id("customer.username.errors");
    private By[] inputMessageIsRequired ={
        By.id("customer.firstName.errors"),
        By.id("customer.lastName.errors"),
        By.id("customer.address.street.errors"),
        By.id("customer.address.city.errors"),
        By.id("customer.address.state.errors"),
        By.id("customer.address.zipCode.errors"),
        By.id("customer.ssn.errors"),
        By.id("customer.username.errors"),
        By.id("customer.password.errors"),
        By.id("repeatedPassword.errors")
    };


    
    public RegisterPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
        //TODO Auto-generated constructor stub
    }

    public boolean titleRegisterPagePresensent() throws InterruptedException{

        return this.elementDispaleyed(titleRegisterPage);
        
    }

    public boolean formRegisterUserDisplayed() throws InterruptedException{
        return this.elementDispaleyed(formRegisterUser);
    }

    public boolean isCurrentUrlRegisterPage(){
        return this.currentUrl().equals(urlRegisterPage);
    }

    public void newAccountRegistrationForm(String type) throws InterruptedException{

        User newUser = User.generateUser(type);
        try {

            this.sendText(inputFirstName, newUser.firstName());
            this.sendText(inputLastaName, newUser.lastaName());
            this.sendText(inputAdress, newUser.address());
            this.sendText(inputCity, newUser.city());
            this.sendText(inputState, newUser.state());
            this.sendText(inputZipCode,Integer.toString(newUser.zipCode()) );
            this.sendText(inputPhone, newUser.phone());
            this.sendText(inputSSN, newUser.ssn());
            this.sendText(inputUserName, newUser.userName());
            this.sendText(inputPassword, newUser.password());
            this.sendText(inputConfirmPassword, newUser.password());
            userGlobalVariable = newUser;
            typeUser = type;

        } catch (Exception e) {
            System.out.println("\"Error registro de formulario: "+e );
            
        }
              
    }

    public void clearFormRegisterUser() throws InterruptedException{
        this.clearInput(inputFirstName);
        this.clearInput(inputLastaName);
        this.clearInput(inputAdress);
        this.clearInput(inputCity);
        this.clearInput(inputState);
        this.clearInput(inputZipCode);
        this.clearInput(inputPhone);
        this.clearInput(inputSSN);
        this.clearInput(inputUserName);
        this.clearInput(inputPassword);
        this.clearInput(inputConfirmPassword);        
    }

    public int verifyRegisterUserSuccessful(boolean registerPagePresent) throws InterruptedException{

        int count=0;
       
        if(registerPagePresent){

            while (this.elementDispaleyed(usernameExists)) {
                
                clearFormRegisterUser();            
                newAccountRegistrationForm(typeUser);
                this.clickElement(buttonRegisterForm);
                count++;
            }
        
        }
        globalVariables.setUserName(userGlobalVariable.userName());
        globalVariables.setPassword(userGlobalVariable.password());
        globalVariables.setName(userGlobalVariable.firstName());    
        globalVariables.setLastName(userGlobalVariable.lastaName());
             
       
        return count;        
    }

    public void clickButtonRegisterNewAccount() throws InterruptedException{
        this.clickElement(buttonRegisterForm);
    

    }

    public boolean succefullCreatedAccount() {
        try {

            return this.elementDispaleyed(messageAccountSuccessfly);
                           
                   
        } catch (Exception e) {
            System.out.println("La cuenta no fue creada");
            return false;    
        }
            
    }

    public boolean accountExist() throws InterruptedException{

        return this.elementDispaleyed(messageErrorUserExist);
    }

    public void inputUserNameandPassword() throws InterruptedException{
        //this.clickElement(home);
        this.sendText(inputLoginUserName, globalVariables.getUserName());
        this.sendText(inputLogisPassword, globalVariables.getPassword());
    }

    public void clickLogIn() throws InterruptedException{
        this.clickElement(buttonLoginIn);
    }

    public boolean verifyWelcomeMessage() throws InterruptedException{
        if (!this.elementDispaleyed(messageAcoountCreated)) {
            System.out.println("Elemento no esta visible tomando captura........");
            //this.takeScreenshot("errorAccesoCuentaCreada");

        } else {
            System.out.println("Elemento visible no se toma captura de pantalla");
            
        }
        return this.elementDispaleyed(messageAccountSuccessfly);
    }

    public String getMessageAccountSuccefull(){
        return this.getTextElement(messageAccountSuccessfly);
    }

    public Map<String, Object>  verifyMesaagesIsRequired() throws InterruptedException{

        Map<String, Object> result = this.verifyElementsPreset(inputMessageIsRequired);
        return result;

    }

    public boolean verifyTitleRegisterPage() throws InterruptedException{
        return this.elementDispaleyed(titleRegisterPage);
    }
}
