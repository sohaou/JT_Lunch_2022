package egovframework.jtLunch.cmmn.join.DAO;

import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;

@Repository("joinDAO")
public class JoinDAO {
	
	@Inject
	private SqlSessionTemplate sqlSession;
	
	public void register(CustomUserDetails customuserdetails) {
		sqlSession.insert("join.register", customuserdetails);
	}
	
	public Map<String, Object> checkID(Map<String,Object> requestMap) {
		return sqlSession.selectOne("join.checkID", requestMap);
	}
}
