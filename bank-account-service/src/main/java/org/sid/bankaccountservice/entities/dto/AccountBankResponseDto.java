package org.sid.bankaccountservice.entities.dto;

import lombok.Data;
import org.sid.bankaccountservice.entities.AccountType;
import org.sid.bankaccountservice.models.Client;

import java.util.Date;
@Data
public class AccountBankResponseDto {
    private String id;
    private AccountType type;
    private String currency;
    private Double balance;
    private Date createdAt;
    private Client client;
}
