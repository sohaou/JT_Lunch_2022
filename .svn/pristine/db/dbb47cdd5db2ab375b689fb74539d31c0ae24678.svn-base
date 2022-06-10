package egovframework.jtLunch.cmmn.security.Service.Impl;

import javax.annotation.Resource;



import org.springframework.stereotype.Service;

import egovframework.jtLunch.cmmn.security.DAO.UserAuthDAO;
import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;
import egovframework.jtLunch.cmmn.security.Service.UserService;

@Service("userSer")
public class UserServiceImpl  implements UserService{
	
	@Resource(name="userAuthDAO")
	private UserAuthDAO userDAO;
	
	@Override
	public void countFailure(String username) {
		userDAO.updateFailureCount(username);
	}

	@Override
	public int checkFailureCount(String username) {
		return userDAO.checkFailureCount(username);
	}

	@Override
	public void disabledUsername(String username) {
		userDAO.updateDisabled(username);
	}

	@Override
	public void resetFailureCnt(String username) {
		userDAO.updateFailureCountReset(username);
	}

	@Override
	public void updateAccessDate(String username) {
		userDAO.updateNewAccessDate(username);
	}

	@Override
	public void register(CustomUserDetails customuserdetails) {
		userDAO.register(customuserdetails);		
	}
	
	@Override
	public CustomUserDetails selectUser(String id) {
		return userDAO.selectUser(id);
	}
	
}
