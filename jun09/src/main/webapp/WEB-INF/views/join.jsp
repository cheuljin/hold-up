<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join</title>
<link href="./css/menu.css" rel="stylesheet">
<style type="text/css">
#joinform {
	margin: 0 auto;
	height: 350px;
	width: 500px;
	background-color: white;
	padding: 10px;
}

table {
	margin: 0 auto;
	height: 300px;
	width: 450px;
	background-color: white;
	padding: 10px;
	border-collapse: collapse;
}

tr, td {
	/* border-bottom: 1px red solid; */
	min-height: 50px;
}
.t{
	text-align: center;
	font-size: medium;
	
}

td input {
	margin: 0;
	padding: 0;
	border: 0;
	width: 100%;
	padding-left: 10px;
	height: 45px;
	border-bottom: red 1px solid;
}
</style>
</head>
<body>
	<div id="container">
		<div id="menubar">
			<c:import url="menu.jsp" />
		</div>
		<div id="main">
			<h1>가입하기</h1>
			<div id="joinform">
				<form action="./join" method="post" id="asdf">
					<table>
						<tr>
							<td class="t">아이디</td>
							<td>
								<input type="text" name="id" id="id">
								<p id="checkResult">아이디를 입력하세요.</p>
							</td>
						</tr>
						<tr id='pass'>
							<td class="t">암호</td>
							<td><input type="password" name="pw1" id="pw1"></td>
						</tr>
						<tr>
							<td class="t">암호재입력</td>
							<td><input type="password" name="pw2" id="pw2"></td>
						</tr>
						<tr>
							<td class="t">이름</td>
							<td><input type="text" name="name" id="name"></td>
						</tr>
						<tr>
							<td class="t">email</td>
							<td><input type="text" name="email" id="email"></td>
						</tr>
					</table>
					<button id="joinBtn" disabled="disabled">가입하기</button>
				</form>
			</div>
		</div>
	</div>
<script type="text/javascript">
$(function() {
	$("#id").change(function(){//아이디 검사용
		if(  $("#id").val().length < 4 ){
			alert("ID는 4글자 이상으로 만들어주세요.");
		}else{
			$.ajax({//  ajax 호출 -> id를 서버로 보내기
				url : "./checkID",
				type : "post",
				dataType : "html",
				data : { "id" : $("#id").val() },
				success : function(data){
					if(data == 0){
						$("#checkResult").css("color", "blue");
						$("#checkResult").text("가입할 수 있는 ID입니다");
						$("#joinBtn").attr("disabled", false);
					}else{
						$("#checkResult").css("color", "red");
						$("#checkResult").text("이미 사용중인 ID입니다.");
						$("#joinBtn").attr("disabled", true);
					}
				},
				error : function(){
					$("#checkResult").text("비정상입니다.");
					$("#joinBtn").attr("disabled", true);
				}
				
			});
		}
	});
	
	
	$("#asdf").on("submit", function() {var id = $("#id").val();if (id == "") {alert("아이디를 입력해주세요.");$("#id").focus();return false;}
if(id.length < 4){alert("ID는 4글자 이상으로 만들어주세요.");$("#id").focus();return false;}
				var pw1 = $("#pw1").val();
				if(pw1 == "" || pw1.length < 4){
					alert("암호는 4글자 이상으로 만들어주세요.");
					$("#pw1").focus();
					return false;
				}
				
				var pw2 = $("#pw2").val();
				if(pw2 == "" || pw2.length < 4){
					alert("암호는 4글자 이상으로 만들어주세요.");
					$("#pw2").focus();
					return false;	
				}
				if(pw1 != pw2){
					alert("암호가 일치하지 않습니다.\n다시입력하세요.");	
					$("#pw1").val("");
					$("#pw2").val("");
					$("#pw1").focus();
					return false;
				}
		
				var name = $("#name").val();
				if (name == "") {
					alert("이름을 입력해주세요.");
					$("#name").focus();
					return false;
				}
				var email = $("#email").val();
				var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
				if (email == "" || !reg_email.test(email)) {
					alert("email을 입력해주세요.");
					$("#email").focus();
					return false;
				}
			});
		});
	</script>
</body>
</html>