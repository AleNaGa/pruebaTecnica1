package com.vedruna.pruebatecnica1.persistance.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vedruna.pruebatecnica1.persistance.model.Country;

public interface CountryRepositoryI extends JpaRepository<Country,String> {
    

    Page<Country> findAll(Pageable pageable);
}
