package com.tennis.model;

public class User {
	private int id;
	private String login;
	private String password;
	private int profil;

	public User() {
		
	}
	
	public User(int id,String login, String password, int profil) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.profil = profil;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getProfil() {
		return profil;
	}

	public void setProfil(int profil) {
		this.profil = profil;
	}
}
