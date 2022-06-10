package egovframework.jtLunch.admin.owner.Service.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.admin.owner.DAO.MenuDAO;
import egovframework.jtLunch.admin.owner.DTO.MenuDTO;
import egovframework.jtLunch.admin.owner.Service.MenuService;

@Service("MenuService")
public class MenuServiceImpl implements MenuService{
	@Inject
	private MenuDAO dao;
	
	//식당 운영자 _ 메뉴 등록(메뉴명 중복 확인)
	@Override
	public int checkMenu(String menu_name) throws Exception{
	return dao.checkMenu(menu_name);
	}	
	//메뉴 등록
	@Override
	public int MenuInsert(MenuDTO dto) throws Exception{ 
		return dao.MenuInsert(dto);
	}
	//테이블에 저장된 모든 메뉴 출력
	@Override
	public List<MenuDTO> printMenuAll() throws Exception{
		return dao.printMenuAll();
	}
	//수정할 메뉴 정보 가져오기
	@Override
	public MenuDTO getUpdateMenu(String menu_id) throws Exception{ 
		return dao.getUpdateMenu(menu_id);
	}
	//메뉴 수정
	@Override
	public int UpdateMenu(MenuDTO dto) throws Exception{ 
		return dao.UpdateMenu(dto);
	}
	//메뉴 삭제
	@Override
	public int DeleteMenu(String menu_id) throws Exception{ 
		return dao.DeleteMenu(menu_id);
	}
	//식당운영자_좋아요수 상위 10개 메뉴 출력
	@Override
	public List<MenuDTO> selectLikeCount10() throws Exception{ 
		return dao.selectLikeCount10();
	}
}
