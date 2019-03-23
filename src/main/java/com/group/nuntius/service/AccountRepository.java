package com.group.nuntius.service;

import com.group.nuntius.model.BankAccount;
import com.group.nuntius.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<BankAccount, Long> {

    List<BankAccount> findByClient(Client client);
}
