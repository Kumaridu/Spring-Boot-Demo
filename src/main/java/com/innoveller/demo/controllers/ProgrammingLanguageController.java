package com.innoveller.demo.controllers;

import com.innoveller.demo.models.ProgrammingLanguage;
import com.innoveller.demo.services.ProgrammingLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/programmingLangs")
public class ProgrammingLanguageController {

    @Autowired
    private ProgrammingLanguageService service;

    @GetMapping
    public List<ProgrammingLanguage> getList() {
        return service.findAll();
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
