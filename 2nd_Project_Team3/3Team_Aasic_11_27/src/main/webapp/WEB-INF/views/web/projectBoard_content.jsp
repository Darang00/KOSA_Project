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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">

$(function(){
	$(document).on(
            "click",
            "#reply_check",
            function() {
	//댓글 달기 비동기처리
		const content = $('#reply_content').val();
		
		if(content == ""){
		 	alert("내용을 입력하세요");
		}
		else if (confirm("댓글을 등록하시겠습니까?") == true){    //확인
			//비동기 함수 호출
		
				$.ajax(
					{
						url:"boardReplyOk.user", //보낼 URL
						type:"GET",			 //보내는 방식
						dataType:"html",	 //데이터 타입
						data : {
		 					'reply_userid' :  $('#reply_userid').val(), //댓글 작성자 값
		    				'reply_content' : $('#reply_content').val(),//댓글 내용 값
		    				/* 'reply_pwd' : $('#reply_pwd').val(),		//비밀번호 값 */
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
		 }else{   //취소
			 alert("취소하였습니다.");
		     return false;
		 }
	});
	
	//댓글 삭제 비동기 처리
	$(document).on(
               "click",
               "#reply_del",
               function() {
            	
		 if (confirm("정말 삭제하시겠습니까?") == true){    //확인
			//비동기 함수 호출
			 $.ajax(
						{
							url:"boardReplyDeleteOk.user",  //보낼 URL
							type:"GET",					//보내는 방식
							dataType:"html",			//데이터 타입
							data : {
			 					'no1' :  $('#no1').val(), 		// ? 
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
		 }else{   //취소
				alert("취소하였습니다.");
		     return false;
		 }
	}); 

		//첨부파일 상세보기 비동기 
		$('#fileDetail').click(function(){
			//비동기 함수 호출
			$.ajax(
				{
					url:"fileDetailList.user",  //URL 보내기
					type:"GET",					 //방식
					dataType:"html",		  	 //데이터 타입
					data : {
						'idx' : $('#idx').val()  //글번호 
					},
					success:function(data){
					console.log(data);
					$('#fileList').empty(data);
					$('#fileList').append(data); //비어있는 <div>인 fileList에다 사진 붙여줌(append)
					},
					error:function(request,status,error){
							alert("code:" +request.status+"\n" + "message:"+request.responseText+"\n"+"error:"+error);
							console.log(error);
					}
				});
		});
		
		// 추천 버튼 클릭시 추천 여부 검사
		$('#lovecheck').on("click",(function(){
				
			$.ajax({
				url: 'loveUpdate.user',
				type: 'POST',
				dataType: 'html',
				data : {
					'idx' : ${requestScope.idx}
					/* 'userid': '${sessionScope.userid}' */
				},				
				success: function(data){ 
			        console.log('추천 체크 성공');
			        console.log(data);
			        $('#love1').empty(data);
					$('#love1').append(data);
			    	console.log("나 데이터야 : "+data);
			        
			    },
			    error:function(request,status,error){
			    	alert("code:" +request.status+"\n" + "message:"+request.responseText+"\n"+"error:"+error);
			        console.log("추천 체크 실패");
			        console.log("에러표시"+error);
			    }
			});
			}));
		
		
		$('#bookmark').click(
				function (){
			$.ajax({
				url: "bookMark.user",
				type: "POST",
				data : {
					'idx' : '${requestScope.idx}',
					'userid': '${sessionScope.userid}'
				},				
				success: function(data){ 
			        console.log("북마크로 설정");
			    },
			    error:function(){
			        console.log("북마크 해제");
			    }
			});
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
	<c:set var="lovecount" value="${requestScope.lovecount}" />
	<c:set var="boardname" value="${requestScope.boardname}" />
	<c:set var="filename" value="${requestScope.filename}" />

	<jsp:include page="/WEB-INF/views/include/userHeader.jsp"></jsp:include>
	<div id="pageContainer">
		<div style="padding-top: 30px; text-align: center">
			<center>
				<b>게시판 글내용</b>
				<div id="jun"></div>
				<table width="80%" border="1">
					<tr>
						<td width="20%" align="center"><b> 글번호 </b></td>
						<td width="30%">${idx}</td>
						<td width="20%" align="center"><b>작성일</b></td>
						<td>${board.writedate}</td>
					</tr>
					<tr>
						<td width="20%" align="center"><b>글쓴이</b></td>
						<td width="30%">${board.userid}</td>
						<td width="20%" align="center"><b>조회수</b></td>
						<td>${board.readnum}</td>
					</tr>
					<tr>
						<td width="20%" align="center"><b>첨부파일</b></td>
						<td><c:choose>
								<c:when test="${filename != null && fn:length(filename) > 10}">
									${fn:substring(filename,0,15)}...
									<button type="button" href="#" id="fileDetail">상세보기</button>
								</c:when>
								<c:otherwise>
									${filename}<button type="button" href="#" id="fileDetail">
										상세보기</button>
								</c:otherwise>
							</c:choose></td>
						<td width="20%" align="center"><b>추천수</b></td>
					</tr>
					<tr>
						<td width="20%" align="center"><b>제목</b></td>
						<td colspan="3">${board.title}</td>
					</tr>
					<tr height="100">
						<td width="20%" align="center"><b>글내용</b></td>
						<td colspan="3">${fn:replace(board.content, newLineChar,"<br>")}
							<br>
							<div id="fileList"></div>
						</td>
					</tr>

					<tr>
						<td colspan="4" align="center"><a id="love1">${lovecount}</a></td>
					</tr>

					<tr>
						<td colspan="4" align="center"><a
							href="javascript:history.back();">목록가기</a> <a
							href="boardEdit.user?boardname=${board.boardname}&idx=${idx}&cp=${cpage}&ps=${pagesize}&filename=${filename}">편집</a>
							| <a
							href="boardDelete.user?boardname=${board.boardname}&idx=${idx}&cp=${cpage}&ps=${pagesize}&writerid=${board.userid}">삭제</a>
							| <input type="button" id="lovecheck" value="추천"></td>
					</tr>
				</table>
				<!--  꼬리글 달기 테이블 -->
				<!-- <form name="reply" action="ReplyOk.board" method="POST"> -->
				<!-- hidden 태그  값을 숨겨서 처리  -->
				<c:if test="${sessionScope.userid ne null }">
					<input type="hidden" id="idx" name="idx" value="${idx}">
					<input type="hidden" name="userid" value="">
					<!-- 추후 필요에 따라  -->
					<!-- hidden data -->
					<table width="80%" border="1">
						<tr>
							<th colspan="2">덧글 쓰기</th>
						</tr>
						<tr>
							<td align="left"><input type="hidden" id="reply_userid"
								name="reply_userid" value="${sessionScope.userid}" readonly><br />
								<textarea id="reply_content" name="reply_content" rows="2"
									cols="50" placeholder="댓글 내용을 입력하세요" required></textarea> <br>
								<button type="button" id="reply_check"
									class="btn btn-info rounded-pill m-2">등록</button></td>
						</tr>

					</table>

				</c:if>

				<script type="text/javascript">
		
		$(document).on(
			    "click",
			    "#reply_modify",
			    	 function hideRow()  {
						  const row = document.getElementById('modify');
						  row.style.display = "";
			}); 
		
	
	/* 	reply_modify */
		</script>
				<br>
				<!-- 꼬리글 목록 테이블 -->
				<div id="newView">
					<c:if test="${not empty replyList}">
						<c:forEach var="reply" items="${replyList}">
							<table width="80%" border="1">
								<tr>
									<th colspan="2">REPLY LIST ${reply.replynum} 번째 댓글
										(${idx}번 게시물)</th>
								</tr>
								<tr align="left">
									<td width="80%">[${reply.userid}] : ${reply.content} <br>
										작성일:${reply.replydate}
									</td>
									<c:if test="${sessionScope.userid eq reply.userid}">
										<td><input type="text" id="no1" name="no1"
											value="${reply.replynum}"> <input type="hidden"
											id="delidx" name="idx" value="${idx}">
											<button type="button" id="reply_modify" name="reply_modify">수정</button>
											<button type="button" id="reply_del" name="reply_del">삭제</button>
										</td>
									</c:if>

								</tr>
								<!-- 수정 form -->

								<tr align="left" id="modify" style="display: none">
									<td width="80%">[${reply.userid}] : ${reply.content} <br>
										작성일:${reply.replydate}
									</td>
									<c:if test="${sessionScope.userid eq reply.userid}">
										<td><input type="hidden" id="no" name="no"
											value="${reply.replynum}"> <input type="hidden"
											id="delidx" name="idx" value="${idx}">
											<button type="button" id="reply_modify" name="reply_modify">완료</button>
											<button type="button" id="reply_del" name="reply_del">취소</button>
										</td>
									</c:if>

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





