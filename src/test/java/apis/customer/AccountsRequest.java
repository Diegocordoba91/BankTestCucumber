package apis.customer;

import java.net.ResponseCache;
import java.util.Map;
import java.util.Random;

import org.testng.Assert;
import org.testng.internal.MethodInheritance;

import io.cucumber.java.lu.a;
import io.restassured.http.Method;
import io.restassured.response.Response;
import models.Account;
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

    public int getNumberAccountFunds(){

        var accounts = globalVariables.getAccountsApi();
        int numberAccount = 0;

        for(int i = 0; i < accounts.account().size(); i++){

            if(accounts.account().get(i).balance()>100)
                numberAccount = accounts.account().get(i).accountNumber();
        

        }
        return numberAccount;
    }

    public void getCreateNewAccount(String typeAccount){

        int numberTypeAccount =     switch (typeAccount) {
            case "CHECKING" -> numberTypeAccount = 0;
            case "SAVINGS" -> numberTypeAccount = 1;
            case "LOAN" -> numberTypeAccount = 2;
            default -> throw new IllegalArgumentException("Type Account not supported: " + typeAccount);
        };

        Map<String, String> body = Map.of(
            "customerId", String.valueOf(globalVariables.getIdUser()),
            "newAccountType", String.valueOf(numberTypeAccount),
            "fromAccountId", String.valueOf(getNumberAccountFunds())
        );

        final Response response = getRequest().
                                    basePath("createAccount").
                                    queryParams(body).
                                    request(Method.POST);
        ResponseManager.setResponse(response);
        Account account = ResponseManager.htmlParser(Account.class);
        globalVariables.addAccountApi(account);

    
    }






    
}
