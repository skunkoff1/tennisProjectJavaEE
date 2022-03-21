package com.tennis.dao;

import java.util.List;

import com.tennis.model.Match;

public interface MatchDao {
	List<Match> lister(String mode, String search);
	List<Match> ajouter(int annnee, String tournoi, String winnerName, String winnerFirst, String finalName, String finalFirst);
}
