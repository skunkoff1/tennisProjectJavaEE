package com.tennis.dao;

import java.util.List;

import com.tennis.model.Joueur;

public interface JoueurDao {	
	List<Joueur> lister();
	void ajouter(Joueur joueur);
	void modifier(Joueur joueur);
	void supprimer(int id);
	List<Joueur> chercher(String search);
}
