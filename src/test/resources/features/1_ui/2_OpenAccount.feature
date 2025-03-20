@UI
Feature: Open new account
    
    
    Background:
        Given El usuario se encuentra en la pagina de login 
        And El usuario ingresa al sistema con usuario y contraseña
        When Se hace clic en el boton Login
        Then Se verifica si el login fue exitoso
        And De lo contrario, se crea un nuevo usuario "VALIDO"
 

    @smoke
    Scenario: Open new saving account
        When El usuario hace click en el boton Open New Account
        And Se verifica que se despliegue el formulario de creacion de cuenta
        And Se selecciona el tipo de cuenta "CHECKING"
        And Se hace click en el boton Open Account
        Then Se verifica que el sistema muestre el mensaje Account Opened!
        And Se captura el numero de cuenta "CHECKING" creado para futuras validaciones

    @smoke
    Scenario: Open new checking account
        When El usuario hace click en el boton Open New Account
        And Se verifica que se despliegue el formulario de creacion de cuenta
        And Se selecciona el tipo de cuenta "SAVINGS"
        And Se hace click en el boton Open Account
        Then Se verifica que el sistema muestre el mensaje Account Opened!
        And Se captura el numero de cuenta "SAVINGS" creado para futuras validaciones

    