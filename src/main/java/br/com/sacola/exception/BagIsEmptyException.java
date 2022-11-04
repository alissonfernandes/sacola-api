package br.com.sacola.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class BagIsEmptyException extends Exception{

    public BagIsEmptyException(Long id) {
        super("Bag with ID: " + id + " is empty");
    }
}
