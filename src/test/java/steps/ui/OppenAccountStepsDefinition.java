package steps.ui;

import org.junit.jupiter.api.Assumptions;
import org.testng.Assert;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.BaseSteps;
import utilities.GlobalVariables;
import utilities.ScenarioManager;

public class OppenAccountStepsDefinition extends BaseSteps{

    boolean loginSuccessful;
    
    @Given("El usuario se encuentra en la pagina de login")
    public void El_usuario_se_encuentra_en_la_pagina_de_login() throws InterruptedException {
        Assert.assertTrue(homePage.VerifyTitleHomePageDisplayed(), "El usuario no se encuentra en la pagina de login"); 
    }

    @Given("El usuario ingresa al sistema con usuario y contraseña")
    public void El_usuario_ingresa_al_sistema_con_usuario_y_contrase_a() throws InterruptedException {
        homePage.login();
    }
    
    @When("Se hace clic en el boton Login")
    public void Se_hace_clic_en_el_boton_Login() throws InterruptedException {
        homePage.clickButtonLogin();
        
    }

    @Then("Se verifica si el login fue exitoso")
    public void Se_verifica_si_el_login_fue_exitoso_de_lo_contrario_se_crea_un_nuevo_usuario() throws InterruptedException {
        homePage.loginSuccessful();
        if (loginSuccessful) 
            scenarioManager.log("Usuario: "+globalVariables.getUserName()+", password: "+globalVariables.getPassword());
        
        
        
    }

    @Then("De lo contrario, se crea un nuevo usuario {string}")
    public void De_lo_contrario_se_crea_un_nuevo_usuario(String typeUser) throws InterruptedException {
        if (!homePage.loginSuccessful()) {
            homePage.clickButtonRegister();
            registerPage.newAccountRegistrationForm(typeUser);
            registerPage.clickButtonRegisterNewAccount();
            registerPage.verifyRegisterUserSuccessful(registerPage.verifyTitleRegisterPage());
            scenarioManager.log("Usuario: "+globalVariables.getUserName()+", password: "+globalVariables.getPassword());
    
        }
            

        
    }

    @When("El usuario hace click en el boton Open New Account")
    public void El_usuario_hace_click_en_el_boton_Open_New_Account() throws InterruptedException {
        openAccount.clicOpenNewAccount();
        
        
    }

    @When("Se verifica que se despliegue el formulario de creacion de cuenta")
    public void Se_verifica_que_se_despliegue_el_formulario_de_creacion_de_cuenta() throws InterruptedException {
        openAccount.verifyOpenAccountForm();
    }

 

    @When("Se selecciona el tipo de cuenta {string}")
    public void Se_selecciona_el_tipo_de_cuenta(String typeAccount) throws InterruptedException {
        openAccount.selectAccount(typeAccount);
        
    }
    @When("Se hace click en el boton Open Account")
    public void Se_hace_click_en_el_boton_Open_Account() throws InterruptedException {
        openAccount.clicOpenAccount();
        
    }

  

    @Then("Se verifica que el sistema muestre el mensaje Account Opened!")
    public void Se_verifica_que_el_sistema_muestre_el_mensaje_Account_Opened() throws InterruptedException {
        
        Assert.assertTrue(openAccount.verifyMessageAccountOpened(), "El sistema no muestra el mensaje Account Opened!");
        takeScreenshot();
    }


    @Then("Se captura el numero de cuenta {string} creado para futuras validaciones")
    public void Se_captura_el_numero_de_cuenta_creado_para_futuras_validaciones(String typeAccount) throws InterruptedException {
        openAccount.setNumberAccount(typeAccount, openAccount.getNumberAccount());
        scenarioManager.log("Numero de cuenta: "+openAccount.getAccount(typeAccount));
        
    }


    
}
