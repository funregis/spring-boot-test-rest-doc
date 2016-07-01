package com.altari.spring.ws.repository.client;

import java.util.List;

import javax.cache.annotation.CacheResult;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.altari.spring.ws.domain.client.Pays;


public interface PaysRepository extends CrudRepository<Pays, Long>{
    
    @CacheResult(cacheName="pays")
    public Pays findByLibelle(String libelle);
    
    @Override
    @CacheResult(cacheName="pays")
    public List<Pays> findAll();
    
    @Query("select nationalite from Pays where nationalite like %:partial%")
    public List<String> findNationnaliteByPartialNationnalite(@Param("partial") String partial);
}
