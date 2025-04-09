package apis.customer;



import utilities.BaseRequest;
import utilities.GlobalVariables;
import utilities.ResponseManager;
import utilities.ScenarioManager;

import java.util.concurrent.ExecutionException;

import javax.management.RuntimeErrorException;

import org.apache.hc.core5.http.protocol.ResponseServer;

import com.github.javafaker.Faker;

import io.restassured.http.Method;
import io.restassured.response.Response;


public class CustomerRequest extends BaseRequest{


    

    public void getCustomer(String typeUSer) {
        Faker faker = new Faker();
         try {
            switch (typeUSer) {
                case "Correcto":
                    generatedRequestGetCustomer(globalVariables.getUserName(), globalVariables.getPassword());;
                    
                break;
                case "Incorrecto":
                    generatedRequestGetCustomer(faker.name().username(), faker.internet().password());
                break;
            }
            
         } catch (Exception e) {
            throw new RuntimeException("Tipo de usuario incorrecto");
         }          


    }

    public void generatedRequestGetCustomer(String userName, String password){
        
        final Response response = getRequest().
                basePath("/login/{userName}/{password}").
                pathParam("userName", userName).
                pathParam("password", password).
                request(Method.GET);
        ResponseManager.setResponse(response);
        
    }    
     

    public void setIdUser(){

        globalVariables.setIdUser(Integer.parseInt(ResponseManager.getHTMLPath("id")));
        
        
    }

    public int getIDCustomer(){

        return globalVariables.getIdUser();


        
    }

    public void getCustomerById(int idCustomer){

        final Response response = getRequest().
                basePath("/customers/{idCustomer}").
                pathParam("idCustomer", idCustomer).
                request(Method.GET);
        ResponseManager.setResponse(response);


    }



    




    
}
