@API
Feature: Accounts API

    @regression @API
    Scenario: Get Account Details
        When Se llama al request de cuentas del usuario, obteniendo el id del usuario
        Then Se verifica el status code 200
        And Se verifica que el id del response sea el del usuario
        And Se almancenan las cuentas del usuario