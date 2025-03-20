package steps.api;

import org.apache.logging.log4j.core.util.Assert;

import apis.customer.AccountsRequest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.GlobalVariables;
import utilities.ResponseManager;

public class AccountsStepDefinitionApi {

    AccountsRequest accountsRequest = new AccountsRequest();
    GlobalVariables globalVariables = GlobalVariables.getInstance();

    @When("Se llama al request de cuentas del usuario, obteniendo el id del usuario")
    public void getAccountsByUserId() {
        accountsRequest.getAccounts();
    }

    @Then("Se almancenan las cuentas del usuario")
    public void Se_almancenan_las_cuentas_del_usuario() {
        accountsRequest.setAccounts();
    }

    @Then("Se verifica que el id del response sea el del usuario")
    public void Se_verifica_que_el_id_del_response_sea_el_del_usuario() {
        org.testng.Assert.assertEquals(ResponseManager.getHTMLPath("customerId"), String.valueOf(globalVariables.getIdUser()) );
    }
    
}
