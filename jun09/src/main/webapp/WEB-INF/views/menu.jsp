<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<img alt="logo" src="./img/logo.png" onclick="location.href='./'">
<c:choose>
	<c:when test="${sessionScope.id eq null}">
		<div id="login2">로그인하기</div>
		<div id="loginform">
			<form action="./login" method="post" onsubmit="return login()">
				<input type="text" name="id" placeholder="아이디를 입력하세요">
				<input type="password" name="pw" placeholder="비밀번호를 입력하세요">
				<a href="./join">가입하기</a>
				<button type="submit">로그인하기</button>
			</form>
		</div>
<script>
$(document).ready(function(){
	$("#loginform").hide();
	$("#login2").click(function(){
		$("#login2").hide();
		$("#loginform").fadeIn("slow");
	});
});
</script>
			
	</c:when>
	<c:otherwise>
		<div id="login2">
		${sessionScope.id }님 반갑습니다.
		<img alt="logout" src="./img/logout.png" style="width:24px; vertical-align: middle;" onclick="logout();">
		</div>
	</c:otherwise>
</c:choose>
<div class="wrap">
	<label for="menu1" class="label e"> <span class="rotate"><p>게시판</p></span>
	</label> <input type="radio" name="navigation" id="menu1" class="button">
	<div class="contents">
		<div class="inner">
			<div class="description">
				<p onclick="location.href='./board?b_cate=1'">보드</p>
				<p onclick="location.href='./board?b_cate=2'">보드</p>
				<p onclick="location.href='./board?b_cate=3'">보드</p>
				<p onclick="location.href='./board?b_cate=4'">보드</p>
			</div>
		</div>
	</div>

	<label for="menu2" class="label d"><span class="rotate"><p>사고팔고</p></span>
	</label> <input type="radio" name="navigation" id="menu2" class="button">
	<div class="contents">
		<div class="inner">
			<div class="description">
				<p>다람쥐 헌 챗바퀴에 타고파. 다람쥐 헌 챗바퀴에 타고파.</p>
			</div>
		</div>
	</div>

	<label for="menu3" class="label c"> <span class="rotate"><p>신문기사</p></span>
	</label> <input type="radio" name="navigation" id="menu3" class="button">
	<div class="contents">
		<div class="inner">
			<div class="description">
				<p>다람쥐 헌 챗바퀴에 타고파. 다람쥐 헌 챗바퀴에 타고파. 다람쥐 헌 챗바퀴에 타고파. 다람쥐 헌 챗바퀴에 타고파.</p>
			</div>
		</div>
	</div>
</div>
<c:import url="footer.jsp" />
<script type="text/javascript">
function login(){
	var id = document.getElementsByName("id")[0];
	var pw = document.getElementsByName("pw")[0];
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
function logout(){
	if(confirm("로그아웃 하시겠습니까?")){
		location.href="./logout";
	}	
}
</script>