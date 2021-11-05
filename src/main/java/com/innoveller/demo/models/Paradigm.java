package com.innoveller.demo.models;

import javax.persistence.*;

@Entity
public class Paradigm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paradigm_id")
    private Long paradigmId;

    private String name;

    public Paradigm() {
    }

    public Long getParadigmId() {
        return paradigmId;
    }

    public void setParadigmId(Long paradigmId) {
        this.paradigmId = paradigmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
