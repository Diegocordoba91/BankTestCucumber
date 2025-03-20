package utilities;

import java.io.File;
import java.io.IOException;
import java.net.ResponseCache;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import groovy.xml.XmlParser;
import io.cucumber.java.Scenario;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import models.ListAccounts;

public class ResponseManager {

    private static Response response;
    private static ScenarioManager scenarioManager = ScenarioManager.getScenarioManager();
   

    
    public static void verifyStatusCode(int expectedStatusCode){
        Logs.info( "Verificando el status code");
        
        
        try {
            Assertions.assertEquals(expectedStatusCode, response.getStatusCode(),"Status code mismatch");
            
        } catch (AssertionError e) {

            scenarioManager.log("❌ ERROR: Expected " + expectedStatusCode + 
                                ", but got: " + ResponseManager.getStatusCode());
            throw e;
        }
        
        
    }

    public static int getStatusCode(){
        return response.getStatusCode();
    }

    public static int getResponseTime(){
        return (int) response.getTime() ;
    }
    
    
    public static void verifyTime(int expectedTime){
        Logs.info( "Verificando el tiempo de respuesta");

        Assertions.assertTrue(response.getTime() < expectedTime);
    }

    
    public static void schemaValitation(String schemaPath){
        Logs.info( "Validando el esquema de la respuesta");
        
        scenarioManager.log(ResponseManager.toStringBodyResponse());
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
    }

    public static <T> T getBody(Class<T> clazz){//Método que recibe una clase y devuelve un objeto de esa clase
        return response.getBody().as(clazz);
    }

    public static String getPathAsString(String path){
        return response.path(path).toString();
    }

    public static void setResponse(Response response){//Asigna el response obtenido de la petición al response del ResponseManager
        ResponseManager.response = response;
    }

    public static String toStringBodyResponse(){
        return response.getBody().asString();
    }

    public static String getHTMLPath(String path){
        
        final String htmlBodyResponse = response.getBody().asString();

        final Document document = Jsoup.parse(htmlBodyResponse);

        final Elements links = document.select(path);
        String finalPath=""; 
        
        for(Element link : links){
             finalPath= link.text();
        }

        return finalPath;

    }
    
    public static <T>T htmlParser(Class<T> clazz){

        
        try {
            String xmlResponse = response.getBody().asString();

            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(xmlResponse, clazz);
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    
    
}
