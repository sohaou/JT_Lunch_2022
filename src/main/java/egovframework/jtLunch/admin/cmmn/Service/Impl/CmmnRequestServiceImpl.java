package egovframework.jtLunch.admin.cmmn.Service.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.admin.cmmn.DAO.CmmnRequestDAO;
import egovframework.jtLunch.admin.cmmn.DTO.CmmnRequestDTO;
import egovframework.jtLunch.admin.cmmn.Service.CmmnRequestService;


@Service("CmmnRequestService")
public class CmmnRequestServiceImpl implements CmmnRequestService{
	@Inject
	private CmmnRequestDAO dao;
	
	//요청사항 출력
	public List<CmmnRequestDTO> printRequestAll()throws Exception{ 
		return dao.printRequestAll();
	}
	//요청사항상세보기
	public CmmnRequestDTO selectRequest(String req_id)throws Exception{
		return dao.selectRequest(req_id);
	}
	//식당운영자 요청사항 확인 업데이트
	public int ownerCheck(String req_id) throws Exception{
		return dao.ownerCheck(req_id);
	}
	//관리자 요청사항 확인 업데이트
	public int adminCheck(String req_id) throws Exception{
		return dao.adminCheck(req_id);
	}
}
