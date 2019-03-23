package com.group.nuntius.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Institution institution;
    private String iban;
    private Client client;

    public Account(Institution institution, String iban, Client client) {
        this.institution = institution;
        this.iban = iban;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", institution=" + institution +
                ", iban='" + iban + '\'' +
                ", client=" + client +
                '}';
    }
}
