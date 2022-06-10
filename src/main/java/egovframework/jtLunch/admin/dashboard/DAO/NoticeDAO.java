package egovframework.jtLunch.admin.dashboard.DAO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.dashboard.DTO.NoticeDTO;

@Repository("noticeDAO")
public class NoticeDAO {
	
	@Autowired
	private SqlSession sqlSession;
	//공지사항 전체 수
	public int CountNotice() throws Exception{
		return sqlSession.selectOne("notice.CountNotice");
	}
	//공지사항 출력
	public List<NoticeDTO> NoticePrint() throws Exception{
		return sqlSession.selectList("notice.NoticePrint");
	}
	//공지사항 팝업설정 reset
	public int popUpReset() throws Exception{
		return sqlSession.update("notice.popUpReset");
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
	public int CountUpdate() throws Exception{
		return sqlSession.update("notice.CountUpdate");
	}
	//공지사항 출력번호 정렬
	public int CountSet() throws Exception{
		return sqlSession.update("notice.CountSet");
	}
	//공지사항 입력 id set
	public int CountStartNum(int count) throws Exception{
		return sqlSession.update("notice.CountStartNum",count);
	}
	//공지사항 상세보기
	public NoticeDTO NoticeSelect(int notice_id) throws Exception{
		return sqlSession.selectOne("notice.NoticeSelect", notice_id);
	}
	//공지사항 검색
	public List<NoticeDTO> NoticeSearch(Map <String, Object> searchData) throws Exception{
		return sqlSession.selectList("notice.NoticeSearch", searchData);
	}
	//공지사항 검색 count
	public int NoticeSearchCount(Map <String, Object> searchData) throws Exception{
		return sqlSession.selectOne("notice.NoticeSearchCount", searchData);
	}
	//팝업설정한 공지사항 정보 가져오기
	public List<NoticeDTO> NoticePopup(String today) throws Exception{
		return sqlSession.selectList("notice.NoticePopup", today);
	}
}
