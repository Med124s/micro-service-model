package org.sid.bankaccountservice.service;

import org.sid.bankaccountservice.entities.AccountBank;
import org.sid.bankaccountservice.entities.dto.AccountBankRequestDto;
import org.sid.bankaccountservice.entities.dto.AccountBankResponseDto;

import java.util.List;

public interface IAccountBankService {
    AccountBankResponseDto addAccount(AccountBankRequestDto accountBankRequestDto);
    AccountBankResponseDto updateAccount(String id,AccountBankRequestDto accountBankRequestDto);
    AccountBankResponseDto getAccountById(String id);
    boolean deleteAccount(String id);
    List<AccountBankResponseDto> getAccounts();
    List<AccountBankResponseDto>accountByClientId(Long clientId);

}
