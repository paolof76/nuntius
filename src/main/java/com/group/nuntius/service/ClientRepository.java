package com.group.nuntius.service;

import com.group.nuntius.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findBySurname(String surname);
}