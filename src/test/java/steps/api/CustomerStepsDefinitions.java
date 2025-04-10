package steps.api;

import org.apache.logging.log4j.core.util.Assert;
import org.jsoup.helper.HttpConnection.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.asserts.SoftAssert;

import apis.customer.CustomerRequest;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.GlobalVariables;
import utilities.RequestManager;
import utilities.ResponseManager;
import utilities.ScenarioManager;

public class CustomerStepsDefinitions{

    final CustomerRequest customerRequest = new CustomerRequest();
    final GlobalVariables globalVariables = GlobalVariables.getInstance();
    final ScenarioManager scenarioManager = ScenarioManager.getScenarioManager();


    
    @Given("Se obtiene el userName y password")
    public void getUserNamePassword() {

    }




    @When("Se llama el request de customer con usuario {string}")
    public void Se_llama_el_request_de_customer_con_usuario(String typeUser) {
        
        customerRequest.getCustomer(typeUser);
        scenarioManager.log(RequestManager.toStringRequest());
        
    }


    
    @Then("Se verifica el name y lastName del usuario")
    public void verifyNameLastname() {
        Assertions.assertAll(
            ()-> Assertions.assertEquals(globalVariables.getName(), ResponseManager.getHTMLPath("firstName")),
            ()-> Assertions.assertEquals(globalVariables.getLastName(), ResponseManager.getHTMLPath("lastName"))

        );
    }

    @Then("Se captura el id del usuario")
    public void setIdUser() {
        customerRequest.setIdUser();
    }


    @Given("Se obtiene el id del usuario")
    public void getUserId() {
        scenarioManager.log("Id del usuario: " + customerRequest.getIDCustomer());
    }

    
    @When("Se llama el request de customer por id")
    public void getCustomerById() {
        customerRequest.getCustomerById(customerRequest.getIDCustomer());
        scenarioManager.log(RequestManager.toStringRequest());
        

    }




    
    
}
