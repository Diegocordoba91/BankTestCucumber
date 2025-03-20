package models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public record ListAccounts(
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("account")
    List<Accounts> accounts 
    ) 
    {
    
}
