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

import resource.Resource;
import utilities.ConnectionHelper;
import utilities.DialogX;
import utilities.LayerPanel;



public class Splash extends JPanel {

	private final JLabel header, description, btn;
	private final JPanel btn_icon;
	private final DialogX dialog;
	private final LayerPanel layerPanel;
	private boolean state = false;

	public Splash(LayerPanel layerPanel) {
		
		header = new JLabel("Hello!");
		description = new JLabel();
		btn = new JLabel();
		btn_icon = new JPanel();
		dialog = new DialogX();
		this.layerPanel = layerPanel;
		
		initialize();

		add(header);
		add(description);
		add(btn);
		add(btn_icon);
		
	}

	
	private void initialize() {

        state = ConnectionHelper.createConnection() != null;
		
		
		setBackground(new Color(240, 239, 244));
		setBounds(0, 0, 1000, 600);
		setUI(Resource.renderImage(Resource.getMeshPack()));
		setLayout(null);

		
		header.setForeground(Color.BLACK);
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setFont(Resource.getInterSemibold(100));
		header.setBounds(0, 150, 1000, 100);
		
		
		description.setText((state) ? "To continue, login using your credentials." : "To continue, setup MySQL database.");
		description.setForeground(Color.GRAY);
		description.setHorizontalAlignment(SwingConstants.CENTER);
		description.setFont(Resource.getInterRegular(17));
		description.setBounds(0, 260, 1000, 20);


		btn.setText(state ? "Learn more" : "Let's Setup");
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setHorizontalAlignment(SwingConstants.TRAILING);
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setForeground((state) ? new Color(10, 132, 255) : new Color(255, 0, 51));
		btn.setFont(Resource.getInterMedium(24));
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
		btn_icon.setUI(state ? Resource.renderImage(Resource.ICON_PATH_NEXT_B) : Resource.renderImage(Resource.ICON_PATH_NEXT_R));
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
					dialog.takeInputDialogString(null, 404, "MySQL - Connection URL (eg. localhost:3306)", JOptionPane.QUESTION_MESSAGE),
					dialog.takeInputDialogString(null, 404, "MySQL - Login Credentials (Username).", JOptionPane.QUESTION_MESSAGE),
					dialog.takeInputDialogString(null, 404, "MySQL - Login Credentials (Password).", JOptionPane.QUESTION_MESSAGE)
		);
		layerPanel.init(0, null);
	} // initDB()
	

} // class