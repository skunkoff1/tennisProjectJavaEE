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
			ps = cn.prepareStatement("SELECT * FROM connexion WHERE connexion.login = ? AND connexion.password = ?");
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt("connexion.id"),
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
}
