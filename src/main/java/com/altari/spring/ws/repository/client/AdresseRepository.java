package com.altari.spring.ws.repository.client;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.altari.spring.ws.domain.client.Adresse;

/**
 * 
 * @Régis LIMARE
 *
 */
public interface AdresseRepository extends JpaRepository<Adresse,Long>{

    /**
     * recherche avec pagination en ce basant sur l'objet adresse passé en paramètre ,comme critère de recherche
     */
    @Override
    @EntityGraph(value = "adresse.fetchPays")
    public <S extends Adresse> Page<S> findAll(Example<S> aExample, Pageable aPageable) ;

    @Override
    @EntityGraph(value = "adresse.fetchPays")
    public Adresse findOne(Long aId) ;
    
    
    
}
