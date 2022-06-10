package egovframework.jtLunch.admin.cmmn.Scheduler;

import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.jtLunch.admin.cmmn.APIDataSend;
import egovframework.jtLunch.admin.cmmn.GetProperty;
import egovframework.jtLunch.admin.cmmn.UseDateData;
import egovframework.jtLunch.admin.owner.Service.CheckAteUserService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
// 금일 식사자 정보 본사에 전송
public class FinishEatInfoScheduler {
	private static Logger log = LoggerFactory.getLogger(FinishEatInfoScheduler.class);
	@Autowired
	private CheckAteUserService checkAteUserService;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, SQLException.class }, readOnly = false)
	public void FinishEatInfoScheduler() throws Exception {
		// 식사 날짜
		String eatDate = new UseDateData().getToday();
		// 프로퍼티 파일에서 데이터 전송 주소와 체인점 id를 가져오기 위해
		GetProperty gp = new GetProperty();
		// 프로퍼티 정보 얻기
		// 본사 ip 주소
		String head_url = gp.getHead_Url();
		// 체인점 아이디
		String franchiseid = gp.getFranchiseId();
		// 식사자 수
		int countTodayEat = checkAteUserService.countTodayEat(eatDate, franchiseid);
		// 본사에 전송할 데이터를 담기위해
		JSONObject reqParams = new JSONObject();
		
		reqParams.put("FRANCHISE_ID", franchiseid);
		reqParams.put("EAT_DATE", eatDate);
		reqParams.put("COUNT_EAT", countTodayEat + "");
		
		APIDataSend apidata = new APIDataSend();
		
    	int count = 1;
    	
    	if(!franchiseid.equals("admin")) {
    		while(count < 4) {
        		JSONObject data = apidata.dataSend(reqParams, head_url);
        		// 데이터 전송 결과
    			String result = data.get("sendResult") + "";
    			
    			if(result.equals("success")) {
    				log.info(franchiseid + " : finishEatInfoScheduler 데이터 전송 성공! / "
    						+ "데이터 전송 횟수 : " + count);
    				break;
    			} else if(result.equals("fail")) {
    				log.error(franchiseid + " : finishEatInfoScheduler 데이터 전송 실패 / "
    						+ "데이터 전송 횟수 : " + count);
    				// 식사자 수 정보 재전송
    				count++;
    			}			
    			Thread.sleep(20000);
        	}	
    	}
	}
}
