package utilities;

import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestManager {


    
    public void initRequest(){
        Logs.debug("Creando el request");
        final var request = RestAssured.given().spec(buildRequestSpec());
        
        Logs.debug("Guardando el request en el RequestProvider");
        new RequestProvider().set(request);//Esta código asigna el request a la variable threadLocal de la clase RequestProvider

        
    }

        /* 
         *  public String toStringRequest(){
        final var requesSpect = RequestProvider.get();
        final var request = String.format("(/s)" /t "/s", requesSpect.getUri());
        return 
    }

        */
   
 

    
    public RequestSpecification buildRequestSpec(){

        return new RequestSpecBuilder() // 1. Crea un constructor para definir la configuración de la solicitud
        .addFilter(new RequestFilter()) // 2. Agrega un filtro personalizado (modifica la solicitud antes de enviarla)
        .setBaseUri("https://parabank.parasoft.com/parabank/services/bank") // 3. Establece la URI base de la solicitud
        .setContentType(ContentType.JSON) // 3. Establece el tipo de contenido de la solicitud en JSON
        .build(); // 4. Construye y devuelve la especificación de la solicitud
    }



    




    
}
