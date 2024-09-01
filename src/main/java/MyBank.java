import javax.swing.JOptionPane;

import frame.ApplicationFrame;
import utilities.DialogX;

public class MyBank {

	public static void main(String[] args) {
		try {
			new ApplicationFrame();
		} catch (Throwable e) {
			new DialogX().displayDialog(null, 404,
					e.getClass().getSimpleName() + " : " + e.getMessage(),
					JOptionPane.ERROR_MESSAGE);
			throw e;
		}
	}
}