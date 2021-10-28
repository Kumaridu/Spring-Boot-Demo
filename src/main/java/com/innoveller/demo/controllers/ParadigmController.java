package com.innoveller.demo.controllers;

import com.innoveller.demo.models.Paradigm;
import com.innoveller.demo.services.ParadigmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/paradigms")
public class ParadigmController {

    @Autowired
    private ParadigmService service;

    @GetMapping
    public List<Paradigm> findAll() {
        return service.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Paradigm get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Paradigm create(@RequestBody Paradigm paradigm) {
        return service.save(paradigm);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Paradigm update(@RequestBody Paradigm paradigm) {
        return service.update(paradigm);
    }
}
