package com.altari.spring.ws.repository.client;

import java.util.List;

import javax.cache.annotation.CacheResult;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altari.spring.ws.domain.client.Civilite;


public interface CiviliteRepository extends JpaRepository<Civilite, Long>{
    @Override
    @CacheResult(cacheName="civilite")
    public List<Civilite> findAll();
    
    @Override
    @CacheResult(cacheName="civilite")
    public Civilite findOne(Long id);
    
}
