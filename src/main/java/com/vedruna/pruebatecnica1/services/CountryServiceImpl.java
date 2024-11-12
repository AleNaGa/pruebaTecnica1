package com.vedruna.pruebatecnica1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vedruna.pruebatecnica1.DTO.CountryDTO;
import com.vedruna.pruebatecnica1.DTO.CreateCountryDTO;
import com.vedruna.pruebatecnica1.DTO.ExternalCountryDTO;
import com.vedruna.pruebatecnica1.persistance.model.Country;
import com.vedruna.pruebatecnica1.persistance.repository.CountryRepositoryI;

@Service
public class CountryServiceImpl implements CountryServiceI {

    @Autowired
    CountryRepositoryI countryRepo;
    
    @Autowired
    RestTemplate restTemplate;//Inyectar RestTemplate para fetchear la API

    //Endpoint para obtener los países
    private static final String EXTERNAL_API_URL = "https://restcountries.com/v3.1/all";

    @Override
    public void save() {
        /*ESTA LINEA: 
            Se crea un Array de ECDTO, donde se almacenan los datos obtenidos de la API. 
            RestTemplate.getForObject recoge la informacion de la API y la almacena en el array, se especifica así: (Dirección, Objeto que se almacena)
        */
        ExternalCountryDTO[] externalCountries = restTemplate.getForObject(EXTERNAL_API_URL, ExternalCountryDTO[].class);
        
        for (ExternalCountryDTO countries : externalCountries) {
            //Se crea un objeto CountryDTO con los datos obtenidos de la API
            CreateCountryDTO countryDTO = new CreateCountryDTO(countries);

            //Se crea un objeto Country con los datos obtenidos de la API
            Country country = new Country();
            try{
            country.setCountryId(countryDTO.getId());
            country.setName(countryDTO.getName());
            country.setPopulation(countryDTO.getPopulation());
            countryRepo.save(country);
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage() + " " + countryDTO.getName());
            }
        }

    }

    @Override
    public List<CountryDTO> getAll() {
        List<Country> countries = countryRepo.findAll();
        return countries.stream().map(country -> new CountryDTO(country)).toList();
    }

    @Override
    public String test() {
       return "Hello World";
    }
    
}
