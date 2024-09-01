package panels;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import resource.Resource;
import models.UserModel;
import utilities.LayerPanel;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class Account extends JPanel {

	private final JPanel btn_icon, line_1, line_2, line_3, change_btn_icon;
	private final JLabel back_btn, username_label, mail_label, dob_label, contact_label, gender_label, bal_label, password_label, username_txt, mail_txt, dob_txt, contact_txt, gender_txt, bal_txt, change_btn_1, change_btn_2;
	private final JLabel delete_btn;
	private final JPanel delete_icon;
	


	public Account(LayerPanel layerPanel, UserModel userModel) {
		
		btn_icon = new JPanel();
		back_btn = new JLabel("Dashboard");
		
		line_1 = new JPanel();
		line_2 = new JPanel();
		line_3 = new JPanel();
		
		username_label = new JLabel("USERNAME");
		mail_label = new JLabel("EMAIL ADDRESS");
		dob_label = new JLabel("BIRTHDAY");
		contact_label = new JLabel("PHONE / CONTACT");
		gender_label = new JLabel("GENDER");
		bal_label = new JLabel("CURRENT BALANCE");
		password_label = new JLabel("PASSWORD");

		username_txt = new JLabel(userModel.getUNAME());
		mail_txt = new JLabel(userModel.getUMAIL());
		dob_txt = new JLabel(userModel.getUDOB());
		contact_txt = new JLabel("+91 " + userModel.getUCON());
		gender_txt = new JLabel((userModel.getUGEN() == 1) ? "MALE" : (userModel.getUGEN() == 2) ? "FEMALE" : "null");
		bal_txt = new JLabel("₹" + userModel.getUBAL());
		
		change_btn_1 = new JLabel("CHANGE");
		change_btn_2 = new JLabel("PASSWORD");
		change_btn_icon = new JPanel();
		
		delete_btn = new JLabel("Delete Account");
		delete_icon = new JPanel();
	
		
		initialize(layerPanel, userModel);
		
		add(back_btn);
		add(btn_icon);
		add(line_1);
		add(line_2);
		add(line_3);
		add(username_label);
		add(mail_label);
		add(dob_label);
		add(contact_label);
		add(gender_label);
		add(bal_label);
		add(password_label);
		add(username_txt);
		add(mail_txt);
		add(dob_txt);
		add(contact_txt);		
		add(gender_txt);		
		add(bal_txt);
		add(change_btn_1);
		add(change_btn_2);
		add(change_btn_icon);
		add(delete_btn);
		add(delete_icon);

	}

	
	private void initialize(LayerPanel layerPanel, UserModel userModel) {
		
//		setUI(new ImageRender("/MESH/gradient_"+ new Random().nextInt(12) + ".png"));
		setBackground(new Color(240, 239, 244));
		setBounds(0, 0, 1000, 600);
		setLayout(null);
		
	
		back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back_btn.setHorizontalAlignment(SwingConstants.TRAILING);
		back_btn.setForeground(new Color(10, 132, 255));
		back_btn.setFont(Resource.getInterMedium(24));
		back_btn.setBounds(40, 10, 130, 40);
		back_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init(3, userModel);
			}
		});
		
		
		btn_icon.setLayout(null);
		btn_icon.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		btn_icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_icon.setUI(Resource.renderImage(Resource.ICON_PATH_BACK));
		btn_icon.setBounds(20, 20, 20, 25);
		btn_icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layerPanel.init(3, userModel);
			}
		});
		
		
		delete_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		delete_btn.setForeground(new Color(255, 0, 51));
		delete_btn.setFont(Resource.getInterMedium(24));
		delete_btn.setBounds(770, 10, 180, 40);
		
		delete_icon.setLayout(null);
		delete_icon.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		delete_icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		delete_icon.setUI(Resource.renderImage(Resource.ICON_PATH_NEXT_R));
		delete_icon.setBounds(960, 20, 20, 25);
		
		
		line_1.setBackground(Color.GRAY);
		line_2.setBackground(Color.GRAY);
		line_3.setBackground(Color.GRAY);
		
		line_1.setBounds(40, 240, 640, 1);
		line_2.setBounds(40, 385, 640, 1);
		line_3.setBounds(730, 100, 1, 420);
		
		line_1.setLayout(null);
		line_2.setLayout(null);
		line_3.setLayout(null);
		
		
		username_label.setVerticalAlignment(SwingConstants.TOP);
		mail_label.setVerticalAlignment(SwingConstants.TOP);
		dob_label.setVerticalAlignment(SwingConstants.TOP);
		contact_label.setVerticalAlignment(SwingConstants.TOP);
		gender_label.setVerticalAlignment(SwingConstants.TOP);
		bal_label.setVerticalAlignment(SwingConstants.TOP);
		password_label.setVerticalAlignment(SwingConstants.TOP);
		
		username_label.setFont(Resource.getInterMedium(18));
		mail_label.setFont(Resource.getInterMedium(18));
		dob_label.setFont(Resource.getInterMedium(18));
		contact_label.setFont(Resource.getInterMedium(18));
		gender_label.setFont(Resource.getInterMedium(18));
		bal_label.setFont(Resource.getInterMedium(18));
		password_label.setFont(Resource.getInterMedium(18));
		
		username_label.setForeground(Color.GRAY);
		mail_label.setForeground(Color.GRAY);
		dob_label.setForeground(Color.GRAY);
		contact_label.setForeground(Color.GRAY);
		gender_label.setForeground(Color.GRAY);
		bal_label.setForeground(Color.GRAY);
		password_label.setForeground(Color.GRAY);
		
		username_label.setBounds(60, 140, 200, 30);
		mail_label.setBounds(300, 140, 320, 30);
		dob_label.setBounds(60, 280, 200, 30);
		contact_label.setBounds(300, 280, 320, 30);
		gender_label.setBounds(60, 420, 150, 30);
		bal_label.setBounds(300, 420, 320, 30);
		password_label.setBounds(780, 232, 130, 30);
		
		
		username_txt.setVerticalAlignment(SwingConstants.TOP);
		mail_txt.setVerticalAlignment(SwingConstants.TOP);
		dob_txt.setVerticalAlignment(SwingConstants.TOP);
		contact_txt.setVerticalAlignment(SwingConstants.TOP);
		gender_txt.setVerticalAlignment(SwingConstants.TOP);
		bal_txt.setVerticalAlignment(SwingConstants.TOP);
		
		username_txt.setForeground(new Color(11, 11, 11));
		mail_txt.setForeground(new Color(11, 11, 11));
		dob_txt.setForeground(new Color(11, 11, 11));
		contact_txt.setForeground(new Color(11, 11, 11));
		gender_txt.setForeground(new Color(11, 11, 11));
		bal_txt.setForeground(new Color(11, 11, 11));
		
		username_txt.setFont(Resource.getInterSemibold(27));
		mail_txt.setFont(Resource.getInterSemibold(27));
		dob_txt.setFont(Resource.getInterSemibold(27));
		contact_txt.setFont(Resource.getInterSemibold(27));
		gender_txt.setFont(Resource.getInterSemibold(27));
		bal_txt.setFont(Resource.getInterSemibold(27));
		
		username_txt.setBounds(60, 175, 200, 40);
		mail_txt.setBounds(300, 175, 340, 40);
		dob_txt.setBounds(60, 315, 200, 40);
		contact_txt.setBounds(300, 315, 320, 40);
		gender_txt.setBounds(60, 455, 150, 40);
		bal_txt.setBounds(300, 455, 340, 40);
		
		
		change_btn_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		change_btn_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		change_btn_1.setForeground(new Color(10, 132, 255));
		change_btn_2.setForeground(new Color(10, 132, 255));

		change_btn_1.setFont(Resource.getInterMedium(24));
		change_btn_2.setFont(Resource.getInterMedium(24));

		change_btn_1.setBounds(780, 270, 110, 25);
		change_btn_2.setBounds(780, 295, 140, 30);


		change_btn_icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		change_btn_icon.setLayout(null);
		change_btn_icon.setBackground(new Color(0, 0, 0, 0));
		change_btn_icon.setUI(Resource.renderImage(Resource.ICON_PATH_NEXT_B));
		change_btn_icon.setBounds(925, 300, 20, 25);
		
	} // initialize()
	
} // class