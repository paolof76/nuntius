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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;

    public Account(Institution institution, String iban, Client client, Card card) {
        this.institution = institution;
        this.iban = iban;
        this.client = client;
        this.card = card;
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

    public Card getCard() {
        return card;
    }

    public Client getClient() {
        return client;
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
