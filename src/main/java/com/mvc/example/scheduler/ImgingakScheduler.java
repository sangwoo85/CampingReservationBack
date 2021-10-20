package com.mvc.example.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mvc.example.service.SeleniumImgingakService;



@Component
public class ImgingakScheduler {

	@Autowired
	private SeleniumImgingakService seleniumImgingakService;
	
	public ImgingakScheduler() {
		
		System.out.println("ImgingakScheduler");
	}
	//@Scheduled(fixedDelay = 100000)
	@Scheduled(cron = "30/100 * 07-19 * * *")
	public void remainingRoomScheduler() {
		
		System.out.println("===========ImgingakScheduler remainingRoomScheduler START");
		
		String[] dateList = {"2021-10-23","2021-10-30","2021-11-06","2021-11-13","2021-11-20","2021-11-27"}; 
		
		seleniumImgingakService.findRemainRoom(dateList);
		
	}
}
