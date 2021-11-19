package com.innoveller.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ProgrammingLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programming_language_id")
    private Long programmingLanguageId;

    @NotEmpty(message = "Please enter the name")
    private String name;

    @NotNull(message = "Please enter the first release date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "initial_release_date")
    private Date initialReleaseDate;

    @NotNull(message = "Please enter the latest release date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "last_release_date")
    private Date lastReleaseDate;

    @ManyToMany
    @JoinTable(
            name="programming_language_paradigm",
            joinColumns = @JoinColumn(name="programming_language_id"),
            inverseJoinColumns = @JoinColumn(name="paradigm_id")
    )
    private List<Paradigm> paradigms = new ArrayList<>();

    @NotEmpty(message = "Please enter the author")
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
