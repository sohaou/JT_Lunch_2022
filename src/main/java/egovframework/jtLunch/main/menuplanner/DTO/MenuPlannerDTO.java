package egovframework.jtLunch.main.menuplanner.DTO;

public class MenuPlannerDTO {
	private String today_date;
	private String steamed_rice;
	private String soup;
	private String side_dish;
	private String menu_name;
	private String select_date;
	public String getSelect_date() {
		return select_date;
	}
	public void setSelect_date(String select_date) {
		this.select_date = select_date;
	}
	/**
	 * @return the steamed_rice
	 */
	public String getSteamed_rice() {
		return steamed_rice;
	}
	/**
	 * @param steamed_rice the steamed_rice to set
	 */
	public void setSteamed_rice(String steamed_rice) {
		this.steamed_rice = steamed_rice;
	}
	/**
	 * @return the soup
	 */
	public String getSoup() {
		return soup;
	}
	/**
	 * @param soup the soup to set
	 */
	public void setSoup(String soup) {
		this.soup = soup;
	}
	/**
	 * @return the side_dish
	 */
	public String getSide_dish() {
		return side_dish;
	}
	/**
	 * @param side_dish the side_dish to set
	 */
	
	public void setSide_dish(String side_dish) {
		this.side_dish = side_dish;
	}
	

	public String getToday_date() {
		return today_date;
	}
	public void setToday_date(String today_date) {
		this.today_date = today_date;
	}
	
	
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	@Override
	public String toString() {
		return "MenuPlannerDTO [today_date=" + today_date + ", steamed_rice=" + steamed_rice + ", soup=" + soup
				+ ", side_dish=" + side_dish + "]";
	}

	
}
