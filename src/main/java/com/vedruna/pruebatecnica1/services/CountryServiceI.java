package com.vedruna.pruebatecnica1.services;

import java.util.List;

import com.vedruna.pruebatecnica1.DTO.CountryDTO;
import com.vedruna.pruebatecnica1.DTO.CreateCountryDTO;
import com.vedruna.pruebatecnica1.DTO.ExternalCountryDTO;

public interface CountryServiceI {
    //TEST
    public String test();
    // Guardar los países
    public void save();
    //listar los países
    public List<CountryDTO> getAll(); 
}
