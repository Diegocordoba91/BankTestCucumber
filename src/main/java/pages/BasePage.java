package pages;

import java.lang.reflect.Array;
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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



import io.cucumber.java.lu.a;
import utilities.GlobalVariables;
import utilities.ScreenShotUtil;

public class BasePage {

    protected GlobalVariables globalVariables = GlobalVariables.getInstance();
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions ;
    

    

    public BasePage(WebDriver driver) {
        
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    protected String getTitle() throws InterruptedException {
        waitForPageToLoad();
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
        waitForPageToLoad();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            this.driver.findElement(locator).click();    
        } catch (Exception e) {
            System.out.println("Elemento" + locator + "no disponible. Error: "+e);
            
        }       
        
    }

    protected String getTextElement(By locator){
        waitForPageToLoad();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return this.driver.findElement(locator).getText();
        } catch (Exception e) {
            throw new RuntimeException("Elemento" + locator + "no disponible. Error: "+e);
            
        }
    }

    protected void sendText(By locator, String text) throws InterruptedException{
        waitForPageToLoad();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            this.driver.findElement(locator).sendKeys(text);
        } catch (Exception e) {
            System.out.println("Elemento" + locator + "no disponible. Error: "+e);
        }
        
        
    }

    protected boolean elementDispaleyed(By locator) throws InterruptedException{
        waitForPageToLoad();
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
        waitForPageToLoad();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    protected void moveElementClick(WebElement webElement){

        actions.moveToElement(webElement).click().perform();

    }


    protected enum SelectionDropdown {
        TEXT,
        VALUE,
        INDEX

    }

 
    protected void selectionDropdownItem (By locator,  SelectionDropdown selectionType , String selectectionValue){
        
        waitForPageToLoad();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("option")));
        WebElement element = driver.findElement(locator);
        Select select = new Select(element);

        
        try {            
            switch (selectionType) {
                case TEXT:
                    select.selectByVisibleText(selectectionValue.trim());
                    break;
                case VALUE:
                    select.selectByValue(selectectionValue.trim());    
                    break;
                case INDEX:
                    try {
                        int index = Integer.parseInt(selectectionValue.trim());
                        select.selectByIndex(index);
                    } catch (Exception e) {
                        System.out.println("Error: para la opción index, debe ingresar un número");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Opción de selección no válida: " + selectionType);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error in select item : "+e);
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

    public ArrayList<List<String>> readTable(By locator) throws InterruptedException{ 

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        List<WebElement> rows = driver.findElements(By.xpath(".//tbody/tr"));

        ArrayList<List<String>> table = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            List<String> rowData = new ArrayList<>();
            for (WebElement cell : cells) {
                rowData.add(cell.getText());
            }
            
            table.add(rowData);
        }  

        return table;
    }

    public void waitForPageToLoad(){
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

}
