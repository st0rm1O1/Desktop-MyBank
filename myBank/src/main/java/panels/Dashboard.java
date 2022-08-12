package panels;

import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

import models.ResourceModel;
import models.UserModel;
import utilities.ImageRender;
import utilities.LayerPanel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel img_panel, manage_account_panel, database_panel;
	private JButton logout_btn;
	private JLabel greetings, mng_acc_header, mng_acc_description_1;
	private JLabel mng_acc_description_2;
	private JLabel mng_acc_btn;
	private JPanel mng_acc_btn_icon;
	private JLabel db_acc_header;
	private JLabel db_acc_description_1;
	private JLabel db_acc_description_2;
	private JLabel db_acc_btn;
	private JPanel db_acc_btn_icon;
	
	private int HOUR;
	private ResourceModel res;
	

	public Dashboard(LayerPanel layerPanel, UserModel userModel) {
		
		HOUR = Integer.parseInt(DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now()));
		
		greetings = new JLabel();
		logout_btn = new JButton("Logout");
		img_panel = new JPanel();
		res = new ResourceModel();
		
		manage_account_panel = new JPanel();
		mng_acc_header = new JLabel("Manage Account");
		mng_acc_description_1 = new JLabel("Manage your info, privacy, and security to");
		mng_acc_description_2 = new JLabel("make database work better for you.");
		mng_acc_btn = new JLabel("Learn more");
		mng_acc_btn_icon = new JPanel();
		
		database_panel = new JPanel();
		db_acc_header = new JLabel( ((userModel.getUROLE() == -1) ? "Database" : "Transactions") );
		db_acc_description_1 = new JLabel( (userModel.getUROLE() == -1) ? "Organize, store and retrieve data for" : "See all payment activity and all the" );
		db_acc_description_2 = new JLabel( ((userModel.getUROLE() == -1) ? "usersbase" : "transactions") + " in real time." );
		db_acc_btn = new JLabel("Explore");
		db_acc_btn_icon = new JPanel();
		
		
		initialize(layerPanel, userModel);

		add(greetings);
		add(logout_btn);
		add(img_panel);
		add(manage_account_panel);
		add(database_panel);
	
		
		manage_account_panel.add(mng_acc_header);
		manage_account_panel.add(mng_acc_description_1);
		manage_account_panel.add(mng_acc_description_2);
		manage_account_panel.add(mng_acc_btn);
		manage_account_panel.add(mng_acc_btn_icon);
		
		database_panel.add(db_acc_header);
		database_panel.add(db_acc_description_1);
		database_panel.add(db_acc_description_2);
		database_panel.add(db_acc_btn);
		database_panel.add(db_acc_btn_icon);
		
	}


	private void initialize(LayerPanel layerPanel, UserModel userModel) {
		
//		setUI(new ImageRender("/MESH/gradient_"+ new Random().nextInt(12) + ".png"));
		setBackground(new Color(240, 239, 244));
		setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		greetings.setFont(res.getInter(3, 48));
		greetings.setForeground(Color.WHITE);
		greetings.setBounds(40, 0, 755, 200);
		
	
		greetings.setText(((HOUR >= 0 && HOUR < 6) ? "Mornin' Sunshine, " :
			(HOUR >= 6 && HOUR < 12) ? "Good Morning, " :
			(HOUR >= 12 && HOUR < 17) ? "Good Afternoon, " :
			(HOUR >= 17 && HOUR < 22) ? "Good Evening, " :
			(HOUR >= 22 && HOUR <= 24) ? "Good Night, " : "st0rm1O1, ") + userModel.getUNAME() + ".");
		
/*
		String gMsg = ((HOUR >= 0 && HOUR < 6) ? "Mornin' Sunshine, " :
				(HOUR >= 6 && HOUR < 12) ? "Good Morning, " :
				(HOUR >= 12 && HOUR < 17) ? "Good Afternoon, " :
				(HOUR >= 17 && HOUR < 22) ? "Good Evening, " :
				(HOUR >= 22 && HOUR <= 24) ? "Good Night, " : "st0rm1O1");
		
		greetings.setText(gMsg + userModel.getUNAME() + ".");
*/	
		
/*
		if (HOUR >= 0 && HOUR < 6) 
			greetings.setText("Mornin' Sunshine, " + userModel.getUNAME() + ".");
		
		else if (HOUR >= 6 && HOUR < 12)
			greetings.setText("Good Morning, " + userModel.getUNAME() + ".");
		
		else if (HOUR >= 12 && HOUR < 17) 
			greetings.setText("Good Afternoon, " + userModel.getUNAME() + ".");
		
		else if (HOUR >= 17 && HOUR < 22) 
			greetings.setText("Good Evening, " + userModel.getUNAME() + ".");
		
		else if (HOUR >= 22 && HOUR <= 24) 
			greetings.setText("Good Night, " + userModel.getUNAME() + ".");
*/

		
		logout_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logout_btn.setForeground(Color.WHITE);
		logout_btn.setFont(res.getInter(1, 22));
		logout_btn.setBorder(new LineBorder(Color.WHITE));
		logout_btn.setBackground(new Color(0, 0, 0));
		logout_btn.setBounds(800, 75, 140, 50);
		logout_btn.setOpaque(false);
		logout_btn.setContentAreaFilled(false);
		logout_btn.setRolloverEnabled(false);
		logout_btn.setFocusPainted(false);
		logout_btn.setFocusTraversalKeysEnabled(false);
		logout_btn.setFocusable(false);
		logout_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layerPanel.init(0, null);
			}
		});
		
		
		img_panel.setUI(new ImageRender(res.getSLIDE_PATH()));
		img_panel.setLayout(null);
		img_panel.setBounds(0, 0, 1000, 200);
		
		
		
		manage_account_panel.setBounds(40, 260, 440, 280);
		manage_account_panel.setLayout(null);
		manage_account_panel.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.3f));
		manage_account_panel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 153, 255)), new EmptyBorder(2, 10, 2, 10)));
		
		mng_acc_header.setFont(res.getInter(3, 41));
		mng_acc_header.setHorizontalAlignment(SwingConstants.CENTER);
		mng_acc_header.setBounds(0, 40, 440, 55);
		mng_acc_header.setForeground(Color.BLACK);

		mng_acc_description_1.setForeground(Color.GRAY);
		mng_acc_description_1.setBackground(null);
		mng_acc_description_1.setFont(res.getInter(1, 17));
		mng_acc_description_1.setBounds(0, 110, 440, 22);
		mng_acc_description_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		mng_acc_description_2.setHorizontalAlignment(SwingConstants.CENTER);
		mng_acc_description_2.setForeground(Color.GRAY);
		mng_acc_description_2.setFont(res.getInter(1, 17));
		mng_acc_description_2.setBackground((Color) null);
		mng_acc_description_2.setBounds(0, 132, 440, 22);
		
		mng_acc_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mng_acc_btn.setHorizontalAlignment(SwingConstants.TRAILING);
		mng_acc_btn.setForeground(new Color(10, 132, 255));
		mng_acc_btn.setFont(res.getInter(2, 24));
		mng_acc_btn.setBounds(127, 215, 135, 40);
		mng_acc_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init(4, userModel);
			}
		});
		
		mng_acc_btn_icon.setBounds(270, 226, 20, 25);
		mng_acc_btn_icon.setLayout(null);
		mng_acc_btn_icon.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		mng_acc_btn_icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mng_acc_btn_icon.setUI(new ImageRender(res.getNEXT_B_PATH()));
		mng_acc_btn_icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init(4, userModel);
			}
		});
		
		
		
		database_panel.setLayout(null);
		database_panel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 153, 255)), new EmptyBorder(2, 10, 2, 10)));
		database_panel.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.3f));
		database_panel.setBounds(520, 260, 440, 280);
		
		db_acc_header.setHorizontalAlignment(SwingConstants.CENTER);
		db_acc_header.setForeground(Color.BLACK);
		db_acc_header.setFont(res.getInter(3, 41));
		db_acc_header.setBounds(0, 40, 440, 55);
		
		db_acc_description_1.setHorizontalAlignment(SwingConstants.CENTER);
		db_acc_description_1.setForeground(Color.GRAY);
		db_acc_description_1.setFont(res.getInter(1, 17));
		db_acc_description_1.setBackground((Color) null);
		db_acc_description_1.setBounds(0, 110, 440, 22);
		
		db_acc_description_2.setHorizontalAlignment(SwingConstants.CENTER);
		db_acc_description_2.setForeground(Color.GRAY);
		db_acc_description_2.setFont(res.getInter(1, 17));
		db_acc_description_2.setBackground((Color) null);
		db_acc_description_2.setBounds(0, 132, 440, 22);
		
		db_acc_btn.setHorizontalAlignment(SwingConstants.TRAILING);
		db_acc_btn.setForeground(new Color(10, 132, 255));
		db_acc_btn.setFont(res.getInter(2, 24));
		db_acc_btn.setBounds(167, 215, 90, 40);
		db_acc_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		db_acc_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init( ((userModel.getUROLE() == 0) ? 6 : 5) , userModel);
			}
		});
		
		db_acc_btn_icon.setLayout(null);
		db_acc_btn_icon.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		db_acc_btn_icon.setBounds(265, 226, 20, 25);
		db_acc_btn_icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		db_acc_btn_icon.setUI(new ImageRender(res.getNEXT_B_PATH()));
		db_acc_btn_icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init( ((userModel.getUROLE() == 1) ? 5 : 6) , userModel);
			}
		});
			
	} // initialize()
	
} // class
