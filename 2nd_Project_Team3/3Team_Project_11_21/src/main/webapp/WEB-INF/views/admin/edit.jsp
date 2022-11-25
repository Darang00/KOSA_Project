<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Pages / F.A.Q - NiceAdmin Bootstrap Template</title>
</head>

<body>

 	<jsp:include page="/WEB-INF/views/include/adminHeader.jsp"></jsp:include>
 	<jsp:include page="/WEB-INF/views/include/adminLeft.jsp"></jsp:include>
		<div>
			<div>
				<div>
					<h2>
						<b>회원관리 페이지</b> 
					</h2>

					<table>
						<thead>
							<tr>
								<th scope="col">아이디</th>
								<th scope="col">IP</th>
								<th scope="col">삭제</th>
								<th scope="col">수정</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="mem" items="${requestScope.memberList}">
								<tr>
									<td>${mem.userId}</td>
									<td>${mem.ip}</td>
									<td><a href="stop.admin?userid=${mem.userId}"}>[정지]</a> </td>
									<td><a href="activate.admin?userid=${mem.userId}"}>[활성화]</a> </td>
									<td><a href="edit.admin?userid=${mem.userId}"}>[수정]</a> </td>
								</tr>
							</c:forEach>

						</tbody>
					</table>

				</div>

			</div>
		</div>
	<a href="#"><i></i></a>

</body>
</html>