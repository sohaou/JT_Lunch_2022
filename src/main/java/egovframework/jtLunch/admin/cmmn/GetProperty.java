package egovframework.jtLunch.admin.cmmn;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

public class GetProperty {
	private static Logger log = LoggerFactory.getLogger(GetProperty.class);
	// 컨테이너 생성
	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
	// 환경변수 관리 객체 생성
	ConfigurableEnvironment env = ctx.getEnvironment();		
	// 프로퍼티 관리 객체 생성
	MutablePropertySources prop = env.getPropertySources();		
	
	String classpath = "classpath:FranchiseInfo.properties";
	// 프로퍼티 정보 얻기
	// 본사 데이터 전송 URL 가져오기
	public String getHead_Url(){
		// 프로퍼티 관리 객체에 프로퍼티 파일 추가
		try {
			prop.addLast(new ResourcePropertySource(classpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("getHead_Url error : " + e.toString());
		}
		// 본사 ip 주소
		String head_url = env.getProperty("head_URL");
		
		return head_url;
	}
	// 체인점 아이디 가져오기
	public String getFranchiseId(){
		try {
			prop.addLast(new ResourcePropertySource(classpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("getFranchiseId error : " + e.toString());
		}
		// 체인점 아이디
		String franchiseid = env.getProperty("franchiseid");
		
		return franchiseid;
	}
	// 식단을 받는 체인점 주소 가져오기
	public String getFranchiseUrl(String url) {
		try {
			prop.addLast(new ResourcePropertySource(classpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 체인점 식단 데이터 받는 주소
		String franchise_url = env.getProperty("dietSendProtocol") + url + env.getProperty("dietSendUrl");
		
		return franchise_url;
	}
}
