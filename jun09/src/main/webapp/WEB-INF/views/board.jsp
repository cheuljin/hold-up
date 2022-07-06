<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보드</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/board.css" rel="stylesheet">
</head>
<body>
	<div id="container">
		<div id="menubar">
			<c:import url="menu.jsp" />
		</div>
		<div id="main">
			<div id="board">
					<h1>${b_cate }보드</h1><c:choose><c:when test="${fn:length(boardList) > 0 }">
<%
LocalDate now = LocalDate.now();//현재날짜 뽑기
%>
<c:set value="<%=now%>" var="now" />
						<div id="boardTable">
							<table>
								<tr>
									<th>제목</th>
									<th>날짜</th>
									<th>쓴사람</th>
									<th>조회수</th>
								</tr><c:forEach items="${boardList }" var="b">
								<tr onclick="location.href='./detail?b_no=${b.b_no }'">
									<td id="r3">
									<c:if test="${b.fileCount gt 0 }"><img alt="img" src="./img/image.png"></c:if>
									${b.b_title }<c:if test="${b.commentCount gt 0}"><small>${b.commentCount }</small></c:if></td>
									<td id="r2"><fmt:formatDate value="${b.b_date }" pattern="yyyy-MM-dd" var="bdateYear" /> <c:choose><c:when test="${bdateYear eq now}"><fmt:formatDate value="${b.b_date }" pattern="HH:mm:ss" var="bdateYear" /></c:when><c:otherwise><fmt:formatDate value="${b.b_date }" pattern="yyyy-MM-dd" var="bdateYear" /></c:otherwise></c:choose> ${bdateYear }</td>
									<td id="r2">${b.u_id }</td>
									<td id="r1">${b.b_count }</td>
								</tr></c:forEach>
							</table>
				</div>
				<div id="pagination"><ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="linkPage" /></div>
				</c:when><c:otherwise><H1>글이 없습니다.</H1></c:otherwise></c:choose>
<c:if test="${sessionScope.id ne null }"><button onclick="location.href='./write?b_cate=${b_cate}'">글쓰기</button></c:if>
			</div>
			<!-- Controller -> Service -> DAO -> DB  (Model) jsp(View) -> 사용자 -->
		</div>
	</div>
<script type="text/javascript">function linkPage(pageNo) {location.href = "./board?b_cate=${b_cate}&pageNo=" + pageNo;}</script>
</body>
</html>