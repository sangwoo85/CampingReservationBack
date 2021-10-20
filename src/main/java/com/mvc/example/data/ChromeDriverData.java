package com.mvc.example.data;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ChromeDriverData {

	private static final Logger logger = LoggerFactory.getLogger(ChromeDriverData.class);

	private final static String DRIVE_PATH = "C:\\WebDriver\\chromedriver_win32\\chromedriver.exe";
	
	private ChromeDriver drivers;
	
	public ChromeDriverData(){
		
		if(drivers == null) {
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
	        drivers = chromeDriver;
		}
		
	}
	
	public ChromeDriver getInstance() {
		
		logger.info("ChromeDriverData getInstance START");
		
		if(drivers == null) {
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
	        drivers = chromeDriver;
		}
		
		return drivers;
	}
	
	
}
