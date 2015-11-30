package ese4server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadLogin extends Thread {
	private Socket connessione;
	// stream per gestire il flusso in output
	private OutputStream out;
	private PrintWriter sOUT = null;
	
	private InputStreamReader in;
	private BufferedReader sIN;	
	
	//---------------------MySql o Access?----------------------------------------
	//Mysqlclass utilizza i database di Msql, DBClass utilizza access.
	//Rimuovere i commenti per attivare uno/l' altro
	//----------------------------------------------------------------------------

	//private MySqlClass db;
	private DBClass db;
	
	public ThreadLogin(Socket conn) {
		this.connessione = conn;	
		
		//db = new MySqlClass();
		db = new DBClass();
		
		try {
			// connessioni output del socket
			out = connessione.getOutputStream();
			sOUT = new PrintWriter(out);
			
			// connessioni input del socket
			in = new InputStreamReader(connessione.getInputStream());
			sIN = new BufferedReader(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void run() {
		System.out.println("Connessione stabilita.");

		String msgRicevuto;
		String scelta = "";
		String [] st = null;
		try {
			// legge messaggio client
			msgRicevuto = sIN.readLine();
			System.out.println("msgRicevuto:" + msgRicevuto);
			st = msgRicevuto.split(";");
			scelta = st[0];

			System.out.println("scelta:" + scelta);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		switch (scelta) {
		case "A": 
			// Login
			if(db.checkPassword(st[1],st[2])){
				db.close();
				System.out.println("login effettuato da " + st[1]);
				//Login effettuato
				sOUT.println('y');
				sOUT.flush();
			}
			else{
				System.out.println("è fallito un login!");
				sOUT.println('n');
				db.close();
			}
			break;
		case "R":
			// Registrazione		
			if(!db.checkUsername(st[1], st[1])){
				db.addUser(st[1], st[2], st[3], st[4], st[5]);
				db.close();
				System.out.println("Nuovo utente registrato: " + st[1]);
				sOUT.println('y');
				sOUT.flush();
			}
			else {
				db.close();
				System.out.println("è fallita una registrazione!");
				sOUT.println('n');
				sOUT.flush();
			}
			break;
			
		default:
			sOUT.println('n');
			sOUT.flush();
			break;
		}

		
		try {
			sIN.close();
			sOUT.close();
			connessione.close();
			System.out.println("Connessione chiusa.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
