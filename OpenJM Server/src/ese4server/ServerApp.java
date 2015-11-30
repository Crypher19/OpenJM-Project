package ese4server;
import java.net.*;
import java.io.*;

public class ServerApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServerSocket sSocket;
		Socket connessione;
		int porta = 3333;

				
		try {
			sSocket = new ServerSocket(porta);
			System.out.println("In attesa di connessioni...");

			while (true) {
				connessione = sSocket.accept();
				ThreadLogin tl = new ThreadLogin(connessione);
				tl.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
