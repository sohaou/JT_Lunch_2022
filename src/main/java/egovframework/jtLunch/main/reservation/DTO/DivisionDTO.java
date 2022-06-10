package egovframework.jtLunch.main.reservation.DTO;

public class DivisionDTO {

	private String department;
	private String team;
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
		return "DivisionDTO [department=" + department + ", team=" + team + "]";
	}
	
	
}
