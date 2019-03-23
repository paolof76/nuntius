package com.group.nuntius.obp.domain;

import lombok.Data;

@Data
public class ATM {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
