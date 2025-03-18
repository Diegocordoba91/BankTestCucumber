package steps.ui;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.BaseSteps;

public class TransferFoundsStepDefinitions extends BaseSteps{

    //Background
    @Then("Se obtienen las cuentas del usuario")
    public void Se_obtienen_las_cuentas_del_usuario() throws InterruptedException {
        accountOverviewPage.clickButtonAccountsOverview();
        scenarioManager.log("La tabla de cuenta esta visible? : "+transferFoundsPage.verifyMessageTransferComplete());
        accountOverviewPage.extractAccountsNumber();
        scenarioManager.log(globalVariables.getAccounts().toString());
    }

    @When("El usuario hace clic en el boton Transfer founds")
    public void El_usuario_hace_clic_en_el_boton_Transfer_founds() throws InterruptedException {
        transferFoundsPage.clickButtonTransferFounds();
    }

    @When("Se verifica que el se visualice en pantalla el formlario de transferencia de fondos")
    public void Se_verifica_que_el_se_visualice_en_pantalla_el_formlario_de_transferencia_de_fondos() throws InterruptedException {
        Assert.assertTrue(transferFoundsPage.isTransferFormPresent(),"El formulario de transferencia de fondos no se visualiza en pantalla");
        takeScreenshot();
    }

    @When("El usuario ingresa la cantidad a transferir {string}")
    public void El_usuario_ingresa_la_cantidad_a_transferir(String amount) throws InterruptedException {
        transferFoundsPage.inputAmount(amount);
    }

    @When("El usuario selecciona la cuenta origen")
    public void El_usuario_selecciona_la_cuenta_origen() {
        transferFoundsPage.selectFromAccount();
    }

    @When("El usuario selecciona la cuenta destino")
    public void El_usuario_selecciona_la_cuenta_destino() {
        transferFoundsPage.selectToAccount();

    }

    @When("El usuario hace clic en el boton Transfer")
    public void El_usuario_hace_clic_en_el_boton_Transfer() throws InterruptedException {
        transferFoundsPage.clickButtonTransfer();
    }

    @Then("Se visualiza en pantalla el mensaje Transfer Complete!")
    public void Se_visualiza_en_pantalla_el_mensaje_Transfer_Complete() throws InterruptedException {
        Assert.assertTrue(transferFoundsPage.verifyMessageTransferComplete(),"El mensaje Transfer Complete! no se visualiza en pantalla");
        takeScreenshot();
    }





    




}
