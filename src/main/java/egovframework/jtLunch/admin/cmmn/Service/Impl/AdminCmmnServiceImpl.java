package egovframework.jtLunch.admin.cmmn.Service.Impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.admin.cmmn.DAO.AdminCmmnDAO;
import egovframework.jtLunch.admin.cmmn.Service.AdminCmmnService;
import egovframework.jtLunch.admin.owner.DTO.DateData;

@Service("AdminCmmnService")
public class AdminCmmnServiceImpl implements AdminCmmnService{
	@Inject
	private AdminCmmnDAO dao;
	
	@Override
	public int CountAteTerm(DateData dateData) throws Exception{
		return dao.CountAteTerm(dateData);
	}
	@Override
	public int CountAteTermTable(String ate_date) throws Exception{
		return dao.CountAteTermTable(ate_date);
	}
}
