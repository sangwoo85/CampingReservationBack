<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<script>

$(function(){


	$("#resverveTest").on('click',function(){
		testReserve();
	});
	
	$("#resverveTest1").on('click',function(){
		testReserveKimpo();
	});
	
});

function testReserve(){
		var param = {};
		$.ajax({
		    url: "/imgingak/reserve.do", // 클라이언트가 요청을 보낼 서버의 URL 주소
		    data: { name: "홍길동" },                // HTTP 요청과 함께 서버로 보낼 데이터
		    type: "POST",                             // HTTP 요청 방식(GET, POST)
		    dataType: "json"                         // 서버에서 보내줄 데이터의 타입
		// HTTP 요청이 성공하면 요청한 데이터가 done() 메소드로 전달됨.
		}).done(function(json) {
		    $("<h1>").text(json.title).appendTo("body");
		    $("<div class=\"content\">").html(json.html).appendTo("body");
		}).fail(function(xhr, status, errorThrown) {
		// HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨.
		    $("#text").html("오류가 발생했습니다.<br>").append("오류명: " + errorThrown + "<br>").append("상태: " + status);

		})
	/* 	$.ajax("/imgingak/reserve.do",param).done(function(data){
					console.log('test');
		});
	 */
}

function testReserveKimpo(){
	var param = {};
	$.ajax({
	    url: "/kimpo/moonsu.do", // 클라이언트가 요청을 보낼 서버의 URL 주소
	    data: { name: "홍길동" },                // HTTP 요청과 함께 서버로 보낼 데이터
	    type: "POST",                             // HTTP 요청 방식(GET, POST)
	    dataType: "json"                         // 서버에서 보내줄 데이터의 타입
	// HTTP 요청이 성공하면 요청한 데이터가 done() 메소드로 전달됨.
	}).done(function(json) {
	    $("<h1>").text(json.title).appendTo("body");
	    $("<div class=\"content\">").html(json.html).appendTo("body");
	}).fail(function(xhr, status, errorThrown) {
	// HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨.
	    $("#text").html("오류가 발생했습니다.<br>").append("오류명: " + errorThrown + "<br>").append("상태: " + status);

	})
/* 	$.ajax("/imgingak/reserve.do",param).done(function(data){
				console.log('test');
	});
 */
}

</script>

<html>
<head>

	<title>Home</title>
	
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<button id="resverveTest">Test</button>
<button id="resverveTest1">moonsuTest</button>

<a href="http://www.naver.com">링크</a>
</body>
</html>
