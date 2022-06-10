package egovframework.jtLunch.admin.owner.Service.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.admin.dashboard.DTO.NoticeDTO;
import egovframework.jtLunch.admin.owner.DAO.OwnerNoticeDAO;
import egovframework.jtLunch.admin.owner.Service.OwnerNoticeService;

@Service("OwnerNoticeService")
public class OwnerNoticeServiceImpl implements OwnerNoticeService{
	@Inject
	private OwnerNoticeDAO dao;
	
	//식당운영자 공지사항 등록
	public int OwnerNoticeInsert(NoticeDTO dto) throws Exception{
		return dao.OwnerNoticeInsert(dto);
	}
	//공지사항 모두 출력
	public List<NoticeDTO> OwnerNoticePrint() throws Exception{
		return dao.OwnerNoticePrint();
	}
	//공지사항 수정 정보 가져오기
	public NoticeDTO OwnerNoticeDetail(int notice_id) throws Exception{
		return dao.OwnerNoticeDetail(notice_id);
	}
	//공지사항 수정
	public int OwnerNoticeUpdate(NoticeDTO dto) throws Exception{
		return dao.OwnerNoticeUpdate(dto);
	}
	//공지사항 삭제
	public int OwnerNoticeDelete(int notice_id) throws Exception{	
		return dao.OwnerNoticeDelete(notice_id);
	}
	//공지사항 삭제 후 notice_id 재정렬
	public int OwnerCountUpdate() throws Exception{
		return dao.OwnerCountUpdate();
	}
	//공지사항 삭제 후 notice_id 재정렬_2
	public int OwnerCountSet() throws Exception{
		return dao.OwnerCountSet();
	}
}
