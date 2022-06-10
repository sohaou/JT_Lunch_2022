package egovframework.jtLunch.admin.owner.Service;

import java.util.List;

import egovframework.jtLunch.admin.dashboard.DTO.NoticeDTO;

public interface OwnerNoticeService {
	//식당운영자 공지사항 등록
	public int OwnerNoticeInsert(NoticeDTO dto) throws Exception;
	//공지사항 모두 출력
	public List<NoticeDTO> OwnerNoticePrint() throws Exception;
	//공지사항 수정 정보 가져오기
	public NoticeDTO OwnerNoticeDetail(int notice_id) throws Exception;
	//공지사항 수정
	public int OwnerNoticeUpdate(NoticeDTO dto) throws Exception;
	//공지사항 삭제
	public int OwnerNoticeDelete(int notice_id) throws Exception;
	//공지사항 삭제 후 notice_id 재정렬
	public int OwnerCountUpdate() throws Exception;
	//공지사항 삭제 후 notice_id 재정렬_2
	public int OwnerCountSet() throws Exception;
	
}
