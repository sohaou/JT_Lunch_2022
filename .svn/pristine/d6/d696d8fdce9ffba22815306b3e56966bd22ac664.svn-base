package egovframework.jtLunch.main.menuplanner.Service.Impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.main.menuplanner.DAO.MenuPlannerDAO;
import egovframework.jtLunch.main.menuplanner.DTO.MenuPlannerDTO;
import egovframework.jtLunch.main.menuplanner.Service.MenuPlannerService;

@Service("menuService")
public class MenuPlannerServiceImpl implements MenuPlannerService{
	
	@Inject
	private MenuPlannerDAO menuplannerDAO;
	
	@Override
	public MenuPlannerDTO selectmenu() {
		return menuplannerDAO.selectmenu();
	}

	@Override
	public int likecountrice(String steamed_rice) {
		return menuplannerDAO.likecountrice(steamed_rice);
	}

	@Override
	public int likecountsoup(String soup) {
		return menuplannerDAO.likecountsoup(soup);
	}

	@Override
	public int likecountsidedish(String side_dish) {
		return menuplannerDAO.likecountsidedish(side_dish);
	}

}
