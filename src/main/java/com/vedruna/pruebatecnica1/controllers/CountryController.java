package com.vedruna.pruebatecnica1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.pruebatecnica1.dto.CountryDTO;
import com.vedruna.pruebatecnica1.services.CountryServiceI;


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

    @GetMapping("/all")
    /*Para introducit los parametros de page y size: 
    GET /all?page=0&size=10*/
    public List<CountryDTO> findAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {
        // paises por página
        return countryService.getAll(page, size);
    }
    
    
    @PostMapping("/save")
    public String save() {
        countryService.save();
        return "guardado";
    }
    
}
