package egovframework.jtLunch.admin.owner.DTO;

public class UserInfoDTO {
	private String USER_ID;
	private String USER_PW;
	private String USER_NAME;
	private String USER_TEL;
	private String USER_AUTH;
	private int ENABLED;
	private String ACCESS_DATE;
	private String JOIN_DATE;
	private String DEPARTMENT;
	private String TEAM;
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getUSER_PW() {
		return USER_PW;
	}
	public void setUSER_PW(String uSER_PW) {
		USER_PW = uSER_PW;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public String getUSER_TEL() {
		return USER_TEL;
	}
	public void setUSER_TEL(String uSER_TEL) {
		USER_TEL = uSER_TEL;
	}
	public String getUSER_AUTH() {
		return USER_AUTH;
	}
	public void setUSER_AUTH(String uSER_AUTH) {
		USER_AUTH = uSER_AUTH;
	}
	public int getENABLED() {
		return ENABLED;
	}
	public void setENABLED(int eNABLED) {
		ENABLED = eNABLED;
	}
	public String getACCESS_DATE() {
		return ACCESS_DATE;
	}
	public void setACCESS_DATE(String aCCESS_DATE) {
		ACCESS_DATE = aCCESS_DATE;
	}
	public String getJOIN_DATE() {
		return JOIN_DATE;
	}
	public void setJOIN_DATE(String jOIN_DATE) {
		JOIN_DATE = jOIN_DATE;
	}
	public String getDEPARTMENT() {
		return DEPARTMENT;
	}
	public void setDEPARTMENT(String dEPARTMENT) {
		DEPARTMENT = dEPARTMENT;
	}
	public String getTEAM() {
		return TEAM;
	}
	public void setTEAM(String tEAM) {
		TEAM = tEAM;
	}
	@Override
	public String toString() {
		return "UserInfoDTO [USER_ID=" + USER_ID + ", USER_PW=" + USER_PW + ", USER_NAME=" + USER_NAME + ", USER_TEL="
				+ USER_TEL + ", USER_AUTH=" + USER_AUTH + ", ENABLED=" + ENABLED + ", ACCESS_DATE=" + ACCESS_DATE
				+ ", JOIN_DATE=" + JOIN_DATE + ", DEPARTMENT=" + DEPARTMENT + ", TEAM=" + TEAM + "]";
	}
}
