package steps.api;

import org.apache.logging.log4j.core.util.Assert;

import apis.customer.AccountsRequest;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.GlobalVariables;
import utilities.ResponseManager;
import utilities.ScenarioManager;

public class AccountsStepDefinitionApi {

    AccountsRequest accountsRequest = new AccountsRequest();
    GlobalVariables globalVariables = GlobalVariables.getInstance();
    ScenarioManager scenarioManager = ScenarioManager.getScenarioManager();

    @When("Se llama al request de cuentas del usuario, obteniendo el id del usuario")
    public void getAccountsByUserId() {
        accountsRequest.getAccounts();
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
    
    }

    
    
}
