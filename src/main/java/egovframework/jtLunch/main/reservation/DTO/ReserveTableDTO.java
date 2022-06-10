package egovframework.jtLunch.main.reservation.DTO;

public class ReserveTableDTO {

	private String no;
	private String reserv_time;
	private String count;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getReserv_time() {
		return reserv_time;
	}
	public void setReserv_time(String reserv_time) {
		this.reserv_time = reserv_time;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "ReserveTableDTO [no=" + no + ", reserv_time=" + reserv_time + ", count=" + count + "]";
	}
	
}
