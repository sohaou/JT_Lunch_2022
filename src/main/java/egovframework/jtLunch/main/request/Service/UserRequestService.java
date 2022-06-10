package egovframework.jtLunch.main.request.Service;

import java.util.List;

import egovframework.jtLunch.admin.dashboard.DTO.DashBoardDTO;
import egovframework.jtLunch.main.request.DTO.UserRequestDTO;

public interface UserRequestService {
	
	public List<UserRequestDTO> UserRequestPrintAll() throws Exception;
	public int UserRequestInsert(UserRequestDTO dto) throws Exception;
	public UserRequestDTO UserRequestDetail(int req_id) throws Exception;
	public int UserRequestUpdate(UserRequestDTO dto) throws Exception;
	public int UserRequestDelete(int req_id) throws Exception;
	public int UserCountUpdate() throws Exception;
	public int UserCountSet() throws Exception;
	public DashBoardDTO UserLoginName(String user_id) throws Exception;
}
