package com.vedruna.pruebatecnica1.persistance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "country")
public class Country {

    @Id
    @Column(name="country_id")
    private String countryId;

    @Column(name="name")
    private String name;

    @Column(name="population")
    private Long population;
    
}
