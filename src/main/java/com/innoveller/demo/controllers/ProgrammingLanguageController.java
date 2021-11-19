package com.innoveller.demo.controllers;

import com.innoveller.demo.models.ProgrammingLanguage;
import com.innoveller.demo.models.ProgrammingLanguageDto;
import com.innoveller.demo.services.ProgrammingLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/programmingLangs")
public class ProgrammingLanguageController {

    @Autowired
    private ProgrammingLanguageService service;

    @GetMapping
    public List<ProgrammingLanguageDto> getList() {
        List<ProgrammingLanguage> list =  service.findAll();
        return  list.stream().map(language -> {
            ProgrammingLanguageDto dto = new ProgrammingLanguageDto();
            dto.id = language.getProgrammingLanguageId();
            dto.name = language.getName();
            dto.firstReleaseDate = language.getInitialReleaseDate();
            dto.LastReleaseDate = language.getLastReleaseDate();
            dto.author = language.getAuthor();
            dto.paradigms = language.getParadigms()
                    .stream().map(paradigm -> paradigm.getName()).collect(Collectors.toList());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("{id}")
    public ProgrammingLanguage get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ProgrammingLanguage create(@RequestBody ProgrammingLanguage language) {
        return service.save(language);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ProgrammingLanguage update(@RequestBody ProgrammingLanguage language) {
        return service.update(language);
    }

}
