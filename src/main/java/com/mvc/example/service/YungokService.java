package com.mvc.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.example.data.ChromeDriverData;
import com.mvc.example.data.TokenVo;

@Service
public class YungokService {


	@Autowired
	private MailSendService MailSendService;

	@Autowired
	private NaverLineService naverLineService;

	@Autowired
	ChromeDriverData chromeDriverData;
	
	private static final Logger logger = LoggerFactory.getLogger(YungokService.class);
	
	private static ChromeDriver driver;
	
	private String URL = "https://camping.gtdc.or.kr/DZ_reservation/reserCamping_v3.php?xch=reservation&xid=camping_reservation&sdate=202110";
	
	public void findRemainRoom(Map<String,List<String>> dateList) {
		
		logger.info("============== findRemainRoom START");
		
		String naverLineMessage = "연곡솔향기 캠핑장";

		try {

			driver = chromeDriverData.getInstance();

			for (String date : dateList.keySet()) {

				String yungokURL = URL+date;

				// 웹페이지 요청
				driver.get(yungokURL);

				Thread.sleep(1500);

				logger.info("============== DATE " + date + "=============================");
				
				try {

					List<WebElement> elementList = driver.findElementsByTagName("Button");

					for (WebElement element : elementList) {
						
						String openDate = element.getAttribute("value");
						
						try {
						
							if(!"".equals(openDate)) {
								
								String[] arrText = openDate.split(":");
								
								List<String> dateArrayList =  dateList.get(date);
								
								if(dateArrayList.contains(arrText[1])) {
									
									logger.info("Open Date : "+openDate);
								
									String message = arrText[0] +"구역 "+ arrText[1] + " " + naverLineMessage + " " + yungokURL;

									naverLineService.sendLineNotify(TokenVo.getROOM_TOKEN_IMGINGAK(), message);
									naverLineService.sendLineNotify(TokenVo.getROOM_TOKEN_KIM(), message);
									MailSendService.sendMail(date+openDate, "솔향기캠핑장", yungokURL);
									
								}
								
							}
							

						} catch (Exception e) {
							logger.info("parsing Error");
							e.printStackTrace();
						}

					}

					logger.info("==========================================");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			driver = null;
		}
		
		
	} 
	
	public static void main(String[] args) {
		
		//https://camping.gtdc.or.kr/DZ_reservation/reserCamping_v3.php?xch=reservation&xid=camping_reservation&sdate=202110
		
	}
	
}
