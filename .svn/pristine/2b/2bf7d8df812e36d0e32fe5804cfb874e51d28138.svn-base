package egovframework.jtLunch.admin.owner.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.owner.DTO.MenuDTO;

@Repository("MenuDAO")
public class MenuDAO {
	@Inject
	SqlSession sqlSession;
	
	//식당 운영자 _ 메뉴 등록(메뉴명 중복 확인)
	public int checkMenu(String menu_name) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("menu.checkMenu",menu_name);
	}	
	//메뉴 등록
	public int MenuInsert(MenuDTO dto) throws Exception{
		return sqlSession.insert("menu.MenuInsert",dto);
	}
	//테이블에 저장된 모든 메뉴 출력
	public List<MenuDTO> printMenuAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("menu.printMenuAll");
	}
	//테이블에 저장된 총 메뉴 수
	public int countMenu() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("menu.countMenu");
	}
	//수정할 메뉴 정보 가져오기
	public MenuDTO getUpdateMenu(String menu_id){
		return sqlSession.selectOne("menu.getUpdateMenu",menu_id);
	}
	//메뉴 수정
	public int UpdateMenu(MenuDTO dto){
		return sqlSession.update("menu.UpdateMenu",dto);
	}
	//메뉴 삭제
	public int DeleteMenu(String menu_id){
		return sqlSession.delete("menu.DeleteMenu",menu_id);
	}
	//식당 운영자 _ 좋아요 수 상위 10개 메뉴 출력 
	public List<MenuDTO> selectLikeCount10() {
	// TODO Auto-generated method stub
	return sqlSession.selectList("menu.selectLikeCount10");
	}
}
