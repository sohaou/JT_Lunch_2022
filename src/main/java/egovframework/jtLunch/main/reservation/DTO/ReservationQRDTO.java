package egovframework.jtLunch.main.reservation.DTO;

public class ReservationQRDTO {
	private String id;
	private String name;
	private String ate_date;
	private String department;
	private String team;
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
	@Override
	public String toString() {
		return "ReservationQRDTO [id=" + id + ", name=" + name + ", ate_date=" + ate_date + ", department=" + department
				+ ", team=" + team + "]";
	}
	
	
}
