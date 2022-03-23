package com.tennis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tennis.model.Match;

public class MatchDaoImpl implements MatchDao{

	private DaoFactory dao;
	
	public MatchDaoImpl(DaoFactory dao) {
		super();
		this.dao = dao;
	}
	
	@Override
	public List<Match> lister(String mode, String search) {
		ArrayList<Match> liste = new ArrayList<Match>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		if(search.equals("")) {
			if(mode.equals("winner") || mode.equals("all")) {
				try {
					cn = dao.getConnection();
					ps = cn.prepareStatement("SELECT tournoi.NOM, epreuve.ANNEE, joueur.NOM, joueur.PRENOM "
											+ "FROM joueur, epreuve, tournoi, match_tennis "
											+ "WHERE match_tennis.ID_VAINQUEUR = joueur.ID "
											+ "AND match_tennis.ID_EPREUVE = epreuve.ID "
											+ "AND epreuve.ID_TOURNOI = tournoi.ID");
					rs = ps.executeQuery();
					while(rs.next()) {
						liste.add(new Match(rs.getInt("epreuve.ANNEE"), 
								rs.getString("tournoi.NOM"), 
								rs.getString("joueur.PRENOM") + " " + rs.getString("joueur.NOM"), 
								"vainqueur"));
					}						
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}	
			} 
			if(mode.equals("final") || mode.equals("all")) {
				try {
					cn = dao.getConnection();
					ps = cn.prepareStatement("SELECT tournoi.NOM, epreuve.ANNEE, joueur.NOM, joueur.PRENOM "
											+ "FROM joueur, epreuve, tournoi, match_tennis "
											+ "WHERE match_tennis.ID_FINALISTE = joueur.ID "
											+ "AND match_tennis.ID_EPREUVE = epreuve.ID "
											+ "AND epreuve.ID_TOURNOI = tournoi.ID");
					rs = ps.executeQuery();
					while(rs.next()) {
						liste.add(new Match(rs.getInt("epreuve.ANNEE"), 
								rs.getString("tournoi.NOM"), 
								rs.getString("joueur.PRENOM") + " " + rs.getString("joueur.NOM"), 
								"finaliste"));
					}			
				}catch (SQLException e){
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
					}
				}	
			}
		} else {
			if(mode.equals("winner") || mode.equals("all")) {
				try {
					cn = dao.getConnection();
					ps = cn.prepareStatement("SELECT tournoi.NOM, epreuve.ANNEE, joueur.NOM, joueur.PRENOM "
											+ "FROM joueur, epreuve, tournoi, match_tennis "
											+ "WHERE match_tennis.ID_VAINQUEUR = joueur.ID "
											+ "AND match_tennis.ID_EPREUVE = epreuve.ID "
											+ "AND epreuve.ID_TOURNOI = tournoi.ID "
											+ "AND (joueur.NOM LIKE '%" + search +"%' OR joueur.PRENOM LIKE '%" + search + "%')");
					rs = ps.executeQuery();
					while(rs.next()) {
						liste.add(new Match(rs.getInt("epreuve.ANNEE"), 
								rs.getString("tournoi.NOM"), 
								rs.getString("joueur.PRENOM") + " " + rs.getString("joueur.NOM"), 
								"vainqueur"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}	
			} 
			if(mode.equals("final") || mode.equals("all")) {
				try {
					cn = dao.getConnection();
					ps = cn.prepareStatement("SELECT tournoi.NOM, epreuve.ANNEE, joueur.NOM, joueur.PRENOM "
											+ "FROM joueur, epreuve, tournoi, match_tennis "
											+ "WHERE match_tennis.ID_FINALISTE = joueur.ID "
											+ "AND match_tennis.ID_EPREUVE = epreuve.ID "
											+ "AND epreuve.ID_TOURNOI = tournoi.ID "
											+ "AND (joueur.NOM LIKE '%"+search+"%' OR joueur.PRENOM LIKE '%"+search+"%')");
					rs = ps.executeQuery();
					while(rs.next()) {
						liste.add(new Match(rs.getInt("epreuve.ANNEE"), 
								rs.getString("tournoi.NOM"), 
								rs.getString("joueur.PRENOM") + " " + rs.getString("joueur.NOM"), 
								"finaliste"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}	
			} 
		}
		return liste;
				
	}

	@Override
	public List<Match> ajouter(int annnee, String tournoi, String winnerName, String winnerFirst, String finalName,
			String finalFirst) {
		return null;
	}
			
	
	
}
