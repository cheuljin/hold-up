<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link href="./css/menu.css" rel="stylesheet">
</head>
<body>
<div id="container">
	<div id="menubar">		
		<c:import url="menu.jsp"/>
	</div>
	<div id="main">
		<h1>시스템이 응답하지 않습니다.</h1>
		<h2>다시 시도해주세요.</h2>
		
		<button onclick="location.href='./board'">보드로 이동</button>
		<button onclick="location.href='./login'">로그인하기</button>
	</div>
</div>

</body>
</html>