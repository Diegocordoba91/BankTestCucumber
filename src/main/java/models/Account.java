package models;



import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record Account(
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
