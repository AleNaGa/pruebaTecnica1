package com.vedruna.pruebatecnica1.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCountryDTO {

    private String id;
    private String name;
    private Long population;

    public CreateCountryDTO(ExternalCountryDTO country) {
        this.id = country.getCcn3();
        this.name = country.getName().getCommon();
        this.population = country.getPopulation();
    }

}
