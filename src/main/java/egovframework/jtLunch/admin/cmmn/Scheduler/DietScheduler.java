package egovframework.jtLunch.admin.cmmn.Scheduler;

import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.jtLunch.admin.cmmn.APIDataSend;
import egovframework.jtLunch.admin.cmmn.GetProperty;
import egovframework.jtLunch.admin.cmmn.UseDateData;
import egovframework.jtLunch.admin.cmmn.DTO.FranchiseDTO;
import egovframework.jtLunch.admin.cmmn.Service.AdminFranchiseService;
import egovframework.jtLunch.admin.owner.DTO.MenuPlanDTO;
import egovframework.jtLunch.admin.owner.DTO.SideMenuDTO;
import egovframework.jtLunch.admin.owner.Service.MenuService;
import egovframework.jtLunch.admin.owner.Service.OwnerMenuPlanService;
import lombok.extern.slf4j.Slf4j;

// 랜덤 식단 생성 (매일 오후 7시에 다음날 식단 생성)
@Slf4j
public class DietScheduler {
	private static Logger log = LoggerFactory.getLogger(DietScheduler.class);	

	@Autowired
	private MenuService menuService;
	@Autowired
	private OwnerMenuPlanService ownerMenuPlanService;
	@Autowired
	private AdminFranchiseService franchiseService;
	
	// propagaion 옵션 : 트랜잭션이 동작할 때 다른 트랜잭션이 호출되면 어떻게 처리할지를 정하는 옵션
	// propagation.required : default. 부모 트랜잭션 내에서 실행하게 하고 만약 부모 트랜잭션이 없으면 새로운 트랜잭션을 생성하게 하는 설정
	// readonly는 true인 경우 DML(insert, update 등) 실행 시 예외가 발생됨.
	// rollbackFor : 롤백이 되는 클래스를 지정
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, SQLException.class }, readOnly = false)
	public void randomDietScheduler() throws Exception {
		// 트랜잭션이란 데이터베이스의 상태를 변경시키는 작업 또는 한번에 수행되어야하는 연산들을 의미
		// 작업이 끝나면 commit 또는 rollback이 되어야함.
		// 체인점 정보 가져오기
		List<FranchiseDTO> franchiseData = franchiseService.printAllFranchise();
		// 식단 정보
		MenuPlanDTO menuPlanDTO = new MenuPlanDTO();
		// url 정보
		String sendUrl = "";
        // 식단날짜
		UseDateData dateData = new UseDateData();
		String dietDate = dateData.randomDietDate();
        menuPlanDTO.setToday_date(dietDate);
        
        // 전송할 식단 정보를 담기 위해
        JSONObject reqParams = new JSONObject();
        
		// DB 메뉴 테이블에 저장되어 있는 메뉴 : 밥
		List<String> riceMenu = menuService.printMenuType("R");
		// DB 메뉴 테이블에 저장되어 있는 메뉴 : 국
		List<String> soupMenu = menuService.printMenuType("S");
		// DB 메뉴 테이블에 저장되어 있는 메뉴 : 밥
		List<String> sideMenu = menuService.printMenuType("D");
	
		// 식단은 랜덤으로 생성 / 메뉴 구성 : 밥, 국, 반찬 (3-4개)
		menuPlanDTO.setSteamed_rice(riceMenu.get((int)(Math.random() * (riceMenu.size() - 1))));
		menuPlanDTO.setSoup(soupMenu.get((int)(Math.random() * (soupMenu.size() - 1))));
		// 랜덤으로 생성된 반찬을 담기위한 리스트
		// 반찬은 랜덤으로 3~4개 생성
		List<String> selectSideDish = new ArrayList<>();
		// 몇 개의 반찬을 생성할 것인지 (3~4개)
		int sideRandom = (int)(Math.random() * 2) + 3;
		for(int i = 0; i < sideRandom; i++) {
			int randomNum = (int)(Math.random() * (sideMenu.size() - 1));
			selectSideDish.add(sideMenu.get(randomNum));
			sideMenu.remove(randomNum);
		}
		
		// 반찬 정보 (식단 날짜, 반찬 메뉴명)
		SideMenuDTO sideMenuDTO = new SideMenuDTO();
		try {
			// 식단 테이블(본사)에 식단 저장
			ownerMenuPlanService.insertTodayMenu(menuPlanDTO);
			for (int i = 0; i < selectSideDish.size(); i++) {
				if (selectSideDish.get(i) != null){
					sideMenuDTO.setMenuplan_date(dietDate);
					sideMenuDTO.setSide_dish(selectSideDish.get(i));
					// 날짜 - 반찬 테이블(뵨사)에 반찬 저장
					ownerMenuPlanService.insertTodayMenuSide(sideMenuDTO);
					// json 형태로 데이터를 넘기기위해 반찬명 encode
					selectSideDish.set(i, URLEncoder.encode(selectSideDish.get(i),"UTF-8"));
				}
			}
		    // 식단 날짜, 밥, 국, 반찬 put
		    // 밥, 국은 한글이기 때문에 encode
		    reqParams.put("TODAY_DATE", dietDate);
		    reqParams.put("STEAMED_RICE", URLEncoder.encode(menuPlanDTO.getSteamed_rice(),"UTF-8"));
		    reqParams.put("SOUP", URLEncoder.encode(menuPlanDTO.getSoup(),"UTF-8"));
		    reqParams.put("SIDE", selectSideDish);
		    
			
		    // dietSend(삭단정보,전송URL)호출
		    for(int i = 0; i < franchiseData.size(); i++) {
		    	// 데이터 전송 함수 호출
				APIDataSend apidata = new APIDataSend();
				// 프로퍼티 파일에서 데이터 전송 주소와 체인점 id를 가져오기 위해
				GetProperty gp = new GetProperty();
				// 전송 횟수 (3번까지만 재전송하기위해)
		    	int sendCount = 1;
		    	// 전송 URL : 체인점 식단 데이터 전송 URL
		    	sendUrl = gp.getFranchiseUrl(franchiseData.get(i).getDATAURL());
		    	
		    	// 전송 횟수가 3번 이하까지
		    	while(sendCount < 4) {
		    		// 데이터 전송 응답 결과
		    		JSONObject data = apidata.dataSend(reqParams, sendUrl);
		    		// 데이터 전송 결과
					String result = data.get("sendResult") + "";
					
					if(result.equals("success")) {
						//log.info("DietScheduler 데이터 전송 성공");
						//log.info(">>>>>>>>>>success!!");
						log.info(franchiseData.get(i).getDATAURL() + " : DietScheduler 데이터 전송 성공 / "
								+ "데이터 전송 횟수 : " + sendCount);
						break;
					} else if(result.equals("fail")) {
						//log.error(">>>>>>>>>>Fail!!!");
						log.error(franchiseData.get(i).getDATAURL() + "DietScheduler 데이터 전송 실패 / "
								+ "데이터 전송 횟수 : " + sendCount);
						// 전송횟수 ++
						sendCount++;
					}			
					Thread.sleep(20000);
		    	}
		    	// 전송횟수가 3보다 크면 3번 모두 전송 실패한 것
		    	if(sendCount > 3) {
		    		log.error(franchiseData.get(i).getID() + " / " + franchiseData.get(i).getDATAURL() + " : 랜덤 식단 데이터 전송 최종 실패");
		    	}
		    }
		} catch(Exception e){
			e.printStackTrace();
			log.error("DietScheduler : " + e.toString());
		}
	}
}