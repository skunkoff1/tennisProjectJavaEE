package com.tennis.model;

import com.tennis.beans.BeanException;

public class Joueur {
	private int id;
	private String nom;
	private String prenom;
	private String sexe;
	
	public Joueur() {
		
	}
	
	public Joueur(int id, String nom, String prenom, String sexe) {
		this.id = id;
		this.nom =nom;
		this.prenom = prenom;
		this.sexe = sexe;
	}
	
	public Joueur ( String nom, String prenom, String sexe) {
		this.nom =nom;
		this.prenom = prenom;
		this.sexe = sexe;
	}
	
	public Joueur(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) throws BeanException {
		if(nom.length() >20) {
			throw new BeanException("le nom est trop grand (20 caractères maximum)");
		}
		else {
			this.nom = nom;			
		}
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) throws BeanException {
		if(prenom.length() >20) {
			throw new BeanException("le nom est trop grand (20 caractères maximum)");
		}
		else {
			this.prenom = prenom;
		}
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}

}
