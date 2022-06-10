package egovframework.jtLunch.admin.dashboard.Service;

import java.util.List;
import java.util.Map;

import egovframework.jtLunch.admin.dashboard.DTO.NoticeDTO;

public interface NoticeService {
	// 전체 공지사항 출력
	//public List<NoticeDTO> NoticePrintAll(PagingDTO vo) throws Exception;
	// 공지사항 전체 개수
	public int CountNotice() throws Exception;
	
	public int NoticeInsert(NoticeDTO dto) throws Exception;
	
	// 공지사항 팝업 설정 reset
	public int popUpReset() throws Exception;
	
	public int NoticeUpdate(NoticeDTO dto) throws Exception;
	
	public int NoticeDelete(int notice_id) throws Exception;
	
	public List<NoticeDTO> NoticePrint() throws Exception;
	
	public int CountUpdate() throws Exception;
	
	public int CountSet() throws Exception;

	//공지사항 입력 id set
	public int CountStartNum(int count) throws Exception;
	
	public NoticeDTO NoticeSelect(int notice_id) throws Exception;
	// 공지사항 검색
	public List<NoticeDTO> NoticeSearch(Map <String, Object> searchData) throws Exception;
	// 공지사항 검색 count
	public int NoticeSearchCount(Map <String, Object> searchData) throws Exception;
	// 팝업 설정한 공지사항 정보 가져오기
	public List<NoticeDTO> NoticePopup(String today) throws Exception;
}
