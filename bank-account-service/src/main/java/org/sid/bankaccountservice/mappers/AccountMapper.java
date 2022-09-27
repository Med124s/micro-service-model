package org.sid.bankaccountservice.mappers;

import org.sid.bankaccountservice.entities.AccountBank;
import org.sid.bankaccountservice.entities.dto.AccountBankRequestDto;
import org.sid.bankaccountservice.entities.dto.AccountBankResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
//We can use Mapper Normal with BeanUtils
public class AccountMapper {
    public AccountBankResponseDto fromAccountBank(AccountBank accountBank){
        AccountBankResponseDto accountBankResponseDto = new AccountBankResponseDto();
        BeanUtils.copyProperties(accountBank,accountBankResponseDto);
        return accountBankResponseDto;
    }
    public AccountBank fromRequestAccount(AccountBankRequestDto accountBankRequestDto){
        AccountBank accountBank = new AccountBank();
        BeanUtils.copyProperties(accountBankRequestDto,accountBank);
        return accountBank;
    }


}
