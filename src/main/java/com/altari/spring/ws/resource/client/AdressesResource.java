package com.altari.spring.ws.resource.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.altari.spring.ws.domain.client.Adresse;
import com.altari.spring.ws.json.view.Views;
import com.altari.spring.ws.repository.client.AdresseRepository;
import com.altari.spring.ws.repository.common.ResponseErrors.EntityNotExistException;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * ressource Adresses
 * @Régis LIMARE
 *
 */
@RestController
@RequestMapping(path="/api/adresses",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdressesResource {

    @Autowired
    private AdresseRepository adresseRepository;
        
    @RequestMapping(path="/search/find-by-criteria",method = RequestMethod.POST)
    public @JsonView(Views.Simple.class) ResponseEntity<Page<Adresse>> findByCriteria( @RequestBody Adresse adresse,
            Pageable pageRequest) {
        
        // recherche par critères partiels , insensible à la casse
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

        Example<Adresse> criteria = Example.of(adresse, matcher);

        return new ResponseEntity<Page<Adresse>>(adresseRepository.findAll(criteria, pageRequest), HttpStatus.OK);
    }
    @JsonView(Views.Simple.class)
    @RequestMapping(path="/{id}",method = RequestMethod.GET)
    public ResponseEntity<Adresse> findById(@PathVariable("id") Long id) {
        Optional<Adresse> adresse=Optional.ofNullable(adresseRepository.findOne(id));
        
        return new ResponseEntity<Adresse>(adresse.orElseThrow(() -> new EntityNotExistException(id,Adresse.class)), HttpStatus.OK);
    }
    @JsonView(Views.Simple.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Adresse adresse) throws URISyntaxException {
        Adresse created=adresseRepository.save(adresse);
        URI location=ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(created.getIdTec()).toUri();

        return ResponseEntity.created(location).build();
    }
    @JsonView(Views.Simple.class)
    @RequestMapping(path="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        adresseRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
