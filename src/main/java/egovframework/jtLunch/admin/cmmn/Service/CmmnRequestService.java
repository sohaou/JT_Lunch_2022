package egovframework.jtLunch.admin.cmmn.Service;

import java.util.List;

import egovframework.jtLunch.admin.cmmn.DTO.CmmnRequestDTO;

public interface CmmnRequestService {
	//요청사항출력
	public List<CmmnRequestDTO> printRequestAll()throws Exception;
	//요청사항상세보기
	public CmmnRequestDTO selectRequest(String req_id)throws Exception;
	//식당운영자 요청사항 확인 업데이트
	public int ownerCheck(String req_id) throws Exception;
	//관리자 요청사항 확인 업데이트
	public int adminCheck(String req_id) throws Exception;
}
