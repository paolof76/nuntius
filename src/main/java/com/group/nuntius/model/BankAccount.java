package com.group.nuntius.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "institution_id", referencedColumnName = "id")
    private Institution institution;
    private String iban;
    private Long amount;
    private String currency;
    //example 0.5% for now is fine
    private String interestRate;

    @ManyToOne()
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public BankAccount() {
    }

    public BankAccount(Institution institution, String iban, Long amount, String currency, String interestRate, Client client) {
        this.institution = institution;
        this.iban = iban;
        this.amount = amount;
        this.currency = currency;
        this.interestRate = interestRate;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
