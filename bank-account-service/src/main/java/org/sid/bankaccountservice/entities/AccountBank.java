package org.sid.bankaccountservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bankaccountservice.models.Client;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountBank {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private String currency;
    private Double balance;
    private Date createdAt;
    private Long clientId;
    @Transient
    private Client client;
}
