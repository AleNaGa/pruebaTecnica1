package com.vedruna.pruebatecnica1.persistance.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vedruna.pruebatecnica1.persistance.model.Country;

public interface CountryRepositoryI extends JpaRepository<Country,String> {
    

    Page<Country> findAll(Pageable pageable);

    List<Country> findByName(String name);
}
