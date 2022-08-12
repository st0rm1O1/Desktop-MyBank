package panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import models.ResourceModel;
import utilities.ConnectionHelper;
import utilities.DialogX;
import utilities.ImageRender;
import utilities.LayerPanel;



public class Splash extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel header, description, btn;
	private JPanel btn_icon;
	private DialogX dialog;
	private ResourceModel res;
	private LayerPanel layerPanel;
	private boolean state = false;

	public Splash(LayerPanel layerPanel) {
		
		header = new JLabel("Hello!");
		description = new JLabel();
		btn = new JLabel("Learn more");
		btn_icon = new JPanel();
		dialog = new DialogX();
		res = new ResourceModel();
		this.layerPanel = layerPanel;
		
		initialize();

		add(header);
		add(description);
		add(btn);
		add(btn_icon);
		
	}

	
	private void initialize() {
		
		if (ConnectionHelper.createConnection() == null) state = false;			
		else state = true;
		
		
		setBackground(new Color(240, 239, 244));
		setBounds(0, 0, 1000, 600);
		setUI(new ImageRender(res.getMESH_PATH()));
		setLayout(null);

		
		header.setForeground(Color.BLACK);
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setFont(res.getInter(3, 100));
		header.setBounds(0, 150, 1000, 100);
		
		
		description.setText((state) ? "To continue, login using your credentials." : "To continue, setup MySQL database.");
		description.setForeground(Color.GRAY);
		description.setHorizontalAlignment(SwingConstants.CENTER);
		description.setFont(res.getInter(1, 17));
		description.setBounds(0, 260, 1000, 20);
		
		
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setHorizontalAlignment(SwingConstants.TRAILING);
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setForeground((state) ? new Color(10, 132, 255) : new Color(255, 0, 51));
		btn.setFont(res.getInter(2, 24));
		btn.setBounds(415, 420, 135, 40);
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (state) layerPanel.init(1, null);
				else initDB();

			}
		});
		
		btn_icon.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		btn_icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_icon.setUI((state) ? new ImageRender(res.getNEXT_B_PATH()) : new ImageRender(res.getNEXT_R_PATH()));
		btn_icon.setLayout(null);
		btn_icon.setBounds(558, 431, 20, 25);
		btn_icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (state) layerPanel.init(1, null);
				else initDB();
			}
		});

	} // initialize()
	
	
	private void initDB() {
		
		ConnectionHelper.setPreference(
					dialog.takeInputDialogString(null, 200, "MySQL URL-Link.", JOptionPane.INFORMATION_MESSAGE),
					dialog.takeInputDialogString(null, 200, "MySQL Login Credentials (USERNAME).", JOptionPane.INFORMATION_MESSAGE),
					dialog.takeInputDialogString(null, 200, "MySQL Login Credentials (PASSWORD).", JOptionPane.INFORMATION_MESSAGE)
				);
		
		layerPanel.init(0, null);
	
	} // initDB()
	

} // class