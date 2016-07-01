package com.altari.spring.ws.repository.client;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altari.spring.ws.domain.client.Client;


public interface ClientRepository extends JpaRepository<Client, Long>{

}
