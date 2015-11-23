package com.ismonnet.openjm;

public class DatiCondivisi {
	private Utente utente;
	private String serverAddress;
	private int serverPort;
	private boolean autenticato;

	
	public DatiCondivisi() {
		utente = new Utente();
		serverAddress = "172.22.108.108";
		serverPort = 3333;
		autenticato = false;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public boolean isAutenticato() {
		return autenticato;
	}

	public void setAutenticato(boolean autenticato) {
		this.autenticato = autenticato;
	}
}
