package com.innoveller.demo.repositories;

import com.innoveller.demo.models.Paradigm;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParadigmRepository extends JpaRepository<Paradigm, Long> {

    @Override
    List<Paradigm> findAll(Sort sort);
}
