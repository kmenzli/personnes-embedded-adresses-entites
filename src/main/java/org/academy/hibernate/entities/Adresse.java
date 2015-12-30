package org.academy.hibernate.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class Adresse implements Serializable {

	// champs
	@Column(length = 30, nullable = false)
	private String adr1;

	@Column(length = 30)
	private String adr2;

	@Column(length = 30)
	private String adr3;

	@Column(length = 5, nullable = false)
	private String codePostal;

	@Column(length = 20, nullable = false)
	private String ville;

	@Column(length = 3)
	private String cedex;

	@Column(length = 20, nullable = false)
	private String pays;

	// constructeurs
	public Adresse() {

	}

	public Adresse(String adr1, String adr2, String adr3, String codePostal, String ville, String cedex, String pays) {
		super();
		this.adr1 = adr1;
		this.adr2 = adr2;
		this.adr3 = adr3;
		this.codePostal = codePostal;
		this.ville = ville;
		this.cedex = cedex;
		this.pays = pays;
	}

	// getters et setters
	public String getAdr1() {
		return adr1;
	}

	public void setAdr1(String adr1) {
		this.adr1 = adr1;
	}

	public String getAdr2() {
		return adr2;
	}

	public void setAdr2(String adr2) {
		this.adr2 = adr2;
	}

	public String getAdr3() {
		return adr3;
	}

	public void setAdr3(String adr3) {
		this.adr3 = adr3;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCedex() {
		return cedex;
	}

	public void setCedex(String cedex) {
		this.cedex = cedex;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	// toString
	public String toString() {
		return String.format("A[%s,%s,%s,%s,%s,%s,%s]", getAdr1(), getAdr2(), getAdr3(), getCodePostal(), getVille(), getCedex(), getPays());
	}
}
