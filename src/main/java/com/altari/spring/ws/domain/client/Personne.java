package com.altari.spring.ws.domain.client;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "PERSONNE")
@JsonAutoDetect
@SuppressWarnings("serial")
public class Personne implements Serializable{

	/** Identifiant technique. */
	@Column(name = "CESEQO")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERSONNE")
	@SequenceGenerator(name = "SEQ_PERSONNE", sequenceName = "SPHSPERSONNE_CESEQO", allocationSize = 1)
	private Long idTec;

	// @Version
	// @Column(name="version")
	@Transient
	private Integer version;

	/** La date de naissance. */
	@Column(name = "DAHEME")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy",shape=Shape.STRING)
	@NotNull
	private Date dateNaissance;

	/** Le nom marital. */
	@Column(name = "NOMMAR")
	private String nomMarital;

	/** Le nom patronymique. */
	@Column(name = "NOMPAT")
	@NotNull
	private String nom;

	/** Le prenom de la personne. */
	@Column(name = "PRENOM")
	@NotNull
	private String prenom;

	/** Le deuxieme prenom de la personne. */
	@Column(name = "PRENO2")
	private String deuxiemePrenom;

	/** Le numero de securite sociale. */
	@Column(name = "COSECU")
	private String numeroSecuriteSociale;

	// Correspond au code client ou code assure
	@NotNull(message = "le matricule est obligatoire!")
	@Size(max = 8, message = "la longeur est 8 max!")
	@Column(name = "COENVO")
	private String matricule;

	/** La nationalite. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONATI", nullable = false)
	@NotNull
	private Pays nationalite;

	/** Adresse de residence. */
	@OneToOne(cascade = { CascadeType.REMOVE })
	@PrimaryKeyJoinColumn
	private Adresse adresseResidence;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COCIVI", nullable = false)
	@NotNull
	private Civilite civilite;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COSITF", nullable = false)
	@NotNull
	private SituationFamiliale situationFamiliale;

	// ---------------------------------------------------------------------
	// Methodes a redefinir
	// ---------------------------------------------------------------------

	/**
	 * <p>
	 * On redefinit la methode toString() pour avoir un affichage plus parlant
	 * (en logs, dans le debuggeur...).
	 * </p>
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Personne [idTec=" + idTec + ", version=" + version
				+ ", dateNaissance=" + dateNaissance + ", nomMarital="
				+ nomMarital + ", nom=" + nom + ", prenom=" + prenom
				+ ", deuxiemePrenom=" + deuxiemePrenom
				+ ", numeroSecuriteSociale=" + numeroSecuriteSociale
				+ ", matricule=" + matricule + ", nationalite=" + nationalite
				+ ", adresseResidence=" + adresseResidence + ", civilite="
				+ civilite + ", situationFamiliale=" + situationFamiliale + "]";
	}

	/**
	 * Cette methode doit etre redefinie des que la methode equals(Object)
	 * l'est.
	 * 
	 * <p>
	 * Les attributs utilisees pour indiquer que 2 personnes sont identiques
	 * doivent etre les memes : ici le matricule.
	 * </p>
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(matricule);
	}

	/**
	 * On redefinit cette methode pour indiquer que fonctionnellement deux
	 * personnes sont identiques s'ils ont le meme matricule independamment de
	 * la casse (la casse devra etre verifiee dans le Manager).
	 * 
	 * <p>
	 * Il faut systematiquement redefinir la methode hashCode() avec les memes
	 * criteres fonctionnelles ! Ce hashCode() sera bien plus performant pour
	 * retrouver une personne dans une Collection.
	 * </p>
	 * 
	 * @param obj
	 *            l'objet de comparaison
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		return !(obj == null || !(obj instanceof Personne)) && Objects.equals(this.matricule, ((Personne) obj).matricule);
	}

	// ---------------------------------------------------------------------
	// Accesseurs
	// ---------------------------------------------------------------------

	public Long getIdTec() {
		return idTec;
	}

	public void setIdTec(Long idTec) {
		this.idTec = idTec;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getDeuxiemePrenom() {
		return deuxiemePrenom;
	}

	public void setDeuxiemePrenom(String deuxiemePrenom) {
		this.deuxiemePrenom = deuxiemePrenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomMarital() {
		return nomMarital;
	}

	public void setNomMarital(String nomMarital) {
		this.nomMarital = nomMarital;
	}

	public String getNumeroSecuriteSociale() {
		return numeroSecuriteSociale;
	}

	public void setNumeroSecuriteSociale(String numeroSecuriteSociale) {
		this.numeroSecuriteSociale = numeroSecuriteSociale;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Pays getNationalite() {
		return nationalite;
	}

	public void setNationalite(Pays pays) {
		nationalite = pays;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public SituationFamiliale getSituationFamiliale() {
		return situationFamiliale;
	}

	public void setSituationFamiliale(SituationFamiliale situationFamiliale) {
		this.situationFamiliale = situationFamiliale;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Adresse getAdresseResidence() {
		return adresseResidence;
	}

	public void setAdresseResidence(Adresse adresseResidence) {
		this.adresseResidence = adresseResidence;
	}

	public Integer getVersion() {
		return version;
	}

}