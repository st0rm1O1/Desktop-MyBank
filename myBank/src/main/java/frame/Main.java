package frame;

import javax.swing.JOptionPane;
import utilities.DialogX;



public class Main {
	
	
	
	public static void main(String[] args) {
		
		try { new GUI(); } // try
		
		catch (Throwable e) {
			
			new DialogX().displayDialog(null, 404,
					e.getClass().getSimpleName() + " : " + e.getMessage(),
					JOptionPane.ERROR_MESSAGE);
			
			throw e;

		} // catch()
		
		
	} // main()
	
} // class