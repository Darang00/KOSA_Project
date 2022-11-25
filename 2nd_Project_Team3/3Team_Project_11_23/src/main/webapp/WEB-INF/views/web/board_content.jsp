<%@page import="kr.or.kosa.dto.Bookmarks"%>
<%@page import="kr.or.kosa.dao.MemberDao"%>
<%@page import="kr.or.kosa.dto.Reply"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosa.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board_content</title>
<script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
<%-- <link rel="Stylesheet" href="${pageContext.request.contextPath}/style/default.css" /> --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">

//북마크 체크
$(function(){
	$('#bookmark').click(
            function (){
         console.log("${requestScope.idx}");
         console.log("${sessionScope.userid}");
         $.ajax({
            url: "bookMarks.user",
            type: "POST",
            data : {
               'idx' : '${requestScope.idx}',
               'userid': '${sessionScope.userid}',
               'title' : '${board.title}',
               'writedate' : '${board.writedate}'               
            },            
            success: function(data){ 
                 console.log("북마크 체크 성공");
             },
             error:function(){
                 console.log("북마크 체크 실패");
             }
         });         
      });
	
	/*
	<c:choose>
		<c:when test="${requestScope.bookMarking ne 1}">
		<td width="20%" align="center" bgcolor="beige"><button id="bookmark" value="0">북마크 하긔</button></td>
		<td>북마크 버튼 해제상태(bookMarkCheck is not equal to 1)</td>
		</c:when>
		<c:otherwise>
		<td width="20%" align="center" bgcolor="yelloW"><button id="bookmark" value="1">북마크 해제 하긔</button></td>		   		 			
		<td>북마크 버튼 채워져있다(bookMarkCheck is equal to 1)</td>
		</c:otherwise>
	</c:choose>  

	*/
	
	
	
	
	
	
	//댓글 달기 비동기처리
	$('#reply_check').click(function(){
		//비동기 함수 호출
		$.ajax(
			{
				url:"ReplyOk.board", //보낼 URL
				type:"GET",			 //보내는 방식
				dataType:"html",	 //데이터 타입
				data : {
 					'reply_writer' :  $('#reply_writer').val(), //댓글 작성자 값
    				'reply_content' : $('#reply_content').val(),//댓글 내용 값
    				'reply_pwd' : $('#reply_pwd').val(),		//비밀번호 값
    				'idx' : $('#idx').val() 					//글 번호
				},
				success:function(data){
				console.log(data);
				$('#newView').append(data);  //댓글 목록 에 추가(append)
				},
				error:function(request,status,error){  //에러 났을 경우 
						alert("code:" +request.status+"\n" + "message:"+request.responseText+"\n"+"error:"+error);
						console.log(error);
				}
				
			}		
		);
	});
	
	
	//댓글 삭제 비동기 처리
	$('#reply_del').click(function(){
		//비동기 함수 호출
		$.ajax(
			{
				url:"ReplyDeleteOk.board",  //보낼 URL
				type:"GET",					//보내는 방식
				dataType:"html",			//데이터 타입
				data : {
 					'no' :  $('#no').val(), 		// ? 
    				'delPwd' : $('#delPwd').val(),  // 비밀번호
    				'idx' : $('#delidx').val() 		// ?
				},
				success:function(data){
				console.log(data);
				$('#newView').append(data);			//삭제한 결과 댓글 목록에 반영
				},
				error:function(request,status,error){
						alert("code:" +request.status+"\n" + "message:"+request.responseText+"\n"+"error:"+error);
						console.log(error);
				}
				
			}		
		);
	});
	
	//첨부파일 상세보기 비동기 
	$('#fileDetail').click(function(){
		//비동기 함수 호출
		$.ajax(
			{
				url:"fileDetailList.board",  //URL 보내기
				type:"GET",					 //방식
				dataType:"html",		  	 //데이터 타입
				data : {
					'idx' : $('#idx').val()  //글번호 
				},
				success:function(data){
				console.log(data);
				$('#fileList').append(data); //비어있는 <div>인 fileList에다 사진 붙여줌(append)
				},
				error:function(request,status,error){
						alert("code:" +request.status+"\n" + "message:"+request.responseText+"\n"+"error:"+error);
						console.log(error);
				}
				
			}		
		);
	});
	
	
});
</script>



</head>
<body>
	<c:set var="board" value="${requestScope.board}" />
	<c:set var="idx" value="${requestScope.idx }" />
	<c:set var="cpage" value="${requestScope.cp}" />
	<c:set var="pagesize" value="${requestScope.ps}" />
	<c:set var="replyList" value="${requestScope.replyList}" />
	


 		<%-- <c:choose>
		    <c:when test="${requestScope.bookMarkCheck ne 1}">
		          북마크 버튼 해제상태(bookMarkCheck is not equal to 1)
		          <td width="20%" align="center" bgcolor="beige"><button onclick = "location.href ='bookMarks.user'">북마크 하긔</button></td>
		    </c:when>
		    <c:otherwise>
		    북마크 버튼 채워져있다(bookMarkCheck is equal to 1)   
				<td width="20%" align="center" bgcolor="beige"><button onclick = "location.href ='bookMarks.user'">북마크해제</button></td>
		    </c:otherwise>
		</c:choose>   --%>

	
<%-- 	<c:forEach var="reply" items="${replyList}">
	[${reply.userid}] : ${reply.content} --%>
	
	
	
