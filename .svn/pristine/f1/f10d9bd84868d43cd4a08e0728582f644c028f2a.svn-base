package egovframework.jtLunch.cmmn.join.Service.Impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.cmmn.join.DAO.JoinDAO;
import egovframework.jtLunch.cmmn.join.Service.JoinService;
import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;

@Service("joinService")
public class JoinServiceImpl implements JoinService{
	
	@Resource(name="joinDAO")
	private JoinDAO joinDAO;
	
	@Override
	public void register(CustomUserDetails customuserdetails) {
		joinDAO.register(customuserdetails);
	}

	@Override
	public Map<String,Object>  checkID(Map<String,Object> requestMap) {
		return joinDAO.checkID(requestMap);
	}

}
