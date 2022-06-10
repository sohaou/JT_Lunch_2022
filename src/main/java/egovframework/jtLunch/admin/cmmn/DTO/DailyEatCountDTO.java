package egovframework.jtLunch.admin.cmmn.DTO;

public class DailyEatCountDTO {
	// 체인점 아이디
	private String FRANCHISE_ID;
	// 식사날짜
	private String EAT_DATE;
	// 식사자 수
	private int COUNT_EAT;
	// 지점명
	private String SHOP_NAME;
	// 식사자 수 총합
	private int TOTAL;
	
	public String getFRANCHISE_ID() {
		return FRANCHISE_ID;
	}
	public void setFRANCHISE_ID(String fRANCHISE_ID) {
		FRANCHISE_ID = fRANCHISE_ID;
	}
	public String getEAT_DATE() {
		return EAT_DATE;
	}
	public void setEAT_DATE(String eAT_DATE) {
		EAT_DATE = eAT_DATE;
	}
	public int getCOUNT_EAT() {
		return COUNT_EAT;
	}
	public void setCOUNT_EAT(int cOUNT_EAT) {
		COUNT_EAT = cOUNT_EAT;
	}
	public String getSHOP_NAME() {
		return SHOP_NAME;
	}
	public void setSHOP_NAME(String sHOP_NAME) {
		SHOP_NAME = sHOP_NAME;
	}
	public int getTOTAL() {
		return TOTAL;
	}
	public void setTOTAL(int tOTAL) {
		TOTAL = tOTAL;
	}
	@Override
	public String toString() {
		return "DailyEatCountDTO [FRANCHISE_ID=" + FRANCHISE_ID + ", EAT_DATE=" + EAT_DATE + ", COUNT_EAT=" + COUNT_EAT
				+ ", SHOP_NAME=" + SHOP_NAME + ", TOTAL=" + TOTAL + "]";
	}
}
