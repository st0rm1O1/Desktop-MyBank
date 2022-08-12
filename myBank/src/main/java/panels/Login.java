package panels;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import models.ResourceModel;
import models.UserModel;
import utilities.DAO;
import utilities.ImageRender;
import utilities.LayerPanel;




public class Login extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ResourceModel res;
	private JTextField public_key;
	private JPasswordField private_key;
	private JButton btn;
	private JTextArea public_util, private_util;
	private JPanel img_panel, btn_icon;
	private JLabel register_txt, register_action, back_btn;
	private DAO db;
	private final String public_hint = "email address or username";
	private final String private_hint = "password";
	

	public Login(LayerPanel layerPanel, final UserModel userModel) {
		
		public_key = new JTextField(public_hint);
		private_key = new JPasswordField(private_hint);
		btn = new JButton("Login");
		public_util = new JTextArea();
		private_util = new JTextArea();
		img_panel = new JPanel();
		register_txt = new JLabel("Don't have an account?");
		register_action = new JLabel("Sign-up!");
		back_btn = new JLabel("Back");
		btn_icon = new JPanel();
		db = new DAO();
		res = new ResourceModel();
		
		initialize(layerPanel, userModel);
			
		add(public_key);		
		add(private_key);
		add(public_util);
		add(private_util);
		add(btn);
		add(img_panel);
		add(register_action);
		add(register_txt);
		add(back_btn);
		add(btn_icon);
	
	}


	private void initialize(LayerPanel layerPanel, final UserModel userModel) {
		
//		setUI(new ImageRender("/MESH/gradient_"+ new Random().nextInt(12) + ".png"));
		setBackground(new Color(240, 239, 244));
		setBounds(0, 0, 1000, 600);
		setLayout(null);
	
		
		
		img_panel.setUI(new ImageRender(res.getAVATAR_PATH()));
		img_panel.setBounds(430, 40, 140, 140);
		img_panel.setLayout(null);
		
		
		public_key.setSelectionColor(new Color(227, 242, 253));
		public_key.setMargin(new Insets(4, 10, 4, 10));
		public_key.setFont(res.getInter(1, 20));
		public_key.setBounds(300, 230, 400, 50);
		public_key.setForeground(new Color(174, 173, 178));
		public_key.setColumns(10);
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
		private_key.setFont(res.getInter(1, 20));
		private_key.setColumns(10);
		private_key.setEchoChar((char) 0);
		private_key.setBounds(300, 340, 400, 50);
		private_key.addFocusListener(new FocusListener() {
			
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (String.valueOf(private_key.getPassword()).equals(private_hint)) {
		        	private_key.setText(null);
		        	private_key.setForeground(Color.BLACK);
		        	private_key.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (String.valueOf(private_key.getPassword()).isEmpty()) {
		        	private_key.setForeground(new Color(174, 173, 178));
		        	private_key.setText(private_hint);
		        	private_key.setEchoChar((char) 0);
		        }
		    }
		    
	});
		
		
		btn.setFocusPainted(false);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setFont(res.getInter(2, 26));
		btn.setForeground(Color.WHITE);
		btn.setBackground(new Color(10, 132, 255));
		btn.setBorder(null);
		btn.setBounds(340, 460, 300, 50);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String user_data = public_key.getText().toString();
				String pass_data = String.valueOf(private_key.getPassword());
				UserModel model = userModel;
				
				
				if (user_data.isEmpty() && pass_data.isEmpty() || user_data.equals(public_hint) && pass_data.equals(private_hint)) {
					
					showUtil(0, true, "Enter an email or username to continue.");
					showUtil(1, true, "Enter password to continue.");
					
				}
				
				else {
				
					if (user_data.isEmpty() || user_data.equals(public_hint)) {
						showUtil(0, true, "Enter an email or username to continue.");
						showUtil(1, false, null);
					}
					
					else if (pass_data.isEmpty() || pass_data.equals(private_hint)) {
						showUtil(1, true, "Enter password to continue.");
						showUtil(0, false, null);
					}
					
					else {

						model = db.checkLoginCredentials(user_data, pass_data);
						
						if (model != null) {
							
							showUtil(0, false, null);
							showUtil(1, false, null);
							
							public_key.setText(null);
							private_key.setText(null);
							layerPanel.init(3, model);
							
						}
						
						else if (model == null) {
							
							showUtil(0, true, "The email address or password you've entered isn't connected to an account. try again!");
							showUtil(1, true, "The password you've entered is incorrect. try again!");
							
						}
						
					}
				
				}
				
				
				
			}

			private void showUtil(int code, boolean state, String msg) {
				
				if (code == 0) {
					
					if (state) {
						public_util.setText(msg);
						layerPanel.repaint();
						public_key.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2), new EmptyBorder(4, 10, 4, 10)));
					}
					else {
						public_util.setText(null);
						layerPanel.repaint();
						public_key.setBorder(UIManager.getBorder("TextField.border"));
					}
				}
				
				else if (code == 1) {
					
					if (state) {
						private_util.setText(msg);
						layerPanel.repaint();
						private_key.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2), new EmptyBorder(4, 10, 4, 10)));
					}
					else {
						private_util.setText(null);
						layerPanel.repaint();
						private_key.setBorder(UIManager.getBorder("TextField.border"));
					}
				}
				
			}
			
		});
		
		
		public_util.setText(null);
		public_util.setLineWrap(true);
		public_util.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		public_util.setForeground(new Color(255, 0, 51));
		public_util.setFont(res.getInter(1, 16));
		public_util.setFocusTraversalKeysEnabled(false);
		public_util.setFocusable(false);
		public_util.setEditable(false);
		public_util.setBorder(null);
		public_util.setBounds(300, 285, 400, 40);
		public_util.getCaret().deinstall(public_util);
		
		
		private_util.setText(null);
		private_util.setLineWrap(true);
		private_util.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		private_util.setForeground(new Color(255, 0, 51));
		private_util.setFont(res.getInter(1, 16));
		private_util.setFocusTraversalKeysEnabled(false);
		private_util.setFocusable(false);
		private_util.setEditable(false);
		private_util.setBorder(null);
		private_util.setBounds(300, 395, 400, 40);
		private_util.getCaret().deinstall(private_util);
		
		
		register_txt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		register_txt.setForeground(Color.DARK_GRAY);
		register_txt.setFont(res.getInter(1, 16));
		register_txt.setHorizontalAlignment(SwingConstants.TRAILING);
		register_txt.setBounds(360, 530, 180, 20);
		register_txt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init(2, null);
			}
		});
		
		
		register_action.setVerticalAlignment(SwingConstants.TOP);
		register_action.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		register_action.setForeground(new Color(10, 132, 255));
		register_action.setFont(res.getInter(2, 17));
		register_action.setBounds(545, 530, 70, 22);
		register_action.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init(2, null);
			}
		});
		
	
		back_btn.setFont(res.getInter(2, 22));
		back_btn.setBounds(40, 10, 60, 40);
		back_btn.setForeground(new Color(10, 132, 255));
		back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init(0, null);
			}
		});
		
		
		btn_icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_icon.setUI(new ImageRender(res.getBACK_PATH()));
		btn_icon.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		btn_icon.setLayout(null);
		btn_icon.setBounds(20, 20, 20, 25);
		btn_icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init(0, null);
			}
		});
		
	} // initialize()
	
} // class
