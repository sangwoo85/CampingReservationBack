package util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MainUtil {

	
	@Autowired
	private JavaMailSender mailSender;
	
	public void mailTest() {
		System.out.println("================ Mail 발송 ===========================");
		String setfrom = "ksswy@naver.com";
		String[] tomail = {"ksswy79@gmail.com","ksswy@naver.com","incastleyoo@naver.com","yunhee4287@nate.com"}; // 諛쏅뒗 �궗�엺 �씠硫붿씪
		String title = "임진각 메일"; // �젣紐�
		String content = ""; // �궡�슜
		String contentHtml = "<html> <body><h1>임진각 예약</h1> 밑의 링크 타면 가능  <a href=\"http://imjingakcamping.co.kr/resv/res_01.html?checkdate=2021-02-06\">링크</a> </body></html>";
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					true, "UTF-8");

			messageHelper.setFrom(setfrom); // 蹂대궡�뒗�궗�엺 �깮�왂�븯硫� �젙�긽�옉�룞�쓣 �븞�븿
			messageHelper.setTo(tomail); // 諛쏅뒗�궗�엺 �씠硫붿씪
			messageHelper.setSubject(title); // 硫붿씪�젣紐⑹� �깮�왂�씠 媛��뒫�븯�떎
			messageHelper.setText(contentHtml,true); // 硫붿씪 �궡�슜

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}
}
	
}
