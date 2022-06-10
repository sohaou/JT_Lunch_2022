package egovframework.jtLunch.admin.cmmn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 랜덤 식단 데이터(본사) 및 일간 식사자 수(체인점) 전송
public class APIDataSend {
	private static Logger log = LoggerFactory.getLogger(APIDataSend.class);
	
	public JSONObject dataSend(JSONObject reqParams, String Sendurl) {
		HttpURLConnection conn = null;
		URL url;
		BufferedWriter bw = null;
		BufferedReader br = null;
		// 응답 결과를 담기위해
		JSONObject jsonObj = new JSONObject();
		// 전송 응답 결과 (성공 / 실패)
		String returnData = null;
		
		try {
			// api url
			url = new URL(Sendurl);
			// HttpURLConnection 객체 생성
		    conn = (HttpURLConnection) url.openConnection();
		    // 요청 방식 (기본값은 GET)
		    conn.setRequestMethod("POST");
		    // 타입설졍(application/json) 형식으로 전송
		    conn.setRequestProperty("Content-Type", "application/json");
		    conn.setRequestProperty("Accept-Charset", "UTF-8");
		    // 서버 연결 제한 시간 : 2초
		    conn.setConnectTimeout(2000);
		    // 서버 연결 후 데이터 read 제한 시간 : 2초
		    conn.setReadTimeout(2000);
		    // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션
		    conn.setDoOutput(true);
		    // InputStream으로 서버로부터 응답을 받겠다는 옵션
		    conn.setDoInput(true);
		    // 캐시에 저장된 결과가 아닌 동적으로 그 순간에 생성된 결과를 읽도록
		    conn.setUseCaches(false);  
		    // POST	방식으로 String을 통한 json 전송
		    // conn.getOutputStream() : 해당 URL에 대한 출력 스트림을 구함.
		    // OutputStreamWriter(스트림, 인코딩) : 문자 스트림에서 바이트 스트림으로 변환
		    bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		    // 출력
		    bw.write(reqParams.toString());
		    // 남아있는 데이터를 모두 출력시킴
		    bw.flush();
		    // 종료(스트림 닫음)
		    bw.close();
		    log.info("전송 데이터 : " + URLDecoder.decode(reqParams.toString(),"UTF-8"));
		    log.info("데이터 전송 완료");
		    
		    // HTTP 응답 코드 수신
		    int responseCode = conn.getResponseCode();
		    if(responseCode == 200) {
		    	// 정상일 때
		    	log.info("정보 전달 응답코드 : " + responseCode);
		    	// 서버에서 보낸 응답 데이터 수신 받기
		    	// 예외처리 필수
			    try {
			    	br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			    	StringBuilder sb = new StringBuilder();
				    String line = null;
				    
				    while((line = br.readLine()) != null) {
				    	sb.append(line);
				    }
				    // 데이터 파싱하는 메서드 호출
				    DataParsing dp = new DataParsing();
					jsonObj = dp.ResponeDataPsrsing(URLDecoder.decode(sb.toString(),"UTF-8"));
					// 응답결과가 성공이면
					if(jsonObj.get("resultMsg").equals("success")) {
						returnData = "success";
					} else{
						// 그 외 경우는 실패
						returnData = "fail";
					}
			    } catch(IOException e) {
			    	e.printStackTrace();
			    }
		    } else { 
		    	// 오류일 때
		    	returnData = "fail";
		    	log.error("정보 전송 응답 오류 :" + responseCode);
		    	log.error("정보 전송 오류 내용 : " + conn.getResponseMessage());
		    }
		} catch(Exception e) {
			log.error("오류 발생 : " + e.toString());
			//jsonObj.put("sendResult", "fail");
			returnData = "fail";
		} finally {
			try {
				if(conn != null){conn.disconnect();}
				if(bw != null){bw.close();}
				if(br != null){br.close();}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		jsonObj.put("sendResult", returnData);
		
		return jsonObj;
	}
}
