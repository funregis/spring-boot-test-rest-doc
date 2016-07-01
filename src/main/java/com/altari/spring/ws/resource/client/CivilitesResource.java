package com.altari.spring.ws.resource.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altari.spring.ws.domain.client.Civilite;
import com.altari.spring.ws.repository.client.CiviliteRepository;
import com.altari.spring.ws.repository.common.ResponseErrors.EntityNotExistException;

@RestController
@RequestMapping(path="/api/civilites",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CivilitesResource {
    
    @Autowired
    private CiviliteRepository civiliteRepository;
    
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Civilite>> findAll(){
        return new ResponseEntity<List<Civilite>>(civiliteRepository.findAll(), HttpStatus.OK);
    }
    @RequestMapping(method=RequestMethod.GET,path="/{id}")
    public ResponseEntity<Civilite> findById(@PathVariable("id") Long id){
        Optional<Civilite> civilite=Optional.ofNullable(civiliteRepository.findOne(id));      
        return new ResponseEntity<Civilite>(civilite.orElseThrow(() -> new EntityNotExistException(id,Civilite.class)), HttpStatus.OK);
    }  
}
