package egovframework.jtLunch.admin.cmmn.Service;

import egovframework.jtLunch.admin.owner.DTO.DateData;

public interface AdminCmmnService {
	public int CountAteTerm(DateData dateData) throws Exception;
	public int CountAteTermTable (String ate_date) throws Exception;

}
