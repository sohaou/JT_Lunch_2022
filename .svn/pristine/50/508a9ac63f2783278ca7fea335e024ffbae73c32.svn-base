package egovframework.jtLunch.admin.owner.Service;

import java.util.List;

import egovframework.jtLunch.admin.owner.DTO.MenuDTO;

public interface MenuService {
	//식당 운영자 _ 메뉴 등록(메뉴명 중복 확인)
	public int checkMenu(String menu_name) throws Exception;
	//메뉴등록
	public int MenuInsert(MenuDTO dto) throws Exception; 
	//테이블에 저장된 모든 메뉴 출력	
	public List<MenuDTO> printMenuAll() throws Exception;
	//수정할 메뉴 정보 가져오기
	public MenuDTO getUpdateMenu(String menu_id) throws Exception;
	//메뉴 수정
	public int UpdateMenu(MenuDTO dto) throws Exception; 
	//메뉴 삭제
	public int DeleteMenu(String menu_id) throws Exception; 
	//식당운영자 _ 좋아요 수 상위 10개 메뉴 출력
	public List<MenuDTO> selectLikeCount10() throws Exception; 
}
