package egovframework.jtLunch.admin.owner.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import egovframework.jtLunch.admin.cmmn.GetProperty;
import egovframework.jtLunch.admin.owner.DTO.MenuPlanDTO;
import egovframework.jtLunch.admin.owner.DTO.SideMenuDTO;
import egovframework.jtLunch.admin.owner.Service.OwnerMenuPlanService;

@RestController
@RequestMapping("/diet")
@CrossOrigin(origins="*")
public class OwnerDietShareAPI {
	@Autowired
	private OwnerMenuPlanService ownerMenuPlanService;
	private static final Logger log = LoggerFactory.getLogger(OwnerDietShareAPI.class);

	
	@RequestMapping(value = "/randomData", method = RequestMethod.POST)
	public String randomData(HttpServletRequest req, HttpServletResponse res) throws Exception{
		//Thread.sleep(4000);
		// 프로퍼티 파일에서 체인점 id를 가져오기 위해
		GetProperty gp = new GetProperty();
		// 응답 결과를 담기위해
		JSONObject result = new JSONObject(); 
		BufferedReader br = null;
		try {
			br = req.getReader();
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			
			String jsonData = URLDecoder.decode(sb.toString(),"UTF-8");
			JSONParser jsonParse = new JSONParser();
			Object obj = jsonParse.parse(jsonData);
			JSONObject jsonObj = (JSONObject) obj;
			
			// 전달받은 식단 정보 set
			String dietDate = "";
			dietDate = jsonObj.get("TODAY_DATE").toString();
			MenuPlanDTO menuPlanDTO = new MenuPlanDTO();
			menuPlanDTO.setToday_date(dietDate);
			menuPlanDTO.setSteamed_rice(jsonObj.get("STEAMED_RICE").toString());
			menuPlanDTO.setSoup(jsonObj.get("SOUP").toString());
			
			List<String> selectSideDish = (List<String>) jsonObj.get("SIDE");
			
			log.info("jsonData : " + jsonData);
			log.info("TODAY_DATE : " + menuPlanDTO.getToday_date());
			log.info("STEAMED_RICE : " + menuPlanDTO.getSteamed_rice());
			log.info("SOUP : " + menuPlanDTO.getSoup());
			log.info("SIDE : " + selectSideDish);
			
			result.put("resultMsg", "success");
			result.put("resultDetail", gp.getFranchiseId() + " : Success");
			
			try {
				// 식단 테이블(체인점)에 식단 저장
				ownerMenuPlanService.insertTodayMenu(menuPlanDTO);
				SideMenuDTO sideMenuDTO = new SideMenuDTO();
				for (int i = 0; i < selectSideDish.size(); i++) {
					if (selectSideDish.get(i) != null){
						sideMenuDTO.setMenuplan_date(dietDate);
						sideMenuDTO.setSide_dish(selectSideDish.get(i));
						// 날짜 - 반찬 테이블(뵨사)에 반찬 저장
						ownerMenuPlanService.insertTodayMenuSide(sideMenuDTO);
					}
				}
				log.info("DBInsertResult (" + gp.getFranchiseId() +  "): DB INSERT SUCCESS");
			} catch(Exception e1){
				e1.printStackTrace();
				log.error("DBInsertResult(" + gp.getFranchiseId() +  "): DB INSERT FAIL");
				log.error("DBInsertResultDetail (" + gp.getFranchiseId() +  "): " + e1.getMessage());
			}
		} catch(Exception e) {
			e.printStackTrace();
			log.error("Error reading JSON string (" + gp.getFranchiseId() +  "): " + e.getMessage());

			result.put("resultMsg", "fail");
			result.put("resultDetail", gp.getFranchiseId() + " : Fail");
		} finally {
			if(br != null) {try {br.close();} catch (IOException re) {re.printStackTrace();}}
		}
	    
		return result.toString(); 
	}
}
