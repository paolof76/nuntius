package com.group.nuntius.service;

import com.group.nuntius.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<BankAccount, Long> {

}
