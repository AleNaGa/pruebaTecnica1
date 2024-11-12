package com.vedruna.pruebatecnica1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
//La clase ExternalCountryDTO es una clase anidada que recoge del JSON. Se usará para CreateCountryDTO
public class ExternalCountryDTO {

    //Los atributos de la clase son los nombre de los campos de la api
    private String ccn3;
    private Name name;
    private Long population;




    //El objeto nombre no es un String sino que tiene nombres anidados. Queremos el nombre bajo el atributo common. 
    public static class Name {
        private String common;

        public String getCommon() {
            return common;
        }

        public void setCommon(String common) {
            this.common = common;
        }
    }


}
