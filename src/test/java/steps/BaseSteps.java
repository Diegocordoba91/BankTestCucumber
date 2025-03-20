package steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;


import hooks.Hooks;
import io.cucumber.java.Scenario;
import pages.AccountOverviewPage;
import pages.BasePage;
import pages.HomePage;
import pages.OpenAccountPage;
import pages.RegisterPage;
import pages.TransferFoundsPage;
import utilities.GlobalVariables;
import utilities.ScenarioManager;
import utilities.ScreenShotUtil;


public class BaseSteps{

    protected WebDriver driver = Hooks.getDriver();
    private WebDriverWait wait;
    private Actions actions;
    

    protected BasePage basePage = new BasePage(driver);
    protected HomePage homePage = new HomePage(driver);
    protected RegisterPage registerPage = new RegisterPage(driver);
    protected ScenarioManager scenarioManager = ScenarioManager.getScenarioManager();
    protected OpenAccountPage openAccount = new OpenAccountPage(driver);
    protected GlobalVariables globalVariables = GlobalVariables.getInstance();
    protected TransferFoundsPage transferFoundsPage = new TransferFoundsPage(driver);
    protected AccountOverviewPage accountOverviewPage = new AccountOverviewPage(driver);
    protected SoftAssert softAssert = new SoftAssert(); 
    
    public void takeScreenshot(){
        scenarioManager.takesScreenshot(driver, "STEPS");
    }




    
    
    
    

    
}
