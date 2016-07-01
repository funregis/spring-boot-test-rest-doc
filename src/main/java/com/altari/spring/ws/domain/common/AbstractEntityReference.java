package com.altari.spring.ws.domain.common;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import com.altari.spring.ws.json.view.Views;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Entite mere de toutes les entites de type referentiel (code + libelle).
 */
@MappedSuperclass
@SuppressWarnings("serial")
public class AbstractEntityReference implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ABSTRACTENTITYREFERENCE")
    @SequenceGenerator(name = "SEQ_ABSTRACTENTITYREFERENCE", sequenceName = "SPHSREFERENTIEL", allocationSize = 1)
    @Column(name = "CESEQO")
    private Long idTec;

    @Column(name = "LINUT1")
    @JsonView(Views.Simple.class)
    private String libelle;

    // ----------------------------------------------------------------------------
    // Accesseurs
    // ----------------------------------------------------------------------------

    public Long getIdTec() {
        return idTec;
    }

    public void setIdTec(Long idTec) {
        this.idTec = idTec;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Cette methode doit etre redefinie des que la methode equals(Object) l'est.
     * 
     * <p>
     * Les attributs utilisees pour indiquer que 2 entites sont identiques doivent etre les memes : ici l'identifiant.
     * </p>
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(idTec);
    }

    /**
     * On redefinit cette methode pour indiquer que fonctionnellement deux entites sont identiques si elles ont le meme identifiant.
     * 
     * <p>
     * Il faut systematiquement redefinir la methode hashCode() avec les memes criteres fonctionnelles ! Ce sera bien plus performant pour retrouver une entite dans une Collection.
     * </p>
     * 
     * @param obj
     *            l'objet de comparaison
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        final AbstractEntityReference other = (AbstractEntityReference) obj;
        return Objects.equals(this.idTec, other.idTec);
    }
}