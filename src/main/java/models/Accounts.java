package models;

import org.openqa.selenium.devtools.v131.fedcm.model.Account;

public record Accounts(
    int accountNumber,
    double balance,
    double availableAmount,
    String typeAccount
) {
    

}
