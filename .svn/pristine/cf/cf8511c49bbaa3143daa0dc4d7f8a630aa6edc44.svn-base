package egovframework.jtLunch.admin.dashboard.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.dashboard.DTO.NoticeDTO;

@Repository("noticeDAO")
public class NoticeDAO {
	
	@Autowired
	private SqlSession sqlSession;
	//공지사항 출력
	public List<NoticeDTO> NoticePrint() throws Exception{
		return sqlSession.selectList("notice.NoticePrint");
	}
	//공지사항 삭제
	public int NoticeDelete(int notice_id) throws Exception{
		return sqlSession.delete("notice.NoticeDelete", notice_id);
	}
	//공지사항 수정
	public int NoticeUpdate(NoticeDTO dto) throws Exception{
		return sqlSession.update("notice.NoticeUpdate", dto);
	}
	//공지사항 등록
	public int NoticeInsert(NoticeDTO dto) throws Exception{
		return sqlSession.insert("notice.NoticeInsert", dto);
	}
	//공지사항 출력번호 정렬
	public int CountUpdate(NoticeDTO dto) throws Exception{
		return sqlSession.update("notice.CountUpdate", dto);
	}
	//공지사항 출력번호 정렬
	public int CountSet() throws Exception{
		return sqlSession.update("notice.CountSet");
	}
	//공지사항 상세보기
	public NoticeDTO NoticeSelect(int notice_id) throws Exception{
		return sqlSession.selectOne("notice.NoticeSelect", notice_id);
	}
	
}
