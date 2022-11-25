<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<header>
    
    <div class="title">
    	<a href="${pageContext.request.contextPath}">Main(index페이지 호출)</a>
    </div>
    
    <div class="links">      
    <c:choose>
		    <c:when test="${sessionScope.userid ne null}">
		          ${sessionScope.userid } 개발자님, 환영합니다 :)
		          <a href = "logout.do">log out</a>		 
		          <a href = "update.user">내정보 수정</a>	
		          <a href="bookmarkslist.jsp">북마크</a>		       
		    </c:when>
		    <c:otherwise>        
		        <a href = "register.do">sign in</a>
		        <a href = "login.do">Log in</a>
		    </c:otherwise>
		</c:choose>  
    </div>
    <div id="menu">
            <div>
                <ul>
                    <li><a href="boardList.user?boardname=트러블슈팅">트러블슈팅</a></li>
					<li><a href="boardList.user?boardname=질문과답변">질문과답변</a></li>
					<li><a href="boardList.user?boardname=프로젝트모집">프로젝트모집</a></li>
					<li><a href="boardList.user?boardname=자유게시판">자유게시판</a></li>
					<li><a href="boardList.user?boardname=공지사항">공지사항</a></li>
                </ul>
            </div>
        </div>
        <div style="text-align:right;margin-top:1px;
        	border:solid 1px;padding:5px">
        	[ TOTAL : 명 ]
        	[ CURRENT : 명 ]
        </div>    
</header>