package com.github.st0rm1O1.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableModel;

import com.github.st0rm1O1.models.UserModel;



public class DAO {
	
	private final Connection link = ConnectionHelper.createConnection();

	private PreparedStatement ps;
	private ResultSet rs;
	private UserModel userModel;
	
	private final String getRowCountTransactions = "SELECT COUNT(*) FROM transactions";
	private final String getRowCountUsers = "SELECT COUNT(*) FROM users";
	private final String getTotalAmount = "SELECT SUM(UBAL) FROM users";
	private final String getUserMoney = "SELECT UBAL FROM users WHERE UMAIL = ?";
	private final String getUserID = "select UID from users where UMAIL = ?";
	
	private final String readTransactions = "SELECT * FROM transactions where UFID = ?";
	private final String readUsers = "SELECT * FROM users";
	
	private final String loginQuery = "SELECT * FROM users WHERE (USERN = ? OR UMAIL = ?) AND UPASS = ?";
	private final String checkTable = "SHOW TABLES LIKE ?";
	private final String checkDB = "SHOW DATABASES LIKE ?";
	private final String checkUser = "SELECT * FROM users WHERE USERN = ?";
	private final String checkMail = "SELECT * FROM users WHERE UMAIL = ?";
	
	private final String createDB = "CREATE DATABASE mybank";
	private final String insertSystemUser = "INSERT INTO users (USERN, UMAIL, UPASS, UDOB, UCON, UGEN, UROLE, UBAL) VALUES ('system', 'system@gmail.com', 'admin', '00-00-0000', '0987654321', 0, -1, 0)";
	private final String createUserTable = 
			"create table users ("
			+ "  UID int not null auto_increment,"
			+ "  USERN varchar(20) not null,"
			+ "  UMAIL varchar(40) not null,"
			+ "  UPASS varchar(20) not null,"
			+ "  UDOB varchar(10) not null,"
			+ "  UCON varchar(10) not null,"
			+ "  UGEN int not null,"
			+ "  UROLE int not null,"
			+ "  UBAL double not null,"
			+ "  PRIMARY KEY (UID)"
			+ ")";
	
	private final String createTransactionTable = 
			"create table transactions ("
			+ "  TID int auto_increment,"
			+ "  UFID int not null,"
			+ "  TDOB varchar(30) not null,"
			+ "  TMOD int not null,"
			+ "  TFROM varchar(50),"
			+ "  TTO varchar(50),"
			+ "  TFUND double not null,"
			+ "  UFBAL double not null,"
			+ "  PRIMARY KEY (TID)"
			+ ")";
	
	
	private final String insertUser = "INSERT INTO users (USERN, UMAIL, UPASS, UDOB, UCON, UGEN, UROLE, UBAL) VALUES (?, ?, ?, ?, ?, ?, 1, 0)";
	private final String insertEmptyUser = "INSERT INTO users (USERN, UMAIL, UPASS, UDOB, UCON, UGEN, UROLE, UBAL) VALUES ('NULL', 'NULL', 'NULL', '00-00-0000', 1234567890, 0, 1, 0)";
	private final String updateUser1 = "UPDATE users SET USERN = ? WHERE UID = ?";
	private final String updateUser2 = "UPDATE users SET UMAIL = ? WHERE UID = ?";
	private final String updateUser3 = "UPDATE users SET UPASS = ? WHERE UID = ?";
	private final String updateUser4 = "UPDATE users SET UDOB = ? WHERE UID = ?";
	private final String updateUser5 = "UPDATE users SET UCON = ? WHERE UID = ?";
	private final String updateUser6 = "UPDATE users SET UGEN = ? WHERE UID = ?";
	private final String updateUser7 = "UPDATE users SET UROLE = ? WHERE UID = ?";
	private final String updateUser8 = "UPDATE users SET UBAL = ? WHERE UID = ?";
	private final String beforeDeleteUser = "DELETE from transactions WHERE UFID = ?";
	private final String deleteUser = "DELETE from users WHERE UID = ?";

