package com.vedruna.pruebatecnica1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.pruebatecnica1.DTO.CountryDTO;
import com.vedruna.pruebatecnica1.services.CountryServiceI;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    @Autowired
    CountryServiceI countryService;

    @GetMapping("/test")
    public String hello() {
        return countryService.test();
    }

    @GetMapping
    public List<CountryDTO> findAll(){
        return countryService.getAll();
    }
    
    
    @PostMapping("/save")
    public String save() {
        countryService.save();
        return "guardado";
    }
    
}
