package egovframework.jtLunch.admin.dashboard.Service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.admin.dashboard.DAO.NoticeDAO;
import egovframework.jtLunch.admin.dashboard.DTO.NoticeDTO;
import egovframework.jtLunch.admin.dashboard.Service.NoticeService;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService{
	
	@Resource(name="noticeDAO")
	private NoticeDAO noticeDAO;
	
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
	public int CountUpdate(NoticeDTO dto) throws Exception{
		return noticeDAO.CountUpdate(dto);
	}
	@Override
	public int CountSet() throws Exception{
		return noticeDAO.CountSet();
	}
	@Override
	public NoticeDTO NoticeSelect(int notice_id) throws Exception{
		return noticeDAO.NoticeSelect(notice_id);
	}
	
}