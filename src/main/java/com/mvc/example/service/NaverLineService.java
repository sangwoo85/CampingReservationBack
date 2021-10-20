package com.mvc.example.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mvc.example.data.TokenVo;

@Service
public class NaverLineService {

	private static final Logger logger = LoggerFactory.getLogger(NaverLineService.class);

	NaverLineService(){
		logger.info("NaverLineService START Message");
		String message = "서버 재시작  알람 전송 확인  Message";
		//this.sendLineNotify(TokenVo.getROOM_TOKEN_IMGINGAK(),message);
		//this.sendLineNotify(TokenVo.getROOM_TOKEN_KIM(), message);
	}
	
	public void sendLineNotify(String token,String message) {

		
		logger.info("=================== sendLineNotify START");
		logger.info("=================== sendLineNotify message["+message+"]");
		
		
		try {
			URL url = new URL("https://notify-api.line.me/api/notify");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.addRequestProperty("Authorization", "Bearer "+token);
			conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//conn.connect();
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();

			sb.append("message=");
			sb.append(message);
			
			logger.info("Message : "+sb.toString());
			
			bw.write(sb.toString());
			
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			logger.info("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			
			logger.info("response body : " + result);
			 
			br.close();
			bw.close();

			JsonParser parser = new JsonParser(); 
			JsonElement element = parser.parse(result);
			
			String status = element.getAsJsonObject().get("status").getAsString();
			String responseMessage = element.getAsJsonObject().get("message").getAsString();
			
			
			logger.info("Response status : "+status);
			logger.info("Response Message : "+responseMessage);
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		logger.info("=================== sendLineNotify END");
	}
	public static void main(String[] args) {
		
		
		NaverLineService service = new NaverLineService();
		
		service.sendLineNotify("7YPpeAwicOBcv9MoZfCGdL4eHfBx9FoW2nmJrbWc8mG","여기 테스트 http://naver.com");
		
	}
}
