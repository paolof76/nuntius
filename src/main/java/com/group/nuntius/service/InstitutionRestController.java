package com.group.nuntius.service;

import com.group.nuntius.model.BankAccount;
import com.group.nuntius.model.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/institution")
@CrossOrigin(origins = "http://localhost:4200")
public class InstitutionRestController {

    @Autowired
    private InstitutionRepository intitutionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Institution> getAll() {
        List<Institution> institutions = new ArrayList<>();
        intitutionRepository.findAll().forEach(institutions::add);
        return institutions;
    }

    @RequestMapping(value = "/notUsed", method = RequestMethod.GET)
    public Iterable<Institution> getNotYetLinkedInstitutions() {
        List<BankAccount> bankAccounts = new ArrayList<>();
        accountRepository.findAll().forEach(bankAccounts::add);
        Set<Institution> alreadyUsedInstitutions = bankAccounts.stream()
                .map(BankAccount::getInstitution).collect(Collectors.toSet());

        List<Institution> institutions = new ArrayList<>();
        intitutionRepository.findAll().forEach(institutions::add);

        institutions.removeAll(alreadyUsedInstitutions);
        return institutions;
    }

}
