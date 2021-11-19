package com.innoveller.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class ProgrammingLanguageDto {
    public Long id;
    public String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date firstReleaseDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date LastReleaseDate;
    public String author;
    public List<String> paradigms;
}
