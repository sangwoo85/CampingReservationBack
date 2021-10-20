package com.mvc.example.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mvc.example.service.SeleniumMoonSuService;





@Component
public class MoonsuScheduler {

	@Autowired
	private SeleniumMoonSuService seleniumMoonSuService;
	
	
	//@Scheduled(fixedDelay = 100000)
	public void remainingRoomScheduler() {
		
		System.out.println("===========remainingRoomScheduler START");
		
		//양식 날짜 1020-1021 10월20일 부터 10월 21일
		
		String[] dateList = {"1002-1003","1009-1010"};
		
		
		seleniumMoonSuService.findRemainRoom(dateList);
		
	}
}
