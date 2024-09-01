package panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JTextField;

import resource.Resource;
import models.UserModel;
import utilities.DAO;
import utilities.DialogX;
import utilities.LayerPanel;




public class Transactions extends JPanel {

	private final JPanel btn_icon, empty_panel;
	private final JLabel back_btn, bal_label, bal_txt, empty_label, head_label, op_label;
	private final JTable transaction_table;
	private final JScrollPane table_scroll;
	private final JButton btn_OP, btn_M, op_btn, close_btn;
	private final JPanel crud_panel;
	private final JTextField op_field;
	private final JPopupMenu menu;
	private final JMenuItem deposit, withdraw, add_user, update_user, view_transaction, delete_user;
	
	private final DefaultTableModel tableModel;
	private int row_index = -1, col_index = -1;
	private final Object[] col_obj_t = { "ID", "TIMESTAMP", "MODE", "FROM", "TO", "AMOUNT", "BALANCE" };
	private final Object[] col_obj_u = { "ID", "USERNAME", "MAIL", "PASSWORD", "DOB", "CONTACT", "GENDER", "ROLE", "BALANCE" };
	private final DAO db;
	private final DialogX dialog;

	
	

	
	
	public Transactions(LayerPanel layerPanel, UserModel userModel) {

		db = new DAO();
		dialog = new DialogX();
		btn_icon = new JPanel();
		back_btn = new JLabel("Back");
		
		bal_label = new JLabel((userModel.getUROLE() == -1) ? "TOTAL AMOUNT" : "CURRENT BALANCE");
		bal_txt = new JLabel("₹" + db.getUserMoney(userModel.getUMAIL()));
		
		btn_OP = new JButton("Operations");
		btn_M = new JButton("Money Transfer");
		
		menu = new JPopupMenu();
		deposit = new JMenuItem("Deposit", new ImageIcon(Resource.loadImage(Resource.ICON_PATH_DEPOSIT)));
		withdraw = new JMenuItem("Withdraw", new ImageIcon(Resource.loadImage(Resource.ICON_PATH_WITHDRAW)));
		add_user = new JMenuItem("Add New User", new ImageIcon(Resource.loadImage(Resource.ICON_PATH_ADD_USER)));
		update_user = new JMenuItem("Update User", new ImageIcon(Resource.loadImage(Resource.ICON_PATH_UPDATE_USER)));
		delete_user = new JMenuItem("Delete User", new ImageIcon(Resource.loadImage(Resource.ICON_PATH_DELETE_USER)));
		view_transaction = new JMenuItem("View Transactions", new ImageIcon(Resource.loadImage(Resource.ICON_PATH_VIEW_USER)));
		
		
		empty_panel = new JPanel();
		empty_label = new JLabel("You don't have any transaction history yet.");
		head_label = new JLabel("Oops..!");
		
		crud_panel = new JPanel();
		op_label = new JLabel();
		op_field = new JTextField();
		op_btn = new JButton();
		close_btn = new JButton("CLOSE");

		
		table_scroll = new JScrollPane();
		transaction_table = new JTable();
		
		tableModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};

		initialize(layerPanel, userModel);
		
		add(btn_icon);
		add(back_btn);
		add(bal_label);
		add(bal_txt);
		add(btn_OP);
		add(btn_M);
		add(empty_panel);
		add(table_scroll);
		add(crud_panel);

		empty_panel.add(head_label);
		empty_panel.add(empty_label);

