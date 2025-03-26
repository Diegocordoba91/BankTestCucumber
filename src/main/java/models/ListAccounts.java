package models;

import java.util.List;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "accounts")
public record ListAccounts(
    @JacksonXmlElementWrapper(useWrapping = false)
    List<Account> account
    ) 
    {
    
}
