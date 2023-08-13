package db;

import java.net.URL; 
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class WifiMain {
    public static void main(String[] args) {
    	JsonTest jt = new JsonTest();
    	
    	
    	
    	String key = "634b71424867756e3139446479574b";
    	String wifiUrl = "http://openapi.seoul.go.kr:8088/key/json/TbPublicWifiInfo/";
    	
    	URL url = null;
    	try {
    		url = new URL(wifiUrl);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	System.out.println(key);
    }
}