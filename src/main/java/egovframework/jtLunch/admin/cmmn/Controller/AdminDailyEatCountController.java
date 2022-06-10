package egovframework.jtLunch.admin.cmmn.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;

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

import egovframework.jtLunch.admin.cmmn.DTO.DailyEatCountDTO;
import egovframework.jtLunch.admin.cmmn.DTO.FranchiseDTO;
import egovframework.jtLunch.admin.cmmn.Service.AdminDailyEatCountService;
import egovframework.jtLunch.admin.cmmn.Service.AdminFranchiseService;

@RestController
@RequestMapping("/daily")
@CrossOrigin(origins="*")
public class AdminDailyEatCountController {
	// 체인점으로부터 식사자 수 정보를 받음
	private static final Logger log = LoggerFactory.getLogger(AdminDailyEatCountController.class);
	@Autowired
	private AdminDailyEatCountService service1;
	@Autowired
	private AdminDailyEatCountService HighLevelService;
	@Autowired
	private AdminFranchiseService franchiseService;
	
	@RequestMapping(value="/EatCount", method = RequestMethod.POST)
	public String eatInfo(HttpServletRequest req, HttpServletResponse res) throws Exception{
		// 응답 결과를 담기위해
		JSONObject result = new JSONObject();
		// 전달받은 정보를 담기위해
		DailyEatCountDTO dto = new DailyEatCountDTO();
		BufferedReader br = null;
		log.info("dailyEatCount 호출");
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
			
			// 전달받은 식사자 수 정보 : 체인점 id
			String id = jsonObj.get("FRANCHISE_ID").toString();
			// 전달받은 식사자 수 정보 : 식사 날짜
			String eatDate = jsonObj.get("EAT_DATE").toString();
			// 전달받은 식사자 수 정보 : 해당 날짜의 식사자 수
			String count_eat = jsonObj.get("COUNT_EAT").toString();
			
			// 전달받은 정보 SET
			dto.setFRANCHISE_ID(id);
			dto.setEAT_DATE(eatDate);
			dto.setCOUNT_EAT(Integer.parseInt(count_eat));
			
			log.info("JSONData : " + jsonData);
			log.info("COUNT_EAT : " + count_eat);
			log.info("id : " + id);
			log.info("eatDate : " + eatDate);
			
			result.put("resultMsg", "success");
			
			log.info("result Data : " + result);
			
			if(!id.equals("admin")) {
				// 체인점 정보 가져오기
				FranchiseDTO fdto = franchiseService.franchiseDetail(id);
				// 체인점 일반/고급 분류
				if(fdto.getTYPE() == 0) {
					// 일반 체인점인 경우
					service1.InsertdailyEatCount(dto);
				} else if(fdto.getTYPE() == 1) {
					// 고급 체인점인 경우
					HighLevelService.InsertdailyEatCount(dto);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			
			result.put("resultMsg", "fail");
		} finally {
			if(br != null) {try {br.close();} catch (IOException re) {re.printStackTrace();}}
		}
		
		return result.toString();
	}
}
