package org.sid.bankaccountservice.dao;

import org.sid.bankaccountservice.entities.AccountBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountBankRepository extends JpaRepository<AccountBank,String> {
    List<AccountBank>findByClientId(Long clientId);
}
