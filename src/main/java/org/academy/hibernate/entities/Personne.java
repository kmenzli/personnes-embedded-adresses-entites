package org.academy.hibernate.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@SuppressWarnings({ "unused", "serial" })
@Entity
@Table(name = "jpa02_hb_personne")
public class Personne implements Serializable{

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@Version
	private int version;

	@Column(length = 30, nullable = false, unique = true)
	private String nom;

	@Column(length = 30, nullable = false)
	private String prenom;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date datenaissance;

	@Column(nullable = false)
	private boolean marie;

	@Column(nullable = false)
	private int nbenfants;

	@Embedded
	private Adresse adresse;

	// constructeurs
	public Personne() {
	}

	public Personne(String nom, String prenom, Date datenaissance, boolean marie, int nbenfants) {
		setNom(nom);
		setPrenom(prenom);
		setDatenaissance(datenaissance);
		setMarie(marie);
		setNbenfants(nbenfants);
	}

	// toString
	public String toString() {
		return String.format("P[%d,%d,%s,%s,%s,%s,%d,%s]", getId(), getVersion(), getNom(), getPrenom(),
				new SimpleDateFormat("dd/MM/yyyy").format(getDatenaissance()), isMarie(), getNbenfants(), getAdresse());
	}

	// getters and setters
	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	private void setVersion(int version) {
		this.version = version;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}

	public boolean isMarie() {
		return marie;
	}

	public void setMarie(boolean marie) {
		this.marie = marie;
	}

	public int getNbenfants() {
		return nbenfants;
	}

	public void setNbenfants(int nbenfants) {
		this.nbenfants = nbenfants;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

}
