package com.innoveller.demo.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

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
