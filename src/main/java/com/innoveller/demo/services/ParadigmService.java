package com.innoveller.demo.services;

import com.innoveller.demo.models.Paradigm;
import com.innoveller.demo.repositories.ParadigmRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParadigmService {

    @Autowired
    private ParadigmRepository repository;

    public List<Paradigm> findAll() {
        return repository.findAll();
    }

    public Paradigm findById(Long id) {
        return repository.findById(id).get();
    }

    public Paradigm save(Paradigm paradigm) {
        return repository.saveAndFlush(paradigm);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Paradigm update(Paradigm paradigm) {
        Paradigm existingParadigm = repository.getById(paradigm.getParadigmId());
        BeanUtils.copyProperties(paradigm, existingParadigm, "paradigm_id");
        return repository.saveAndFlush(existingParadigm);
    }
}
