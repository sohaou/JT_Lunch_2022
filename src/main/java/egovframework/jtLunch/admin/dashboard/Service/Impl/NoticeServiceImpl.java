package egovframework.jtLunch.admin.dashboard.Service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.admin.dashboard.DAO.NoticeDAO;
import egovframework.jtLunch.admin.dashboard.DTO.NoticeDTO;
import egovframework.jtLunch.admin.dashboard.Service.NoticeService;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService{
	
	@Resource(name="noticeDAO")
	private NoticeDAO noticeDAO;
	
	// 공지사항 전체 개수
	@Override
	public int CountNotice() throws Exception{
		return noticeDAO.CountNotice();
	}
	@Override
	public List<NoticeDTO> NoticePrint() throws Exception{
		return noticeDAO.NoticePrint();
	}
	@Override
	public int NoticeDelete(int notice_id) throws Exception{
		return noticeDAO.NoticeDelete(notice_id);
	}
	@Override
	public int NoticeUpdate(NoticeDTO dto) throws Exception{
		return noticeDAO.NoticeUpdate(dto);
	}
	@Override
	public int NoticeInsert(NoticeDTO dto) throws Exception{
		return noticeDAO.NoticeInsert(dto);
	}
	@Override
	public int CountUpdate() throws Exception{
		return noticeDAO.CountUpdate();
	}
	@Override
	public int CountSet() throws Exception{
		return noticeDAO.CountSet();
	}
	//공지사항 입력 id set
	public int CountStartNum(int count) throws Exception{
		return noticeDAO.CountStartNum(count);
	}
	@Override
	public NoticeDTO NoticeSelect(int notice_id) throws Exception{
		return noticeDAO.NoticeSelect(notice_id);
	}
	// 공지사항 검색
	@Override
	public List<NoticeDTO> NoticeSearch(Map <String, Object> searchData) throws Exception{
		return noticeDAO.NoticeSearch(searchData);
	}
	// 공지사항 검색 count
	@Override
	public int NoticeSearchCount(Map <String, Object> searchData) throws Exception{
		return noticeDAO.NoticeSearchCount(searchData);
	}
	// 공지사항 팝업 설정 reset
	@Override
	public int popUpReset() throws Exception{
		return noticeDAO.popUpReset();
	}
	// 팝업 설정한 공지사항 정보 가져오기
	@Override
	public List<NoticeDTO> NoticePopup(String today) throws Exception{
		return noticeDAO.NoticePopup(today);
	}
}