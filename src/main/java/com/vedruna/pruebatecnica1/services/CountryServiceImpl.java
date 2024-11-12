package com.vedruna.pruebatecnica1.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vedruna.pruebatecnica1.dto.CountryDTO;
import com.vedruna.pruebatecnica1.dto.CreateCountryDTO;
import com.vedruna.pruebatecnica1.dto.ExternalCountryDTO;
import com.vedruna.pruebatecnica1.exceptions.CountrySaveException;
import com.vedruna.pruebatecnica1.persistance.model.Country;
import com.vedruna.pruebatecnica1.persistance.repository.CountryRepositoryI;

@Service
public class CountryServiceImpl implements CountryServiceI {

    //Implementación del Logger para el registro de trazas
    private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);


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
            logger.info("Iniciando el guardado del país: {}", countryDTO.getName());//TRAZA
            //Se crea un objeto Country con los datos obtenidos de la API
            Country country = new Country();
            try{//Se guardan los datos en la BBDD
                //Comprobar que tenga ID
                if(countryDTO.getId()==null || countryDTO.getId().equals("")){
                    countryDTO.setId("1110");
                }
            country.setCountryId(countryDTO.getId());
            country.setName(countryDTO.getName());
            country.setPopulation(countryDTO.getPopulation());
            countryRepo.save(country);
            logger.info("País guardado exitosamente: {}", countryDTO.getName()); //TRAZA
            }catch (Exception e) {
                logger.error("Error al guardar el país: {}", countryDTO.getName(), e);//TRAZA
                throw new CountrySaveException("Error al guardar el país: " + country.getName(), e); // Excepción personalizada
            }
        }
    }

    @Override
    public List<CountryDTO> getAll(int page, int size) {
        //Objeto Pageable
        Pageable pageable = PageRequest.of(page, size);
        Page<Country> countries = countryRepo.findAll(pageable);
        try{
            logger.info("Se han obtenido {} países", countries.getNumberOfElements());
            return countries.getContent().stream().map(CountryDTO::new).collect(Collectors.toList());// De pageable a List
        }catch (Exception e) {
            logger.error("Error al obtener los paíseses: {}",e.getCause(), e);//TRAZA
            throw new CountrySaveException("Error al obtener los países", e);
        } 
    }

    @Override
    public String test() {
       return "Hello World";
    }
    
}
