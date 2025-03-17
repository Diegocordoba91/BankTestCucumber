@UI
Feature: Registro de usuarios

    Background:
        Given El usuario se encuentra en la pagina de registro de usuario
    
    @regression @smoke
    Scenario: Creación de nuevo usuario
        When El formulario de creación de usuario debe ser visible
        And Se ingresan los datos de usuario tipo "VALIDO"
        And El usuario hace click en el boton register
        And Se verifica que el nombre de usuario sea válido; en caso contrario, se intenta con uno nuevo
        Then Se verifica que el usuario visualice en pantalla el mensaje "Your account was created successfully. You are now logged in."

    @regression @smoke
    Scenario: Creación de usuario vacio
        When El formulario de creación de usuario debe ser visible
        And Se ingresan los datos de usuario tipo "VACIO"
        And El usuario hace click en el boton register
        Then Se verifica que el usuario visualice en pantalla el mensaje Is Required en todos los campos

    @smoke
    Scenario: Ingreso de password con caracteres especiales
        When El formulario de creación de usuario debe ser visible
        And Se ingresan los datos de usuario tipo "CARACTER_ESPECIAL"
        And El usuario hace click en el boton register
        And Se verifica que el nombre de usuario sea válido; en caso contrario, se intenta con uno nuevo
        Then Se verifica que el sistema no permita el registro del usuario
    
    @smoke
    Scenario: Ingreso de password con menos de 6 caracteres y caractares especiales
        When El formulario de creación de usuario debe ser visible
        And Se ingresan los datos de usuario tipo "CONTRASEÑA_INVALIDA"
        And El usuario hace click en el boton register
        Then Se verifica que el sistema no permita el registro del usuario

    
        