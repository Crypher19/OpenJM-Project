package ese4server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBClass {
	Connection con=null;
    Statement stmt=null;
    String url ="";
    
    DBClass(){
    	String path ="D://Plenzick//ese4-server//DBUtenti.accdb"; //Posizione database
    	String url = "jdbc:ucanaccess://" + path;
	    
	    try {
	    	//Crea una connessione con il database
	        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");    
	        con = DriverManager.getConnection(url, "", "");
	        stmt = con.createStatement();
	        
	    } catch (SQLException ex) {
	        System.err.println("SQLException: " + ex.getMessage());	        
	    } catch (ClassNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
    }

	public void addUser(String Username,String Password,String Nome,String cognome, String email){ //creazione nuovo utente.
		try {
			//Query SQL per registrare un utente
			stmt.execute("INSERT INTO Utenti (Username,Password,Nome,Cognome,Email)  VALUES ('"+Username+"', '"+Password+"', '"+Nome+"', '"+cognome+"', '"+email+"');");
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkPassword(String username, String password){ //controlla se la password è corretta
		try {
			//Query SQL per cercare Username e Password
			String query = "Select * from Login";
		    ResultSet rs = stmt.executeQuery(query);
	        
		    	//Lettura del risultato della Query di ricerca
		        while (rs.next()) {
		            String fName = rs.getString("Username");
		            String fPass = rs.getString("Password");
	    
		            if(fName.equals(username)&& fPass.equals(password)){ //Controlla username e password
		            	
		            	//Login effettuato.
		            	return true;
		            }	
		        }		        
		}
		catch(Exception e){
			//FIIII SE ESCE STO ERRORE TI ATTACCHI EH!
		}
		//Username o password errati.
		return false;
	}
	
	public boolean checkUsername(String username, String email){ //Ricerca esistenza usrname/pass
		try{
			//Query SQL per ricercare Username e Eail
			String query = "Select * from Users_Mails";
			ResultSet rs = stmt.executeQuery(query);
		        
		        //Lettura risultato ricerca della query
		        while (rs.next()) {
		            String fName = rs.getString("Username");
		            String fMail = rs.getString("Email");
		            
		            if(fName.equals(username) || fMail.equals(email)){ //Controlla esistenza username/Password
		            	System.out.println("username o email Esistenti!");
		            	//Username o password sono già utilizzati
		            	return true;
		            }
		        }
			}
			catch(Exception e){
				
			}
		//Username e email sono liberi!
		return false;
	}
	
	public void close(){
		try {
			//Rilascio la risorsa Database, va richiamato ogni volta che si usa una funzione del database
			//Per permettere agli altri thread di accedervi.
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}