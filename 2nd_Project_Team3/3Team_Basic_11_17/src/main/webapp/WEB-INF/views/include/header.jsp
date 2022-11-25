<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<header>
    
    <div class="title">
    	<a href="${pageContext.request.contextPath}">Main(index페이지 호출)</a>
    </div>
    
    <div class="links">        
		<c:choose>
		    <c:when test="${sessionScope.id ne null}">
		          ${sessionScope.id } 님
		          <span href = "logout.do">log out</span>		        
		    </c:when>
		    <c:otherwise>        
		        <span href = "login.do">sign in</span>
		    </c:otherwise>
		</c:choose>
		
		
		           
		<a href="BoardList.board">공지사항 게시판</a>
		 
		<c:choose>
			<c:when test="${sessionScope.id ne null}">
			    <a href="logout.do"><i></i>
			    <span>Sign Out</span></a>
			    <jsp:forward page="/session.do"/>
			</c:when>
			<c:otherwise>
			   <a href="login.do">
			   <span>Sign in</span></a>    
			</c:otherwise>
		</c:choose>

    </div>         
</header>