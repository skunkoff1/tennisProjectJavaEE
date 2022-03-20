package com.tennis.dao;

import java.util.List;

import com.tennis.model.Tournoi;

public interface TournoiDao {
	List<Tournoi> lister();
	void ajouter(Tournoi tournoi);
	void modifier(Tournoi tournoi);
	void supprimer(int id);
	void chercher(String search);
}
