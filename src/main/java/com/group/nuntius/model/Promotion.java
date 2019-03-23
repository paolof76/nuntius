package com.group.nuntius.model;

import javax.persistence.*;

@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "institution_id", referencedColumnName = "id")
    private Institution institution;
    private Long minimalPartecipationCount;
    private Long currentCount;

    public Promotion(String message, Institution institution, Long minimalPartecipationCount, Long currentCount) {
        this.message = message;
        this.institution = institution;
        this.minimalPartecipationCount = minimalPartecipationCount;
        this.currentCount = currentCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Long getMinimalPartecipationCount() {
        return minimalPartecipationCount;
    }

    public void setMinimalPartecipationCount(Long minimalPartecipationCount) {
        this.minimalPartecipationCount = minimalPartecipationCount;
    }

    public Long getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Long currentCount) {
        this.currentCount = currentCount;
    }
}
