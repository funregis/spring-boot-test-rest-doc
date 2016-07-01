package com.altari.spring.ws.domain.client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.altari.spring.ws.domain.common.AbstractEntityReference;

/**
 * Pays represente des donnees de reference (non modifiables).<br/>
 * La Table est en Read Only uniquement.
 * 
 * <p>
 * Exemple de pays :
 * </p>
 * <ul>
 * <li>id=1, libelle=France, nationalite=Franeaise.</li>
 * </ul>
 */
@Entity
@Table(name = "PAYS")
@SuppressWarnings("serial")
public class Pays extends AbstractEntityReference {
    
    @Column(name = "LIBNAT")
    private String nationalite;

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    @Override
    public String toString() {
        return "Pays [nationalite=" + nationalite + ", getIdTec()=" + getIdTec() + ", getLibelle()=" + getLibelle() + "]";
    }
    
}