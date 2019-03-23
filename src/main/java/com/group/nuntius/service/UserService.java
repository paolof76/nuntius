package com.group.nuntius.service;

import com.group.nuntius.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private ClientRepository clientRepository;

    public String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public Client getClient() {
        Optional<Client> clientOptional = clientRepository.getByEmail(getUserName());
        if (clientOptional.isPresent()) {
            return clientOptional.get();
        } else {
            throw new RuntimeException("No client found for " + getUserName());
        }
    }
}
