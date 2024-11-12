package com.vedruna.pruebatecnica1.DTO;

import com.vedruna.pruebatecnica1.persistance.model.Country;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CountryDTO {
    
    private String name;
    private Long population;

    public CountryDTO(Country country) {
        this.name = country.getName();
        this.population = country.getPopulation();
    } 
    
}
