package org.sid.bankaccountservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bankaccountservice.entities.AccountType;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AccountBankRequestDto {
    private AccountType type;
    private String currency;
    private Double balance;
    private Long clientId;
}
