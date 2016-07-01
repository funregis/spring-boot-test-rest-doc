package com.altari.spring.ws.repository.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Contient les Exception liées au ressources REST
 * @Régis LIMARE
 *
 */
public class ResponseErrors {
    @SuppressWarnings("serial")
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    public static class EntityNotExistException extends RuntimeException {
        
        public EntityNotExistException(String id,Class<?> entite) {
                super(String.format("La ressource '%s' d'id '%s' n'existe pas",entite.getSimpleName(),id));
        }

        public EntityNotExistException(Long id,Class<?> entite) {
            this(id.toString(),entite);
        }
    };
    
    
}
