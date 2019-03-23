package com.group.nuntius.service;

import com.group.nuntius.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientRestController {

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Long login(@RequestParam("email") String email) {
        return clientRepository.getByEmail(email).orElse(clientRepository.save(new Client(email, email))).getId();
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Client getById(@RequestParam Long id) {
        return clientRepository.findById(id).orElse(null);
    }

}
