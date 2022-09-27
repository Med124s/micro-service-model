package org.sid.bankaccountservice.service;

import lombok.AllArgsConstructor;
import org.sid.bankaccountservice.dao.AccountBankRepository;
import org.sid.bankaccountservice.entities.AccountBank;
import org.sid.bankaccountservice.entities.dto.AccountBankRequestDto;
import org.sid.bankaccountservice.entities.dto.AccountBankResponseDto;
import org.sid.bankaccountservice.mappers.AccountBankMapper;
import org.sid.bankaccountservice.models.Client;
import org.sid.bankaccountservice.models.ClientServiceRestClient;
import org.sid.bankaccountservice.service.exceptions.ClientNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AccountBankServiceImp implements IAccountBankService {
    private final AccountBankRepository accountBankRepository;
    private final AccountBankMapper accountMapper;
    private final ClientServiceRestClient clientServiceRestClient;
    @Override
    public AccountBankResponseDto addAccount(AccountBankRequestDto accountBankRequestDto) {
        AccountBank accountBank = accountMapper.toAccount(accountBankRequestDto);
        accountBank.setId(UUID.randomUUID().toString());
        accountBank.setCreatedAt(new Date());
        Client client = null;
        try{
            client = clientServiceRestClient.getClientById(accountBankRequestDto.getClientId());
        }
        catch(Exception e){
            throw new ClientNotFoundException("Client Not Found");
        }
        AccountBank savedAccount = accountBankRepository.save(accountBank);
        AccountBankResponseDto accountBankResponseDto = accountMapper.toAccountResponseDto(savedAccount);
        accountBankResponseDto.setClient(client);
        return accountBankResponseDto;
    }

    @Override
    public AccountBankResponseDto updateAccount(String id,AccountBankRequestDto accountBankRequestDto) {
       AccountBank account = accountBankRepository.findById(id).orElseThrow();
           if(accountBankRequestDto.getBalance()!=null) account.setBalance(accountBankRequestDto.getBalance());
           if(accountBankRequestDto.getType()!=null) account.setType(accountBankRequestDto.getType());
           if(accountBankRequestDto.getCurrency()!=null) account.setCurrency(accountBankRequestDto.getCurrency());
        AccountBank updatedAccount = accountBankRepository.save(account);

        AccountBankResponseDto accountBankResponseDto = accountMapper.toAccountResponseDto(updatedAccount);

        return accountBankResponseDto;
    }

    @Override
    public AccountBankResponseDto getAccountById(String id) {
        AccountBank accountBank = accountBankRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
        Client client = clientServiceRestClient.getClientById(accountBank.getClientId());
        accountBank.setClient(client);
        return accountMapper.toAccountResponseDto(accountBank);
    }

    @Override
    public boolean deleteAccount(String id) {
        if(accountBankRepository.findById(id).isPresent()){
             accountBankRepository.deleteById(id);
             return true;
        }
        return false;
    }

    @Override
    public List<AccountBankResponseDto> getAccounts() {
        List<AccountBank>accounts = accountBankRepository.findAll();
        accounts.stream().forEach(acc->{
            Client c = clientServiceRestClient.getClientById(acc.getClientId());
            acc.setClient(c);
            accountBankRepository.save(acc);
        });
        List<AccountBankResponseDto>accountsBankResponseDto=
        accounts.stream().map(account->

             accountMapper.toAccountResponseDto(account)
        ).collect(Collectors.toList());
        return accountsBankResponseDto;
    }

    @Override
    public List<AccountBankResponseDto> accountByClientId(Long clientId) {
        List<AccountBank> accounts = accountBankRepository.findByClientId(clientId);
        System.out.println("********************List Accounts By Client "+accounts);
        if(accounts.isEmpty()){
            throw new RuntimeException("NOT FOUND ANY ACCOUNT FOR THIS CLIENT ID");
        }
        accounts.stream().forEach(acc->{
            Client c = clientServiceRestClient.getClientById(acc.getClientId());
            acc.setClient(c);
            accountBankRepository.save(acc);
        });
        List<AccountBankResponseDto> accountsBankResponseDto =
                accounts.stream().map(account ->
                        accountMapper.toAccountResponseDto(account)
                ).collect(Collectors.toList());
        return accountsBankResponseDto;
    }



}
