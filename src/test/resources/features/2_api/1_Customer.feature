@API
Feature: Customer API
    
    @regression @API
    Scenario: consulta el detalle de un cliente por userName y password
        Given Se obtiene el userName y password
        When Se llama el request de customer
        Then Se verifica el status code 200
        And Se verifica el name y lastName del usuario
        And Se captura el id del usuario

    


    