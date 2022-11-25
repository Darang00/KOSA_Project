<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<header>
    
    <div class="title">
    	<a href="index.do">Main(index페이지 호출)</a>
    </div>
    
    <div class="links">        
        <c:forEach var="cate" items="${requestScope.categoryList}">
			<tr>
				<td>${cate.boardname}</td>

			</tr>
		</c:forEach>
		<c:choose>
			<c:when test="${sessionScope.userid ne null}">
			    <a href="logout.do"><i></i>
			    <span>Sign Out</span></a>
			    <jsp:forward page="/session.do"/>
			    
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
				
			</c:when>
			<c:otherwise>
			   <a href="login.do">
			   <span>Sign in</span></a>
				<div id="menu">
		            <div>
		                <ul>
		                	<!-- a : 페이지 이동을 처리하는 마크업 -->
		                    <li><a href="login.do">트러블슈팅</a></li>
							<li><a href="login.do">질문과답변</a></li>
							<li><a href="login.do">프로젝트모집</a></li>
							<li><a href="login.do">자유게시판</a></li>
							<li><a href="login.do">공지사항</a></li>
		                </ul>
		            </div>
		        </div>
			</c:otherwise>
		</c:choose>
		
		    <!-- 기존 헤더 -->
    	<div id="header">
            <div class="title">
                <h1 class="mb-4"><a href="index.do">극락조 웹사이트</a></h1>
           		<button type="button" onclick="index.do" class="btn btn-lg btn-lg-square btn-primary m-2"><i class="fa fa-home"></i></button>
            </div>
            <div class="links">
                <a href="javscript:void(0);">${pageContext.request.contextPath}</a>
                <a href="javscript:void(0);">추후등록</a>
                <a href="javscript:void(0);">추후로그아웃</a>

            </div>
        </div>    
		
		

    </div>         
</header>