package egovframework.jtLunch.main.qrcode.DTO;

public class QrCodeDTO {
	
	private String res_id;
	private String res_name;
	private String res_date;
	private int res_check;
	private String id;
	private String name;
	private String ate_date;
	private String department;
	private String team;
	private String tel;
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAte_date() {
		return ate_date;
	}
	public void setAte_date(String ate_date) {
		this.ate_date = ate_date;
	}
	public String getRes_id() {
		return res_id;
	}
	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}
	public String getRes_name() {
		return res_name;
	}
	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}
	public String getRes_date() {
		return res_date;
	}
	public void setRes_date(String res_date) {
		this.res_date = res_date;
	}
	public int getRes_check() {
		return res_check;
	}
	public void setRes_check(int res_check) {
		this.res_check = res_check;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "QrCodeDTO [res_id=" + res_id + ", res_name=" + res_name + ", res_date=" + res_date + ", res_check="
				+ res_check + ", id=" + id + ", name=" + name + ", ate_date=" + ate_date + ", department=" + department
				+ ", team=" + team + ", tel=" + tel + "]";
	}
	
	
}
