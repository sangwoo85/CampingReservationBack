package com.mvc.example.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {

	private static final Logger logger = LoggerFactory.getLogger(MailSendService.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	public void sendMail(String date,String inputTitle, String url) {
		
		logger.info("============================= mail Test START");
		
		String setfrom = "ksswy@naver.com";
		String[] tomail = {"ksswy79@gmail.com","ksswy@naver.com","incastleyoo@naver.com","yunhee4287@nate.com","pkmin85@naver.com","prt_hj@naver.com"}; // 받는 사람 이메일
		//String[] tomail = {"ksswy79@gmail.com"};
		String title = "날짜 "+date+" "+inputTitle+" 예약 자리 나옴"; // 제목
		String content = "임진각 예약 확인 해보셩"; // 내용
		String contentHtml = "<html> <body><h1>"+title+"</h1> 밑에 링크 클릭 <a href='"+url+"'>링크</a> </body></html>";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(contentHtml,true); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
