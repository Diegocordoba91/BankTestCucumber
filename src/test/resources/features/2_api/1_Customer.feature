@API
Feature: Customer API
    
    @regression @API
    Scenario: consulta el detalle de un cliente por userName y password
        When Se llama el request de customer con usuario "Correcto"
        Then Se verifica el status code 200
        And Se verifica el name y lastName del usuario
        And Se captura el id del usuario

    @regression @API
    Scenario: consulta el detalle de un cliente por userName y password incorrecto
        When Se llama el request de customer con usuario "Incorrecto"
        Then Se verifica el status code 400

    @regression @API
    Scenario: consulta de detalle de customer por id
        Given Se obtiene el id del usuario
        When Se llama el request de customer por id
        Then Se verifica el status code 200
        And Se verifica el name y lastName del usuario
        And Se captura el id del usuario

    


    


    