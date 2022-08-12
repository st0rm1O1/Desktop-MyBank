package utilities;

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