<jsp:include page="/WEB-INF/views/include/userHeader.jsp"></jsp:include>
	<div id="pageContainer">
		<div style="padding-top: 30px; text-align: center">
			<center>
				<b>게시판 글내용</b>
				<table width="80%" border="1">
					<tr>
						<td width="20%" align="center"><b> 글번호 </b></td>
						<td width="30%">${idx}</td>
						<td width="20%" align="center" name = "writedate" id = "writedate"><b>작성일</b></td>
						<td>${board.writedate}</td>
					</tr>
					<tr>
						<td width="20%" align="center"><b>글쓴이</b></td>
						<td width="30%">${board.userid}</td>
						<td width="20%" align="center"><b>조회수</b></td>
						<td>${board.readnum}</td>
					</tr>
					<tr>
					<%-- 	<%	
							MemberDao memberdao = new MemberDao();
							List<Bookmarks> bookMarks = memberdao.getBookmarks(1, "hi", 1);
							if (bookMarks.isEmpty()){ %>
						<td width="20%" align="center" bgcolor="beige"><button onclick = "location.href ='bookMarks.user'">북마크</button></td>
						<%	}
						else{ %>
						<td width="20%" align="center" bgcolor="beige"><button onclick = "location.href ='bookMarks.user'">북마크해제</button></td>
						<%	} %> --%>
						
						<c:choose>
		    				<c:when test="${requestScope.bookMarking ne 1}">
		          			<td width="20%" align="center" bgcolor="beige"><button id="bookmark" value="0">북마크 하긔</button></td>
		    				<td>북마크 버튼 해제상태(bookMarkCheck is not equal to 1)</td>
		    				</c:when>
		    				<c:otherwise>
							<td width="20%" align="center" bgcolor="yelloW"><button id="bookmark" value="1">북마크 해제 하긔</button></td>		   		 			
							<td>북마크 버튼 채워져있다(bookMarkCheck is equal to 1)</td>
		   		 			</c:otherwise>
						</c:choose>  
					</tr>

					
					
					<tr>
						<td width="20%" align="center" id="title"><b>제목</b></td>
						<td colspan="3">${board.title}</td>
					</tr>
					<tr height="100">
						<td width="20%" align="center"><b>글내용</b></td>
						<td colspan="3">${fn:replace(board.content, newLineChar,"<br>")}
						<br>
						<div id ="fileList"></div>
						</td>
						
					</tr>
					<tr>
						<td colspan="4" align="center"><a
							href="QnABoard.board?cp=${cpage}&ps=${pagesize}">목록가기</a> |<a
							href="QnABoardEdit.board?idx=${idx}&cp=${cpage}&ps=${pagesize}">편집</a> |<a
							href="QnABoardDelete.board?idx=${idx}&cp=${cpage}&ps=${pagesize}">삭제</a>
							|<a
							href="BoardRewrite.board?idx=${idx}&cp=${cpage}&ps=${pagesize}&subject=${board.title}">답글</a>
						</td>
					</tr>
				</table>
				<!--  꼬리글 달기 테이블 -->
				<!-- <form name="reply" action="ReplyOk.board" method="POST"> -->
						<!-- hidden 태그  값을 숨겨서 처리  -->
						<input type="hidden" id="idx" name="idx" value="${idx}"> 
						<input type="hidden" name="userid" value=""><!-- 추후 필요에 따라  -->
						<!-- hidden data -->
						<table width="80%" border="1">
							<tr>
								<th colspan="2">덧글 쓰기</th>
							</tr>
							<tr>
								<td align="left">작성자 :
								 	<input type="text" id="reply_writer" name="reply_writer"><br /> 
								 	내&nbsp;&nbsp;용 : 
								 	<textarea id="reply_content" name="reply_content" rows="2" cols="50" class="ckeditor"></textarea>
								</td>
								<td align="left">
									비밀번호:
									<input type="password" id="reply_pwd" name="reply_pwd" size="4"> 
									<!-- <input type="button" value="등록" id = "reply_check"> -->
									<button type="button" id = "reply_check" class="btn btn-info rounded-pill m-2">등록</button>
								</td>
							</tr>
						</table>
				<!-- </form> -->
				<!-- 유효성 체크	 -->
				<script type="text/javascript">
					function reply_del(frm) {
						//alert("del");
				
						//var frm = document.replyDel;
						//alert(frm);
						if (frm.delPwd.value == "") {
							alert("비밀번호를 입력하세요");
							frm.delPwd.focus();
							return false;
						}
						frm.submit();
					}
				</script>
				<br>
				<!-- 꼬리글 목록 테이블 -->
				<div id="newView">
				<c:if test="${not empty replyList}">
					<c:forEach var="reply" items="${replyList}">
						<table width="80%" border="1">
							<tr>
								<th colspan="2">REPLY LIST</th>
							</tr>
							<tr align="left">
								<td width="80%">
								[${reply.userid}] : ${reply.content}
								<br> 작성일:${reply.writedate}
								</td>
								<td width="20%">
									<input type="hidden" id = "no" name="no" value="${reply.no}"> 
									<input type="hidden" id = "delidx" name="idx" value="${idx}"> 
									password :<input type="password" id="delPwd" name="delPwd" size="4"> 
									<!-- <input type="button" value="삭제" id = "reply_del"> -->
									<button type="button" id = "reply_del" class="btn btn-info rounded-pill m-2">삭제</button>
								</td>
							</tr>
						</table>
					</c:forEach>
				</c:if>
				</div>
			</center>
		</div>
	</div>
</body>
</html>





