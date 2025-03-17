package utilities;

import io.restassured.specification.RequestSpecification;

public class BaseRequest {


    

    protected RequestSpecification getRequest(){// 1. Crea un método protegido para obtener la especificación de la solicitud
        return new RequestProvider().get();// 2. Devuelve una nueva instancia de RequestProvider
    }

    
    
}
