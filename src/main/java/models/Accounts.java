package models;

import org.openqa.selenium.devtools.v131.fedcm.model.Account;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record Accounts(
    @JacksonXmlProperty(localName = "customerId")
    Integer customerId,
    @JacksonXmlProperty(localName = "id")
    int accountNumber,
    @JacksonXmlProperty(localName = "balance")
    double balance,
    double availableAmount,
    @JacksonXmlProperty(localName = "type")
    String typeAccount

) {
    

}
