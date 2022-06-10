package egovframework.jtLunch.cmmn.security.Service;

import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;

public interface UserService {

	void countFailure(String username);

	int checkFailureCount(String username);

	void disabledUsername(String username);

	void resetFailureCnt(String username);

	void updateAccessDate(String username);
	
	void register(CustomUserDetails customuserdetails);
	
	CustomUserDetails selectUser(String id);
}
