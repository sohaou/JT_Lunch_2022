package egovframework.jtLunch.cmmn.security.DAO;

import org.mybatis.spring.SqlSessionTemplate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;

@Repository("userAuthDAO")
public class UserAuthDAO {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
 
    //로그인 했을 시 권한 검증 USER, ADMIN, MEMBER
    public CustomUserDetails getUserById(String id) {
        return sqlSession.selectOne("login.selectUserById", id);
    }
    
    //로그인 실패 시 실패 횟수 증가
    public void updateFailureCount(String id) {
		sqlSession.update("login.updateFailureCount", id);
	}
    
    //로그인 시도 시 현재 실패 횟수 조회
	public int checkFailureCount(String id) {
		return sqlSession.selectOne("login.checkFailureCount", id);
	}
	
	//로그인 3회 시도 실패 시 아이디 비활성화
	public void updateDisabled(String id) {
		sqlSession.update("login.updateUnenabled", id);
	}

	//로그인 성공하여 실패 횟수 초기화
	public void updateFailureCountReset(String id) {
		sqlSession.update("login.updateFailureCountReset", id);
	}

	//로그인 시간 업데이트
	public void updateNewAccessDate(String id) {
		sqlSession.update("login.updateAccessDate", id);
	}
	
	public void register(CustomUserDetails customuserdetails) {
		sqlSession.insert("login.register", customuserdetails);
	}
	
	public CustomUserDetails selectUser(String id) {
		return sqlSession.selectOne("login.selectUser", id);
	}
}