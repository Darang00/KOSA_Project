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
		           
		<c:choose>
		    <c:when test="${sessionScope.id ne null}">
		         <span href = "logout.do">로그아웃</span>
		    </c:when>
		    <c:otherwise>
		        <span href = "login.do">로그인</span>
		    </c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${sessionScope.id ne null}">
			    <a href="logout.do"><i></i>
			    <span>Sign Out</span></a>
			</c:when>
			<c:otherwise>
			   <a href="login.do">
			   <span>Sign in</span></a>    
			</c:otherwise>
		</c:choose>
    </div>
    <div id="menu">
            <div>
                <ul>
                	<!-- a : 페이지 이동을 처리하는 마크업 -->
                    <li><a href="${pageContext.request.contextPath}/BoardList.board">공지사항 게시판</a></li>
					<!-- <li><a href="${pageContext.request.contextPath}/BoardWrite.do.board">BOARD WRITE</a></li>  -->
					<li><a href="javscript:void(0);"></a></li>
					<li><a href="javscript:void(0);"></a></li>
                </ul>
            </div>
        </div>
        <div style="text-align:right;margin-top:1px;
        	border:solid 1px;padding:5px">
        	[ TOTAL : 명 ]
        	[ CURRENT : 명 ]
        </div>      
</header>