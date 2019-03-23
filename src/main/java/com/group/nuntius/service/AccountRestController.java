package com.group.nuntius.service;

import com.group.nuntius.model.Account;
import com.group.nuntius.model.Client;
import com.group.nuntius.model.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountRestController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private ClientRepository clientRepository;

    // /info/123 -> PathParam
    // /info?id=123 -> RequestParam

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Account info(@RequestParam("id") Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Account> getAll(@RequestParam("client") Long clientId) {
        return clientRepository.findById(clientId).map(Client::getAccounts).orElse(Collections.emptyList());
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public Account create(@RequestParam("client") Long clientId, @RequestParam("institution") Long institutionId) {
        Optional<Institution> institution = institutionRepository.findById(institutionId);
        Optional<Client> client = clientRepository.findById(clientId);

        if (institution.isPresent() && client.isPresent()) {
            // call library to create account
            String iban = "testIban";
            Account account = new Account(institution.get(), iban, client.get());
            accountRepository.save(account);
            return account;

        } else {
            throw new RuntimeException("Could not find institution for id " + institutionId);
        }

    }
}
