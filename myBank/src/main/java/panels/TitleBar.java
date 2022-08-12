package panels;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import models.ResourceModel;
import utilities.RoundedBorder;



public class TitleBar extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	private JPanel view, close, min, max;
	

	
	public TitleBar(final JFrame f) {
		
		UIManager.put("ToolTip.background", new Color(236, 239, 241));
		UIManager.put("ToolTip.foreground", new Color(38, 50, 56));
		UIManager.put("ToolTip.font", new Font("Inter", Font.PLAIN, 15));
		UIManager.put("ToolTip.border", new CompoundBorder(BorderFactory
												.createLineBorder(new Color(38, 50, 56), 1),
																  new EmptyBorder(4, 12, 4, 12)));
		
		 
		title = new JLabel("myBank");
		view = new JPanel();
		close = new JPanel();
		max = new JPanel();
		min = new JPanel();
		
		initialize(f);
		
		add(title);
		add(close);
		add(min);
		add(max);
		add(view);
	}
	
	
	private void initialize(final JFrame f) {
		
		setSize(1000, 40);
		setBackground(new Color(246, 245, 248));
		setLayout(null);
		
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new ResourceModel().getInter(3, 20));
		title.setForeground(new Color(11, 11, 11));
		title.setBounds(0, 0, 1000, 38);
		
		view.setBounds(0, 39, 1000, 1);
		view.setBackground(new Color(195, 194, 198));
		
		close.setToolTipText("Close");
		close.setBounds(20, 10, 18, 18);
		close.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		close.setBorder(new RoundedBorder(50, new Color(255, 95, 87)));
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		
		
		max.setToolTipText("Restore");
		max.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		max.setBorder(new RoundedBorder(50, new Color(255, 191, 46)));
		max.setBounds(50, 10, 18, 18);
		
		min.setToolTipText("Minimize");
		min.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		min.setBorder(new RoundedBorder(50, new Color(39, 201, 63)));
		min.setBounds(80, 10, 18, 18);
		min.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				f.setState(JFrame.ICONIFIED);
			}
		});
	
	} // initialize()

	
	public void setTitleB(String title) {
		this.title.setText(title);
	} // setTitleB()	
	
} // class