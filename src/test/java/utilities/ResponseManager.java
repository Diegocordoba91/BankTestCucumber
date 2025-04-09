package utilities;

import java.io.File;
import java.io.IOException;
import java.net.ResponseCache;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.math3.exception.util.ExceptionContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.json.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import groovy.xml.XmlParser;
import io.cucumber.java.Scenario;
import io.restassured.http.Header;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.internal.support.Prettifier;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import models.ListAccounts;

public class ResponseManager {

    private static Response response;
    private static ScenarioManager scenarioManager = ScenarioManager.getScenarioManager();
    private static FilterableRequestSpecification requestSpec;
   

    
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
    
    public static String toStringRequest(){
        final String requestMethodURL = String.format("(%s) \t %s", requestSpec.getMethod(), requestSpec.getURI());
        return requestMethodURL;
    }

    public static String toStringBodyResponse(){

        int responseStatusCodeResponseTime = response.getStatusCode();
        String responseHeaderMessage = getHeadersInfo(response.getHeaders().asList());
        final var responseBodyMessage = response.getBody().asPrettyString();


        if (responseBodyMessage == null) {
            Logs.trace("requestInfo body is null");
            return String.format(
                    """
                            \n=============================================
                            ✅ Response Body
                            =============================================
                            Status Code: %s
                            Response Headers:
                            %s
                            """, responseStatusCodeResponseTime, responseHeaderMessage
            );
        } else {
            Logs.trace("requestInfo body is NOT null");
            return String.format(
                    """
                            \n=============================================
                            Response
                            =============================================
                            Status Code: %s
                            Response Headers:
                            %s
                            Response Body:
                            %s
                            """, responseStatusCodeResponseTime, responseHeaderMessage, responseBodyMessage
            );
        }
    }

    public static String getHeadersInfo(List<Header> listHeader) {
        final var stringBuilder = new StringBuilder();
        for (var header : listHeader) {
            stringBuilder.append(String.format("\t%s: %s%n", header.getName(), header.getValue()));
        }
        return stringBuilder.toString();
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

    public static JsonNode xmlToJsonConvert(){

        try {
            XmlMapper xmlMapper = new XmlMapper();

            JsonNode jsonNode= xmlMapper.readTree(response.getBody().asString().getBytes());

            return jsonNode;

    
            
        } catch (IOException e) {
            throw new RuntimeException("Error conversión xml a Json: "+e);
        }


    }

    
    
}
