package com.group.nuntius.service;

import com.group.nuntius.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientRestController {

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Long login(@RequestParam("email") String email) {
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Email '" + email + "' invalid!");
        }

        Optional<Client> client = clientRepository.getByEmail(email);
        if(client.isPresent()) {
            return client.get().getId();
        }
        return clientRepository.save(new Client(email, email)).getId();
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Client getById(@RequestParam Long id) {
        return clientRepository.findById(id).orElse(null);
    }

}
