package br.com.sacola.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ProductDifferentException extends Exception{

    public ProductDifferentException(Long id) {
        super("product with id " + id + " is from a different restaurant");
    }
}
