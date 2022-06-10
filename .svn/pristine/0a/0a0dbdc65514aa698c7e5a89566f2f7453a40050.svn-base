package egovframework.jtLunch.admin.owner.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.dashboard.DTO.NoticeDTO;

@Repository("OwnerNoticeDAO")
public class OwnerNoticeDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<NoticeDTO> OwnerNoticePrint() throws Exception{
		return sqlSession.selectList("ownerNotice.OwnerNoticePrint");
	}
	public int OwnerNoticeInsert(NoticeDTO dto) throws Exception{
		return sqlSession.insert("ownerNotice.OwnerNoticeInsert", dto);
	}
	public NoticeDTO OwnerNoticeDetail(int notice_id) throws Exception{
		return sqlSession.selectOne("ownerNotice.OwnerNoticeDetail", notice_id);
	}
	public int OwnerNoticeUpdate(NoticeDTO dto) throws Exception{
		return sqlSession.update("ownerNotice.OwnerNoticeUpdate", dto);
	}
	public int OwnerNoticeDelete(int notice_id) throws Exception{	
		return sqlSession.delete("ownerNotice.OwnerNoticeDelete", notice_id);
	}
	public int OwnerCountUpdate() throws Exception{	
		return sqlSession.update("ownerNotice.OwnerCountUpdate");
	}
	public int OwnerCountSet() throws Exception{
		return sqlSession.update("ownerNotice.OwnerCountSet");
	}
}
