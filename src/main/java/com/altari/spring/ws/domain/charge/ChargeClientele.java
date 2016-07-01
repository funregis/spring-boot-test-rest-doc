package com.altari.spring.ws.domain.charge;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class ChargeClientele {

    @Id
    @Column(name = "CESEQO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CHARGECLIENTELE")
    @SequenceGenerator(name = "SEQ_CHARGECLIENTELE", sequenceName = "SPHSUSER_CESEQO", allocationSize = 1)
    private Long idTec;

    // @Version
    // @Column(name="version")
    @Transient
    private Integer version;

    @Column(name = "NOMPAT")
    @NotNull
    private String nom;

    @Column(name = "PRENOM")
    @NotNull
    private String prenom;

    @Pattern(message = "{chargeClientele.companyCode.invalid']}", regexp = "[0-9]{2}[0-9a-zA-Z]")
    @Column(name = "COETBL")
    @NotNull
    private String companyCode;

    @Column(name = "LINUT5")
    @Size(max = 6)
    @Pattern(message = "{chargeClientele.userRACF.invalid']}", regexp = "[a-z0-9]*")
    @NotNull
    private String userRACF;

    // ---------------------------------------------------------------------
    // Accesseurs des attributs
    // ---------------------------------------------------------------------

    /**
     * Cette methode retourne l'identifiant technique (sequence) necessaire pour l'utilisation dans le cadre des actions.
     */
    public Long getIdTec() {
        return idTec;
    }

    /**
     * Cette methode alimente l'identifiant technique (sequence) necessaire pour l'utilisation dans le cadre des actions.
     */
    public void setIdTec(Long idTec) {
        this.idTec = idTec;
    }

    /**
     * Cette methode retourne le nom du Charge de clientele.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Cette methode alimente le nom du Charge de clientele.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Cette methode retourne le prenom du Charge de clientele.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Cette methode alimente le prenom du Charge de clientele.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Cette methode retourne le code etablissement du Charge de clientele.
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * Cette methode alimente le code etablissement du Charge de clientele.
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * Cette methode retourne le user Racf du Charge de clientele.
     */
    public String getUserRACF() {
        return userRACF;
    }

    /**
     * Cette methode alimente le user Racf du Charge de clientele.
     */
    public void setUserRACF(String userRACF) {
        this.userRACF = userRACF;
    }

    public Integer getVersion() {
        return version;
    }

    // ---------------------------------------------------------------------
    // Redefinition des methodes "techniques"
    // ---------------------------------------------------------------------

	/**
	 * On redefinit la methode toString() pour avoir un affichage plus parlant
	 * (en logs, dans le debuggeur...).
	 */
	@Override
	public String toString() {
		return "ChargeClientele [id=" + idTec + ", version=" + version + ", nom="
				+ nom + ", prenom=" + prenom + ", companyCode=" + companyCode
				+ ", userRACF=" + userRACF + "]";
	}

    /**
     * Cette methode doit etre redefinie des que la methode equals(Object) l'est.
     * 
     * <p>
     * Les attributs utilisees pour indiquer que 2 charges de clientele sont identiques doivent etre les memes : ici le User RACF.
     * </p>
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(userRACF);
    }

    /**
     * On redefinit cette methode pour indiquer que fonctionnellement deux charges de clientele sont identiques s'ils ont le meme User RACF independamment de la casse (la casse devra etre verifiee
     * dans le Manager).
     * 
     * <p>
     * Il faut systematiquement redefinir la methode hashCode() avec les memes criteres fonctionnelles ! Ce hashCode() sera bien plus performant pour retrouver un Charge de clientele dans une
     * Collection.
     * </p>
     * 
     * @param aObject
     *            l'objet de comparaison
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return !(obj == null || !(obj instanceof ChargeClientele)) && Objects.equals(this.userRACF, ((ChargeClientele) obj).userRACF);
    }

}