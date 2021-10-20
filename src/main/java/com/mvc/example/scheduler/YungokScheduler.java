package com.mvc.example.scheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mvc.example.service.YungokService;

@Component
public class YungokScheduler {

	@Autowired
	private YungokService yungokService;
	
	private static final Logger logger = LoggerFactory.getLogger(YungokScheduler.class);
	
	//@Scheduled(fixedDelay = 100000)
	//@Scheduled(cron = "30/100 * 07-19 * * *")
	public void remainingRoomScheduler() {
		
		logger.info("===========remainingRoomScheduler START");
		
		String[] dateList = {"2021-10-02","2021-10-10","2021-10-11","2021-10-23"}; 
		
		Map<String,List<String>> reservationList = new HashMap<String,List<String>>();
		
		//0209162330
		List<String> dayList = new ArrayList<String>();
		dayList.add("2021-10-09");
		dayList.add("2021-10-16");
		dayList.add("2021-10-23");
		dayList.add("2021-10-30");
		
		reservationList.put("202110", dayList);

		dayList = new ArrayList<String>();
		dayList.add("2021-11-06");
		dayList.add("2021-11-13");
		dayList.add("2021-11-20");
		dayList.add("2021-11-27");
		
		reservationList.put("202111", dayList);
		
		//yungokService.findRemainRoom(reservationList);
		
		logger.info("===========remainingRoomScheduler END");
		
	}
	
}
