package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

        switch (browser.toLowerCase()) {
            case "chrome":
                
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
                    chromeOptions.addArguments("--headless");
                    
                }
                return new ChromeDriver(chromeOptions);
        
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            default:
                throw new IllegalArgumentException("❌ Unsupported browser: \" + browser");

        }
   

    }

    public static void quitDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }

    

    
  
    
}
