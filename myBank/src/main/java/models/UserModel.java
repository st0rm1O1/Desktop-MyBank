package models;

public class UserModel {
	
	private int UID;
	private String UNAME;
	private String UMAIL;
	private String UPASS;
	private String UDOB;
	private String UCON;
	private int UGEN;
	private int UROLE;
	private double UBAL;
	
	

	public UserModel() {
		super();
	}

	public UserModel(int uID, String uNAME, String uMAIL, String uPASS,
					String uDOB, String uCON, int uGEN, int uROLE,
					double uBAL) {
	
		super();
		UID = uID;
		UNAME = uNAME;
		UMAIL = uMAIL;
		UPASS = uPASS;
		UDOB = uDOB;
		UCON = uCON;
		UGEN = uGEN;
		UROLE = uROLE;
		UBAL = uBAL;
		
	}
	
	public UserModel(String uNAME, String uMAIL, String uPASS,
			String uDOB, String uCON, int uGEN, int uROLE,
			double uBAL) {

		super();
		UNAME = uNAME;
		UMAIL = uMAIL;
		UPASS = uPASS;
		UDOB = uDOB;
		UCON = uCON;
		UGEN = uGEN;
		UROLE = uROLE;
		UBAL = uBAL;
	
	}

	public int getUID() {
		return UID;
	}

	public void setUID(int uID) {
		UID = uID;
	}

	public String getUNAME() {
		return UNAME;
	}

	public void setUNAME(String uNAME) {
		UNAME = uNAME;
	}

	public String getUMAIL() {
		return UMAIL;
	}

	public void setUMAIL(String uMAIL) {
		UMAIL = uMAIL;
	}

	public String getUPASS() {
		return UPASS;
	}

	public void setUPASS(String uPASS) {
		UPASS = uPASS;
	}

	public String getUDOB() {
		return UDOB;
	}

	public void setUDOB(String uDOB) {
		UDOB = uDOB;
	}

	public String getUCON() {
		return UCON;
	}

	public void setUCON(String uCON) {
		UCON = uCON;
	}

	public int getUGEN() {
		return UGEN;
	}

	public void setUGEN(int uGEN) {
		UGEN = uGEN;
	}

	public int getUROLE() {
		return UROLE;
	}

	public void setUROLE(int uROLE) {
		UROLE = uROLE;
	}

	public double getUBAL() {
		return UBAL;
	}

	public void setUBAL(double uBAL) {
		UBAL = uBAL;
	}
	
}