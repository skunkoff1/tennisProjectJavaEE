package com.tennis.model;

public class User {
	private int id;
	private String name;
	private String login;
	private String password;
	private int profil;

	public User() {
		
	}
	
	public User(String name, String login, String password) {		
		this.name= name;
		this.login = login;
		this.password = password;
		this.profil = 1;
	}
	
	public User(int id,String name, String login, String password, int profil) {
		this.id = id;
		this.name= name;
		this.login = login;
		this.password = password;
		this.profil = profil;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
