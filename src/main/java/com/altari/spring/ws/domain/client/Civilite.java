package com.altari.spring.ws.domain.client;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.altari.spring.ws.domain.common.AbstractEntityReference;

/**
 * Civilite represente des donnees de reference (non modifiables).<br/>
 * La Table en Read Only uniquement.
 * 
 * <p>
 * Exemple de civilites :
 * </p>
 * <ul>
 * <li>1 Inconnue</li>
 * <li>2 Madame</li>
 * <li>3 Mademoiselle</li>
 * <li>4 Monsieur</li>
 * </ul>
 */
@Entity
@Table(name = "CIVILITE")
@SuppressWarnings("serial")
public class Civilite extends AbstractEntityReference {

}