package com.github.st0rm1O1.utilities;

import com.github.st0rm1O1.frame.ApplicationFrame;
import lombok.SneakyThrows;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.prefs.Preferences;



public class ConnectionHelper {
	
	
	private static Connection link;
	private static Preferences pref;
	
	private static String URL;
	private static String USER;
	private static String PASS;
	

    public static Connection createConnection() {
		
		try { 
			
			if (link == null) {
				
				pref = Preferences.userNodeForPackage(ConnectionHelper.class);
				URL = pref.get("URL", "localhost:3306");
				USER = pref.get("username", "root");
				PASS = pref.get("key", "root");
				
				Class.forName("com.mysql.cj.jdbc.Driver");				
				link = DriverManager.getConnection("jdbc:mysql://" + URL,
						USER, PASS);


				new DAO().checkDatabaseExistance();
				
				
				link = DriverManager.getConnection("jdbc:mysql://" + URL + "/mybank",
						USER, PASS);
				
				new DAO().checkTableExistance();
				

			} // if - (link == null)
			
		} // try
		
		catch(Exception e) {
			new DialogX().displayDialog(
					null,
					500,
					("""
							Whoops... Connection could not be established :(
							\t
							1. Ensure MySQL is installed on your system by running `mysql --version` or checking for MySQL server binaries.
							2. Verify the MySQL service is running using a command like `systemctl status mysql` (Linux) or check the service manager on other systems.
							3. Ensure the provided credentials (username, password, and host) are correct by testing the login with a MySQL client or through your application’s connection logic.
					"""),
					JOptionPane.ERROR_MESSAGE
			);

			e.printStackTrace();
		}
		
		return link;
		
	} // createConnection()
	
	
	
	
	public static void setPreference(String URL, String USER, String PASS) {
		
		try {
			
				pref.put("URL", URL);
				pref.put("username", USER);
				pref.put("key", PASS);        
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
	} // setPreference()
	
	


} // class