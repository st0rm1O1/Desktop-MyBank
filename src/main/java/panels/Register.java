package panels;

import resource.Resource;
import utilities.DAO;
import utilities.DialogX;
import utilities.LayerPanel;

import java.util.regex.Pattern;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;



public class Register extends JPanel {
	
	private enum enuModel {
		_SELECT_,
		Male,
		Female
	}
	
	private final String public_hint = "eg.st0rm1O1";
	private final String private_hint = "Must be at least 6 characters";
	private final String confirm_hint = "Confirm password";
	private final String email_hint = "xyz@mail.com";
	private final String dob_hint = "DD-MM-YYYY";
	private final String contact_hint = "+91 (XXX) XXX-XXXX";
	
//	private final String emailValidationPattern = "^(.+)@(\\S+)$";
	private final String emailValidationPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	private final String dobValidationPattern = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
	private final String contactValidationPattern = "^[0-9]{10}$";
	private final String privateValidationPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*., ?]).+$";
	
	
	private final JTextArea public_msg, mail_msg, private_msg, confirm_msg, dob_msg, contact_msg, gender_msg;
	private final JPanel btn_icon, private_icon, confirm_icon, private_icon_border, confirm_icon_border;
	private final JLabel back_btn, register_txt, register_action, public_util, private_util, confirm_util, mail_util, contact_util, gender_util, dob_util;
	private final JTextField public_key, email_key, dob_key, contact_key;
	private final JPasswordField private_key, confirm_key;
	private final JButton btn;
	private final JComboBox<enuModel> gender_key;
	private final DAO db;
	private final DialogX dialog;
	
	private boolean private_visibility = false;
	private boolean confirm_visibility = false;

	

	public Register(LayerPanel layerPanel) {

		db = new DAO();
		dialog = new DialogX();
		back_btn = new JLabel("Back");
		btn_icon = new JPanel();
		private_icon = new JPanel();
		confirm_icon = new JPanel();
		confirm_icon_border = new JPanel();
		private_icon_border = new JPanel();
		
		public_key = new JTextField(public_hint);
		private_key = new JPasswordField(private_hint);
		confirm_key = new JPasswordField(confirm_hint);
		email_key = new JTextField(email_hint);
		dob_key = new JTextField(dob_hint);
		contact_key = new JTextField(contact_hint);
		gender_key = new JComboBox<>(new DefaultComboBoxModel<>(enuModel.values()));

		btn = new JButton("Register");
		register_txt = new JLabel("Already have an account?");
		register_action = new JLabel("Sign-in!");
		
		public_util = new JLabel("USERNAME");
		private_util = new JLabel("PASSWORD");
		confirm_util = new JLabel("CONFIRM PASSWORD");
		mail_util = new JLabel("EMAIL ADDRESS");
		dob_util = new JLabel("DATE OF BIRTH");
		contact_util = new JLabel("PHONE / CONTACT");
		gender_util = new JLabel("GENDER");
		
		public_msg = new JTextArea();
		mail_msg = new JTextArea();
		private_msg = new JTextArea();
		confirm_msg = new JTextArea();
		dob_msg = new JTextArea();
		contact_msg = new JTextArea();
		gender_msg = new JTextArea();
		
		
		initialize(layerPanel);
	
		add(back_btn);
		add(btn_icon);
		add(public_key);
		add(private_key);
		add(email_key);
		add(dob_key);
		add(contact_key);
		add(gender_key);
		add(btn);
		add(register_txt);
		add(register_action);
		add(confirm_key);
		add(private_util);
		add(dob_util);
		add(gender_util);
		add(contact_util);
		add(public_util);
		add(mail_util);
		add(confirm_util);
		add(private_msg);
		add(confirm_msg);
		add(dob_msg);
		add(contact_msg);
		add(gender_msg);
		add(public_msg);
		add(mail_msg);
		add(private_icon);
		add(confirm_icon);
		add(confirm_icon_border);
		add(private_icon_border);
	
	}



	private void initialize(LayerPanel layerPanel) {
		
//		setUI(new ImageRender("/MESH/gradient_"+ new Random().nextInt(12) + ".png"));
		setBackground(new Color(240, 239, 244));
		setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		back_btn.setForeground(new Color(10, 132, 255));
		back_btn.setFont(Resource.getInterMedium(22));
		back_btn.setBounds(40, 10, 60, 40);
		back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init(1, null);
			}
		});
		
		btn_icon.setLayout(null);
		btn_icon.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		btn_icon.setBounds(20, 20, 20, 25);
		btn_icon.setUI(Resource.renderImage(Resource.ICON_PATH_BACK));
		btn_icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init(1, null);
			}
		});
		
		private_util.setForeground(Color.GRAY);
		private_util.setFont(Resource.getInterRegular(17));
		private_util.setBounds(50, 355, 430, 30);
		
		dob_util.setForeground(Color.GRAY);
		dob_util.setFont(Resource.getInterRegular(17));
		dob_util.setBounds(50, 210, 300, 30);
		
		gender_util.setForeground(Color.GRAY);
		gender_util.setFont(Resource.getInterRegular(17));
		gender_util.setBounds(750, 210, 200, 30);
		
		contact_util.setForeground(Color.GRAY);
		contact_util.setFont(Resource.getInterRegular(17));
		contact_util.setBounds(400, 210, 300, 30);
		
		public_util.setForeground(Color.GRAY);
		public_util.setFont(Resource.getInterRegular(17));
		public_util.setBounds(50, 65, 440, 30);
		
		mail_util.setForeground(Color.GRAY);
		mail_util.setFont(Resource.getInterRegular(17));
		mail_util.setBounds(510, 65, 440, 30);
		
		confirm_util.setForeground(Color.GRAY);
		confirm_util.setFont(Resource.getInterRegular(17));
		confirm_util.setBounds(520, 355, 430, 30);
		
		
		private_msg.setText(null);
		private_msg.setLineWrap(true);
		private_msg.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		private_msg.setForeground(new Color(255, 0, 51));
		private_msg.setFont(Resource.getInterRegular(16));
		private_msg.setFocusable(false);
		private_msg.setFocusTraversalKeysEnabled(false);
		private_msg.setEditable(false);
		private_msg.setBorder(null);
		private_msg.setBounds(50, 440, 430, 40);

		
		confirm_msg.setText(null);
		confirm_msg.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		confirm_msg.setLineWrap(true);
		confirm_msg.setForeground(new Color(255, 0, 51));
		confirm_msg.setFont(Resource.getInterRegular(16));
		confirm_msg.setFocusable(false);
		confirm_msg.setFocusTraversalKeysEnabled(false);
		confirm_msg.setEditable(false);
		confirm_msg.setBorder(null);
		confirm_msg.setBounds(520, 440, 430, 40);

		
		dob_msg.setText(null);
		dob_msg.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		dob_msg.setLineWrap(true);
		dob_msg.setForeground(new Color(255, 0, 51));
		dob_msg.setFont(Resource.getInterRegular(16));
		dob_msg.setFocusable(false);
		dob_msg.setFocusTraversalKeysEnabled(false);
		dob_msg.setEditable(false);
		dob_msg.setBorder(null);
		dob_msg.setBounds(50, 295, 300, 40);
		
		contact_msg.setText(null);
		contact_msg.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		contact_msg.setLineWrap(true);
		contact_msg.setForeground(new Color(255, 0, 51));
		contact_msg.setFont(Resource.getInterRegular(16));
		contact_msg.setFocusable(false);
		contact_msg.setFocusTraversalKeysEnabled(false);
		contact_msg.setEditable(false);
		contact_msg.setBorder(null);
		contact_msg.setBounds(400, 295, 300, 40);
		
		gender_msg.setText(null);
		gender_msg.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		gender_msg.setLineWrap(true);
		gender_msg.setForeground(new Color(255, 0, 51));
		gender_msg.setFont(Resource.getInterRegular(16));
		gender_msg.setFocusable(false);
		gender_msg.setFocusTraversalKeysEnabled(false);
		gender_msg.setEditable(false);
		gender_msg.setBorder(null);
		gender_msg.setBounds(750, 295, 200, 40);
		
		public_msg.setText(null);
		public_msg.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		public_msg.setLineWrap(true);
		public_msg.setForeground(new Color(255, 0, 51));
		public_msg.setFont(Resource.getInterRegular(16));
		public_msg.setFocusable(false);
		public_msg.setFocusTraversalKeysEnabled(false);
		public_msg.setEditable(false);
		public_msg.setBorder(null);
		public_msg.setBounds(50, 150, 440, 40);
		
		mail_msg.setText(null);
		mail_msg.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		mail_msg.setLineWrap(true);
		mail_msg.setForeground(new Color(255, 0, 51));
		mail_msg.setFont(Resource.getInterRegular(16));
		mail_msg.setFocusable(false);
		mail_msg.setFocusTraversalKeysEnabled(false);
		mail_msg.setEditable(false);
		mail_msg.setBorder(null);
		mail_msg.setBounds(510, 150, 440, 40);
		
		
		public_key.setSelectionColor(new Color(227, 242, 253));
		public_key.setMargin(new Insets(4, 10, 4, 10));
		public_key.setForeground(new Color(174, 173, 178));
		public_key.setFont(Resource.getInterRegular(22));
		public_key.setColumns(10);
		public_key.setBounds(50, 95, 440, 50);
		public_key.setHorizontalAlignment(SwingConstants.CENTER);
		public_key.addFocusListener(new FocusListener() {
			
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (public_key.getText().equals(public_hint)) {
		        	public_key.setText(null);
		        	public_key.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (public_key.getText().isEmpty()) {
		        	public_key.setForeground(new Color(174, 173, 178));
		        	public_key.setText(public_hint);
		        }
		    }
		    
		});
		
		private_key.setSelectionColor(new Color(227, 242, 253));
		private_key.setMargin(new Insets(4, 10, 4, 10));
		private_key.setForeground(new Color(174, 173, 178));
		private_key.setFont(Resource.getInterRegular(22));
		private_key.setColumns(10);
		private_key.setEchoChar((char) 0);
		private_key.setBounds(50, 385, 380, 50);
		private_key.setHorizontalAlignment(SwingConstants.CENTER);
		private_key.addFocusListener(new FocusListener() {
			
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (String.valueOf(private_key.getPassword()).equals(private_hint)) {
		        	private_key.setText(null);
		        	private_key.setForeground(Color.BLACK);
					private_visibility = false;
					private_key.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
					layerPanel.repaint();
					private_icon.setUI(Resource.renderImage(Resource.ICON_PATH_LOCK));
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (String.valueOf(private_key.getPassword()).isEmpty()) {
		        	private_key.setForeground(new Color(174, 173, 178));
		        	private_key.setText(private_hint);
		        	private_visibility = false;
		        	private_key.setEchoChar((char) 0);
					layerPanel.repaint();
					private_icon.setUI(Resource.renderImage(Resource.ICON_PATH_LOCK));
		        }
		    }
		    
		});
		
		confirm_key.setSelectionColor(new Color(227, 242, 253));
		confirm_key.setMargin(new Insets(4, 10, 4, 10));
		confirm_key.setHorizontalAlignment(SwingConstants.CENTER);
		confirm_key.setForeground(new Color(174, 173, 178));
		confirm_key.setFont(Resource.getInterRegular(22));
		confirm_key.setEchoChar((char) 0);
		confirm_key.setColumns(10);
		confirm_key.setBounds(520, 385, 380, 50);
		confirm_key.addFocusListener(new FocusListener() {
			
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (String.valueOf(confirm_key.getPassword()).equals(confirm_hint)) {
		        	confirm_key.setText(null);
		        	confirm_key.setForeground(Color.BLACK); 	
					confirm_visibility = false;
					confirm_key.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
					layerPanel.repaint();
					confirm_icon.setUI(Resource.renderImage(Resource.ICON_PATH_LOCK));
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (String.valueOf(confirm_key.getPassword()).isEmpty()) {
		        	confirm_key.setForeground(new Color(174, 173, 178));
		        	confirm_key.setText(confirm_hint);
		        	confirm_visibility = false;
					confirm_key.setEchoChar((char) 0);
					layerPanel.repaint();
					confirm_icon.setUI(Resource.renderImage(Resource.ICON_PATH_LOCK));
		        }
		    }
		    
		});
		
		
		email_key.setSelectionColor(new Color(227, 242, 253));
		email_key.setMargin(new Insets(4, 10, 4, 10));
		email_key.setForeground(new Color(174, 173, 178));
		email_key.setFont(Resource.getInterRegular(22));
		email_key.setColumns(10);
		email_key.setHorizontalAlignment(SwingConstants.CENTER);
		email_key.setBounds(510, 95, 440, 50);
		email_key.addFocusListener(new FocusListener() {
			
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (email_key.getText().equals(email_hint)) {
		        	email_key.setText(null);
		        	email_key.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (email_key.getText().isEmpty()) {
		        	email_key.setForeground(new Color(174, 173, 178));
		        	email_key.setText(email_hint);
		        }
		    }
		    
		});
		
		dob_key.setSelectionColor(new Color(227, 242, 253));
		dob_key.setMargin(new Insets(4, 10, 4, 10));
		dob_key.setForeground(new Color(174, 173, 178));
		dob_key.setFont(Resource.getInterRegular(22));
		dob_key.setColumns(10);
		dob_key.setHorizontalAlignment(SwingConstants.CENTER);
		dob_key.setBounds(50, 240, 300, 50);
		dob_key.addFocusListener(new FocusListener() {
			
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (dob_key.getText().equals(dob_hint)) {
		        	dob_key.setText(null);
		        	dob_key.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (dob_key.getText().isEmpty()) {
		        	dob_key.setForeground(new Color(174, 173, 178));
		        	dob_key.setText(dob_hint);
		        }
		    }
		    
		});
		
		contact_key.setSelectionColor(new Color(227, 242, 253));
		contact_key.setHorizontalAlignment(SwingConstants.CENTER);
		contact_key.setMargin(new Insets(4, 10, 4, 10));
		contact_key.setForeground(new Color(174, 173, 178));
		contact_key.setFont(Resource.getInterRegular(22));
		contact_key.setColumns(10);
		contact_key.setBounds(400, 240, 300, 50);
		contact_key.addFocusListener(new FocusListener() {
			
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (contact_key.getText().equals(contact_hint)) {
		        	contact_key.setText(null);
		        	contact_key.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (contact_key.getText().isEmpty()) {
		        	contact_key.setForeground(new Color(174, 173, 178));
		        	contact_key.setText(contact_hint);
		        }
		    }
		    
		});
		
		gender_key.setSelectedItem(-1);
		gender_key.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		gender_key.setBorder(UIManager.getBorder("TextField.border"));
		gender_key.setFont(Resource.getInterRegular(22));
		gender_key.setBounds(750, 240, 200, 50);
		gender_key.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				layerPanel.repaint();
			}
		});
		
		btn.setFocusPainted(false);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setFont(Resource.getInterMedium(26));
		btn.setForeground(Color.WHITE);
		btn.setBackground(new Color(10, 132, 255));
		btn.setBorder(null);
		btn.setBounds(340, 500, 300, 50);
		btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String user_data = public_key.getText().trim();
				String email_data = email_key.getText().trim();
				String dob_data = dob_key.getText().trim();
				int gender_data = gender_key.getSelectedIndex();
				String contact_data = contact_key.getText().trim();
				String pass_data = String.valueOf(private_key.getPassword());
				String confirm_data = String.valueOf(confirm_key.getPassword());
			
					
				if (
						(user_data.equals(public_hint)) ||
						(email_data.equals(email_hint)) ||
						(dob_data.equals(dob_hint)) ||
						(gender_data == 0) ||
						(contact_data.equals(contact_hint)) ||
						(pass_data.equals(private_hint)) ||
						(confirm_data.equals(confirm_hint))	
					) 
				{
					
					if (user_data.equals(public_hint))
						showUtil(0, true, "Enter username to continue.");
						
					if (email_data.equals(email_hint))
						showUtil(1, true, "Enter email address to continue.");

					if (dob_data.equals(dob_hint))
						showUtil(2, true, "Enter DOB to continue.");
					
					if (gender_data == 0)
						showUtil(3, true, "Select gender.");

					if (contact_data.equals(contact_hint))
						showUtil(4, true, "Enter contact number to continue.");

					if (pass_data.equals(private_hint))
						showUtil(5, true, "Enter password to continue.");
					
					if (confirm_data.equals(confirm_hint))
						showUtil(6, true, "Confirm your password to continue.");
					
				}
				
				
				if (
						(!user_data.equals(public_hint)) ||
						(!email_data.equals(email_hint)) ||
						(!dob_data.equals(dob_hint)) ||
						(gender_data != 0) ||
						(!contact_data.equals(contact_hint)) ||
						(!pass_data.equals(private_hint)) ||
						(!confirm_data.equals(confirm_hint))	
					) 
				{
					
					if (!user_data.equals(public_hint)) {
						showUtil(0, false, null);
						
						if (user_data.length() > 10) 
							showUtil(0, true, "The username can not be more than 10 characters.");
						
						else showUtil(0, false, null);
						
					}
						
					if (!email_data.equals(email_hint)) {
						showUtil(1, false, null);
						
						if (!Pattern.matches(emailValidationPattern, email_data)) 
							showUtil(1, true, "The entered email address is invalid.");
						else showUtil(1, false, null);
					}

					if (!dob_data.equals(dob_hint)) {
						showUtil(2, false, null);
						
						if (!Pattern.matches(dobValidationPattern, dob_data)) 
							showUtil(2, true, "The entered DOB does not match the  format (DD-MM-YYYY).");
						else showUtil(2, false, null);
					}
	
					
					if (gender_data != 0)
						showUtil(3, false, null);
					

					if (!contact_data.equals(contact_hint)) {
						showUtil(4, false, null);
						
						if (!Pattern.matches(contactValidationPattern, contact_data)) 
							showUtil(4, true, "Enter only 10 digits without (+91).");
						else showUtil(4, false, null);
					}

					if (!pass_data.equals(private_hint)) {
						showUtil(5, false, null);
						
						if (!Pattern.matches(privateValidationPattern, pass_data)) 
							showUtil(5, true, "Password must contain uppercase, lowercase, numbers, non-alphanumeric characters.");
						
						else if (pass_data.length() < 9)
							showUtil(5, true, "Password must be at least 8 characters long.");
						
						else if (pass_data.length() > 20)
							showUtil(5, true, "Password must be less than 20 characters long.");
						
						else showUtil(5, false, null);
							
					}
					
					if (!confirm_data.equals(confirm_hint)) {
						showUtil(6, false, null);
						
						if (!confirm_data.equals(pass_data))
							showUtil(6, true, "Password does not match, try again!");
						else showUtil(6, false, null);
				
					}
					
					
					
					
					if (
							(!public_msg.isVisible()) &&
							(!mail_msg.isVisible()) &&
							(!dob_msg.isVisible()) &&
							(!gender_msg.isVisible()) &&
							(!contact_msg.isVisible()) &&
							(!private_msg.isVisible()) &&
							(!confirm_msg.isVisible())
					)
						
					{
						
						int RES = db.checkUserMail(user_data, email_data);
						
						switch (RES) {
						
							case 0 : {
								
								showUtil(0, false, null);
								showUtil(1, false, null);
								
								if (!db.insertUser(user_data, email_data, pass_data, dob_data, contact_data, gender_data))
									dialog.displayDialog(Register.this, 502, "Registration Failed!", 0);
								
								else {
									dialog.displayDialog(Register.this, 200, "Successfully Registered!", 1);
									layerPanel.init(1, null);
								}
								
								break;
								
							} // case 0
							
							case 1 : showUtil(0, true, "Entered username already exist, try again!");
								break;
								
							case 2: showUtil(1, true, "Entered email address already exist, try again!");
								break;
						
						} // switch
						
					} // visibility
					
				} // not null
					
			} // actionPerformed()
			
			
			
			private void showUtil(int code, boolean state, String msg) {
				
				switch(code) {
				
					case 0: {
						
						if (state) {
							public_util.setForeground(new Color(255, 0, 0));
							public_key.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2), new EmptyBorder(4, 10, 4, 10)));
							public_msg.setText(msg);
							public_msg.setVisible(true);
						} 
						
						else {
							public_util.setForeground(Color.GRAY);
							public_key.setBorder(UIManager.getBorder("TextField.border"));
							public_msg.setText(null);
							public_msg.setVisible(false);
						}
						
						break;
					}
						
					case 1: {
						
						if (state) {
							mail_util.setForeground(new Color(255, 0, 0));
							email_key.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2), new EmptyBorder(4, 10, 4, 10)));
							mail_msg.setText(msg);
							mail_msg.setVisible(true);
						} 
						
						else {
							mail_util.setForeground(Color.GRAY);
							email_key.setBorder(UIManager.getBorder("TextField.border"));
							mail_msg.setText(null);
							mail_msg.setVisible(false);
						}
						
						break;
					}
						
					case 2: {
						
						if (state) {
							dob_util.setForeground(new Color(255, 0, 0));
							dob_key.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2), new EmptyBorder(4, 10, 4, 10)));
							dob_msg.setText(msg);
							dob_msg.setVisible(true);
						} 
						
						else {
							dob_util.setForeground(Color.GRAY);
							dob_key.setBorder(UIManager.getBorder("TextField.border"));
							dob_msg.setText(null);
							dob_msg.setVisible(false);
						}
						
						break;
					}
						
					case 3: {
						
						if (state) {
							gender_util.setForeground(new Color(255, 0, 0));
							gender_key.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2), new EmptyBorder(4, 10, 4, 10)));
							gender_msg.setText(msg);
							gender_msg.setVisible(true);
						} 
						
						else {
							gender_util.setForeground(Color.GRAY);
							gender_key.setBorder(UIManager.getBorder("TextField.border"));
							gender_msg.setText(null);
							gender_msg.setVisible(false);
						}
						
						break;
					}
						
					case 4: {
						
						if (state) {
							contact_util.setForeground(new Color(255, 0, 0));
							contact_key.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2), new EmptyBorder(4, 10, 4, 10)));
							contact_msg.setText(msg);
							contact_msg.setVisible(true);
						} 
						
						else {
							contact_util.setForeground(Color.GRAY);
							contact_key.setBorder(UIManager.getBorder("TextField.border"));
							contact_msg.setText(null);
							contact_msg.setVisible(false);
						}
						
						break;
					}
						
					case 5: {
						
						if (state) {
							private_util.setForeground(new Color(255, 0, 0));
							private_key.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2), new EmptyBorder(4, 10, 4, 10)));
							private_icon_border.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2), new EmptyBorder(4, 10, 4, 10)));
							private_visibility = false;
							private_key.setEchoChar((char) 0);
							layerPanel.repaint();
							private_icon.setUI(Resource.renderImage(Resource.ICON_PATH_LOCK));
							private_msg.setText(msg);
							private_msg.setVisible(true);
						} 
						
						else {
							private_util.setForeground(Color.GRAY);
							private_key.setBorder(UIManager.getBorder("TextField.border"));
							private_icon_border.setBorder(UIManager.getBorder("TextField.border"));
							private_visibility = false;
							private_key.setEchoChar((char) 0);
							layerPanel.repaint();
							private_icon.setUI(Resource.renderImage(Resource.ICON_PATH_LOCK));
							private_msg.setText(null);
							private_msg.setVisible(false);
						}
						
						break;
					}
						
					case 6: {
						
						if (state) {
							confirm_util.setForeground(new Color(255, 0, 0));
							confirm_key.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2), new EmptyBorder(4, 10, 4, 10)));
							confirm_icon_border.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2), new EmptyBorder(4, 10, 4, 10)));
							confirm_visibility = false;
							confirm_key.setEchoChar((char) 0);
							layerPanel.repaint();
							confirm_icon.setUI(Resource.renderImage(Resource.ICON_PATH_LOCK));
							confirm_msg.setText(msg);
							confirm_msg.setVisible(true);
						} 
						
						else {
							confirm_util.setForeground(Color.GRAY);
							confirm_key.setBorder(UIManager.getBorder("TextField.border"));
							confirm_icon_border.setBorder(UIManager.getBorder("TextField.border"));
							confirm_visibility = false;
							confirm_key.setEchoChar((char) 0);
							layerPanel.repaint();
							confirm_icon.setUI(Resource.renderImage(Resource.ICON_PATH_LOCK));
							confirm_msg.setText(null);
							confirm_msg.setVisible(false);
						}
						
						break;
					}
				
				} // switch()
				
			} // showUtil()


			
		});
		
		register_txt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		register_txt.setForeground(Color.DARK_GRAY);
		register_txt.setFont(Resource.getInterRegular(16));
		register_txt.setHorizontalAlignment(SwingConstants.TRAILING);
		register_txt.setBounds(350, 560, 200, 20);
		register_txt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init(1, null);
			}
		});
		
		
		register_action.setVerticalAlignment(SwingConstants.TOP);
		register_action.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		register_action.setForeground(new Color(10, 132, 255));
		register_action.setFont(Resource.getInterMedium(17));
		register_action.setBounds(555, 560, 70, 22);
		register_action.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init(1, null);
			}
		});
		
		
		private_icon.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		private_icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		private_icon.setUI(Resource.renderImage(Resource.ICON_PATH_LOCK));
		private_icon.setLayout(null);
		private_icon.setBounds(440, 392, 50, 40);
		
		private_icon_border.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		private_icon_border.setBorder(UIManager.getBorder("TextField.border"));
		private_icon_border.setBounds(428, 385, 60, 50);
		private_icon_border.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (!String.valueOf(private_key.getPassword()).equalsIgnoreCase(private_hint)) {
					
					if (private_visibility) {
						private_visibility = false;
						private_key.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
						layerPanel.repaint();
						private_icon.setUI(Resource.renderImage(Resource.ICON_PATH_LOCK));
					}
					
					else {
						private_visibility = true;
						private_key.setEchoChar((char) 0);
						layerPanel.repaint();
						private_icon.setUI(Resource.renderImage(Resource.ICON_PATH_UNLOCK));
					}
				
				}
				
			}
		});
		
		confirm_icon.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		confirm_icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		confirm_icon.setUI(Resource.renderImage(Resource.ICON_PATH_LOCK));
		confirm_icon.setLayout(null);
		confirm_icon.setBounds(910, 392, 50, 40);
		
		confirm_icon_border.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		confirm_icon_border.setBorder(UIManager.getBorder("TextField.border"));
		confirm_icon_border.setBounds(898, 385, 60, 50);
		confirm_icon_border.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (!String.valueOf(confirm_key.getPassword()).equalsIgnoreCase(confirm_hint)) {
				
					if (confirm_visibility) {
						confirm_visibility = false;
						confirm_key.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
						layerPanel.repaint();
						confirm_icon.setUI(Resource.renderImage(Resource.ICON_PATH_LOCK));
					}
					
					else {
						confirm_visibility = true;
						confirm_key.setEchoChar((char) 0);
						layerPanel.repaint();
						confirm_icon.setUI(Resource.renderImage(Resource.ICON_PATH_UNLOCK));
					}
					
				}
					
			}
		});
		
	} // initialize()
	
} // class
