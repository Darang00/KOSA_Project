<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
인덱스 :${requestScope.idx_fk} &nbsp;&nbsp;
파일 명 : ${requestScope.filename}

	<hr>
	<h3>다운로드(특정 경로 저장)</h3>
<a href="filedownload.user?filename=${requestScope.filename}">${requestScope.filename}다운로드하기</a>
<h3>웹 브라우져 보기</h3>
<a href="upload/${requestScope.filename}">브라우져 보기</a>
<img width="100px" height="120px" src="upload/${requestScope.filename}">
</body>
</html>