package com.tennis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tennis.model.Joueur;

public class JoueurDaoImpl implements JoueurDao{
		
	private DaoFactory dao;
	
	public JoueurDaoImpl(DaoFactory dao) {
		super();
		this.dao = dao;
	}
	
	@Override
	public List<Joueur> lister() {	
		ArrayList<Joueur> liste = new ArrayList<Joueur>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = dao.getConnection();
			ps = cn.prepareStatement("SELECT * FROM joueur");
			rs = ps.executeQuery();
			while(rs.next()) {
				liste.add(new Joueur(rs.getInt("joueur.ID"), 
						rs.getString("joueur.NOM"), 
						rs.getString("joueur.PRENOM"),
						rs.getString("joueur.SEXE")));
			}			
		}
		catch (SQLException e) {
			return null;
		}
		return liste;
	}
	
	@Override
	public void ajouter(Joueur joueur) {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = dao.getConnection();
			ps = cn.prepareStatement("INSERT INTO joueur(NOM, PRENOM, SEXE) VALUES(?,?,?)");
			ps.setString(1, joueur.getNom());
			ps.setString(2, joueur.getPrenom());
			ps.setString(3, joueur.getSexe());
			ps.executeUpdate();
					
		}
		catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	@Override
	public void modifier(Joueur joueur) {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = dao.getConnection();
			ps = cn.prepareStatement("UPDATE joueur SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?");
			ps.setString(1, joueur.getNom());
			ps.setString(2, joueur.getPrenom());
			ps.setString(3, joueur.getSexe());
			ps.setInt(4, joueur.getId());
			ps.executeUpdate();
					
		}
		catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	@Override
	public void supprimer(int id) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> IdMatch = new ArrayList<Integer>();
		try {
			cn = dao.getConnection();
			ps = cn.prepareStatement("SELECT ID FROM match_tennis WHERE ID_VAINQUEUR=? OR ID_FINALISTE=?");
			ps.setInt(1, id);
			ps.setInt(2, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				IdMatch.add(rs.getInt("match_tennis.ID"));
			}
			ps.close();
			for(int i=0; i<IdMatch.size(); i++) {
				ps = cn.prepareStatement("DELETE FROM score_vainqueur WHERE ID=?");
				ps.setInt(1, IdMatch.get(i));
				ps.executeUpdate();
			}
			ps.close();	
			
			ps = cn.prepareStatement("DELETE FROM match_tennis WHERE ID_VAINQUEUR=? OR ID_FINALISTE=?");
			ps.setInt(1, id);
			ps.setInt(2, id);
			ps.executeUpdate();
			ps.close();
			
			ps = cn.prepareStatement("DELETE FROM joueur WHERE ID=?");	
			ps.setInt(1, id);
			ps.executeUpdate();					
		}catch (SQLException e){
			e.printStackTrace();
		}	
	}

	@Override
	public List<Joueur> chercher(String search) {
		ArrayList<Joueur> liste = new ArrayList<Joueur>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = dao.getConnection();
			ps = cn.prepareStatement("SELECT * FROM joueur "
									+ "WHERE NOM LIKE '%" + search + "%' OR PRENOM LIKE '%" + search + "%'");
			rs = ps.executeQuery();
			while(rs.next()) {
				liste.add(new Joueur(rs.getInt("joueur.ID"), 
						rs.getString("joueur.NOM"), 
						rs.getString("joueur.PRENOM"),
						rs.getString("joueur.SEXE")));
			}			
		}
		catch (SQLException e) {
			return null;
		}
		return liste;
	}
}
