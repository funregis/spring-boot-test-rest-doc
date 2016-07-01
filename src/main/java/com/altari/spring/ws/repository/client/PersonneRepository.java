package com.altari.spring.ws.repository.client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.altari.spring.ws.domain.client.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long>{

    //scaffolding via le nom de la méthode
    public List<Personne> findByNomIgnoreCaseOrderByPrenomAsc(String nom);
    
    //custom jpql query
    @Query("from Personne p join fetch p.adresseResidence where LOWER(p.nom) = :nom order by p.prenom asc")
    public List<Personne> findByNomIgnoreCaseOrderByPrenomAscCustom(@Param("nom") String nom);
    
    //custom jpql query
    @Query("from Personne p join fetch p.adresseResidence where prenom = :prenom and rownum < 100")
    public List<Personne> findByPrenom(@Param("prenom") String prenom);
    
    //pas bien !!!
    @Query("from Personne where prenom = :prenom and rownum < 100")
    public List<Personne> findBadPerfPrenom(@Param("prenom") String prenom);
}
