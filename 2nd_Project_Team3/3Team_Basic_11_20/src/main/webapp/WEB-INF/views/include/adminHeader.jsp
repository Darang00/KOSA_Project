<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<link rel="Stylesheet" href="style/default.css">
<header style = "background-color: gold">
    
    <div class="title">
    	<a href="${pageContext.request.contextPath}">Main(index페이지 호출)</a>
    </div>
    
    <div class="links">        
<!--  <c:choose>
		    <c:when test="${sessionScope.id ne null}">
		          ${sessionScope.id } 님 환영합니다^^
		          <span href = "logout.do">Click here to sign out -> </span>		        
		    </c:when>
		    <c:otherwise>        
		        <span href = "login.do">sign in</span>
		    </c:otherwise>
		</c:choose>    -->
		           
<!--		<c:choose>
		    <c:when test="${sessionScope.id ne null}">
		         <span href = "logout.do">로그아웃</span>
		    </c:when>
		    <c:otherwise>
		        <span href = "login.do">로그인</span>
		    </c:otherwise>
		</c:choose>    -->
		 
		<c:choose>
			<c:when test="${sessionScope.id ne null}">
			 ${sessionScope.id } 님 환영합니다^^ <br>
			 Click here to sign out -> 
			    <a href="logout.do">
			    <span>Sign Out</span></a>  <br>
			    
			    <div id="menu">
			    <ul>
				    <li><a href="${pageContext.request.contextPath}/BoardList.board">공지사항 게시판</a></li>
				    <li><a href="${pageContext.request.contextPath}/BoardList.board">트러블슈팅 게시판</a></li>
				    <li><a href="${pageContext.request.contextPath}/BoardList.board">질문 게시판</a></li>
				    <li><a href="${pageContext.request.contextPath}/BoardList.board">자유 게시판</a></li>
				    <li><a href="${pageContext.request.contextPath}/BoardList.board">모집 게시판</a></li>
				</ul>
			    </div>
			    
			</c:when>
			<c:otherwise>
			   <a href="login.do">
			   <span>Sign in</span></a>    
			</c:otherwise>
		</c:choose>

    </div>         
</header>