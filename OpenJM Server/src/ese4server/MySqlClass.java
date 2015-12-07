package ese4server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlClass {
	Connection con=null;
    Statement stmt=null;
    
    MySqlClass(){

	    String url = "jdbc:mysql://localhost:1010/test?";
	    
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        
	        con = DriverManager.getConnection(url, "root", "ciao123");
	        stmt = con.createStatement();
	        
	    } catch (SQLException ex) {
	        System.err.println("SQLException: " + ex.getMessage());
	    } catch (ClassNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	       
	    }
	     
    }
	public static void main(String[] args) {
		
		
	}
	
	public void addUser(String Username,String Password,String Nome,String cognome, String email){ //creazione nuovo utente.
		try {
			stmt.execute("INSERT INTO Utenti (Username,Password,Nome,Cognome,Email)  VALUES ('"+Username+"', '"+Password+"', '"+Nome+"', '"+cognome+"', '"+email+"');");
		    
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkPassword(String username, String password){ //controlla se la password è corretta
		try{
		 String query = "Select * from Utenti";
		    ResultSet rs = stmt.executeQuery(query);
	        
	        
	        while (rs.next()) {
	            String fName = rs.getString("Username").toLowerCase();
	            String fPass = rs.getString("Password");
	            
	            if(fName.equals(username.toLowerCase())&& fPass.equals(password)){ //Controlla username e password
	            	return true;
	            }
	        }
	        
		}
		catch(Exception e){
			
		}
		return false;
	}
	
	public boolean checkUsername(String username, String email){ //Ricerca esistenza usrname/pass
		try{
			 String query = "Select * from Utenti";
			 ResultSet rs = stmt.executeQuery(query);
		        
		        
		        while (rs.next()) {
		            String fName = rs.getString("Username");
		            String fMail = rs.getString("Email");
		            
		            if(fName.equals(username) || fMail.equals(email)){ //Controlla esistenza username/Password
		            	System.out.println("username o email Esistenti!");
		            	return true;
		            }
		        }
			}
			catch(Exception e){
				
			}
		return false;
	}
	public void close(){
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
