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
import utilities.ResponseManager;
import utilities.ScenarioManager;

public class CustomerStepsDefinitions{

    final CustomerRequest customerRequest = new CustomerRequest();
    final GlobalVariables globalVariables = GlobalVariables.getInstance();
    final ScenarioManager scenarioManager = ScenarioManager.getScenarioManager();





    @Given("Se obtiene el userName y password")
    public void getUserNamePassword() {

    }



    @When("Se llama el request de customer")
    public void getCustomerApi() {
        customerRequest.getCustomer(globalVariables.getUserName(), globalVariables.getPassword());
        
    }

    
    @Then("Se verifica el name y lastName del usuario")
    public void verifyNameLastname() {
        Assertions.assertAll(
            ()-> Assertions.assertEquals(globalVariables.getName(), ResponseManager.getHTMLPath("firstName")),
            ()-> Assertions.assertEquals(globalVariables.getLastName(), ResponseManager.getHTMLPath("lastName"))

        );
    }






    
}
