package com.tennis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tennis.model.Joueur;
import com.tennis.model.Tournoi;

public class TournoiDaoImpl implements TournoiDao{
	
	private DaoFactory dao;
	
	public TournoiDaoImpl(DaoFactory dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<Tournoi> lister() {
		ArrayList<Tournoi> liste = new ArrayList<Tournoi>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		try {
			cn = dao.getConnection();
			ps = cn.prepareStatement("SELECT epreuve.ANNEE, epreuve.TYPE_EPREUVE, tournoi.NOM, epreuve.ID "
									+ "FROM tennis.epreuve, tennis.tournoi "
									+ "WHERE epreuve.ID_TOURNOI = tournoi.ID");			
			rs = ps.executeQuery();
			while(rs.next()) {	
				liste.add(new Tournoi(rs.getInt("epreuve.ID"), 
						rs.getInt("epreuve.ANNEE"), 
						rs.getString("tournoi.NOM"),
						rs.getString("epreuve.TYPE_EPREUVE")));
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
		return liste;
	}
	
	@Override
	public void ajouter(Tournoi tournoi) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int ID = 0;
		
		try {
			cn = dao.getConnection();
			ps = cn.prepareStatement("SELECT ID FROM tournoi WHERE NOM = ?");	
			ps.setString(1, tournoi.getNom());
			rs = ps.executeQuery();				
			boolean empty = true;
			while( rs.next() ) {
			    //ResultSet processing here
			    empty = false;
			    ID = rs.getInt("tournoi.ID");
			}

			if( empty ) {
			    //Empty result set
				System.err.println("pas de résultat");
			}
			else {
				ps.close();
				ps = cn.prepareStatement("INSERT INTO epreuve(ANNEE, TYPE_EPREUVE, ID_TOURNOI) VALUES(?,?,?)");
				ps.setInt(1,tournoi.getAnnee());
				ps.setString(2, tournoi.getType());
				ps.setInt(3,ID);
				ps.executeUpdate();
				ps.close();
				
			}						
		}catch (SQLException e){
			e.printStackTrace();
			System.err.println("Un problème est survenu" + e);
		}
	}

	@Override
	public void modifier(Tournoi tournoi) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int ID = 0;
		
		try {
			cn = dao.getConnection();
			ps = cn.prepareStatement("SELECT ID FROM tournoi WHERE NOM = ?");	
			ps.setString(1, tournoi.getNom());
			rs = ps.executeQuery();				
			boolean empty = true;
			while( rs.next() ) {
			    //ResultSet processing here
			    empty = false;
			    ID = rs.getInt("tournoi.ID");
			}

			if( empty ) {
			    //Empty result set
				System.err.println("pas de résultat");
			}
			else {
				ps.close();
				ps = cn.prepareStatement("UPDATE epreuve SET ANNEE=?, TYPE_EPREUVE=?, ID_TOURNOI=? WHERE ID=?");
				ps.setInt(1,tournoi.getAnnee());
				ps.setString(2, tournoi.getType());
				ps.setInt(3,ID);
				ps.setInt(4, tournoi.getId());
				ps.executeUpdate();
				ps.close();
				
			}						
		}catch (SQLException e){
			e.printStackTrace();
			System.err.println("Un problème est survenu" + e);
		}
	}
	
	@Override
	public void supprimer(int id) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int IDmatch = 0;
		try {
			cn = dao.getConnection();
			ps = cn.prepareStatement("SELECT ID FROM match_tennis WHERE match_tennis.ID_EPREUVE = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				IDmatch = rs.getInt(1);
			}
			ps.close();
			
			ps = cn.prepareStatement("DELETE FROM score_vainqueur WHERE ID=?");
			ps.setInt(1, IDmatch);
			ps.executeUpdate();
			ps.close();
			
			ps = cn.prepareStatement("DELETE FROM match_tennis WHERE match_tennis.ID_EPREUVE = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			ps = cn.prepareStatement("DELETE FROM epreuve WHERE ID = ?");	
			ps.setInt(1, id);
			ps.executeUpdate();						
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Tournoi> chercher(String search) {
		ArrayList<Tournoi> liste = new ArrayList<Tournoi>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {	
			cn = dao.getConnection();
			ps = cn.prepareStatement("SELECT epreuve.ANNEE, epreuve.TYPE_EPREUVE, tournoi.NOM, epreuve.ID "
					+ "FROM tennis.epreuve, tennis.tournoi"
					+ " WHERE epreuve.ID_TOURNOI = tournoi.ID AND epreuve.ANNEE "
					+ "LIKE '%"+search+"%' OR tournoi.NOM LIKE '%"+search+"%'");
			rs = ps.executeQuery();
			while(rs.next()) {	
				liste.add(new Tournoi(rs.getInt("epreuve.ID"), 
						rs.getInt("epreuve.ANNEE"), 
						rs.getString("tournoi.NOM"),
						rs.getString("epreuve.TYPE_EPREUVE")));
				}
			}
			catch (SQLException e){
				e.printStackTrace();
				System.err.println("Un problème est survenu" + e);
			}
		return liste;
	}

	@Override
	public List<Joueur> getplayers(int id) {
		ArrayList<Joueur> liste = new ArrayList<Joueur>();
		Connection cn = null;
		PreparedStatement ps = null;	
		ResultSet rs =null;
		try {
			cn = dao.getConnection();
			ps = cn.prepareStatement("SELECT joueur.nom, joueur.prenom "
									+ "FROM joueur, match_tennis "
									+ "WHERE match_tennis.ID_EPREUVE ='"+id+"'"
									+ " AND joueur.ID = match_tennis.ID_VAINQUEUR;");
			rs = ps.executeQuery();
			boolean empty = true;
			while(rs.next()) {	
				empty = false;
				liste.add(new Joueur(rs.getString("joueur.PRENOM"),rs.getString("joueur.NOM")));
			}	
			if(empty == true) {
				liste.add(new Joueur("pas de ","données"));
			}
			ps.close();				
			rs = null;
			cn = dao.getConnection();
			ps = cn.prepareStatement("SELECT joueur.nom, joueur.prenom "
									+ "FROM joueur, match_tennis "
									+ "WHERE match_tennis.ID_EPREUVE ='"+id+"'"
									+ " AND joueur.ID = match_tennis.ID_FINALISTE;");
			rs = ps.executeQuery();
			empty=true;
			while(rs.next()) {		
				empty = false;
				liste.add(new Joueur(rs.getString("joueur.PRENOM"),rs.getString("joueur.NOM")));
			}
			if(empty == true) {
				liste.add(new Joueur("pas de ","données"));
			}
		}catch (SQLException e){
			e.printStackTrace();
		
		}
		return liste;
	}

	

}
