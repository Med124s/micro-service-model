package org.sid.bankaccountservice.mappers;

import org.mapstruct.Mapper;
import org.sid.bankaccountservice.entities.AccountBank;
import org.sid.bankaccountservice.entities.dto.AccountBankRequestDto;
import org.sid.bankaccountservice.entities.dto.AccountBankResponseDto;

@Mapper(componentModel = "spring")
//Mapper Object with entities using pattern Dto with framework mapStruct
public interface AccountBankMapper {
    AccountBankResponseDto toAccountResponseDto(AccountBank accountBank);
    AccountBank toAccount(AccountBankRequestDto accountBankRequestDto);
}
