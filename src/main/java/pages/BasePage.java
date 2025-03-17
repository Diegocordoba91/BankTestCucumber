package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.lu.a;
import utilities.ScreenShotUtil;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions ;

    

    public BasePage(WebDriver driver, WebDriverWait wait, Actions actions) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    protected String getTitle() throws InterruptedException {
        try {
            wait.until(wd -> 
            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
            return this.driver.getTitle();

            
        } catch (Exception e) {
            System.out.println("La pagina no cargo completamente. Error: " + e);
            return null;
        }
        
        
    }

    protected void clickElement (By locator) throws InterruptedException{

        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            this.driver.findElement(locator).click();    
        } catch (Exception e) {
            System.out.println("Elemento" + locator + "no disponible. Error: "+e);
            
        }       
        
    }

    protected String getTextElement(By locator){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return this.driver.findElement(locator).getText();
        } catch (Exception e) {
            throw new RuntimeException("Elemento" + locator + "no disponible. Error: "+e);
            
        }
    }

    protected void sendText(By locator, String text) throws InterruptedException{

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            this.driver.findElement(locator).sendKeys(text);
        } catch (Exception e) {
            System.out.println("Elemento" + locator + "no disponible. Error: "+e);
        }
        
        
    }

    protected boolean elementDispaleyed(By locator) throws InterruptedException{
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return this.driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            System.out.println("Elemento " + locator + " no se encuentra visible.");
            return false;
        }
    }

    protected String currentUrl(){

        try {
            wait.until(wd -> 
            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
            return this.driver.getCurrentUrl();
            
        } catch (Exception e) {
            return null;
        }
        
    }

    protected WebElement findElement(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    protected void moveElementClick(WebElement webElement){

        actions.moveToElement(webElement).click().perform();

    }


    protected enum SelectionType {
        TEXT,
        VALUE,
        INDEX

    }

    protected void selectionDropdownItem (By locator,  SelectionType selectionType , String selectectionValue){
        
        
        WebElement element = driver.findElement(locator);
        Select select = new Select(element);
        
        try {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator,1));
            switch (selectionType) {
                case TEXT:
                    select.selectByVisibleText(selectectionValue);
                    break;
                case VALUE:
                    select.selectByValue(selectectionValue);    
                    break;
                case INDEX:
                    try {
                        int index = Integer.parseInt(selectectionValue);
                        select.selectByIndex(index);
                    } catch (Exception e) {
                        System.out.println("Error: para la opción index, debe ingresar un número");
                    }
                default:
                    System.out.println("Error: selectionTypre no valido, debe ingresar 'text, 'value', o 'index'");
            }
            
        } catch (Exception e) {
            System.out.println("El localizador "+locator+" no se encuentra visible");
        }

        
    }

    public void takeScreenshot(String fileName) {

        ScreenShotUtil.takeScreenshot(this.driver,fileName);
    }

    protected void clearInput(By locator){
        this.driver.findElement(locator).clear();
    }

    protected Map<String, Object> verifyElementsPreset(By[] elements) throws InterruptedException{

        Map <String, Object> result = new HashMap<>();
        boolean resultValue = true;
        List<String> foundElementList = new ArrayList<>();
        for(By element: elements){
            if (!elementDispaleyed(element)) {
                resultValue = false;
                foundElementList.add(element.toString());
                
            }

        }
        
        result.put("result", resultValue);
        result.put("elements", foundElementList);

              

        return result;
    }


}
