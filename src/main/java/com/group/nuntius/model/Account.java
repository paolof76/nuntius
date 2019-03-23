package com.group.nuntius.model;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "institution_id", referencedColumnName = "id")
    private Institution institution;
    private String iban;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    public Account(Institution institution, String iban, Client client) {
        this.institution = institution;
        this.iban = iban;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public Institution getInstitution() {
        return institution;
    }

    public String getIban() {
        return iban;
    }

    public Client getClient() {
        return client;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", institution=" + institution +
                ", iban='" + iban + '\'' +
                '}';
    }
}
