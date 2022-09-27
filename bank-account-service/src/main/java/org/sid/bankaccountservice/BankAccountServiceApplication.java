package org.sid.bankaccountservice;

import org.sid.bankaccountservice.dao.AccountBankRepository;
import org.sid.bankaccountservice.entities.AccountBank;
import org.sid.bankaccountservice.entities.AccountType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
@EnableFeignClients
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(AccountBankRepository accountBankRepository){
		return args -> {
			for(int i=0;i<5;i++){
				AccountBank accountBank = AccountBank.builder()
						.id(UUID.randomUUID().toString())
						.createdAt(new Date())
						.type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
						.balance(10000+Math.random() * 90000)
						.currency("MAD")
						.clientId(i+1l)
						.build();
				accountBankRepository.save(accountBank);
			}
		};
	}

}
