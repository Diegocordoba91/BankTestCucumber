@UI
Feature: Transfer founds
    Background: 
        Given El usuario se encuentra en la pagina de login 
        And El usuario ingresa al sistema con usuario y contraseña
        When Se hace clic en el boton Login
        Then Se verifica si el login fue exitoso
        And De lo contrario, se crea un nuevo usuario "VALIDO"
        And Se obtienen las cuentas del usuario
    
    @OPEN
    Scenario: Transferencia exitosa de fondos a una cuenta propia
        When El usuario hace clic en el boton Transfer founds
        And Se verifica que el se visualice en pantalla el formlario de transferencia de fondos
        And El usuario ingresa la cantidad a transferir "100"
        And El usuario selecciona la cuenta origen
        And El usuario selecciona la cuenta destino
        And El usuario hace clic en el boton Transfer
        Then Se visualiza en pantalla el mensaje Transfer Complete!