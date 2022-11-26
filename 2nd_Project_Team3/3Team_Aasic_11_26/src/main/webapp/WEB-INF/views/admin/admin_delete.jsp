<%@page import="kr.or.kosa.dto.Reply"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosa.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user_delete</title>
<style>
		table {
	
		    font-family: arial, sans-serif;
		    border-collapse: collapse; /* 붕괴하다 , 무너지다 */
		    width: 70%;
		}
		
		th {
		    border: 1px solid #dddddd;
		    text-align: center;
		    padding: 8px;
		}
		td{
		    border: 1px solid #dddddd;
			text-align: left;
			padding: 8px;
		}
		tr:nth-child(even) {  /* even 짝수     odd 홀수 */
		    background-color: #dddddd;
		}
	</style>
</head>
<body>
<%-- 	<c:set var="board" value="${requestScope.board}" /> --%>	
	<%-- <c:set var="userid" value="${requestScope.userid}" />
	<c:set var="boardname" value="${requestScope.boardname}" /> --%>

 	<jsp:include page="/WEB-INF/views/include/adminHeader.jsp"></jsp:include>
 	<jsp:include page="/WEB-INF/views/include/adminLeft.jsp"></jsp:include>
	<div id="pageContainer">
	<h2><b>$ 회원관리 페이지 $</b></h2>
	<h5><b>KingBird 회원 목록</b></h5>
		<div style="padding-top: 30px; text-align: center;">
			<table style="border:1px">
				<tr>
					<th>ID</th>
					<th>이름</th>
					<th>Email</th>
					<th>전화번호</th>
					<th>성별</th>
					<th>가입일</th>
				</tr>
				<c:forEach var="memberList" items="${requestScope.memberList}">
					<tr>
						<td>${memberList.userid}</td>
						<td>${memberList.name}</td>
						<td>${memberList.email}</td>
						<td>${memberList.phone}</td>
						<td>${memberList.gender}</td>
						<td>${memberList.startdate}</td>
					</tr>
				</c:forEach>
			</table>
				<br>
				<b>- 탈퇴시킬 회원님의 ID를 입력하세요 -</b>
				<form name="bbs" action="userDeleteOK.do" method="POST">
				<input name="userid" size="10">
				<input type="hidden" name="userid" value="${userid}">
				<input type="submit" value="입력">
				</form>
				<br>
		</div>
	</div>
</body>
</html>
