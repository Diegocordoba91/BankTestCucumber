package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;

public class ScenarioManager {

    private static ScenarioManager instanceScenarioManager;
    private Scenario scenario;
    


    // Get the single instance of ScenarioManager
    private ScenarioManager(){}

    //Get a single instance of ScenarioManger
    public static ScenarioManager getScenarioManager(){
        if (instanceScenarioManager == null)
            instanceScenarioManager = new ScenarioManager();
        
            return instanceScenarioManager;
    }

    // Setter method for Scenario (used in Hooks)
    public void setScensario(Scenario scenario){
        this.scenario = scenario;
    }

    public void log(String message){
        if(scenario != null)
            scenario.log("📌 " + message);
        else
            System.out.println("⚠ Scenario is null. Cannot log: " + message);

    }

    public Scenario getScenario(){
        return scenario;
    }

    public void takesScreenshot(WebDriver driver)    {
            if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "ScreenShot");
        }


    }

    public String getScenarioStatus(){
        return scenario.getStatus().toString();
    }


 


    
}
