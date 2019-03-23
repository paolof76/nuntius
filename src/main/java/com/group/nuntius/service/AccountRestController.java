package com.group.nuntius.service;

import com.group.nuntius.model.BankAccount;
import com.group.nuntius.model.Client;
import com.group.nuntius.model.Institution;
import com.group.nuntius.obp.clientapi.DirectAuthenticationClient;
import com.group.nuntius.obp.clientapi.ObpApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountRestController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DirectAuthenticationClient directAuthenticationClient;
    @Autowired
    private ObpApiClient obiApiClient;

    // /info/123 -> PathParam
    // /info?id=123 -> RequestParam

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public BankAccount info(@RequestParam("id") Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<BankAccount> getAll(@RequestParam("client") Long clientId) {
        return clientRepository.findById(clientId).map(Client::getBankAccounts).orElse(Collections.emptyList());
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public BankAccount create(@RequestParam("client") Long clientId, @RequestParam("institution") Long institutionId) {
        Optional<Institution> institution = institutionRepository.findById(institutionId);
        Optional<Client> client = clientRepository.findById(clientId);

        if (institution.isPresent() && client.isPresent()) {
            // call library to create account

            String iban = "testIban";
            Long initialAmount = 10000L;
            String interesRate = "0%";
            String currency = "CHF";
            LocalDate creationDate = LocalDate.of(2019, 3, 24);
            LocalDate validUntil = LocalDate.of(2020, 3, 24);

            BankAccount bankAccount = new BankAccount(institution.get(), iban, initialAmount,
                    interesRate, currency, creationDate, validUntil, client.get());
            accountRepository.save(bankAccount);
            return bankAccount;

        } else {
            throw new RuntimeException("Could not find institution for id " + institutionId);
        }
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public void transfer(@RequestParam("client") Long clientId,
                         @RequestParam("fromAccount") Long fromAccount,
                         @RequestParam("toAccount") Long toAccount,
                         @RequestParam("amount") Double amount) throws Exception {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new Exception("Could not find client"));
        BankAccount from = accountRepository.findById(fromAccount).orElseThrow(() -> new Exception("Could not find source account"));
        BankAccount to = accountRepository.findById(toAccount).orElseThrow(() -> new Exception("Could not find target account"));
        // call lib here
    }

    @RequestMapping(value = "/obi", method = RequestMethod.GET)
    public void obi() {
        String token = directAuthenticationClient.login("nuntius", "@Nuntius1234",
                "o153kts4lby2cej3z4cm5d4lrlai0k5xrh4ewk03");


        System.out.println(token);
        System.out.println(obiApiClient.getCurrentUser());


    }
}
