package egovframework.jtLunch.cmmn.security.Service;

import org.apache.log4j.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import egovframework.jtLunch.cmmn.security.DAO.UserAuthDAO;
import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;

public class CustomUserDetailsService implements UserDetailsService {
    
Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private UserAuthDAO userAuthDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.debug("loadUserByUsername ::::::: 2");
		
		//user 검색
		CustomUserDetails user = userAuthDAO.getUserById(username);
		
		if(user==null) { //없으면 exception 던지기
			log.debug("no user :::::::: AuthenticationProvider");
			throw new InternalAuthenticationServiceException(username);
		}
		
		//있으면 CustomUserDetails 에 담아서  리턴
		return user;
	}
 
}