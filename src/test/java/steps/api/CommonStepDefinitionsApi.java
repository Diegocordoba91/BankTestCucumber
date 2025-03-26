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
        scenarioManager.log(ResponseManager.toStringBodyResponse());
    }

    @Then("Se verific que el time response sea menor a {int}")
    public void verifyTimeResponse(Integer expectedTimeResponse){
        ResponseManager.verifyTime(expectedTimeResponse);
    }




}
