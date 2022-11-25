<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Pages / Register - NiceAdmin Bootstrap Template</title>
<meta content="" name="description">
<meta content="" name="keywords">
</head>
<body>
<jsp:include page="/WEB-INF/views/include/userHeader.jsp"></jsp:include>
	<main>
		<div class="container">
			<section>
				<div>
					<!-- End Logo -->
					<div>
						<div>
							<div>
								<h5>Create an Account</h5>
								<p>Enter your personal details to create account</p>
							</div>

							<form action="updateOK.user" method="post">

								<h3 style="text-align: center;">회원정보 수정<br>${sessionScope.userid}님의 수정페이지</h3>
								<div>
									<table
										style="width: 400px; height: 200px; margin-left: auto; margin-right: auto;">
										<c:set var="dao" value="${requestScope.dao}" />
										<tr>
											<td>ID</td>
											<td><input type="text" name="memberid"
												value="${sessionScope.userid}" readonly></td>
										</tr>
										<tr>
											<td>비밀번호</td>
											<td><input type="password" name="pwd" id="pwd" required></td>
										</tr>
										<tr>
											<td>이름</td>
											<td><input type="text" name="name" id="name" required></td>
										</tr>
										<%-- <tr>
											<td>프로필사진</td>
											<td><input type="text" name="profilephoto"
												value="${sessionScope.profilephoto}" readonly></td>
										</tr> --%>
										<tr>
											<td>성별</td>
											<td><input type="radio" name="gender" id="gender" value="여" checked> 여자 
											<input type="radio" name="gender" id="gender" value="남">남자</td>
										</tr>
										<tr>
											<td>이메일</td>
											<td><input type="email" name="email" id="email" required></td>
										</tr>
										<tr>
											<td>Phone</td>
											<td><input type="text" name="phone" id="phone" required></td>
										</tr>
										<tr>
											<td>주소</td>
											<td><input type="text" name="address" id="address"
												required></td>
										</tr>
										<%-- <tr>
											<td>가입일</td>
											<td><input type="text" name="startdate"
												value="${sessionScope.startdate}" readonly></td>
										</tr> --%>
										<tr>
											<td colspan="2"><input type="submit" value="수정하기">
												<a href='index.do'>뒤로가기</a></td>
											<!-- loginok.do로 요청을 보낼 경우에는 
					                        다시 받을 id, pwd값도 없고 그 결과 session이 null이 되서
					                        if문에서 바로 else를 타버리게 된 것 -->
									</table>
								</div>
							</form>
						</div>
					</div>
				</div>
			</section>
		</div>
	</main>
</body>
</html>
