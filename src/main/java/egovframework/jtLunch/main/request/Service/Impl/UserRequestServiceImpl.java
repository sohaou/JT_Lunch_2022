package egovframework.jtLunch.main.request.Service.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.admin.dashboard.DTO.DashBoardDTO;
import egovframework.jtLunch.main.request.DAO.UserRequestDAO;
import egovframework.jtLunch.main.request.DTO.UserRequestDTO;
import egovframework.jtLunch.main.request.Service.UserRequestService;

@Service("UserRequestService")
public class UserRequestServiceImpl implements UserRequestService{
	
	@Inject
	private UserRequestDAO dao;
	
	public List<UserRequestDTO> UserRequestPrintAll() throws Exception{
		return dao.UserRequestPrintAll();
	}
	public int UserRequestInsert(UserRequestDTO dto) throws Exception{
		return dao.UserRequestInsert(dto);
	}
	public UserRequestDTO UserRequestDetail(int req_id) throws Exception{
		return dao.UserRequestDetail(req_id);
	}
	public int UserRequestUpdate(UserRequestDTO dto) throws Exception{
		return dao.UserRequestUpdate(dto);
	}
	public int UserRequestDelete(int req_id) throws Exception{
		return dao.UserRequestDelete(req_id);
	}
	public int UserCountUpdate() throws Exception{
		return dao.UserCountUpdate();
	} 
	public int UserCountSet() throws Exception{
		return dao.UserCountSet();
	}
	public DashBoardDTO UserLoginName(String user_id) throws Exception{
		return dao.UserLoginName(user_id); 
	}
}
