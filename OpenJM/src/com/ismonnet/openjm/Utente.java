package com.ismonnet.openjm;

public class Utente {
	private String user;
	private String pass;
	private String name;
	private String surn;
	private String mail;
	
	public Utente() {
		user = "";
		pass = "";
		name = "";
		surn = "";
		mail = "";
	}
	
	public Utente(String user, String pass, String name, String surn, String mail) {
		this.user = user;
		this.pass = pass;
		this.name = name;
		this.surn = surn;
		this.mail = mail;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurn() {
		return surn;
	}

	public void setSurn(String surn) {
		this.surn = surn;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}

