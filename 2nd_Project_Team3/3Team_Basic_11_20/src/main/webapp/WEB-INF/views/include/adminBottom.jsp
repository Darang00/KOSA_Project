<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<link rel="Stylesheet" href="style/default.css">
<bottom style = "background-color: gold">
	<div class="links"> 
		<c:choose>
			<c:when test="${sessionScope.id ne null}">
			    <div id="menu">
			    <ul>
				    <li><a href="${pageContext.request.contextPath}/BoardWrite.board">공지사항 글쓰기</a></li>
				
				</ul>
			    </div>
			    
			</c:when>
			<c:otherwise>
			   No session status
			</c:otherwise>
		</c:choose>
	
	
	
	
	
	
	</div>





</bottom>