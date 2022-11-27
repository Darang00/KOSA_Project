<%@page import="kr.or.kosa.dto.Reply"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosa.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user_delete</title>
</head>
<body>
<%-- 	<c:set var="board" value="${requestScope.board}" /> --%>	
	<%-- <c:set var="userid" value="${requestScope.userid}" />
	<c:set var="boardname" value="${requestScope.boardname}" /> --%>

<jsp:include page="/WEB-INF/views/include/userHeader.jsp"></jsp:include>
	<div id="pageContainer">
		<div style="padding-top: 30px; text-align: center">
			<center>
				<b>정말 탈퇴하시길 원하신다면<br>PASSWORD를 정확히 입력해주세요</b>
				<form name="bbs" action="userDeleteOK.do" method="POST">
				<input name="userid" size="10">
				<input type="submit" value="입력">
				</form>
				<br>
			</center>
		</div>
	</div>
</body>
</html>
