package egovframework.jtLunch.admin.dashboard.DTO;

public class NoticeDTO {
	
	private int notice_id; // 공지사항 번호
	private String title; // 제목
	private String content; // 내용
	private String writer; // 작성자
	private String up_date; // 등록일
	private int user_check; // 조회수
	private String editor; // 수정자
	private String edit_time; // 수정일
	private int pop_up; // 팝업 설정 여부
	private int noticeId; // 공지사항 번호(grid에 _사용 x이기 때문에 선언)
	private String uploadDate; // 등록일(grid에 _사용 x이기 때문에 선언)
	private int userCheck; // 조회수(grid에 _사용 x이기 때문에 선언)
	private String editTime; // 공지사항 수정일
	private String pop_up_start_date; // 팝업 설정 시작 날짜
	private String pop_up_end_date;	// 팝업 설정 끝 날짜
	
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getUp_date() {
		return up_date;
	}
	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}
	public int getUser_check() {
		return user_check;
	}
	public void setUser_check(int user_check) {
		this.user_check = user_check;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getEdit_time() {
		return edit_time;
	}
	public void setEdit_time(String edit_time) {
		this.edit_time = edit_time;
	}
	public int getPop_up() {
		return pop_up;
	}
	public void setPop_up(int pop_up) {
		this.pop_up = pop_up;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public int getUserCheck() {
		return userCheck;
	}
	public void setUserCheck(int userCheck) {
		this.userCheck = userCheck;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	public String getPop_up_start_date() {
		return pop_up_start_date;
	}
	public void setPop_up_start_date(String pop_up_start_date) {
		this.pop_up_start_date = pop_up_start_date;
	}
	public String getPop_up_end_date() {
		return pop_up_end_date;
	}
	public void setPop_up_end_date(String pop_up_end_date) {
		this.pop_up_end_date = pop_up_end_date;
	}
	@Override
	public String toString() {
		return "NoticeDTO [notice_id=" + notice_id + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", up_date=" + up_date + ", user_check=" + user_check + ", editor=" + editor + ", edit_time="
				+ edit_time + ", pop_up=" + pop_up + ", noticeId=" + noticeId + ", uploadDate=" + uploadDate
				+ ", userCheck=" + userCheck + ", editTime=" + editTime + ", pop_up_start_date=" + pop_up_start_date
				+ ", pop_up_end_date=" + pop_up_end_date + "]";
	}
	
}
