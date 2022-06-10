package egovframework.jtLunch.admin.cmmn.DTO;

public class FranchiseDTO {
	// 체인점 아이디
	private String ID;
	// 체인점 비밀번호
	private String PW;
	// 지점명
	private String SHOP_NAME;
	// 지점명(pq그리드에 데이터를 넣을 때 _인식을 못함)
	private String SHOPNAME;
	// 전화번호
	private String TEL;
	// 우편번호
	private String ZIP_CODE;
	// 기본주소
	private String ADDRESS;
	// 상세주소
	private String DETAIL_ADDRESS;
	// 포트번호
	private String PORT_NUM;
	// 데이터 전송 주소
	private String DATA_URL;
	private String DATAURL;
	// 체인점 분류 (기본/고급)
	private int TYPE;
	private String TYPEstr;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	public String getSHOP_NAME() {
		return SHOP_NAME;
	}
	public void setSHOP_NAME(String sHOP_NAME) {
		SHOP_NAME = sHOP_NAME;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getZIP_CODE() {
		return ZIP_CODE;
	}
	public void setZIP_CODE(String zIP_CODE) {
		ZIP_CODE = zIP_CODE;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getDETAIL_ADDRESS() {
		return DETAIL_ADDRESS;
	}
	public void setDETAIL_ADDRESS(String dETAIL_ADDRESS) {
		DETAIL_ADDRESS = dETAIL_ADDRESS;
	}
	public String getPORT_NUM() {
		return PORT_NUM;
	}
	public void setPORT_NUM(String pORT_NUM) {
		PORT_NUM = pORT_NUM;
	}
	public String getDATA_URL() {
		return DATA_URL;
	}
	public void setDATA_URL(String dATA_URL) {
		DATA_URL = dATA_URL;
	}
	public String getSHOPNAME() {
		return SHOPNAME;
	}
	public void setSHOPNAME(String sHOPNAME) {
		SHOPNAME = sHOPNAME;
	}
	public String getDATAURL() {
		return DATAURL;
	}
	public void setDATAURL(String dATAURL) {
		DATAURL = dATAURL;
	}
	public int getTYPE() {
		return TYPE;
	}
	public void setTYPE(int tYPE) {
		TYPE = tYPE;
	}
	public String getTYPEstr() {
		return TYPEstr;
	}
	public void setTYPEstr(String tYPEstr) {
		TYPEstr = tYPEstr;
	}
	@Override
	public String toString() {
		return "FranchiseDTO [ID=" + ID + ", PW=" + PW + ", SHOP_NAME=" + SHOP_NAME + ", SHOPNAME=" + SHOPNAME
				+ ", TEL=" + TEL + ", ZIP_CODE=" + ZIP_CODE + ", ADDRESS=" + ADDRESS + ", DETAIL_ADDRESS="
				+ DETAIL_ADDRESS + ", PORT_NUM=" + PORT_NUM + ", DATA_URL=" + DATA_URL + ", DATAURL=" + DATAURL
				+ ", TYPE=" + TYPE + "]";
	}
}
