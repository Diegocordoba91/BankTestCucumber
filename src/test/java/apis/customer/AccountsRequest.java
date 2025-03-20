package apis.customer;

import java.net.ResponseCache;

import io.restassured.http.Method;
import io.restassured.response.Response;
import models.ListAccounts;
import utilities.BaseRequest;
import utilities.ResponseManager;

public class AccountsRequest extends BaseRequest{

    public void getAccounts(){

        final Response response = getRequest().
                    basePath("/customers/{idUser}/accounts").
                    pathParam("idUser", globalVariables.getIdUser()).
                    request(Method.GET);
        
        ResponseManager.setResponse(response);
    }


        

    public void setAccounts(){
        ListAccounts accounts = ResponseManager.htmlParser(ListAccounts.class);
        globalVariables.setAccountsApi(accounts);
    }


    
}
