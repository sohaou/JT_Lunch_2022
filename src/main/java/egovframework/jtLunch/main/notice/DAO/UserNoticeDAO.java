package egovframework.jtLunch.main.notice.DAO;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.main.notice.DTO.UserNoticeDTO;

@Repository("UserNoticeDAO")
public class UserNoticeDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<UserNoticeDTO> UserNoticePrint() throws Exception{
		return sqlSession.selectList("userNotice.UserNoticePrint");
	}
	
	public UserNoticeDTO UserNoticeDetail(int notice_id) throws Exception{
		return sqlSession.selectOne("userNotice.UserNoticeDetail", notice_id);
	}
	
	public int ViewUpdate(int notice_id) throws Exception{
		return sqlSession.update("userNotice.ViewUpdate", notice_id);
	}
}
