package apis.customer;



import utilities.BaseRequest;
import utilities.GlobalVariables;
import utilities.ResponseManager;
import utilities.ScenarioManager;
import io.restassured.http.Method;
import io.restassured.response.Response;
import models.ListAccounts;
import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Global;

public class CustomerRequest extends BaseRequest{


    

    public void getCustomer(String userName, String password) {
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



    




    
}
