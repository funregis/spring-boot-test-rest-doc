package com.altari.spring.ws.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.altari.spring.ws.repository.common.ResponseErrors.EntityNotExistException;
import com.altari.spring.ws.repository.common.RestErrorResponse;

/**
 * Gère la remontée des exceptions liées aux service Rest , et renvoi une réponse http associée
 * @Régis LIMARE
 *
 */
@ControllerAdvice
public class RestErrorHandlers {

    /**
     * transforme l'exception EntityNotExistException en reponse standardisée
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(EntityNotExistException.class)
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    public RestErrorResponse userNotFoundExceptionHandler(EntityNotExistException ex) {
        return new RestErrorResponse(ex.getMessage());
    }
}
