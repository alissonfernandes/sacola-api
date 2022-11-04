package br.com.sacola.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class BagIsClosedException extends Exception{

    public BagIsClosedException(Long id) {
        super("Bag is closed with ID: " + id);
    }
}