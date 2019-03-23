package com.group.nuntius.service;

import com.group.nuntius.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findByEmail(String email);

    default Optional<Client> getByEmail(String email) {
        List<Client> clients = findByEmail(email);
        return clients.isEmpty() ? Optional.empty() : Optional.ofNullable(clients.get(0));
    }
}