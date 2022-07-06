<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성공</title>
<link href="./css/menu.css" rel="stylesheet">
</head>
<body>
<div id="container">
	<div id="menubar">		
		<c:import url="menu.jsp"/>
	</div>
	<div id="main">
		<h1>성공했습니다.</h1>
		<h2>다음 페이지로 이동하세요.</h2>
		
		<button onclick="location.href='./board'">보드로 이동</button>
	</div>
</div>

</body>
</html>