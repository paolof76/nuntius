package com.group.nuntius.service;

import com.group.nuntius.model.BankAccount;
import com.group.nuntius.model.Client;
import com.group.nuntius.model.Institution;
import com.group.nuntius.obp.clientapi.DirectAuthenticationClient;
import com.group.nuntius.obp.clientapi.ObpApiClient;
import com.group.nuntius.obp.clientapi.ObpBankMetaApiClient;
import com.group.nuntius.obp.domain.Account;
import com.group.nuntius.obp.domain.AccountRouting;
import com.group.nuntius.obp.domain.Bank;
import com.group.nuntius.obp.domain.User;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private ObpApiClient obpApiClient;
    @Autowired
    private ObpBankMetaApiClient obpBankMetaApiClient;

    // /info/123 -> PathParam
    // /info?id=123 -> RequestParam

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public BankAccount info(@RequestParam("id") Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<BankAccount> getAll(@RequestParam("client") Long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if(client == null)
            return Collections.emptyList();

        return accountRepository.findByClient(client);
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

            BankAccount bankAccount = new BankAccount(institution.get(), iban, initialAmount,
                    interesRate, currency, client.get());
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

        token = "DirectLogin token=" + token;

        User user = obpApiClient.getCurrentUser(token);
        ObpBankMetaApiClient.Banks banks = obpBankMetaApiClient.getBanks(token);
        Bank bank = banks.getBanks().get(0);

        String accountId = "myaccount" + Math.random();

        System.out.println(token);
        System.out.println(user);
        System.out.println(banks);

        AccountRouting accountRouting = new AccountRouting();
        accountRouting.setAddress("UK123456");
        accountRouting.setScheme("OBP");

        Account account = new Account();
        account.setId(accountId);
        account.setBic("ABC12345");
        account.setBranchId("UK123456");
        account.setIban("CH0012345678901234560");
        account.setBalance(Money.of(CurrencyUnit.EUR, 0));
        account.setBankId(bank.getId());
        account.setLabel("TestAccount");
        account.setType("CURRENT");
        account.setUserId(user.getUserId());
//        account.setBranchId(bank.getBranches().get(0).getId());
        account.setAccountRouting(accountRouting);

        System.out.println(obpApiClient.createAccount(token, bank.getId(), accountId, account));
    }
}
