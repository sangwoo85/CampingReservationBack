package com.mvc.example.service;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.mvc.example.data.ChromeDriverData;
import com.mvc.example.data.TokenVo;


@Service
public class SeleniumMoonSuService {

	private static final Logger logger = LoggerFactory.getLogger(SeleniumMoonSuService.class);

	@Autowired
	private NaverLineService naverLineService;
	
	@Autowired
	ChromeDriverData chromeDriverData;
	
	@Autowired
	private MailSendService mailSendService;
	
	private final String defaultURL = "https://www.forcamper.co.kr/campgrounds/1758/areas/";
	
	private final static String DRIVE_PATH = "C:\\WebDriver\\chromedriver_win32\\chromedriver.exe";
	
	private static ChromeDriver driver;
	
	private ImmutableMap<String,String> siteName = ImmutableMap.<String, String>builder()
																				.put("51","호숫가   D-1존")
																				.put("52","호숫가   D-2존")
																				.build();
																		
	public void findRemainRoom(String[] dateList) {
		
		logger.info("=========== findRoomMoonsu START");
		logger.info("=========== findRoomMoonsu Date["+dateList.toString()+"]");
	
		String[] siteNum = {"51","52"};
		
		String fromMonth = "";
		String fromDay 	 = "";
		String toMonth   = "";
		String toDay 	 = "";
		
		String message  = "김포 문수골 캠핑장알람";
		
		/*
		  if(driver == null) { System.setProperty("webdriver.chrome.driver",
		  DRIVE_PATH);
		  
		  // WebDriver 옵션 설정 ChromeOptions options = new ChromeOptions();
		  //options.addArguments("--start-maximized"); // 전체화면으로 실행
		  options.addArguments("--disable-popup-blocking"); // 팝업 무시
		  options.addArguments("--disable-default-apps"); // 기본앱 사용안함
		  options.addArguments("--headless"); //창 안띄우고 실행
		  options.addArguments("--no-sandbox"); //창 안띄우고 실행
		  
		  // WebDriver 객체 생성 ChromeDriver chromeDriver = new ChromeDriver( options );
		  // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); driver =
		  chromeDriver; }
		 */
		
		driver = chromeDriverData.getInstance();
		
		for(String site:siteNum) {

			logger.info("============== SITE : "+site);
			for(String date : dateList) {
				
				//0210206
				fromMonth 	= date.substring(0, 2);
				fromDay 	= date.substring(2, 4);
				toMonth 	= date.substring(5, 7);
				toDay 		= date.substring(7, 9);
				
				String addDate = site+"?check_in=2021%2F"+fromMonth+"%2F"+fromDay+"&check_out=2021%2F"+toMonth+"%2F"+toDay;
				
				String url = defaultURL + addDate;
		
				try {
					
					driver.get(url);
					
					Thread.sleep(1500);
			        
			        logger.info("============== DATE "+date+"=============================");
		        
			        List<WebElement> elementList = driver.findElements(By.className("btn-site-wrapper"));
			        
			        for(WebElement element : elementList) {
		
			        	String resultText = element.getText();
			        	
			        	if(!resultText.contains("(예약완료)")) {
			        		logger.info("예약 가능");
			        		
			        		message = date+" "+siteName.get(site)+" "+message+" "+url;
			        		naverLineService.sendLineNotify(TokenVo.getROOM_TOKEN_KIM(), message);
			        		
			        		mailSendService.sendMail(date, "문수골 캠핑장", url);
			        	}
			        }
		        
				}catch(Exception e) {
					e.printStackTrace();
				}
				message = "";
			}
        
		}
		
		logger.info("=========== findRoomMoonsu END");
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * SeleniumMoonSuService service = new SeleniumMoonSuService();
	 * 
	 * String[] dateList = {"1020-1021","1009-1010"};
	 * //service.findRoomMoonsu(dateList);
	 * 
	 * }
	 */
}
