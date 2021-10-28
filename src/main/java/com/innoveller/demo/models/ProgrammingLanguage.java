package com.innoveller.demo.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "programmingLanguageId")

public class ProgrammingLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programming_language_id")
    private Long programmingLanguageId;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "initial_release_date")
    private Date initialReleaseDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "last_release_date")
    private Date lastReleaseDate;

    @ManyToMany
    @JoinTable(
            name="programming_language_paradigm",
            joinColumns = @JoinColumn(name="programming_language_id"),
            inverseJoinColumns = @JoinColumn(name="paradigm_id")
    )
    private List<Paradigm> paradigms;

    private String author;

    public ProgrammingLanguage() {

    }

    public Long getProgrammingLanguageId() {
        return programmingLanguageId;
    }

    public void setProgrammingLanguageId(Long programmingLanguageId) {
        this.programmingLanguageId = programmingLanguageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInitialReleaseDate() {
        return initialReleaseDate;
    }

    public void setInitialReleaseDate(Date initialReleaseDate) {
        this.initialReleaseDate = initialReleaseDate;
    }

    public Date getLastReleaseDate() {
        return lastReleaseDate;
    }

    public void setLastReleaseDate(Date lastReleaseDate) {
        this.lastReleaseDate = lastReleaseDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Paradigm> getParadigms() {
        return paradigms;
    }

    public void setParadigms(List<Paradigm> paradigms) {
        this.paradigms = paradigms;
    }
}
