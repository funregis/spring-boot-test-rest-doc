package com.altari.spring.ws.domain.client;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.altari.spring.ws.domain.charge.ChargeClientele;

/**
 * <p>
 * Definition fonctionnelle : le client est la personne physique qui souscrit le
 * contrat aupres de leorganisme financier, et qui de ce fait est beneficiaire
 * des rentes ou capitaux generes, et decide de leorientation queil souhaite
 * donner a son produit deepargne. Le client peut designer un beneficiaire
 * (assure) autre que lui-meme, voire en designer plusieurs.
 * </p>
 * 
 * <p>
 * Cette classe materialise l'entite "Client" en mappant la table "CLIENT".
 * </p>
 * 
 * <p>
 * Comme toute entite, elle est serialisable puisqu'elle sera regie par des
 * Actions mises en Scope "CONVERSATION".
 * </p>
 * 
 * <p>
 * Cette entite possede un attribut technique "id" qui correspondra a une
 * sequence Oracle.
 * </p>
 * 
 * <p>
 * On y trouve egalement des annotations Bean Validation pour les contreles
 * de surface et des annotations JPA permettant le mapping objet-relationnel
 * (lien attribut - colonne, lien entre entites).
 * </p>
 * 
 * <p>
 * Tout entite a persister avec JPA doit posseder un constructeur vide. Il est
 * present par defaut, si aucun autre constructeur n'est specifie. Ce
 * constructeur devrait contenir les instanciations de tous les attributs pour
 * eviter un NullPointerException dans son appel dans les EL.
 * </p>
 * 
 * <p>
 * Difference entre Client et {@link Personne} : l'entite personne contient
 * toutes les donnees identifiant une personne, mais de maniere independante au
 * domain banquaire des assurances.<br/>
 * L'entite Client contient les donnees d'un client specifiques au domaine de
 * l'assurance et au domaine banquaire.<br/>
 * Un client fait reference a une personne (lien d'association).
 * </p>
 */
@Entity
@Table(name = "CLIENT")
public class Client   {

        /**
	 * cle primaire partagee sur entite Personne (c.f. &#64;PrimaryKeyJoinColumn ci-dessous)
	 */
	@Id
	@Column(name = "COPERS")
	private Long idTec;

	@Column(name = "DAHEM1")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date dateContact;

	@Column(name = "NUMRIB")
	@NotNull
	private String rib;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COCHAR", nullable = false)
	private ChargeClientele chargeClientele;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@PrimaryKeyJoinColumn(name="COPERS", referencedColumnName="CESEQO")
	private Personne personne;


	// ---------------------------------------------------------------------
	// Methodes fonctionnelles
	// ---------------------------------------------------------------------

	/**
	 * 
	 * <p>
	 * ajoute un contrat.
	 * </p>
	 * 
	 * @param aContrat
	 */
	// public void addContrat(Contrat aContrat) {
	//
	// if (contrats==null){
	// contrats = new ArrayList<Contrat>();
	// }
	// contrats.add(aContrat);
	// }

	/**
	 * <p>
	 * recupere un contrat.
	 * </p>
	 * 
	 * @param aIDContrat
	 * @return un contrat
	 */
	// public Contrat getContrat(long aIDContrat) {
	// if (contrats!=null){
	// Iterator<Contrat> lIT=contrats.iterator();
	// while (lIT.hasNext()){
	// Contrat lContratCourant = lIT.next();
	// if (lContratCourant.getIdTec()==aIDContrat){
	// return lContratCourant;
	// }
	// }
	// }
	// return null;
	// }

	/**
	 * 
	 * <p>
	 * valorise le client avec l'ensemble des valeurs des contrats attaches.
	 * </p>
	 * 
	 */
	public void valorise() {
		// Iterator<Contrat> lIT=contrats.iterator();
		// valeur=0;
		// while (lIT.hasNext()){
		// Contrat lContratCourant=lIT.next();
		// valeur=valeur+lContratCourant.getValeur();
		// }
	}

	// ---------------------------------------------------------------------
	// Accesseurs
	// ---------------------------------------------------------------------

	public ChargeClientele getChargeClientele() {
		return chargeClientele;
	}

	public void setChargeClientele(ChargeClientele chargeClientele) {
		this.chargeClientele = chargeClientele;
	}

	public Date getDateContact() {
		return dateContact;
	}

	public void setDateContact(Date dateContact) {
		this.dateContact = dateContact;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	// public List<Contrat> getContrats() {
	// return contrats;
	// }
	//
	// public void setContrats(List<Contrat> aContrats) {
	// contrats = aContrats;
	// }

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Long getIdTec() {
		return idTec;
	}

	public void setIdTec(Long idTec) {
		this.idTec = idTec;
	}

	// ---------------------------------------------------------------------
	// Redefinition des methodes "techniques"
	// ---------------------------------------------------------------------

	/**
	 * On redefinit la methode toString() pour avoir un affichage plus parlant
	 * (en logs, dans le debuggeur...).
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [idTec=" + idTec + ", dateContact=" + dateContact + ", rib="
				+ rib + ", chargeClientele=" + chargeClientele + ", personne="
				+ personne + "]";
	}

	/**
	 * Cette methode doit etre redefinie des que la methode equals(Object)
	 * l'est.
	 * 
	 * <p>
	 * Les attributs utilisees pour indiquer que 2 clients sont identiques
	 * doivent etre les memes : ici le matricule.
	 * </p>
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(personne);
	}

	/**
	 * On redefinit cette methode pour indiquer que fonctionnellement deux
	 * clients sont identiques s'ils ont le meme numero de matricule.
	 * 
	 * <p>
	 * Il faut systematiquement redefinir la methode hashCode() avec les memes
	 * criteres fonctionnelles ! Ce hashCode() sera bien plus performant pour
	 * retrouver un client dans une Collection.
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
		return !(obj == null || !(obj instanceof Client)) && Objects.equals(this.personne, ((Client) obj).personne);
	}
}