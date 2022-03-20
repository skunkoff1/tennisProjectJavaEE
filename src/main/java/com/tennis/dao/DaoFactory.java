package com.tennis.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

	private String url;
	private String username;
	private String password;
	
	public DaoFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}	
	
	public static DaoFactory getInstance() {
		String url = "jdbc:mysql://localhost:3306/tennis" ;
		String username = "root";
		String password = "Damabiah777!";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
		}
		catch(ClassNotFoundException e) {
			System.err.println(e);
		}
		DaoFactory dao = new DaoFactory(url, username, password);
		return dao;
	}
	
	public Connection getConnection() throws SQLException{	
	    Connection cn = DriverManager.getConnection(url, username, password);
	    return cn;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
 }
