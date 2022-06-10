package egovframework.jtLunch.cmmn.security.Auth;

import java.util.Collection;


import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import egovframework.jtLunch.cmmn.security.Service.UserService;
import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    
	//로그찍고 싶어서 넣었음
	Logger log = Logger.getLogger(getClass());
	
	@Inject
	private BCryptPasswordEncoder pwdEncoder;
	
	//서비스 선언
	@Resource(name="userSer")
	private UserService userSer;
	
	//시큐리티 커스텀 서비스 선언
    @Autowired
    private UserDetailsService userDeSer;
 
    
    @SuppressWarnings("unchecked") //이 어노테이션이 쓰여진 메소드는 경고처리하지마 라는 뜻
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String customid = (String) authentication.getPrincipal(); //id 받아와 customid 저장
		String custompw = (String) authentication.getCredentials(); //pw 받아와 custompw 저장
		
		log.debug("AuthenticationProvider :::::: 1"); //흘러가는 순서 1 > 2 > 3 차례
		
		CustomUserDetails user = (CustomUserDetails) userDeSer.loadUserByUsername(customid);
		
		if(!user.isEnabled() || !user.isCredentialsNonExpired()) { //계정 활성화여부 및 자격증명이 유효한지
			log.debug("isEnabled or isCredentialsNonExpired :::::::: false!");
			throw new AuthenticationCredentialsNotFoundException(customid);
		}
		
		//계정이 갖고있는 권한 목록 얻기
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user.getAuthorities();
		
		log.debug("AuthenticationProvider loadUserByUsername :::::: 3");
		
		//비밀번호 매치 method
		if(!matchPassword(custompw, user.getPassword())) {
			log.debug("custompw : " + custompw);
			log.debug("getPassword : " + user.getPassword());
			log.debug("matchPassword :::::::: false!");
			throw new BadCredentialsException(custompw); //비밀번호가 다르면 exception
		}
		log.debug("matchPassword :::::::: true!");
		
		//인증이 모두 잘 완료되면 UsernamePasswordAuthenticationToken 발급
		return new UsernamePasswordAuthenticationToken(customid, custompw, authorities);
	}
 
    //뭐하는지 모르겟는 메소드
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
    
    //비밀번호 비교
    private boolean matchPassword(String loginPwd, String PASSWORD) {
    	
    	boolean pwMath;
    	log.debug(loginPwd);
    	log.debug(PASSWORD);
    	log.debug("암호화된 비밀번호 입력 매치      : " + pwdEncoder.matches(loginPwd, PASSWORD));
    	log.debug("암호화되지 않은 비밀번호 입력 매치     : " + loginPwd.equals(PASSWORD));
    	if(pwdEncoder.matches(loginPwd, PASSWORD)) {
    		pwMath = true;
    	}else if(loginPwd.equals(PASSWORD)) {
    		pwMath = true;
    	}else {
    		pwMath = false;
    	}
        return pwMath;
    }
    
    
}