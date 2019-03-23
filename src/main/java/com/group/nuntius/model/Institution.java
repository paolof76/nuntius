package com.group.nuntius.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String bic;

    public Institution(String name, String bic) {
        this.name = name;
        this.bic = bic;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBic() {
        return bic;
    }

    @Override
    public String toString() {
        return "Institution{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bic='" + bic + '\'' +
                '}';
    }
}
