package egovframework.jtLunch.main.notice.Service.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.main.notice.DAO.UserNoticeDAO;
import egovframework.jtLunch.main.notice.DTO.UserNoticeDTO;
import egovframework.jtLunch.main.notice.Service.UserNoticeService;

@Service("UserNoticeService")
public class UserNoticeServiceImpl implements UserNoticeService{
	
	@Inject
	private UserNoticeDAO dao;
	
	public List<UserNoticeDTO> UserNoticePrint() throws Exception{
		return dao.UserNoticePrint();
	}
	public UserNoticeDTO UserNoticeDetail(int notice_id) throws Exception{
		return dao.UserNoticeDetail(notice_id);
	}
	public int ViewUpdate(int notice_id) throws Exception{
		return dao.ViewUpdate(notice_id);
	}
}