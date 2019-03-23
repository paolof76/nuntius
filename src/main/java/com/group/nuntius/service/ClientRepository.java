package com.group.nuntius.service;

import com.group.nuntius.Tools;
import com.group.nuntius.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findByEmail(String email);

    default Optional<Client> getByEmail(String email) {
        return Tools.getFirst(findByEmail(email));
    }
}