package com.github.st0rm1O1.utilities;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.github.st0rm1O1.models.UserModel;
import com.github.st0rm1O1.panels.Account;
import com.github.st0rm1O1.panels.Dashboard;
import com.github.st0rm1O1.panels.Login;
import com.github.st0rm1O1.panels.Register;
import com.github.st0rm1O1.panels.Splash;
import com.github.st0rm1O1.panels.TitleBar;
import com.github.st0rm1O1.panels.Transactions;



public class LayerPanel extends JLayeredPane  {
	private final TitleBar bar;
	private Splash splashPanel;
	private Login loginPanel;
	private Register registerPanel;
	private Dashboard dashPanel;
	private Account accPanel;
	private Transactions transactionPanel;


	public LayerPanel(TitleBar bar, int code) {
		
		this.bar = bar;
		init(code, null);
	
	}


	public void init(int code, UserModel userModel) {

		splashPanel = null;
		loginPanel = null;
		registerPanel = null;
		dashPanel = null;
		accPanel = null;
		transactionPanel = null;


		if (userModel == null)
			userModel = new UserModel();

		if (splashPanel == null)
			splashPanel = new Splash(this);
		
		if (loginPanel == null) 
			loginPanel = new Login(this);
		
		if (registerPanel == null) 
			registerPanel = new Register(this);
		
		if (dashPanel == null) 
			dashPanel = new Dashboard(this, userModel);
		
		if (accPanel == null) 
			accPanel = new Account(this, userModel);
		
		if (transactionPanel == null) 
			transactionPanel = new Transactions(this, userModel);
		

		
		switch(code) {
		
			case 0 : 
				switchPanel(splashPanel, bar, "myBank");
				break;
				
			case 1 : 
				switchPanel(loginPanel, bar, "Sign-in");
				break;
				
			case 2 : 
				switchPanel(registerPanel, bar, "Sign-up");
				break;
				
			case 3 : 
				switchPanel(dashPanel, bar, "DashBoard");
				break;
				
			case 4 : 
				switchPanel(accPanel, bar, "Manage Account");
				break;
				
			case 5 : 
				switchPanel(transactionPanel, bar, "Transactions");
				break;
				
			case 6 : 
				switchPanel(transactionPanel, bar, "Database");
				break;
				
		} // switch
		
	} // init()
	
	
	private void switchPanel(JPanel panel, TitleBar bar, String title) {
			
		bar.setTitleB(title);
		removeAll();
		add(panel);
		repaint();
		revalidate();
		
	} // switchPanel()
	
}
