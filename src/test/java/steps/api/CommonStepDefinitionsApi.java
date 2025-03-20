package steps.api;

import org.jsoup.helper.HttpConnection.Response;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utilities.ResponseManager;
import utilities.ScenarioManager;

public class CommonStepDefinitionsApi {


    private ScenarioManager scenarioManager = ScenarioManager.getScenarioManager();

    @Then("Se verifica el status code {int}")
    public void verifyStatusCode(Integer expectedStatusCode) {
        ResponseManager.verifyStatusCode(expectedStatusCode);
        scenarioManager.log("✅ Response Body: " + ResponseManager.toStringBodyResponse());
    }




}