		crud_panel.add(close_btn);
		crud_panel.add(op_label);
		crud_panel.add(op_field);
		crud_panel.add(op_btn);
	}

	
	private void initialize(LayerPanel layerPanel, UserModel userModel) {
		
//		setUI(new ImageRender("/MESH/gradient_"+ new Random().nextInt(12) + ".png"));
		setBackground(new Color(240, 239, 244));
		setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		btn_icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_icon.setUI(Resource.renderImage(Resource.ICON_PATH_BACK));
		btn_icon.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		btn_icon.setLayout(null);
		btn_icon.setBounds(20, 20, 20, 25);
		btn_icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				expandFrame(false, null, null);
				layerPanel.init(3, userModel);
			}
		});
		
		back_btn.setFont(Resource.getInterMedium(22));
		back_btn.setBounds(40, 10, 60, 40);
		back_btn.setForeground(new Color(10, 132, 255));
		back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				expandFrame(false, null, null);
				layerPanel.init(3, userModel);
			}
		});
		
		menu.setBorder(BorderFactory.createCompoundBorder(menu.getBorder(),BorderFactory.createEmptyBorder(8, 0, 8, 0)));
		
		deposit.setFont(Resource.getInterRegular(16));
		withdraw.setFont(Resource.getInterRegular(16));
		add_user.setFont(Resource.getInterRegular(16));
		update_user.setFont(Resource.getInterRegular(16));
		delete_user.setFont(Resource.getInterRegular(16));
		view_transaction.setFont(Resource.getInterRegular(16));
		
		deposit.setIconTextGap(14);
		withdraw.setIconTextGap(14);
		add_user.setIconTextGap(14);
		update_user.setIconTextGap(14);
		delete_user.setIconTextGap(14);
		view_transaction.setIconTextGap(14);
		
		
		if (userModel.getUROLE() == -1) {
			
			menu.add(add_user);
			menu.add(update_user);
			menu.add(delete_user);
			menu.add(view_transaction);
		}
		
		else {
			
			menu.add(deposit);
			menu.add(withdraw);
		}

        // actionPerformed()
        deposit.addActionListener(e -> {

            double AMOUNT = dialog.takeInputDialogDouble(Transactions.this, 200, "ENTER AMOUNT (DEPOSIT)", 1);

//				if (AMOUNT > userModel.getUBAL())
//					dialog.displayDialog(Transactions.this, 400, "INSUFFICIENT BALANCE!", 0);

            if (AMOUNT > 0) {
                db.beforeInsertIntoTransactions(userModel.getUID(), 1, userModel.getUBAL(), AMOUNT);
                db.insertIntoTransactions(userModel.getUMAIL(), userModel.getUMAIL(), userModel.getUID(), 1, AMOUNT);
                layerPanel.init(5, db.checkLoginCredentials(userModel.getUNAME(), userModel.getUPASS()));
            }

        });

        // actionPerformed()
        withdraw.addActionListener(e -> {

            double AMOUNT = dialog.takeInputDialogDouble(Transactions.this, 200, "ENTER AMOUNT (WITHDRAW)", 1);

            if (AMOUNT > userModel.getUBAL())
                dialog.displayDialog(Transactions.this, 400, "INSUFFICIENT BALANCE!", 0);

            else {

                if (AMOUNT > 0) {
                    db.beforeInsertIntoTransactions(userModel.getUID(), 2, userModel.getUBAL(), AMOUNT);
                    db.insertIntoTransactions(userModel.getUMAIL(), userModel.getUMAIL(), userModel.getUID(), 2, AMOUNT);
                    layerPanel.init(5, db.checkLoginCredentials(userModel.getUNAME(), userModel.getUPASS()));
                }

            } // else

        });

        // actionPerformed()
        add_user.addActionListener(e -> {

            expandFrame(true, "Add User", "INSERT");
            op_field.setVisible(false);

        });

        // actionPerformed()
        update_user.addActionListener(e -> {

            expandFrame(true, "Update User", "UPDATE");
            op_field.setVisible(true);

        });

        // actionPerformed()
        delete_user.addActionListener(e -> {

            expandFrame(true, "Delete User", "DELETE");
            op_field.setVisible(false);

        });

        // actionPerformed()
        view_transaction.addActionListener(e -> {

            expandFrame(true, "Transactions", "OPEN");
            op_field.setVisible(false);

        });




		transaction_table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		tableModel.setColumnIdentifiers((userModel.getUROLE() == -1) ? col_obj_u : col_obj_t);
		transaction_table.setModel((userModel.getUROLE() == -1) ? db.readTableU(tableModel) : db.readTableT(userModel.getUID(), tableModel));
		transaction_table.setFocusable(false);
		transaction_table.setSelectionBackground(new Color(255, 240, 245));
		transaction_table.setRowMargin(2);
		transaction_table.setRowHeight(40);
		transaction_table.getTableHeader().setPreferredSize(new Dimension(table_scroll.getWidth(), 50));
		transaction_table.getTableHeader().setFont(Resource.getInterSemibold(20));
		transaction_table.setFont(Resource.getInterRegular(18));
		transaction_table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		((JLabel) transaction_table.getDefaultRenderer(Object.class)).setHorizontalAlignment(SwingConstants.CENTER);
		
		
		transaction_table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				bal_txt.setText("₹" + ((userModel.getUROLE() != -1) ? db.getUserMoney(userModel.getUMAIL()) : db.getTotalMoney()));

				if (op_btn.getText().equals("UPDATE")) {
					
					row_index = transaction_table.getSelectedRow();
					col_index = transaction_table.getSelectedColumn();
					
					Object objValue = transaction_table.getModel().getValueAt(row_index, col_index);
					
					if (objValue != null)
						op_field.setText(objValue.toString());
					
					else op_field.setText(null);
				
				} // update()
				
				if (op_btn.getText().equals("DELETE")) {
					
					row_index = transaction_table.getSelectedRow();
					col_index = transaction_table.getSelectedColumn();
					
				} // delete()
				
				if (op_btn.getText().equals("INSERT")) {
					
					row_index = 0;
					col_index = 1;
					
				} // insert()
				
				if (op_btn.getText().equals("OPEN")) {
					
					row_index = transaction_table.getSelectedRow();
					col_index = transaction_table.getSelectedColumn();
					
				} // open()
			
			} // mouse()
			
		});
		
		if (userModel.getUROLE() == -1) {
			
			transaction_table.getColumnModel().getColumn(0).setPreferredWidth(80);
			transaction_table.getColumnModel().getColumn(1).setPreferredWidth(160);
			transaction_table.getColumnModel().getColumn(2).setPreferredWidth(240);
			transaction_table.getColumnModel().getColumn(3).setPreferredWidth(150);
			transaction_table.getColumnModel().getColumn(4).setPreferredWidth(140);
			transaction_table.getColumnModel().getColumn(5).setPreferredWidth(140);
			transaction_table.getColumnModel().getColumn(6).setPreferredWidth(140);
			transaction_table.getColumnModel().getColumn(7).setPreferredWidth(100);
			transaction_table.getColumnModel().getColumn(8).setPreferredWidth(200);
			bal_txt.setText("₹" + db.getTotalMoney());
			
		}
		
		else {
			
			
			transaction_table.getColumnModel().getColumn(1).setPreferredWidth(270);
			transaction_table.getColumnModel().getColumn(2).setPreferredWidth(120);
			transaction_table.getColumnModel().getColumn(3).setPreferredWidth(200);
			transaction_table.getColumnModel().getColumn(4).setPreferredWidth(200);
			transaction_table.getColumnModel().getColumn(5).setPreferredWidth(150);
			transaction_table.getColumnModel().getColumn(6).setPreferredWidth(150);
			bal_txt.setText("₹" + userModel.getUBAL());
		}


		 
		
		table_scroll.setBounds(25, 155, 945, 420);
		table_scroll.setBorder(null);
		table_scroll.setViewportView(transaction_table);
		
		

		bal_label.setHorizontalAlignment(SwingConstants.TRAILING);
		bal_label.setVerticalAlignment(SwingConstants.TOP);
		bal_label.setForeground(Color.GRAY);
		bal_label.setFont(Resource.getInterMedium(16));
		bal_label.setBounds(460, 50, 510, 25);
		
		bal_txt.setHorizontalAlignment(SwingConstants.TRAILING);
		bal_txt.setVerticalAlignment(SwingConstants.TOP);
		bal_txt.setForeground(Color.BLACK);
		bal_txt.setFont(Resource.getInterSemibold(38));
		bal_txt.setBounds(460, 80, 510, 60);
		
		
		btn_OP.setForeground(Color.WHITE);
		btn_OP.setFont(Resource.getInterMedium(22));
		btn_OP.setFocusPainted(false);
		btn_OP.setBorder(null);
		btn_OP.setBackground(new Color(102, 51, 204));
		btn_OP.setBounds(25, 80, 160, 48);
		btn_OP.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				menu.show(btn_OP, e.getX(), e.getY());
			}

		});
		
		btn_M.setForeground(Color.WHITE);
		btn_M.setFont(Resource.getInterMedium(22));
		btn_M.setFocusPainted(false);
		btn_M.setBorder(null);
		btn_M.setVisible(userModel.getUROLE() != -1);
		btn_M.setBackground(new Color(51, 51, 102));
		btn_M.setBounds(200, 80, 220, 48);
        // actionPerformed()
        btn_M.addActionListener(e -> {

            String TO = dialog.takeInputDialogString(Transactions.this, 200, "ENTER USER-MAIL (MONEY TRANSFER)", 1);

            if (TO != null) {

                if (TO.equalsIgnoreCase(userModel.getUMAIL()))
                    dialog.displayDialog(Transactions.this, 400, "USE DEPOSIT FUNCTIONALITY!", 0);

                else if (db.checkUserMail(null, TO) != 2)
                    dialog.displayDialog(Transactions.this, 400, "NO USER FOUND!", 0);

                else {

                    double AMOUNT = dialog.takeInputDialogDouble(Transactions.this, 200, "ENTER AMOUNT (MONEY TRANSFER)", 1);

                    if (AMOUNT > userModel.getUBAL())
                        dialog.displayDialog(Transactions.this, 400, "INSUFFICIENT BALANCE!", 0);

                    else {

                        if (AMOUNT > 0) {

                            db.beforeInsertIntoTransactions(userModel.getUID(), 2, userModel.getUBAL(), AMOUNT);
                            db.insertIntoTransactions(userModel.getUMAIL(), TO, userModel.getUID(), 3, AMOUNT);

                            db.beforeInsertIntoTransactions(db.getUserID(TO), 1, db.getUserMoney(TO), AMOUNT);
                            db.insertIntoTransactions(userModel.getUMAIL(), TO, db.getUserID(TO), 4, AMOUNT);

                            layerPanel.init(5, db.checkLoginCredentials(userModel.getUNAME(), userModel.getUPASS()));

                        }

                    } // else

                } // else FOUND

            } // empty

        });
		
		empty_panel.setBounds(25, 155, 945, 420);
		empty_panel.setBackground(Color.WHITE);
		empty_panel.setVisible(transaction_table.getModel().getRowCount() <= 0);
		empty_panel.setBorder(UIManager.getBorder("TextField.border"));
		empty_panel.setLayout(null);

		head_label.setForeground(Color.BLACK);
		head_label.setBounds(0, 120, 945, 80);
		head_label.setHorizontalAlignment(SwingConstants.CENTER);
		head_label.setFont(Resource.getInterSemibold(56));
		
		empty_label.setHorizontalAlignment(SwingConstants.CENTER);
		empty_label.setFont(Resource.getInterMedium(24));
		empty_label.setForeground(Color.DARK_GRAY);
		empty_label.setBounds(0, 200, 945, 40);
		
		crud_panel.setBorder(UIManager.getBorder("TextField.border"));
		crud_panel.setBackground(new Color(240, 240, 240));
		crud_panel.setBounds(660, 155, 310, 415);
		crud_panel.setLayout(null);
		crud_panel.setVisible(false);
		
		op_label.setFont(Resource.getInterSemibold(38));
		op_label.setHorizontalAlignment(SwingConstants.CENTER);
		op_label.setBounds(0, 80, 310, 50);
		
		
		op_field.setBounds(40, 160, 230, 50);
		op_field.setColumns(10);
		op_field.setSelectionColor(new Color(227, 242, 253));
		op_field.setMargin(new Insets(4, 10, 4, 10));
		op_field.setForeground(new Color(10, 10, 10));
		op_field.setFont(Resource.getInterRegular(20));
		
		op_btn.setForeground(Color.WHITE);
		op_btn.setFont(Resource.getInterMedium(20));
		op_btn.setFocusPainted(false);
		op_btn.setBorder(null);
		op_btn.setBackground(new Color(0, 139, 139));
		op_btn.setBounds(40, 240, 230, 48);
        // actionPerformed()
        op_btn.addActionListener(e -> {

            if (row_index == -1)
                dialog.displayDialog(Transactions.this, 500, "NO ROWS SELECTED!", JOptionPane.ERROR_MESSAGE);

            else if (col_index == 0)
                dialog.displayDialog(Transactions.this, 500, "ID CAN'T BE SELECETD!", JOptionPane.ERROR_MESSAGE);

            else {

                switch(op_btn.getText()) {

                    case "INSERT": {

                        if (!db.insertEmptyUser())
                            dialog.displayDialog(Transactions.this, 502, "Failed To ADD NEW USER!", 0);

                        refreshTable(userModel);
                        break;
                    }

                    case "UPDATE": {

                        if (!db.updateUser(op_field.getText().replace("₹", ""),
                                col_index,
                                Integer.parseInt(transaction_table.getModel().getValueAt(row_index, 0).toString()))
                            )
                            dialog.displayDialog(Transactions.this, 502, "Failed To UPDATE!", 0);

                        else {

                            if (transaction_table.getModel().getValueAt(row_index, 7).toString().equalsIgnoreCase("ADMIN")) {

                                if (Integer.parseInt(transaction_table.getModel().getValueAt(row_index, 0).toString()) == userModel.getUID()) {
                                    expandFrame(false, null, null);
                                    layerPanel.init(1, null);
                                }

                            }

                        }

                        refreshTable(userModel);
                        break;
                    }

                    case "DELETE": {

                        if (transaction_table.getModel().getValueAt(row_index, 7).toString().equalsIgnoreCase("ADMIN")) {

                            if (Integer.parseInt(transaction_table.getModel().getValueAt(row_index, 0).toString()) == userModel.getUID())
                                dialog.displayDialog(Transactions.this, 404, "CANNOT DELETE ADMIN ACCOUNT!", 0);

                        }

                        else if (!db.deleteUser(Integer.parseInt(transaction_table.getModel().getValueAt(row_index, 0).toString())))
                            dialog.displayDialog(Transactions.this, 404, "Failed To DELETE ACCOUNT!", 0);


                        refreshTable(userModel);
                        break;
                    }

                    case "OPEN": {

                        int ID = Integer.parseInt(transaction_table.getModel().getValueAt(row_index, 0).toString());
                        tableModel.setColumnCount(0);
                        tableModel.setRowCount(0);
                        tableModel.fireTableDataChanged();
                        tableModel.setColumnIdentifiers(col_obj_t);
                        transaction_table.setModel(db.readTableT(ID, tableModel));
                        transaction_table.getColumnModel().getColumn(1).setPreferredWidth(300);
                        transaction_table.getColumnModel().getColumn(2).setPreferredWidth(150);
                        transaction_table.getColumnModel().getColumn(3).setPreferredWidth(180);
                        transaction_table.getColumnModel().getColumn(4).setPreferredWidth(180);
                        transaction_table.getColumnModel().getColumn(5).setPreferredWidth(180);
                        transaction_table.getColumnModel().getColumn(6).setPreferredWidth(180);
                        op_btn.setText("BACK");

                        break;
                    }

                    case "BACK": {

                        refreshTable(userModel);
                        op_btn.setText("OPEN");
                        break;
                    }


                } // switch

            } // else

        });
		
		close_btn.setForeground(Color.WHITE);
		close_btn.setFont(Resource.getInterMedium(20));
		close_btn.setFocusPainted(false);
		close_btn.setBorder(null);
		close_btn.setBackground(new Color(70, 130, 180));
		close_btn.setBounds(40, 300, 230, 48);
		close_btn.addActionListener(e -> {

            if (op_btn.getText().equalsIgnoreCase("BACK")) {
                refreshTable(userModel);
                op_btn.setText("OPEN");
            }

            expandFrame(false, null, null);

        });
		
		
	} // initialize()
	
	
	private void expandFrame(boolean state, String op_title, String op_btn_txt) {
		
		if (state) {
			
			empty_panel.setBounds(25, 155, 600, 420);
			table_scroll.setBounds(25, 155, 600, 420);
			head_label.setBounds(0, 120, 600, 80);
			head_label.setBounds(0, 120, 600, 80);
			
			op_label.setText(op_title);
			op_btn.setText(op_btn_txt);
			op_field.setText(null);
			
		}
		
		else {

			empty_panel.setBounds(25, 155, 945, 420);
			table_scroll.setBounds(25, 155, 945, 420);
			head_label.setBounds(0, 120, 945, 80);
			head_label.setBounds(0, 120, 945, 80);
			
			op_label.setText(null);
			op_btn.setText(null);
			op_field.setText(null);
			
		}
		
		crud_panel.setVisible(state);
		
	} // expandFrame()
	
	private void refreshTable(UserModel userModel) {
		
		tableModel.setColumnCount(0);
		tableModel.setRowCount(0);
		tableModel.fireTableDataChanged();
		tableModel.setColumnIdentifiers((userModel.getUROLE() == -1) ? col_obj_u : col_obj_t);
		transaction_table.setModel((userModel.getUROLE() == -1) ? db.readTableU(tableModel) : db.readTableT(userModel.getUID(), tableModel));
		
		transaction_table.getColumnModel().getColumn(0).setPreferredWidth(80);
		transaction_table.getColumnModel().getColumn(1).setPreferredWidth(160);
		transaction_table.getColumnModel().getColumn(2).setPreferredWidth(240);
		transaction_table.getColumnModel().getColumn(3).setPreferredWidth(150);
		transaction_table.getColumnModel().getColumn(4).setPreferredWidth(140);
		transaction_table.getColumnModel().getColumn(5).setPreferredWidth(140);
		transaction_table.getColumnModel().getColumn(6).setPreferredWidth(140);
		transaction_table.getColumnModel().getColumn(7).setPreferredWidth(100);
		transaction_table.getColumnModel().getColumn(8).setPreferredWidth(200);
		bal_txt.setText("₹" + db.getTotalMoney());
		
	} // refreshTable()
	
	
} // class