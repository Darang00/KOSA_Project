<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script type="text/javascript">
 //jquery 로 간단하게 유효성 check 하기
 function check(){
	 if(!joinForm.userid.value){
		 alert('ID를 입력해 주세요.');
		 joinForm.userid.focus();
		 return false;
	 }else if(!joinForm.pwd.value){
		 alert('PWD를 입력해 주세요.');
		 joinForm.pwd.focus();
		 return false;
	 }else if(!joinForm.name.value){
		 alert('name를 입력해 주세요.');
		 joinForm.name.focus();
		 return false;
	 }else if(!joinForm.firstEmail.value){
		 alert('이메일을 확인해주세요');
		 joinForm.firstEmail.focus();
		 return false;
	 }else if(!joinForm.lastEmail.value){
		 alert('이메일을 확인해주세요');
		 joinForm.lastEmail.focus();
		 return false;
	 }else if(!joinForm.firstNumber.value){
		 alert('핸드폰 앞자리를 입력해 주세요.');
		 joinForm.firstNumber.focus();
		 return false;
	 }else if(!joinForm.phoneNumber.value){
		 alert('핸드폰번호를 입력해 주세요.');
		 joinForm.phoneNumber.focus();
		 return false;
	 }else if(!joinForm.address.value){
		 alert('주소를 입력해 주세요.');
		 joinForm.address.focus();
		 return false;
	 }
	 document.joinForm.submit();
 }
 
</script>
  
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<main>
		<div class="container">

			<section>
				<div>
					<div>
						<div>
							<div>
								<a href="관리자 접근을 해야되나?"> <img alt=""> <span>NiceAdmin</span>
								</a>
							</div>
							<!-- End Logo -->

							<div>

								<div>
									<div>
										<h5>Create an Account</h5>
										<p>Enter your personal details
											to create account</p>
									</div>
									<form action="insert.do" method="post" name="joinForm" id="joinForm"novalidate>
										<div>
											<label for="your ID">Your ID</label> 
											<input type="text" name="userid" id="userid" required>
											<div>Please, enter your ID!</div>
										</div>
										<div>
											<label for="yourPassword">Your Password</label>
												 <input type="password" name="pwd" id="pwd" required>
											<div>Please enter a valid Password!</div>
										</div>
										<div>
											<label for="your Name">Your Name</label> <input type="text" name="name" id="name" required>
											<div>Please, enter your Name!</div>
										</div>
										<div>
											<label for="your Email">Your Email</label> 
											<input type="text" name="firstEmail" id="firstEmail" required> @ <input type="text" name="lastEmail" id="lastEmail" required>
											<div>Please, enter your Email!</div>
										</div>
										<div>
											<select id="firstNumber" name="firstNumber">
		                           				<option value="" selected="selected">선택해주세요.</option>
												<option value="010">010</option>
											 	<option value="011">011</option>
											 	<option value="016">016</option>
											</select>
											<input type="text" name="phoneNumber" id="phoneNumber" required>
										</div>
										<div>
											<label for="your Address">address 추후에 지도api 사용해보자.</label>
											<input type="text" name="address" id="address" required>
											<div>Please enter your Address!</div>
										</div>
										<div>
											<label for="your Gender">Your Gender  </label>
											 <input type="radio" name="gender" id="gender" value="여" checked>여자 <input type="radio"
												name="gender" id="gender" value="남">남자
										</div>

										<div>
											<button type="button" onclick="check();">회원가입</button>
										</div>
										<div>
											<button type="reset">취소</button>
										</div>
										<div>
											<p>
												아이디가 있으신가요?  <a href="login.do"> Log in</a>
											</p>
										</div>
									</form>

								</div>
							</div>

							<div>
								Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
							</div>

						</div>
					</div>
				</div>

			</section>

		</div>
	</main>
	<!-- End #main -->


	<a href="#"></i></a>

</body>
</html>