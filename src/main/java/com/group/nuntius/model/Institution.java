package com.group.nuntius.model;

import javax.persistence.*;

@Entity
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String abbreviation;

    @Column(unique = true)
    private String bic;

    public Institution() {
    }

    public Institution(String name, String abbreviation, String bic) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.bic = bic;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setBic(String bic) {
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
