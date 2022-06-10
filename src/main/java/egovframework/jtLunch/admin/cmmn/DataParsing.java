package egovframework.jtLunch.admin.cmmn;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataParsing {
	
	public JSONObject ResponeDataPsrsing(String jsonData) throws ParseException {
		JSONParser jsonParse = new JSONParser();
		Object obj = jsonParse.parse(jsonData);
		JSONObject jsonObj = (JSONObject) obj;
		
		return jsonObj;
	}
}
