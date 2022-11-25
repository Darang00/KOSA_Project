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
<title>user_password</title>
</head>
<body>
<%-- 	<c:set var="board" value="${requestScope.board}" /> --%>	
	 <c:set var="userid" value="${requestScope.userid}" />
	 <c:set var="boardname" value="${requestScope.boardname}" />

<jsp:include page="/WEB-INF/views/include/userHeader.jsp"></jsp:include>
	<div id="pageContainer">
		<div style="padding-top: 30px; text-align: center">
			<center>
				<b>${userid} 회원님의 계정 비밀번호를 입력하세요</b>
				<form name="bbs" action="update.user" method="POST">
				<input type="password" name="pwd" size="5">
				<input type="hidden" name="userid" value="${userid}">
				<input type="submit" value="비밀번호 확인">
				</form>
				<br>
			</center>
		</div>
	</div>
</body>
</html>





