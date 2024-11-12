package com.vedruna.pruebatecnica1.exceptions;


//Excepción personalizada para recoger los países que den problema en el guardado
public class CountrySaveException extends RuntimeException {

    public CountrySaveException(String message) {
        super(message);
    }

    public CountrySaveException(String message, Throwable cause) {
        super(message, cause);
    }
}