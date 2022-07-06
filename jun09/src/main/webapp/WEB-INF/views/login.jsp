<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/login.css" rel="stylesheet">
<c:if test="${param.error ne null }">
	<script type="text/javascript">
		alert("로그인 정보가 올바르지 않습니다.\n올바른 아이디와 비밀번호를 입력하세요.");
	</script>
</c:if>
<script type="text/javascript">
function loginJSP(){
	var id = document.loginFormJSP.elements["id"];
	var pw = document.loginFormJSP.elements["pw"];
	if(id.value.length < 5){
		alert("올바른 아이디를 입력하세요.");
		id.focus();
		return false;
	}
	if(pw.value.length < 5){			
		alert("올바른 암호를 입력하세요.");
		pw.focus();
		return false;
	}
}
</script>
</head>
<body>
	<div id="container">
		<div id="menubar">
			<c:import url="menu.jsp" />
		</div>
		<div id="main">


			<div id="login">
				<img alt="login" src="./img/login_img.png">
				<form name="loginFormJSP" action="./login" method="post" onsubmit="return loginJSP()">
					<input type="text"  name="id" placeholder="아이디를 입력하세요." required="required">
					<br>
					<input type="password" name="pw" placeholder="암호를 입력하세요." required="required">
					<br>
					<a href="./join">가입하기</a>
					<button type="submit">로그인하기</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>