package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.imageio.ImageIO;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

public class TestMain {

	
	public static void setSSL() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustAllCerts = new TrustManager[] { 
			new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				} 
				public void checkClientTrusted(X509Certificate[] chain, String authType)
						throws CertificateException {
				}
				public void checkServerTrusted(X509Certificate[] chain, String authType)
						throws CertificateException {
				}
			} 
			}; 
		SSLContext sc = SSLContext.getInstance("SSL"); 
		sc.init(null, trustAllCerts, new SecureRandom()); 
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() { 
			@Override 
			public boolean verify(String hostname, SSLSession session) {
				return true; 
			} 
		}); 
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory()); 
	}
	
	
	public static void main(String[] args) {
		
		/*
		
		 System.out.println("===== selectIsReserve ======");
		  
		  String targetUrl =
		  "https://forcamper.co.kr/campgrounds/1758/areas/51?utf8=%E2%9C%93&check_in=2021%2F02%2F16&check_out=2021%2F02%2F17";
		  
		  try {
			TestMain.setSSL();
			
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
		  Connection conn = Jsoup.connect(targetUrl);
		  
		  try {
		  
		  Document html = conn.get();
		  
		  //System.out.println("=============="+html);
		  
		  Elements e = html.getElementsByClass("btn-site");
		  
		  System.out.println(e.toString());
		  
		  System.out.println("#####################################################");
		  
		  for(String a : e.eachText()) {
			  System.out.println(a.lastIndexOf("(예약완료)")); 
		  }
		  
		  System.out.println(e.next());
		  System.out.println(e.contains("disabled"));
		  System.out.println(e.get(1).textNodes().get(0).toString());
		  System.out.println(e.get(2).toString());
		  
		  //imgingak.mailTest();
		  
		  }catch(Exception e) { e.printStackTrace();
		  System.out.println("==========ERROR 발생 ===="); }
		 
		*/

		
			//이미지 URL로 다운로드 
			
			String imgUrl = "http://trandent.com/resources/upload/user/2019718214714257.jpg";

			URL url;
			BufferedImage img;

			try {
				url = new URL(imgUrl);

				String fileName = imgUrl.substring( imgUrl.lastIndexOf('/')+1, imgUrl.length() ); // 이미지 파일명 추출

				String ext = imgUrl.substring( imgUrl.lastIndexOf('.')+1, imgUrl.length() );  // 이미지 확장자 추출
		
				img = ImageIO.read(url);

				ImageIO.write(img, ext, new File("C:\\download\\"+fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
//			VoTest vo = new VoTest();
			
//			System.out.println(vo.getClass());
			
		 
		
		
	}
}
@ToString
@Getter
@Setter
class VoTest{
	
	@NonNull
	private String ab;
	
	@NonNull
	private String abc;
	
	
}
