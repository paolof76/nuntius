package com.group.nuntius.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cardNumber;
    private Account account;
    private String expirationDate;
    private Client client;

    public Card(String cardNumber, Account account, String expirationDate, Client client) {
        this.cardNumber = cardNumber;
        this.account = account;
        this.expirationDate = expirationDate;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", account=" + account +
                ", expirationDate='" + expirationDate + '\'' +
                ", client=" + client +
                '}';
    }
}
