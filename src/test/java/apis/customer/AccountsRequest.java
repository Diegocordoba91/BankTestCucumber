package apis.customer;

import java.net.ResponseCache;
import java.util.Random;

import org.testng.internal.MethodInheritance;

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

    public void getTransactionAccount(int numberAccount){

        final Response response = getRequest().
                                    basePath("/accounts/{accountId}/transactions").
                                    pathParam("accountId", numberAccount).
                                    request(Method.GET);

        ResponseManager.setResponse(response);
                                    

    }


        

    public void setAccountsApi(){
        ListAccounts accounts = ResponseManager.htmlParser(ListAccounts.class);
        globalVariables.setAccountsApi(accounts);
    }


    public  int getNumberAccount(){

        Random random = new Random();

        return globalVariables.getAccountsApi().account().get(random.nextInt(globalVariables.getAccountsApi().account().size())).accountNumber();
        
        
    }


    
}
