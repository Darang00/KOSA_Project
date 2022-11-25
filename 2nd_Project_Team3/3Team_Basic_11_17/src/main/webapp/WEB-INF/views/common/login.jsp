<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<form action="loginok.do" method="post" name="loginForm" id="joinForm" novalidate>
		<div>
			<label>Username</label>
			<div>
				<span id="id">@</span>
				<input name="id" id="id" required>
				<div>Please enter your username.</div>
			</div>
		</div>
	
		<div>
			<label>Password</label>
			<input type="password" name="pwd" id="pwd" required>
			<div class="invalid-feedback">Please enter your password!</div>
		</div>
	
		
		<div>
			<button type="submit">Login</button>
		</div>
		<div>
			<input type="reset" value="취소">
		</div>
		<div>
			<p>
				아이디가 없나요? <a href="register.do">Create an account</a>
			</p>
		</div>
	</form>
</body>
</html>