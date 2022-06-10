package egovframework.jtLunch.cmmn.join.Service;

import java.util.Map;

import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;

public interface JoinService {
	
	void register(CustomUserDetails customuserdetails);
	
	Map<String, Object> checkID(Map<String,Object> requestMap);
}
