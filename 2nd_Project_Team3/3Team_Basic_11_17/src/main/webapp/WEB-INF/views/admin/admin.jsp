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
		<div>
			<div>
				<div>
					<a href="#" data-bs-toggle="dropdown"><i></i></a>
					<ul>
						<li>
							<h6>Filter</h6>
						</li>
						<li><a href="#">Today</a></li>
						<li><a href="#">This Month</a></li>
						<li><a href="#">This Year</a></li>
					</ul>
				</div>

				<div>
					<h2>
						<b>회원관리 페이지</b> 
					</h2>
					</table>

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
							<c:forEach var="mem" items="${requestScope.dtolist}">
								<tr>
									<td>${mem.id}</td>
									<td>${mem.ip}</td>
									<td><a href="delete.admin?id=${mem.id}"}>[삭제]</a> </td>
									<td><a href="edit.admin?id=${mem.id}"}>[수정]</a> </td>
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