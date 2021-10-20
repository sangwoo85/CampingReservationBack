<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="cmm/cmmHeader.jsp" %>

<script>

 	$(document).ready(function(){

		$("#email").on("change",function(){

			console.log($(this).val());
		});

	

	})

 

</script>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<form id ="dataForm">
	<table id="dataTable">
		<tr>
			<td> 이메일</td>
			<td>
				<input type="text" id="email">
			</td>
		</tr>
		<tr>
			<td> 비밀번호</td>
			<td>
				<input type="text" id="password">
			</td>
		</tr>
		<tr>
			<td> 비밀번호확인</td>
			<td>
				<input type="text" id="confirmPassword">
			</td>
		</tr>
		<tr>
			<td> 이름</td>
			<td>
				<input type="text" id="name">	
			</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>
				<input type="text" id="phoneNumber">
			</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>
				<input type="text" id="phoneNumber">
			</td>
		</tr>
	
	</table>
</form>

</body>
</html>