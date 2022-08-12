package models;

import java.awt.Font;
import java.io.File;
import java.util.Random;



public class ResourceModel {
	
	private final String AVATAR_PATH = "/IMG/ICON/avatar.png";
	private final String ICON_PATH = "/IMG/ICON/icon.png";
	
	private final String LOCK_PATH = "/IMG/ICON/lock.png";
	private final String UNLOCK_PATH = "/IMG/ICON/unlock.png";
	private final String NEXT_B_PATH = "/IMG/ICON/next.png";
	private final String NEXT_R_PATH = "/IMG/ICON/next_red.png";
	private final String BACK_PATH = "/IMG/ICON/back.png";
	
	private final String ADD_USER_PATH = "/IMG/ICON/add_user.png";
	private final String UPDATE_USER_PATH = "/IMG/ICON/update_user.png";
	private final String DELETE_USER_PATH = "/IMG/ICON/delete_user.png";
	private final String VIEW_USER_PATH = "/IMG/ICON/view_user.png";
	
	private final String DEPOSIT_PATH = "/IMG/ICON/deposit.png";
	private final String WITHDRAW_PATH = "/IMG/ICON/withdraw.png";
	
	private final String MESH_PATH = "/IMG/MESH/gradient_"+ new Random().nextInt(12) + ".png";
	private final String SLIDE_PATH = "/IMG/SLIDE/" + new Random().nextInt(17) + ".png";
	
	
	
	public Font getInter(int mode, int size) {
		
		/*
			 1 -> REGULAR
			 2 -> MEDIUM
			 3 -> SEMIBOLD
			 4 -> BOLD
		*/
		
		try {   return Font.createFont(Font.TRUETYPE_FONT,
	                    new File(ResourceModel.class
	                    		.getResource("/FONT/" 
	                    				+ ( (mode == 1) ? "regular" :
	                    					(mode == 2) ? "medium" : "semibold") 
	                    				+ ".ttf")
	                    		.toURI()))
			    				.deriveFont(Font.PLAIN, size);
		} // try()
		
		catch(Exception e) {
			e.printStackTrace();
		} // catch()
		
		return null;
		
	}

	
	
	public String getAVATAR_PATH() {
		return AVATAR_PATH;
	}
	
	public String getICON_PATH() {
		return ICON_PATH;
	}
	
	public String getLOCK_PATH() {
		return LOCK_PATH;
	}
	
	public String getUNLOCK_PATH() {
		return UNLOCK_PATH;
	}
	
	public String getNEXT_B_PATH() {
		return NEXT_B_PATH;
	}
	public String getNEXT_R_PATH() {
		return NEXT_R_PATH;
	}
	
	public String getBACK_PATH() {
		return BACK_PATH;
	}
	
	public String getADD_USER_PATH() {
		return ADD_USER_PATH;
	}
	
	public String getUPDATE_USER_PATH() {
		return UPDATE_USER_PATH;
	}
	
	public String getDELETE_USER_PATH() {
		return DELETE_USER_PATH;
	}
	
	public String getVIEW_USER_PATH() {
		return VIEW_USER_PATH;
	}
	
	public String getDEPOSIT_PATH() {
		return DEPOSIT_PATH;
	}
	
	public String getWITHDRAW_PATH() {
		return WITHDRAW_PATH;
	}
	
	public String getMESH_PATH() {
		return MESH_PATH;
	}
	
	public String getSLIDE_PATH() {
		return SLIDE_PATH;
	}
	
}
