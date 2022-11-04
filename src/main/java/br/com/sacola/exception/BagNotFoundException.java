package br.com.sacola.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BagNotFoundException extends Exception{

    public  BagNotFoundException(Long id) {
        super("Bag not found with ID: " + id);
    }
}
