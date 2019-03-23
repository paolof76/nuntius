package com.group.nuntius.service;

import com.group.nuntius.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientRestController {

    @Autowired
    ClientRepository clientRepository;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Client getById(@RequestParam Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public void create(Client client) {
        clientRepository.save(client);
    }


}
