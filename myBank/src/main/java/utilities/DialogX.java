package utilities;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;



public class DialogX {
	
	public DialogX() {
		
		UIManager.put("OptionPane.messageFont", new Font("Inter Semibold", Font.PLAIN, 16));
		UIManager.put("OptionPane.buttonFont", new Font("Inter Medium", Font.PLAIN, 18));
		UIManager.put("OptionPane.font", new Font("Inter", Font.PLAIN, 14));
		UIManager.put("OptionPane.okButtonText", "OK");
		UIManager.put("OptionPane.buttonOrientation", 4);
		UIManager.put("TextField.font", new Font("Inter", Font.PLAIN, 18));
		UIManager.put("TextField.margin", new Insets(4, 10, 4, 10));
		
	}
	
	public String getCODE(int CODE) {
		
		/*
		 
		------------------------------------------------------------------------
		 
		1XX -> INFORMATION
		
			100 (CONTINUE)
			> Indicates that everything so far is OK.
			
			102 (PROCESSING)
			> Server processing the request, but no response is available yet.
			
		------------------------------------------------------------------------
				
		2XX -> SUCCESS
		
			200 (CONTINUE)
			> The request has succeeded.
			
			201 (CREATED)
			> This is typically the response sent after POST requests.
			
		-----------------------------------------------------------------------
		
		3XX -> REDIRECTION
		
			300 (MULTIPLE CHOICE)
			> The Request has more than one possible response.
			
			301 (MOVED PERMANENTLY)
			> The URL has been changed permanently. The new URL is given.
			
		-----------------------------------------------------------------------
		
		4XX -> CLIENT ERROR
			
			400 (BAD REQUEST) 
			> The Server could not understand the request due to invalid syntax.
			
			401 (UNAUTHORIZED)
			> The client must authenticate itself to get the requested response.
			
			404 (NOT FOUND)
			> The server could not find the requested resource.
			
		------------------------------------------------------------------------
		
		5XX -> SERVER ERROR
		
			500 (INTERNAL SERVER ERROR)
			> The server has encountered a situation, it doesn't know how to handle.
			
			502 (BAD GATEWAY)
			> While to get response needed to handle the request, got an invalid response.
			
		------------------------------------------------------------------------	
		
		*/
		
		switch (CODE) {
		
			case 100 : return "100 (CONTINUE)";
			case 102 : return "102 (PROCESSING)";
			case 200 : return "200 (CONTINUE)";
			case 201 : return "201 (CREATED)";
			case 300 : return "300 (MULTIPLE CHOICE)";
			case 301 : return "301 (MOVED PERMANENTLY)";
			case 400 : return "400 (BAD REQUEST)";
			case 401 : return "401 (UNAUTHORIZED)";
			case 404 : return "404 (NOT FOUND)";
			case 500 : return "500 (INTERNAL SERVER ERROR)";
			case 502 : return "502 (BAD GATEWAY)";

		}
		
		return null;
	}
	
	public void displayDialog(JPanel panel, int CODE, String CAUSE, int JCODE) {
		JOptionPane.showMessageDialog(panel, CAUSE, ("E" + getCODE(CODE)), JCODE);
	}
	
	
	
	public String takeInputDialogString(JPanel panel, int CODE, String CAUSE, int JCODE) {
		return JOptionPane.showInputDialog(panel, CAUSE, ("E" + getCODE(CODE)), JCODE);
	}
	

	
	public double takeInputDialogDouble(JPanel panel, int CODE, String CAUSE, int JCODE) {
		
		try {
			
			String data = JOptionPane.showInputDialog(panel, CAUSE, ("E" + getCODE(CODE)), JCODE);
			
			if (data != null)
				return Double.parseDouble(data); 
			
			return -1;
		}
		
		catch (NumberFormatException e) {
			e.printStackTrace();
			displayDialog(panel, 400, "Entered Amount contains characters, try again!", 0);
			takeInputDialogDouble(panel, CODE, CAUSE, JCODE);
		}
		
		return -1;
	}
	
	/*
	 * -1 - CLOSED
	 *  0 - ERROR
	 *  1 - INFORMATION
	 *  2 - CANCEL
	 *  3 - QUESTION
	 */

}
