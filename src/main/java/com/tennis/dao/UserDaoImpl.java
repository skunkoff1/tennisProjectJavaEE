package com.tennis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tennis.model.User;

public class UserDaoImpl {

	private DaoFactory dao;
	
	public UserDaoImpl(DaoFactory dao) {
		this.dao = dao;
	}
	
	public User isValidLogin(String login, String password) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn = dao.getConnection();
			ps = cn.prepareStatement("SELECT * FROM connexion WHERE (connexion.login = ? OR connexion.pseudo = ?) AND connexion.password = ?");
			ps.setString(1, login);
			ps.setString(2, login);
			ps.setString(3, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt("connexion.id"),
						rs.getString("connexion.pseudo"),
						rs.getString("connexion.login"),
						rs.getString("connexion.password"),
						rs.getInt("connexion.profil"));
			}
			else {
				return null;
			}
		}
		catch (SQLException e) {
			return null;
		}
	}
	
	public String registerUser(User user) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn = dao.getConnection();
			
			ps = cn.prepareStatement("SELECT pseudo FROM connexion WHERE pseudo=?");
			ps.setString(1, user.getName());
			rs= ps.executeQuery();
			if(rs.next()) {
				return "le nom ou pseudo est déjà utilisé";
			}
			ps.close();
			
			ps = cn.prepareStatement("SELECT login FROM connexion WHERE login=?");
			ps.setString(1, user.getLogin());
			rs= ps.executeQuery();
			if(rs.next()) {
				return "l'email est déjà utilisé";
			}
			ps.close();
				
			ps = cn.prepareStatement("INSERT INTO connexion (pseudo, login, password, profil) VALUES (?,?,?,?)" );
			ps.setString(1, user.getName());
			ps.setString(2, user.getLogin());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getProfil());
			ps.executeUpdate();			
		}
		catch (SQLException e) {
			System.err.println(e);
			return "erreur";
		}
		return "Enregistrement réussi";
	}
}
