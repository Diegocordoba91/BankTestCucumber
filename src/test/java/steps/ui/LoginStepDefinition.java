package steps.ui;

import org.testng.Assert;

import groovyjarjarantlr4.v4.parse.ANTLRParser.localsSpec_return;
import io.cucumber.java.Scenario;
import io.cucumber.java.StepDefinitionAnnotation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.User;
import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Global;
import pages.RegisterPage;
import steps.BaseSteps;
import utilities.GlobalVariables;
import utilities.ScenarioManager;
import utilities.ScreenShotUtil;


public class LoginStepDefinition extends BaseSteps{

    private final ScenarioManager   scenarioManager = ScenarioManager.getScenarioManager();
    private GlobalVariables globalVariables = GlobalVariables.getInstance();
    private boolean isUserSuccessfull=false;
    
    //Background
    @Given("El usuario se encuentra en la pagina de registro de usuario")
    public void El_usuario_se_encuentra_en_la_pagina_de_registro_de_usuario() throws InterruptedException {
        homePage.VerifyTitleHomePageDisplayed();
        homePage.clickButtonRegister();
        registerPage.titleRegisterPagePresensent();
    }


    
    //Steps
    
    @When("El formulario de creación de usuario debe ser visible")
    public void El_formulario_de_creaci_n_de_usuario_debe_ser_visible() throws InterruptedException {
        registerPage.formRegisterUserDisplayed();
    }

    @When("Se ingresan los datos de usuario tipo {string}")
    public void Se_ingresan_los_datos_de_usuario_tipo(String type) throws InterruptedException { 
        registerPage.newAccountRegistrationForm(type);
    }

    @Then("El usuario hace click en el boton register")
    public void El_usuario_hace_click_en_el_boton_register() throws InterruptedException {
        
        isUserSuccessfull = registerPage.clickButtonRegisterNewAccount();
    }    


    
    @When("Se verifica que el nombre de usuario sea válido; en caso contrario, se intenta con uno nuevo")
    public void Se_verifica_que_el_nombre_de_usuario_sea_v_lido_en_caso_contrario_se_intenta_con_uno_nuevo() throws InterruptedException {
        var result = registerPage.verifyRegisterUserSuccessful(isUserSuccessfull);
        scenarioManager.log("La cantidad de intentos fueron: "+result);
        scenarioManager.log("Usuario creado : "+globalVariables.getUserName()+" con password: "+globalVariables.getPassword());
    }


    @Then("Se verifica que el usuario visualice en pantalla el mensaje {string}")
    public void El_usuario_visualiza_en_pantalla_el_mensaje(String message) {
        Assert.assertEquals(registerPage.getMessageAccountSuccefull(), message);
    }

    @Then("Se verifica que el usuario visualice en pantalla el mensaje Is Required en todos los campos")
    public void El_usuario_visualiza_en_pantalla_el_mensaje_Is_Required_en_todos_los_campos() throws InterruptedException {
        registerPage.verifyMesaagesIsRequired();
        var result = registerPage.verifyMesaagesIsRequired();
        scenarioManager.log(result.get("result").toString());
        scenarioManager.log(result.get("elements").toString());
    }

    @Then("Se verifica que el sistema no permita el registro del usuario")
    public void Se_verifica_que_el_sistema_no_permita_el_registro_del_usuario() throws InterruptedException {
        scenarioManager.log("Name:  "+globalVariables.getName()+", Lastname: "+globalVariables.getLastName());
        scenarioManager.takesScreenshot(this.driver);
        Assert.assertTrue(registerPage.verifyTitleRegisterPage(),"Error: El sistema permitio el registro del usuario con caracteres especiales");
        
        
    }

    
}
