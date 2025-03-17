package steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.WebDriverWait;


import hooks.Hooks;
import io.cucumber.java.Scenario;
import pages.BasePage;
import pages.HomePage;
import pages.OpenAccountPage;
import pages.RegisterPage;
import utilities.GlobalVariables;
import utilities.ScenarioManager;
import utilities.ScreenShotUtil;


public class BaseSteps{

    protected WebDriver driver = Hooks.getDriver();
    private WebDriverWait wait;
    private Actions actions;
    

    protected BasePage basePage = new BasePage(driver, wait, actions);
    protected HomePage homePage = new HomePage(driver, wait, actions);
    protected RegisterPage registerPage = new RegisterPage(driver, wait, actions);
    protected ScenarioManager scenarioManager = ScenarioManager.getScenarioManager();
    protected OpenAccountPage openAccount = new OpenAccountPage(driver, wait, actions);
    protected GlobalVariables globalVariables = GlobalVariables.getInstance();
    
    public void takeScreenshot(){
        scenarioManager.takesScreenshot(driver, "STEPS");
    }




    
    
    
    

    
}
