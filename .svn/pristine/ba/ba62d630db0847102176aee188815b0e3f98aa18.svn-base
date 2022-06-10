package egovframework.jtLunch.admin.dashboard.Service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.admin.dashboard.DAO.DashBoardDAO;
import egovframework.jtLunch.admin.dashboard.DTO.DashBoardDTO;
import egovframework.jtLunch.admin.dashboard.Service.DashBoardService;

@Service("dashBoardService")
public class DashBoardServiceImpl implements DashBoardService{
	
	@Resource(name="dashBoardDAO")
	private DashBoardDAO dashBoardDAO;
	
	@Override
	public DashBoardDTO getChartData() throws Exception{
		return dashBoardDAO.getChartData();
	}
	@Override
	public DashBoardDTO getChartMonth() throws Exception{
		return dashBoardDAO.getChartMonth();
	}
	@Override
	public int getmonthAte() throws Exception{
		return dashBoardDAO.getmonthAte();
	}
	@Override
	public List<DashBoardDTO> searchUser() throws Exception{
		return dashBoardDAO.searchUser();
	}
	@Override
	public int deleteUser(String user_id) throws Exception{
		return dashBoardDAO.deleteUser(user_id);
	}
	@Override
	public List<DashBoardDTO> printAteUser(DashBoardDTO dto) throws Exception{
		return dashBoardDAO.printAteUser(dto);
	}
	@Override
	public int userInsert(DashBoardDTO dto) throws Exception{
		return dashBoardDAO.userInsert(dto);
	}

}

