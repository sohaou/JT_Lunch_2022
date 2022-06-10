package egovframework.jtLunch.admin.owner.DTO;

//메뉴
public class MenuDTO {
	private String menu_id;
	private String menu_type; //메뉴 종류
	private String menu_name; //메뉴 이름
	
	private String type; //검색키
	private String keyword; //검색값
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	@Override
	public String toString() {
		return "MenuDTO [menu_id=" + menu_id + ", menu_type=" + menu_type + ", menu_name=" + menu_name + ", type="
				+ type + ", keyword=" + keyword + "]";
	}
	
}
