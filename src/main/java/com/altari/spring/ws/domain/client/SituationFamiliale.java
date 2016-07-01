package com.altari.spring.ws.domain.client;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.altari.spring.ws.domain.common.AbstractEntityReference;

/**
 * Civilite represente des donnees de reference (non modifiables).<br/> La Table en Read Only uniquement.
 * 
 * <p>
 * Exemple de situations familiales :
 * </p>
 * <ul>
 * <li> id=1, libelle=Autre</li>
 * <li> id=2, libelle=Celibataire</li>
 * <li> id=3, libelle=Concubin</li>
 * <li> id=4, libelle=Divorce</li>
 * <li> id=5, libelle=Marie</li>
 * </ul>
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "FAMILLE")
public class SituationFamiliale extends AbstractEntityReference {
}