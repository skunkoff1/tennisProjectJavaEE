package com.tennis.model;

public class Match {
	private int annee;
	private String tournoi;
	private String nom;
	private String statut;
	
	public Match(int annee, String tournoi, String nom, String statut) {
		this.annee = annee;
		this.tournoi = tournoi;
		this.nom = nom;
		this.statut = statut;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public String getTournoi() {
		return tournoi;
	}

	public void setTournoi(String tournoi) {
		this.tournoi = tournoi;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	
}
