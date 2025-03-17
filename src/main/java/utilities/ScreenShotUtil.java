package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en_lol.WEN;

public class ScreenShotUtil {
   


    public static void takeScreenshot(WebDriver driver,String fileName) {
        
        // Convertir WebDriver a TakesScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Tomar la captura de pantalla como un archivo
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

        // Definir la ubicación donde se guardará la captura

        File destFile = new File("src/test/resources/screenshots/" + fileName +".png");

        var fileExists = destFile.exists();
        int counter = 1;
        
        try {
            while (fileExists) {
                destFile = new File("src/test/resources/screenshots/" + fileName + counter + ".png");
                fileExists = destFile.exists();
                counter++;
                
            }
            // Copiar el archivo al destino
            FileUtils.copyFile(srcFile, destFile);
            
            System.out.println("Captura de pantalla guardada en: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al guardar la captura de pantalla: " + e.getMessage());
        }
    }
    
}
