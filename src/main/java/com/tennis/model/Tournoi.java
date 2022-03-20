package com.tennis.model;

public class Tournoi {
	private int id;
	private int annee;
	private String nom;
	private String type;
	
	public Tournoi() {		
	}
	
	public Tournoi(int annee, String nom, String type) {
		this.annee = annee;
		this.nom = nom;
		this.type = type;
	}
	
	public Tournoi(int id, int annee, String nom, String type) {
		this.id = id;
		this.annee = annee;
		this.nom = nom;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
