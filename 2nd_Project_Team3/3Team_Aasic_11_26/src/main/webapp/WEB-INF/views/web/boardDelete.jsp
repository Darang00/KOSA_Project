<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	 <%-- <link rel="Stylesheet" href="${pageContext.request.contextPath}/style/default.css" /> --%>
	<script type="text/javascript">
	function delCheck(){
		
		/* if(del.pwd.value==""){
			alert("비밀번호를 입력해야합니다.");
			del.pwd.focus();
			return false;
		} */
		/* if(del.pwd.value.length>8){
			alert("비밀번호는 8자리 이내입니다.");
			del.pwd.select();
			return false;
		}//if--------- */
		document.del.submit();
	}
	function historyBack(){
		javascript:history.back();
	}
</script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/include/userHeader.jsp"></jsp:include>
	
	<c:set var="idx" value="${requestScope.idx}" />
	<c:set var="boardname" value="${requestScope.boardname}" />
	<c:set var="writerid" value="${requestScope.writerid}" />
	
	<div id="pageContainer">
		<div style="padding-top: 25px; text-align: center">
			<form name="del" method="POST" action="boardDeleteOk.user?boardname=${boardname}&idx=${idx}&userid=${userid}">
				<center>
					<%-- 비밀번호 :
					<input type="password" name="pwd">
					<input type="hidden"  name="idx" value="${idx}"> --%>
					<hr width="500" color="gold">
					<h3>정말 삭제하시겠습니까?</h3>
					<hr width="500" color="gold">
					<input type="button" value="삭제하기" onclick="delCheck();">
					<button><a href="boardDeleteOk.user?boardname=${boardname}&idx=${idx}&writerid=${writerid}">삭제하기OG</a></button>
					<input type="button" onclick="historyBack();" value="뒤로가기">
					<!-- <input type="reset" value="뒤로가기"> -->
				</center>
				<input type="hidden" name="idx" value="${idx}">
				<input type="hidden" name="writerid" value="${writerid}" >
				<input type="hidden" name="boardname" value="${boardname}">
			</form>
		</div>
	</div>
</body>
</html>