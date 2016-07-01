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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altari.spring.ws.domain.client.Pays;
import com.altari.spring.ws.repository.client.PaysRepository;
import com.altari.spring.ws.repository.common.ResponseErrors.EntityNotExistException;

@RestController
@RequestMapping(path = "/api/pays", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PaysResource {

    @Autowired
    private PaysRepository paysRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Pays>> findAll() {
        return new ResponseEntity<List<Pays>>(paysRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pays> findByIdTec(@PathVariable("id") Long id) {
        Optional<Pays> pays = Optional.ofNullable(paysRepository.findOne(id));
        return new ResponseEntity<Pays>(pays.orElseThrow(() -> new EntityNotExistException(id, Pays.class)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/search/find-by-libelle")
    public ResponseEntity<Pays> findByLibelle(@RequestParam("libelle") String libelle) {
        return new ResponseEntity<Pays>(paysRepository.findByLibelle(libelle), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/search/find-nationalite-by-partial-nationalite")
    public ResponseEntity<List<String>> findNationaliteByPartialNationalite(@RequestParam("partial") String partial) {
        return new ResponseEntity<List<String>>(paysRepository.findNationnaliteByPartialNationnalite(partial), HttpStatus.OK);
    }
}
