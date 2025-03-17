package apis.customer;



import utilities.BaseRequest;

import utilities.ResponseManager;
import utilities.ScenarioManager;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class CustomerRequest extends BaseRequest{

    ScenarioManager scenarioManager = ScenarioManager.getScenarioManager();
    

    public void getCustomer(String userName, String password) {
        final Response response = getRequest().
                                    basePath("/login/{userName}/{password}").
                                    pathParam("userName", userName).
                                    pathParam("password", password).
                                    request(Method.GET);
    ResponseManager.setResponse(response);

    }


    
}
