<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="Stylesheet" href="style/default.css">
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	<!-- 모달 javascript -->
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/userHeader.jsp"></jsp:include>
	<div id="pageContainer">
		<h3>UI(Css 공통 페이지 적용 확인)</h3>
		<h3>DB연결 정보 확인</h3>
		<%
		Connection conn = null;

		Context context = new InitialContext(); //현재 프로젝트에 이름기반 검색
		DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");//java:comp/env/ + name

		//POOL안에서 connection 가지고 오기
		conn = ds.getConnection();
		
		boolean connect1 = conn.isClosed();
		
		//POINT
		//POOL에 connection 받환하기
		conn.close(); //반환하기
		boolean connect2 = conn.isClosed();
		%>
		<c:set var="connect1" value="<%= connect1 %>" />
		db 연결여부 : ${ connect1 }<br>
		<c:set var="connect2" value="<%= connect2 %>" />
		db 연결여부 : ${ connect2 }<br>
		
	</div>
	
	
		<!-- 연습용 모달 버튼 -->
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Open modal for @mdo</button>
	<!--  -->

	<!-- 모달창 연습 -->
	<form action="sendMessage.user" method="POST" id="modalForm" name="modalForm">
	 <input type="hidden" id  = "senduserid" name ="senduserid" value = "${sessionScope.userid}">
	 <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Message</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="form-group">
            <input type="text" class="form-control" name = "userid" id="userid"  value = "${sessionScope.userid}">
          </div>
          <br>
           <div class="form-group">
            <input type="text" class="form-control" id= "messagetitle" name = "messagetitle" placeholder = "제목을 입력해주세요">
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">Message:</label>
            <textarea class="form-control" id="message-text" name = "messagecontent" id = "messagecontent"></textarea>
          </div>
        
      </div>
      <div class="modal-footer">
        <button type="reset" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick = "modalForm.submit()">Send message</button>
      </div>
    </div>
  </div>
</div>
</form>
<!-- 모달 form -->
	
</body>
</html>