package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.epam.healenium.SelfHealingDriver;

import groovyjarjarantlr4.v4.parse.ANTLRParser.ruleReturns_return;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserManager {
    private static WebDriver driver;
    final String baseUrl="";
    

    //Get driver instance
    public static WebDriver getDriver(String browser) {
        if(driver==null){
            driver = initializeDriver(browser);
            driver.get("https://parabank.parasoft.com/parabank/index.htm");
            driver.manage().window().maximize();
        }

        return driver;
            
    }

   

    private static WebDriver initializeDriver(String browser){

        WebDriver delegateDriver;

        switch (browser.toLowerCase()) {
            case "chrome":
                
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
                    chromeOptions.addArguments("--headless");
                    
                }

                delegateDriver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                delegateDriver= new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                delegateDriver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("❌ Unsupported browser: \" + browser");

        }

        return delegateDriver;
   

    }

    public static void quitDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }

    

    
  
    
}
