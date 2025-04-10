package steps.api;

import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.devtools.v131.network.model.Request;

import apis.customer.AccountsRequest;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.GlobalVariables;
import utilities.RequestManager;
import utilities.ResponseManager;
import utilities.ScenarioManager;

public class AccountsStepDefinitionApi {

    AccountsRequest accountsRequest = new AccountsRequest();
    GlobalVariables globalVariables = GlobalVariables.getInstance();
    ScenarioManager scenarioManager = ScenarioManager.getScenarioManager();

    @When("Se llama al request de cuentas del usuario, obteniendo el id del usuario")
    public void getAccountsByUserId() {
        accountsRequest.getAccounts();
        scenarioManager.log(RequestManager.toStringRequest());
    }

    @Then("Se almancenan las cuentas del usuario")
    public void Se_almancenan_las_cuentas_del_usuario() {
        accountsRequest.setAccountsApi();
        scenarioManager.log("Las cuentas almacenadas son: "+globalVariables.getAccountsApi().toString());

        
    }

    @Then("Se verifica que el id del response sea el del usuario")
    public void Se_verifica_que_el_id_del_response_sea_el_del_usuario() {
        org.testng.Assert.assertEquals(ResponseManager.getHTMLPath("customerId"), String.valueOf(globalVariables.getIdUser()) );
    }


    @Given("Se obtiene el núemero de cuenta del usuario")
    public void Se_obtiene_el_n_emero_de_cuenta_del_usuario() {

        accountsRequest.getNumberAccount();

    }

    @When("Se llama al request de consulta de transacciones")
    public void Se_llama_al_request_de_consulta_de_transacciones() {
        accountsRequest.getTransactionAccount(accountsRequest.getNumberAccount());
        scenarioManager.log(RequestManager.toStringRequest());
    
    }

    @Given("Se obtiene el número de cuenta con fondos")
    public void getNumberAccountWithFunds() {
        accountsRequest.getNumberAccountFunds();
    }

    @When("Se llama al request de creción de cuenta con tipo de cuenta {string}")
    public void Se_llama_al_request_de_creci_n_de_cuenta_con_tipo_de_cuenta(String typeAccount) {
        accountsRequest.getCreateNewAccount(typeAccount);
        scenarioManager.log(RequestManager.toStringRequest());
    }

    

    @Then("Se verifica el tipo de cuenta creada")
    public void Se_verifica_el_tipo_de_cuenta_creada() {
        org.testng.Assert.assertEquals(ResponseManager.getHTMLPath("id"), String.valueOf(globalVariables.getAccountsApi().account().get(globalVariables.getAccountsApi().account().size()-1).accountNumber()) );
        
    }





    
    
}
