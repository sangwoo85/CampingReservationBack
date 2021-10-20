package com.mvc.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.example.data.ChromeDriverData;
import com.mvc.example.data.TokenVo;

@Service
public class SeleniumImgingakService {

	@Autowired
	private MailSendService MailSendService;

	@Autowired
	private NaverLineService naverLineService;

	@Autowired
	ChromeDriverData chromeDriverData;

	private static ChromeDriver driver;

	private static final Logger logger = LoggerFactory.getLogger(SeleniumImgingakService.class);

	private final static String DRIVE_PATH = "C:\\WebDriver\\chromedriver_win32\\chromedriver.exe";

	private final static String URL = "https://imjingakcamping.co.kr/resv/res_01.html?checkdate=";

	private Map<String, List<String>> reservationList = new HashMap<String, List<String>>();

	public void findRemainRoom(String[] dateList) {

		String naverLineMessage = "임진각 평화누리 캠핑장 예약 알림";

		try {

			driver = chromeDriverData.getInstance();

			for (String date : dateList) {

				String imgingakUrl = URL + date;

				// 웹페이지 요청
				driver.get(imgingakUrl);

				Thread.sleep(1500);

				logger.info("============== DATE " + date + "=============================");
				try {

					List<WebElement> elementList = driver.findElements(By.className("fwb"));

					for (int i = 1; i < 3; i++) {

						String text = elementList.get(i).getText();

						try {

							int num = Integer.parseInt(text);
							logger.info("number : " + num);
							if (num > 0) {
								MailSendService.sendMail(date, "임진각평화누리캠핑장", imgingakUrl);

								String message = date + " " + naverLineMessage + " " + imgingakUrl;

								List<String> tokenList = new ArrayList<String>();
								tokenList.add(TokenVo.getROOM_TOKEN_IMGINGAK());
								tokenList.add(TokenVo.getROOM_TOKEN_KIM());

								for (String token : tokenList) {
									naverLineService.sendLineNotify(token, message);
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

	/*	
		System.setProperty("webdriver.chrome.driver", DRIVE_PATH);
        
        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함
        options.addArguments("--headless"); 				//창 안띄우고 실행
        options.addArguments("--no-sandbox");				//창 안띄우고 실행
        
        // WebDriver 객체 생성
        ChromeDriver chromeDriver = new ChromeDriver( options );
        //drivers.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        chromeDriver.get("https://imjingakcamping.co.kr/resv/res_01.html?checkdate=2021-10-03");
		List<WebElement> elementList = chromeDriver.findElements(By.className("fwb"));

		for(WebElement element : elementList) {
			System.out.println(element.getText());
		}
		*/
	}

}
