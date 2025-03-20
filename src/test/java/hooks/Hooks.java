package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.epam.healenium.SelfHealingDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utilities.BrowserManager;
import utilities.ConfigReader;
import utilities.RequestManager;
import utilities.ScenarioManager;

public class Hooks {

    private static WebDriver driver;
    private RequestManager requestManager = new RequestManager();
    private String browser = ConfigReader.getProperty("browser");
    private ScenarioManager scenarioManager = ScenarioManager.getScenarioManager();

    @Before(value = "@UI")
    public void setupWebDriver(Scenario scenario){
        
        driver = BrowserManager.getDriver(browser);
        scenarioManager.setScensario(scenario);
        
    }

    @Before(value = "@API")
    public void setupApi(Scenario scenario){
        requestManager.initRequest();
        scenarioManager.setScensario(scenario);        
        
    }

    
    @After(value = "@UI")
    public void tearDownWebDriver(){
        scenarioManager.takesScreenshot(driver, "HOOKS");
        BrowserManager.quitDriver();
        scenarioManager.log("✅ Scenario finished: " + scenarioManager.getScenarioStatus());

    }

    @After(value = "@API")
    public void tearDownApi(Scenario scenario){
        scenarioManager.log("✅ Scenario finished: " + scenario.getStatus());
        
    }
    
    @BeforeAll
    public static void beforeAll(){
        System.out.println("Before all");
    }

    @AfterAll
    public static void AfterAll(){
        System.out.println("After all");

    }

    public static WebDriver getDriver(){
        return driver;
    }





}
