package com.altari.spring.ws.domain.client;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.altari.spring.ws.json.view.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <p>
 * Entite mappant la table <code>ADRESSE</code>.
 * </p>
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ADRESSE")
//fetch graph permettant de passer pays en eager loading
@NamedEntityGraph(name = "adresse.fetchPays",
    attributeNodes = @NamedAttributeNode(value="pays"))
public class Adresse implements Serializable {

    /**
     * cle primaire partagee sur entite Personne 
     */
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADR_SEQ")
    @SequenceGenerator(name="ADR_SEQ",sequenceName="SPHSADRESSE_CESEQO")
    @Column(name = "COPERS",precision=10)
    private Long idTec;

    /**
     * cle primaire partagee
     */
    @OneToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "COPERS", referencedColumnName = "CESEQO")
    private Personne personne;

    // @Version
    // @Column(name="version")
    @Transient
    @JsonIgnore
    private Integer version;

    /** Champ rue de l'adresse de residence. */
    @Column(name = "LIBRUE")
    private String rue;

    /** Champ code postal de l'adresse de residence. */
    @Column(name = "COPOST")
    @NotNull
    @Size(max = 5)
    private String codePostal;

    /** Champ ville de l'adresse de residence. */
    @Column(name = "LIBVIL")
    @NotNull
    private String ville;

    /** ville de naissance */
    @Column(name = "LIBVIN")
    @NotNull
    private String villen;

    /** Code postal pays de naissance */
    @Column(name = "COPOSN")
    @NotNull
    @Size(max = 5)
    private String codePostaln;

    /** Pays de l'adresse de naissance. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COPAYN", nullable = false)
    @NotNull
    private Pays paysn;

    /** Pays de l'adresse de residence. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COPAYS", nullable = false)
    @NotNull
    private Pays pays;

    // ---------------------------------------------------------------------
    // Accesseurs
    // ---------------------------------------------------------------------
    @JsonView(Views.Simple.class)
    public Long getIdTec() {
        return idTec;
    }
    public void setIdTec(Long idTec) {
        this.idTec = idTec;
    }

    @JsonView(Views.Simple.class)
    public String getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    @JsonView(Views.Simple.class)
    public String getRue() {
        return rue;
    }

    public void setRue(String aRue) {
        rue = aRue;
    }
    @JsonView(Views.Simple.class)
    public String getVille() {
        return ville;
    }

    public void setVille(String aVille) {
        ville = aVille;
    }
    @JsonView(Views.Simple.class)
    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays aPays) {
        pays = aPays;
    }

    public String getCodePostaln() {
        return codePostaln;
    }

    public void setCodePostaln(String codePostaln) {
        this.codePostaln = codePostaln;
    }

    public Pays getPaysn() {
        return paysn;
    }

    public void setPaysn(Pays paysn) {
        this.paysn = paysn;
    }

    public String getVillen() {
        return villen;
    }

    public void setVillen(String villen) {
        this.villen = villen;
    }
    
    public Integer getVersion() {
        return version;
    }
    @JsonView(Views.Simple.class)
    public String getLieuNaissance(){
        return this.codePostaln!=null&&this.villen!=null?String.format("%s %s", this.codePostaln,this.villen ):null;
    }
    @Override
    public String toString() {
        return String.format("Adresse [idTec=%s, personne=%s, version=%s, rue=%s, codePostal=%s, ville=%s, villen=%s, codePostaln=%s, paysn=%s, pays=%s]", idTec, personne, version, rue, codePostal,
                ville, villen, codePostaln, paysn, pays);
    }    

}