	private final String updateBeforeInsertTransaction = "UPDATE users SET UBAL = ? WHERE  UID = ?";
	private final String insertTransaction = "INSERT INTO transactions (UFID, TDOB, TMOD, TFROM, TTO, TFUND, UFBAL) VALUES (?, ?, ?, ?, ?, ?, (select UBAL from users where UID = ?))";
	
	// READ TABLE
	public DefaultTableModel readTableU(DefaultTableModel tableModel) {
		
		if (link != null) {
		
			try {
									
				ps = link.prepareStatement(readUsers);	
				rs = ps.executeQuery();
			
				
				while (rs.next()) {
					
					Object row[] = { 
							
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							( (rs.getInt(7)) == 1 ? "Male" : (rs.getInt(7)) == 2 ? "Female" : "null"),
							( (rs.getInt(8)) == -1 ? "ADMIN" : "USER"),
							( (rs.getDouble(9)) % 1 == 0 ? "₹" + Math.round(rs.getDouble(9)) : "\u20B9" + rs.getDouble(9) ),
						};
					
					tableModel.addRow(row);
				}
				
				return tableModel;
				
				
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
			
		} // if (link != null)
		
		return tableModel;
		
	} // readTableU()

	
	
	public DefaultTableModel readTableT(int UID, DefaultTableModel tableModel) {
		
		if (link != null) {
		
			try {
							
				ps = link.prepareStatement(readTransactions);	
				ps.setInt(1, UID);
					
				rs = ps.executeQuery();
				
				while (rs.next()) {
					
					Object row[] = { 
							
							rs.getInt(1),
							rs.getString(3),
							( (rs.getInt(4) == 1) ? "Deposit" : (rs.getInt(4) == 2) ? "Withdraw" : (rs.getInt(4) == 3) ? "Sent" : "Received"),
							rs.getString(5),
							rs.getString(6),
							( (rs.getDouble(7) % 1) == 0 ? "\u20B9" + Math.round(rs.getDouble(7)) : "\u20B9" + rs.getDouble(7) ),
							( (rs.getDouble(8) % 1) == 0 ? "\u20B9" + Math.round(rs.getDouble(8)) : "\u20B9" + rs.getDouble(8) )
							
						};
					
					tableModel.addRow(row);
				}
				
				return tableModel;
				
				
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
			
			} // if (link != null)
		
		return tableModel;
		
	} // readTableT()
	
	
	
	public int getUserID(String MAIL) {
		
		if (link != null) {
		
			try {
				
				ps = link.prepareStatement(getUserID);
				ps.setString(1, MAIL);
				rs = ps.executeQuery();
				
				if (rs.next())		
					return rs.getInt(1);
				
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			return -1;
		
		} // if (link != null)
		
		return 0;
		
	} // getUserID()
	
	
	
	public Double getUserMoney(String MAIL) {
		
		if (link != null) {
		
			try {
				
				ps = link.prepareStatement(getUserMoney);
				ps.setString(1, MAIL);
				rs = ps.executeQuery();
				
				if (rs.next())		
					return rs.getDouble(1);
				
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			return -1.0;
		
		} // if (link != null)
		
		return 0.0;
		
	} // getUserMoney()
	
	
	
	// GET TOTAL MONEY
	public Double getTotalMoney() {
		
		if (link != null) {
		
			try {
				
				ps = link.prepareStatement(getTotalAmount);
				rs = ps.executeQuery();
				
				if (rs.next())		
					return rs.getDouble(1);
				
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			return -1.0;
		
		} // if (link != null)
		
		return 0.0;
		
	} // getTotalMoney()
	
	
	
	
	
	// GET ROW COUNT
	public int getRowCount(int ROLE) {
		
		if (link != null) {
		
			try {
				
				ps = link.prepareStatement((ROLE == -1) ? getRowCountUsers : getRowCountTransactions);
				rs = ps.executeQuery();
				
				if (rs.next())		
					return rs.getInt(1);
				
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			return -1;
		
		} // if (link != null)
		
		return 0;
		
	} // getRowCount()
	
	
	
	
	
	// DELETE
	public boolean deleteUser(int UID) {
		
		if (link != null) {
		
			try {
				
				ps = link.prepareStatement(beforeDeleteUser);
				ps.setInt(1, UID);
				ps.executeUpdate();
					
				ps = link.prepareStatement(deleteUser);
				ps.setInt(1, UID);
					
				if (ps.executeUpdate() > 0) 
					return true;
					
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		} // if (link != null)
		
		return false;
		
	} // deleteUser()
	
	
	

	// UPDATE
	public boolean updateUser(String data, int col_index, int UID) {
		
		if (link != null) {
			
			try {
				
				switch (col_index) {
				
					case 1 : 
						ps = link.prepareStatement(updateUser1);
						ps.setString(1, data);
						break;
						
					case 2 : 
						ps = link.prepareStatement(updateUser2);
						ps.setString(1, data);
						break;
						
					case 3 : 
						ps = link.prepareStatement(updateUser3);
						ps.setString(1, data);
						break;
						
					case 4 : 
						ps = link.prepareStatement(updateUser4);
						ps.setString(1, data);
						break;
						
					case 5 : 
						ps = link.prepareStatement(updateUser5);
						ps.setString(1, data);
						break;
						
					case 6 : 
						ps = link.prepareStatement(updateUser6);
						ps.setInt(1, ((data.equalsIgnoreCase("MALE")) || (data.equalsIgnoreCase("M"))) ? 1 : ((data.equalsIgnoreCase("FEMALE")) || (data.equalsIgnoreCase("F"))) ? 2 : 0 );
						break;
						
					case 7 : 
						ps = link.prepareStatement(updateUser7);
						ps.setInt(1, ((data.equalsIgnoreCase("ADMIN")) || (data.equalsIgnoreCase("A"))) ? -1 : ((data.equalsIgnoreCase("USER")) || (data.equalsIgnoreCase("U"))) ? 1 : 1 );
						break;
						
					case 8 : 
						ps = link.prepareStatement(updateUser8);
						ps.setDouble(1, Double.parseDouble(data));
						break;
				}
				
			
				ps.setInt(2, UID);
				
				if (ps.executeUpdate() > 0) 	
					return true;
				
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		} // if (link != null)
		
		return false;
		
	} // updateUser()


	

	
	public int checkUserMail(String public_key, String mail_key) {
		
		if (link != null) {
		
			try {
				
				/*
				 
				 0 = NO USERNAME - NO MAIL
				 1 = USERNAME EXIST
				 2 = MAIL EXIST
				 
				 */
	
//				USERNAME
				ps = link.prepareStatement(checkUser);
				ps.setString(1, public_key);
				rs = ps.executeQuery();
				
				if (rs.next()) return 1;
				
				
//				MAIL
				ps = link.prepareStatement(checkMail);
				ps.setString(1, mail_key);
				rs = ps.executeQuery();
				
				if (rs.next()) return 2;
				
				
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			return 0;
		
		} // if (link != null)
		
		return 0;
		
	} // checkUserMail()
	
	
	public void checkDatabaseExistance() {
		
		if (link != null) {
		
			try {
				
				ps = link.prepareStatement(checkDB);
				ps.setString(1, "mybank");
				rs = ps.executeQuery();
				
				if (!rs.next())
					createDB();
				
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		} // if (link != null)
		
		
	} // checkDatabaseExistance()
	
	
	
	private void createDB() {
		
		try {
			
			ps = link.prepareStatement(createDB);
			ps.execute();
			
		} // try
		
		catch(Exception e) {
			e.printStackTrace();
		} 
		
		
	} // createDB()



	public void checkTableExistance() {
		
		if (link != null) {
		
			try {
				
				ps = link.prepareStatement(checkTable);
				ps.setString(1, "users");
				rs = ps.executeQuery();
				
				if (!rs.next())
					createUserTable();
				
				
				ps = link.prepareStatement(checkTable);
				ps.setString(1, "transactions");
				rs = ps.executeQuery();
				
				if (!rs.next())
					createTransactionTable();
				
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		} // if (link != null)
		
		
	} // checkTableExistance()
	
	
	
	
	private void createTransactionTable() {
		
		try {
			
			ps = link.prepareStatement(createTransactionTable);
			ps.execute();
			
		} // try
		
		catch(Exception e) {
			e.printStackTrace();
		} // catch()
		
		
	} // createTransactionTable()



	private void createUserTable() {
		
		try {
			
			ps = link.prepareStatement(createUserTable);
			ps.execute();
			
			ps = link.prepareStatement(insertSystemUser);
			ps.executeUpdate();
			
		} // try
		
		catch(Exception e) {
			e.printStackTrace();
		} // catch()
		
		
	} // createUserTable()



	public UserModel checkLoginCredentials(String public_key, String private_key) {
		
		if (link != null) {
		
			try {
				
				userModel = new UserModel();
				
				ps = link.prepareStatement(loginQuery);
				ps.setString(1, public_key);
				ps.setString(2, public_key);
				ps.setString(3, private_key);
				
				rs = ps.executeQuery();
				
				if (rs.next()) {
					
					userModel.setUID(rs.getInt(1));
					userModel.setUNAME(rs.getString(2));
					userModel.setUMAIL(rs.getString(3));
					userModel.setUPASS(rs.getString(4));
					userModel.setUDOB(rs.getString(5));
					userModel.setUCON(rs.getString(6));
					userModel.setUGEN(rs.getInt(7));
					userModel.setUROLE(rs.getInt(8));
					userModel.setUBAL(rs.getDouble(9));
					
					return userModel;
				}
				
				
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		} // if (link != null)
		
		return null;
		
	} // checkLoginCredentials()
	
	
	
	
	
	// INSERT
	public boolean insertUser(String public_key, String email_key, String private_key, String dob_key, String contact_key, int gender_key) {
		
		if (link != null) {
		
			try {
				
				
				ps = link.prepareStatement(insertUser);
				ps.setString(1, public_key);
				ps.setString(2, email_key);
				ps.setString(3, private_key);
				ps.setString(4, dob_key);
				ps.setString(5, contact_key);
				ps.setInt(6, gender_key);
				
				if (ps.executeUpdate() > 0) 		
					return true;
			
			
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		} // if (link != null)
		
		return false;
		
	} // insertUser()	
	
	
	
	public boolean insertEmptyUser() {
		
		if (link != null) {
		
			try {
				
				ps = link.prepareStatement(insertEmptyUser);
				
				if (ps.executeUpdate() > 0) 
					return true;
					
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		} // if (link != null)
		
		return false;
		
	} // insertEmptyUser()
	
	
	
	public boolean beforeInsertIntoTransactions(int UID, int MODE, double UFBAL, double AMOUNT) {
		
		if (link != null) {
		
			try {
				
				ps = link.prepareStatement(updateBeforeInsertTransaction);
				
				switch (MODE) {
				
					case 1:
						ps.setDouble(1, (UFBAL+AMOUNT));
						ps.setInt(2, UID);
						break;
						
					case 2:
						ps.setDouble(1, (UFBAL-AMOUNT));
						ps.setInt(2, UID);
						break;
						
				}
	
				
				if (ps.executeUpdate() > 0) 
						return true;
						
				
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		} // if (link != null)
		
		return false;
		
	} // beforeInsertIntoTransactions()
	
	
	
	public boolean insertIntoTransactions(String FROM, String TO, int UID, int MODE, double AMOUNT) {
		
		if (link != null) {
		
			try {
				
				//INSERT INTO transactions (UFID, TDOB, TMOD, TFROM, TTO, TFUND, UFBAL) VALUES (?, ?, ?, ?, ?, ?, (select UBAL from users where UID = ?))";
	
				ps = link.prepareStatement(insertTransaction);
				ps.setInt(1, UID);
				ps.setString(2, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date()));
				ps.setInt(3, MODE);
				ps.setString(4, FROM);
				ps.setString(5, TO);
				ps.setDouble(6, AMOUNT);
				ps.setInt(7, UID);
					
				if (ps.executeUpdate() > 0) 
					return true;
					
			} // try
			
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		} // if (link != null)
		
		return false;
		
	} // insertIntoTransactions()
	
	
} // class