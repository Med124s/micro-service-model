package org.sid.bankaccountservice.models;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientServiceRestClient {
    @GetMapping("/api/client/{code}")
     Client getClientById(@PathVariable(name="code")Long code);

    @GetMapping("/api/clients")
    List<Client> getClients();
}
