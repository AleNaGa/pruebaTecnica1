package com.vedruna.pruebatecnica1.services;

import java.util.List;

import com.vedruna.pruebatecnica1.dto.CountryDTO;


public interface CountryServiceI {
    //TEST
    public String test();
    // Guardar los países
    public void save();
    //listar los países
    public List<CountryDTO> getAll(int page, int size); //Metodo pageable
}
