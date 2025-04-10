@API
Feature: Accounts API

    @regression @API
    Scenario: Get Account Details
        When Se llama al request de cuentas del usuario, obteniendo el id del usuario
        Then Se verifica el status code 200
        And Se verifica que el id del response sea el del usuario
        And Se almancenan las cuentas del usuario

    @regression @API
    Scenario: Get transactoins by account
        Given Se obtiene el núemero de cuenta del usuario
        When Se llama al request de consulta de transacciones
        Then Se verifica el status code 200

    @regression @API
    Scenario: Create new account
        Given Se obtiene el número de cuenta con fondos
        When Se llama al request de creción de cuenta con tipo de cuenta "LOAN"
        Then Se verifica el status code 200
        And Se verifica el tipo de cuenta creada        
