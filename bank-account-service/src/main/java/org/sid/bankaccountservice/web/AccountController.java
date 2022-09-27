package org.sid.bankaccountservice.web;

import lombok.AllArgsConstructor;
import org.sid.bankaccountservice.entities.AccountBank;
import org.sid.bankaccountservice.entities.dto.AccountBankRequestDto;
import org.sid.bankaccountservice.entities.dto.AccountBankResponseDto;
import org.sid.bankaccountservice.service.AccountBankServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AccountController {
    private AccountBankServiceImp accountBankServiceImp;
    @GetMapping("accounts")
    public List<AccountBankResponseDto> accounts(){
        return accountBankServiceImp.getAccounts();
    }
    @GetMapping("getAccount/{id}")
    public AccountBankResponseDto getAccountById(@PathVariable(name="id")String id){
        return accountBankServiceImp.getAccountById(id);
    }
    @GetMapping("accountByClientId/{id}")
    public List<AccountBankResponseDto> getAccountByClientId(@PathVariable(name="id")Long id){
        return accountBankServiceImp.accountByClientId(id);
    }
    @PutMapping("updateAccount/{id}")
    public AccountBankResponseDto updateAccount(@PathVariable(name="id")String id,@RequestBody()AccountBankRequestDto accountBank){
        return accountBankServiceImp.updateAccount( id,accountBank);
    }
    @DeleteMapping("deleteAccount/{id}")
    public boolean deleteAccount(@PathVariable(name="id") String id){
        return accountBankServiceImp.deleteAccount(id);
    }
    @PostMapping("saveAccount")
    public AccountBankResponseDto save(@RequestBody() AccountBankRequestDto accountBankRequestDto){
        return accountBankServiceImp.addAccount(accountBankRequestDto);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>errorHandler(Exception e){
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
